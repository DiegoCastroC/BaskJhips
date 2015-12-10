'use strict';

angular.module('basjhipsterApp')
    .controller('PartidoDetailController', function ($scope, $rootScope, $stateParams, entity, Partido, Arbitro, Temporada, Especificaciones, Equipo) {
        $scope.partido = entity;
        $scope.load = function (id) {
            Partido.get({id: id}, function(result) {
                $scope.partido = result;
            });
        };
        $rootScope.$on('basjhipsterApp:partidoUpdate', function(event, result) {
            $scope.partido = result;
        });
    });
