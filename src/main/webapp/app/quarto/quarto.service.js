//-----------------------------------------------------
// SERVICE
//-----------------------------------------------------
(function() {
    'use strict';

    angular.module('quarto').factory('QuartoData', QuartoData);

    /* @ngInject */
    function QuartoData(Restangular, $q, $rootScope) {
    	
    	Restangular.setBaseUrl($rootScope.baseURL);
    	
        var apiURL = '/quarto';
        var apiURLCompleta = '/LazarusHotelaria/rest-clovis' + apiURL;

        var service = {
            obter : obter,            
            listar : listar,
            salvar : salvar,
            excluir : excluir,
            pesquisarSemReserva : pesquisarSemReserva,
            pesquisarComReserva : pesquisarComReserva
        };

        return service;
        // //////////////

        function obter(codQuarto, filtros) {
            return Restangular.one(apiURLCompleta, codQuarto).get(filtros);
        }

        function listar(filtros) {
            return Restangular.one(apiURLCompleta).get(filtros);
        }
        
        function pesquisarComReserva(filtros) {
            return Restangular.one(apiURLCompleta).get(filtros);
        }
        
        function pesquisarSemReserva(filtros){
        	return Restangular.one(apiURLCompleta + "/semreserva").get(filtros);
        }
        
        function salvar(quarto) {
            if (quarto.codQuarto) {
                return Restangular.all(apiURLCompleta + "/" + quarto.codQuarto).customPUT(quarto);
            } else {
            	return Restangular.all(apiURLCompleta).post(quarto);
            }
        }
        
        function excluir(codQuarto) {
            return Restangular.one(apiURLCompleta, codQuarto).remove();
        }
    }
})();






