//-----------------------------------------------------
// SERVICE
//-----------------------------------------------------
(function() {
    'use strict';

    angular.module('login').factory('LoginData', LoginData);

    /* @ngInject */
    function LoginData(Restangular, $q, $rootScope) {
    	
    	Restangular.setBaseUrl($rootScope.baseURL);
    	
        var apiURL = '/login';
        var apiURLCompleta = '/LazarusHotelaria/rest-clovis' + apiURL;

        var service = {
            login : login,
            logout : logout
        };

        return service;
        // //////////////

        function login(dadosLogin) {
        	return Restangular.all(apiURLCompleta).post(dadosLogin);
        }
        
        function logout() {
        	return Restangular.all(apiURLCompleta + "/logout").post();
        }

    }
})();






