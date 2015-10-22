'use strict';

angular.module('quizApp')
    .controller('Reponse_joueurDetailController', function ($scope, $rootScope, $stateParams, entity, Reponse_joueur, User, Question, Reponse) {
        $scope.reponse_joueur = entity;
        $scope.load = function (id) {
            Reponse_joueur.get({id: id}, function(result) {
                $scope.reponse_joueur = result;
            });
        };
        $rootScope.$on('quizApp:reponse_joueurUpdate', function(event, result) {
            $scope.reponse_joueur = result;
        });
    });
