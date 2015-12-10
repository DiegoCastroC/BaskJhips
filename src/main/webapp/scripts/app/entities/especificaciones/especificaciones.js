'use strict';

angular.module('basjhipsterApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('especificaciones', {
                parent: 'entity',
                url: '/especificacioness',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'basjhipsterApp.especificaciones.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/especificaciones/especificacioness.html',
                        controller: 'EspecificacionesController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('especificaciones');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('especificaciones.detail', {
                parent: 'entity',
                url: '/especificaciones/{id}',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'basjhipsterApp.especificaciones.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/especificaciones/especificaciones-detail.html',
                        controller: 'EspecificacionesDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('especificaciones');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Especificaciones', function($stateParams, Especificaciones) {
                        return Especificaciones.get({id : $stateParams.id});
                    }]
                }
            })
            .state('especificaciones.new', {
                parent: 'especificaciones',
                url: '/new',
                data: {
                    roles: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/especificaciones/especificaciones-dialog.html',
                        controller: 'EspecificacionesDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {rebotes: null, pases: null, puntos: null, faltas: null, id: null};
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('especificaciones', null, { reload: true });
                    }, function() {
                        $state.go('especificaciones');
                    })
                }]
            })
            .state('especificaciones.edit', {
                parent: 'especificaciones',
                url: '/{id}/edit',
                data: {
                    roles: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/especificaciones/especificaciones-dialog.html',
                        controller: 'EspecificacionesDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Especificaciones', function(Especificaciones) {
                                return Especificaciones.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('especificaciones', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
