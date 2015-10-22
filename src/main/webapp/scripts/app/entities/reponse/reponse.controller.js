'use strict';

angular.module('quizApp')
    .controller('ReponseController', function ($scope, Reponse) {
        $scope.reponses = [];
        $scope.loadAll = function() {
            Reponse.query(function(result) {
               $scope.reponses = result;
            });
        };
        $scope.loadAll();

        $scope.delete = function (id) {
            Reponse.get({id: id}, function(result) {
                $scope.reponse = result;
                $('#deleteReponseConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Reponse.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteReponseConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.reponse = {
                label: null,
                id: null
            };
        };
    });
