/* globals $ */
'use strict';

angular.module('quizApp')
    .directive('quizAppPagination', function() {
        return {
            templateUrl: 'scripts/components/form/pagination.html'
        };
    });
