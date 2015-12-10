'use strict';

angular.module('basjhipsterApp').controller('PartidoDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Partido', 'Arbitro', 'Temporada', 'Especificaciones', 'Equipo',
        function($scope, $stateParams, $modalInstance, entity, Partido, Arbitro, Temporada, Especificaciones, Equipo) {

        $scope.partido = entity;
        $scope.arbitros = Arbitro.query();
        $scope.temporadas = Temporada.query();
        $scope.especificacioness = Especificaciones.query();
        $scope.equipos = Equipo.query();
        $scope.load = function(id) {
            Partido.get({id : id}, function(result) {
                $scope.partido = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('basjhipsterApp:partidoUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.partido.id != null) {
                Partido.update($scope.partido, onSaveFinished);
            } else {
                Partido.save($scope.partido, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
