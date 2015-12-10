'use strict';

angular.module('basjhipsterApp')
    .controller('JugadorDetailController', function ($scope, $rootScope, $stateParams, entity, Jugador, Equipo, Especificaciones) {
        $scope.jugador = entity;
        $scope.load = function (id) {
            Jugador.get({id: id}, function(result) {
                $scope.jugador = result;
            });
        };
        $rootScope.$on('basjhipsterApp:jugadorUpdate', function(event, result) {
            $scope.jugador = result;
        });
    });
