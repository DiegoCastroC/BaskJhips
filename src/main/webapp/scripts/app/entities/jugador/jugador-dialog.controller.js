'use strict';

angular.module('basjhipsterApp').controller('JugadorDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Jugador', 'Equipo', 'Especificaciones',
        function($scope, $stateParams, $modalInstance, entity, Jugador, Equipo, Especificaciones) {

        $scope.jugador = entity;
        $scope.equipos = Equipo.query();
        $scope.especificacioness = Especificaciones.query();
        $scope.load = function(id) {
            Jugador.get({id : id}, function(result) {
                $scope.jugador = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('basjhipsterApp:jugadorUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.jugador.id != null) {
                Jugador.update($scope.jugador, onSaveFinished);
            } else {
                Jugador.save($scope.jugador, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
