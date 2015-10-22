'use strict';

angular.module('quizApp')
    .controller('ReponseDetailController', function ($scope, $rootScope, $stateParams, entity, Reponse, Question) {
        $scope.reponse = entity;
        $scope.load = function (id) {
            Reponse.get({id: id}, function(result) {
                $scope.reponse = result;
            });
        };
        $rootScope.$on('quizApp:reponseUpdate', function(event, result) {
            $scope.reponse = result;
        });
    });
