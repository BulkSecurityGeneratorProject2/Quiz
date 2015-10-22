'use strict';

angular.module('quizApp').controller('ScoreDialogController',
    ['$scope', '$stateParams', '$modalInstance', '$q', 'entity', 'Score', 'User',
        function($scope, $stateParams, $modalInstance, $q, entity, Score, User) {

        $scope.score = entity;
        $scope.users = User.query();
        $scope.load = function(id) {
            Score.get({id : id}, function(result) {
                $scope.score = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('quizApp:scoreUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.score.id != null) {
                Score.update($scope.score, onSaveFinished);
            } else {
                Score.save($scope.score, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
