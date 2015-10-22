'use strict';

angular.module('quizApp').controller('ReponseDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Reponse', 'Question',
        function($scope, $stateParams, $modalInstance, entity, Reponse, Question) {

        $scope.reponse = entity;
        $scope.questions = Question.query();
        $scope.load = function(id) {
            Reponse.get({id : id}, function(result) {
                $scope.reponse = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('quizApp:reponseUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.reponse.id != null) {
                Reponse.update($scope.reponse, onSaveFinished);
            } else {
                Reponse.save($scope.reponse, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
