'use strict';

angular.module('basjhipsterApp')
    .controller('EstadioDetailController', function ($scope, $rootScope, $stateParams, entity, Estadio, Equipo) {
        $scope.estadio = entity;
        $scope.load = function (id) {
            Estadio.get({id: id}, function(result) {
                $scope.estadio = result;
            });
        };
        $rootScope.$on('basjhipsterApp:estadioUpdate', function(event, result) {
            $scope.estadio = result;
        });
    });
