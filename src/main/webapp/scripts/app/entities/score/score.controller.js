'use strict';

angular.module('quizApp')
    .controller('ScoreController', function ($scope, Score) {
        $scope.scores = [];
        $scope.loadAll = function() {
            Score.query(function(result) {
               $scope.scores = result;
            });
        };
        $scope.loadAll();

        $scope.delete = function (id) {
            Score.get({id: id}, function(result) {
                $scope.score = result;
                $('#deleteScoreConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Score.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteScoreConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.score = {
                count: null,
                duration: null,
                id: null
            };
        };
    });
