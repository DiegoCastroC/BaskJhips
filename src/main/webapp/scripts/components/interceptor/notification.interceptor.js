 'use strict';

angular.module('basjhipsterApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-basjhipsterApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-basjhipsterApp-params')});
                }
                return response;
            },
        };
    });