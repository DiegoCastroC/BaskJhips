'use strict';

angular.module('basjhipsterApp')
    .controller('EquipoDetailController', function ($scope, $rootScope, $stateParams, entity, Equipo, Estadio, Socio, Entrenador, Jugador, Partido, Temporada) {
        $scope.equipo = entity;
        $scope.load = function (id) {
            Equipo.get({id: id}, function(result) {
                $scope.equipo = result;
            });
        };
        $rootScope.$on('basjhipsterApp:equipoUpdate', function(event, result) {
            $scope.equipo = result;
        });
    });
