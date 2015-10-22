'use strict';

angular.module('quizApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('reponse_joueur', {
                parent: 'entity',
                url: '/reponse_joueurs',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'quizApp.reponse_joueur.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/reponse_joueur/reponse_joueurs.html',
                        controller: 'Reponse_joueurController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('reponse_joueur');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('reponse_joueur.detail', {
                parent: 'entity',
                url: '/reponse_joueur/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'quizApp.reponse_joueur.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/reponse_joueur/reponse_joueur-detail.html',
                        controller: 'Reponse_joueurDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('reponse_joueur');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Reponse_joueur', function($stateParams, Reponse_joueur) {
                        return Reponse_joueur.get({id : $stateParams.id});
                    }]
                }
            })
            .state('reponse_joueur.new', {
                parent: 'reponse_joueur',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/reponse_joueur/reponse_joueur-dialog.html',
                        controller: 'Reponse_joueurDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    label: null,
                                    label_reponse: null,
                                    reponse_ok: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('reponse_joueur', null, { reload: true });
                    }, function() {
                        $state.go('reponse_joueur');
                    })
                }]
            })
            .state('reponse_joueur.edit', {
                parent: 'reponse_joueur',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/reponse_joueur/reponse_joueur-dialog.html',
                        controller: 'Reponse_joueurDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Reponse_joueur', function(Reponse_joueur) {
                                return Reponse_joueur.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('reponse_joueur', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
