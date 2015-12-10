'use strict';

angular.module('basjhipsterApp').controller('TemporadaDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Temporada', 'Liga', 'Equipo', 'Partido',
        function($scope, $stateParams, $modalInstance, entity, Temporada, Liga, Equipo, Partido) {

        $scope.temporada = entity;
        $scope.ligas = Liga.query();
        $scope.equipos = Equipo.query();
        $scope.partidos = Partido.query();
        $scope.load = function(id) {
            Temporada.get({id : id}, function(result) {
                $scope.temporada = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('basjhipsterApp:temporadaUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.temporada.id != null) {
                Temporada.update($scope.temporada, onSaveFinished);
            } else {
                Temporada.save($scope.temporada, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
