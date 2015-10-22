'use strict';

angular.module('quizApp').controller('QuestionDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Question',
        function($scope, $stateParams, $modalInstance, entity, Question) {

        $scope.question = entity;
        $scope.load = function(id) {
            Question.get({id : id}, function(result) {
                $scope.question = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('quizApp:questionUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.question.id != null) {
                Question.update($scope.question, onSaveFinished);
            } else {
                Question.save($scope.question, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
