'use strict';

angular.module('basjhipsterApp')
    .controller('ArbitroDetailController', function ($scope, $rootScope, $stateParams, entity, Arbitro, Liga, Partido) {
        $scope.arbitro = entity;
        $scope.load = function (id) {
            Arbitro.get({id: id}, function(result) {
                $scope.arbitro = result;
            });
        };
        $rootScope.$on('basjhipsterApp:arbitroUpdate', function(event, result) {
            $scope.arbitro = result;
        });
    });
