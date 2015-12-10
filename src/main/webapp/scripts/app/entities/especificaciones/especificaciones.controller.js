'use strict';

angular.module('basjhipsterApp')
    .controller('EspecificacionesController', function ($scope, Especificaciones, ParseLinks) {
        $scope.especificacioness = [];
        $scope.page = 1;
        $scope.loadAll = function() {
            Especificaciones.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.especificacioness = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.delete = function (id) {
            Especificaciones.get({id: id}, function(result) {
                $scope.especificaciones = result;
                $('#deleteEspecificacionesConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Especificaciones.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteEspecificacionesConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.especificaciones = {rebotes: null, pases: null, puntos: null, faltas: null, id: null};
        };
    });
