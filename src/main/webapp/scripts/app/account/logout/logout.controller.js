'use strict';

angular.module('basjhipsterApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
