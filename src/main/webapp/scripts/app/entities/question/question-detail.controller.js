'use strict';

angular.module('quizApp')
    .controller('QuestionDetailController', function ($scope, $rootScope, $stateParams, entity, Question) {
        $scope.question = entity;
        $scope.load = function (id) {
            Question.get({id: id}, function(result) {
                $scope.question = result;
            });
        };
        $rootScope.$on('quizApp:questionUpdate', function(event, result) {
            $scope.question = result;
        });
    });
