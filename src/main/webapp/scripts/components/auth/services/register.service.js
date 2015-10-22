'use strict';

angular.module('quizApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


