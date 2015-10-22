'use strict';

angular.module('quizApp')
    .controller('QuestionController', function ($scope, Question) {
        $scope.questions = [];
        $scope.loadAll = function() {
            Question.query(function(result) {
               $scope.questions = result;
            });
        };
        $scope.loadAll();

        $scope.delete = function (id) {
            Question.get({id: id}, function(result) {
                $scope.question = result;
                $('#deleteQuestionConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Question.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteQuestionConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.question = {
                label: null,
                id: null
            };
        };
    });
