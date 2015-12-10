'use strict';

angular.module('basjhipsterApp').controller('EspecificacionesDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Especificaciones', 'Jugador', 'Partido',
        function($scope, $stateParams, $modalInstance, entity, Especificaciones, Jugador, Partido) {

        $scope.especificaciones = entity;
        $scope.jugadors = Jugador.query();
        $scope.partidos = Partido.query();
        $scope.load = function(id) {
            Especificaciones.get({id : id}, function(result) {
                $scope.especificaciones = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('basjhipsterApp:especificacionesUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.especificaciones.id != null) {
                Especificaciones.update($scope.especificaciones, onSaveFinished);
            } else {
                Especificaciones.save($scope.especificaciones, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
