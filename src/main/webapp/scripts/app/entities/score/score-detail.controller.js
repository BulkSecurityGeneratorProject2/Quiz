'use strict';

angular.module('quizApp')
    .controller('ScoreDetailController', function ($scope, $rootScope, $stateParams, entity, Score, User) {
        $scope.score = entity;
        $scope.load = function (id) {
            Score.get({id: id}, function(result) {
                $scope.score = result;
            });
        };
        $rootScope.$on('quizApp:scoreUpdate', function(event, result) {
            $scope.score = result;
        });
    });
