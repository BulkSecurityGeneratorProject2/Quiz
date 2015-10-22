'use strict';

angular.module('quizApp')
    .controller('Reponse_joueurController', function ($scope, Reponse_joueur) {
        $scope.reponse_joueurs = [];
        $scope.loadAll = function() {
            Reponse_joueur.query(function(result) {
               $scope.reponse_joueurs = result;
            });
        };
        $scope.loadAll();

        $scope.delete = function (id) {
            Reponse_joueur.get({id: id}, function(result) {
                $scope.reponse_joueur = result;
                $('#deleteReponse_joueurConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Reponse_joueur.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteReponse_joueurConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.reponse_joueur = {
                label: null,
                label_reponse: null,
                reponse_ok: null,
                id: null
            };
        };
    });
