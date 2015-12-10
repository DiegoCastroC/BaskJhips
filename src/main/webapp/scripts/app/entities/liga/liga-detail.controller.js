'use strict';

angular.module('basjhipsterApp')
    .controller('LigaDetailController', function ($scope, $rootScope, $stateParams, entity, Liga, Temporada, Arbitro) {
        $scope.liga = entity;
        $scope.load = function (id) {
            Liga.get({id: id}, function(result) {
                $scope.liga = result;
            });
        };
        $rootScope.$on('basjhipsterApp:ligaUpdate', function(event, result) {
            $scope.liga = result;
        });
    });
