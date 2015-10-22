'use strict';

angular.module('quizApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('reponse', {
                parent: 'entity',
                url: '/reponses',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'quizApp.reponse.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/reponse/reponses.html',
                        controller: 'ReponseController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('reponse');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('reponse.detail', {
                parent: 'entity',
                url: '/reponse/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'quizApp.reponse.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/reponse/reponse-detail.html',
                        controller: 'ReponseDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('reponse');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Reponse', function($stateParams, Reponse) {
                        return Reponse.get({id : $stateParams.id});
                    }]
                }
            })
            .state('reponse.new', {
                parent: 'reponse',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/reponse/reponse-dialog.html',
                        controller: 'ReponseDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    label: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('reponse', null, { reload: true });
                    }, function() {
                        $state.go('reponse');
                    })
                }]
            })
            .state('reponse.edit', {
                parent: 'reponse',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/reponse/reponse-dialog.html',
                        controller: 'ReponseDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Reponse', function(Reponse) {
                                return Reponse.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('reponse', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
