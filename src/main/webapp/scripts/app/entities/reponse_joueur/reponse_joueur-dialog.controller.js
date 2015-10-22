'use strict';

angular.module('quizApp').controller('Reponse_joueurDialogController',
    ['$scope', '$stateParams', '$modalInstance', '$q', 'entity', 'Reponse_joueur', 'User', 'Question', 'Reponse',
        function($scope, $stateParams, $modalInstance, $q, entity, Reponse_joueur, User, Question, Reponse) {

        $scope.reponse_joueur = entity;
        $scope.users = User.query();
        $scope.questions = Question.query({filter: 'reponse_joueur-is-null'});
        $q.all([$scope.reponse_joueur.$promise, $scope.questions.$promise]).then(function() {
            if (!$scope.reponse_joueur.question.id) {
                return $q.reject();
            }
            return Question.get({id : $scope.reponse_joueur.question.id}).$promise;
        }).then(function(question) {
            $scope.questions.push(question);
        });
        $scope.reponses = Reponse.query({filter: 'reponse_joueur-is-null'});
        $q.all([$scope.reponse_joueur.$promise, $scope.reponses.$promise]).then(function() {
            if (!$scope.reponse_joueur.reponse.id) {
                return $q.reject();
            }
            return Reponse.get({id : $scope.reponse_joueur.reponse.id}).$promise;
        }).then(function(reponse) {
            $scope.reponses.push(reponse);
        });
        $scope.load = function(id) {
            Reponse_joueur.get({id : id}, function(result) {
                $scope.reponse_joueur = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('quizApp:reponse_joueurUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.reponse_joueur.id != null) {
                Reponse_joueur.update($scope.reponse_joueur, onSaveFinished);
            } else {
                Reponse_joueur.save($scope.reponse_joueur, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
