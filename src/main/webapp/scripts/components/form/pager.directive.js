/* globals $ */
'use strict';

angular.module('quizApp')
    .directive('quizAppPager', function() {
        return {
            templateUrl: 'scripts/components/form/pager.html'
        };
    });
