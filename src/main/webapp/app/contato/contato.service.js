//-----------------------------------------------------
// SERVICE
//-----------------------------------------------------
(function() {
    'use strict';

    angular.module('contato').factory('ContatoData', ContatoData);

    /* @ngInject */
    function ContatoData(Restangular, $q) {
    	Restangular.setBaseUrl('http://localhost\:8080');
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






