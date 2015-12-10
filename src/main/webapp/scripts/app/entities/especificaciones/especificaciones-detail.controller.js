'use strict';

angular.module('basjhipsterApp')
    .controller('EspecificacionesDetailController', function ($scope, $rootScope, $stateParams, entity, Especificaciones, Jugador, Partido) {
        $scope.especificaciones = entity;
        $scope.load = function (id) {
            Especificaciones.get({id: id}, function(result) {
                $scope.especificaciones = result;
            });
        };
        $rootScope.$on('basjhipsterApp:especificacionesUpdate', function(event, result) {
            $scope.especificaciones = result;
        });
    });
