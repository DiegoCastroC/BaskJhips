'use strict';

angular.module('basjhipsterApp')
    .factory('Especificaciones', function ($resource, DateUtils) {
        return $resource('api/especificacioness/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
