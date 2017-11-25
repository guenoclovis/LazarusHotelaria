//-----------------------------------------------------
// SERVICE
//-----------------------------------------------------
(function() {
    'use strict';

    angular.module('login').factory('LoginData', LoginData);

    /* @ngInject */
    function LoginData(Restangular, $q) {
    	Restangular.setBaseUrl('http://localhost\:8080');
        var apiURL = '/login';
        var apiURLCompleta = '/LazarusHotelaria/rest-clovis' + apiURL;

        var service = {
            obter : obter,            
        };

        return service;
        // //////////////

        function obter(codCliente, filtros) {
            return Restangular.one(apiURLCompleta, codCliente).get(filtros);
        }

    }
})();






