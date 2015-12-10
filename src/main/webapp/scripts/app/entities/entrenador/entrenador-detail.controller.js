'use strict';

angular.module('basjhipsterApp')
    .controller('EntrenadorDetailController', function ($scope, $rootScope, $stateParams, entity, Entrenador, Equipo) {
        $scope.entrenador = entity;
        $scope.load = function (id) {
            Entrenador.get({id: id}, function(result) {
                $scope.entrenador = result;
            });
        };
        $rootScope.$on('basjhipsterApp:entrenadorUpdate', function(event, result) {
            $scope.entrenador = result;
        });
    });
