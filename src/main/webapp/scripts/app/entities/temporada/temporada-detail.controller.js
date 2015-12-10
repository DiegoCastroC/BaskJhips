'use strict';

angular.module('basjhipsterApp')
    .controller('TemporadaDetailController', function ($scope, $rootScope, $stateParams, entity, Temporada, Liga, Equipo, Partido) {
        $scope.temporada = entity;
        $scope.load = function (id) {
            Temporada.get({id: id}, function(result) {
                $scope.temporada = result;
            });
        };
        $rootScope.$on('basjhipsterApp:temporadaUpdate', function(event, result) {
            $scope.temporada = result;
        });
    });
