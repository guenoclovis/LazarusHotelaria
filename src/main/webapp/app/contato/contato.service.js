//-----------------------------------------------------
// SERVICE
//-----------------------------------------------------
(function() {
    'use strict';

    angular.module('contato').factory('ContatoData', ContatoData);

    /* @ngInject */
    function ContatoData(Restangular, $q, $rootScope) {
    	
    	Restangular.setBaseUrl($rootScope.baseURL);
    	
        var apiURL = '/contato';
        var apiURLCompleta = '/LazarusHotelaria/rest-clovis' + apiURL;

        var service = {
    		enviarEmail : enviarEmail
        };

        return service;
        // //////////////


        
        function enviarEmail(dados) {
        	return Restangular.all(apiURLCompleta).post(dados);
        }
    }
})();






