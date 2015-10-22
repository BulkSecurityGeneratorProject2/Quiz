'use strict';

angular.module('quizApp')
    .factory('Reponse', function ($resource, DateUtils) {
        return $resource('api/reponses/:id', {}, {
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
