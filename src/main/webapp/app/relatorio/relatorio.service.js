//-----------------------------------------------------
// SERVICE
//-----------------------------------------------------
(function() {
    'use strict';

    angular.module('relatorio').factory('RelatorioData', RelatorioData);

    /* @ngInject */
    function RelatorioData(Restangular, $q, $rootScope) {
    	
    	Restangular.setBaseUrl($rootScope.baseURL);
    	
        var apiURL = '/relatorio';
        var apiURLCompleta = '/LazarusHotelaria/rest-clovis' + apiURL;

        var service = {
        	gerarRelatorio : gerarRelatorio
        };

        return service;
        // //////////////


        
        function gerarRelatorio(dados) {
        	return Restangular.all(apiURLCompleta).post(dados);
        }
    }
})();






