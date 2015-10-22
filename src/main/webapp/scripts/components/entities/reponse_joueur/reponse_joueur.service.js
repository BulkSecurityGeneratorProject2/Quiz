'use strict';

angular.module('quizApp')
    .factory('Reponse_joueur', function ($resource, DateUtils) {
        return $resource('api/reponse_joueurs/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
