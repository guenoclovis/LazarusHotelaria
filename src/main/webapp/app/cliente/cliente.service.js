//-----------------------------------------------------
// SERVICE
//-----------------------------------------------------
(function() {
    'use strict';

    angular.module('cliente').factory('ClienteData', ClienteData);

    /* @ngInject */
    function ClienteData(Restangular, $q, $rootScope) {
    	
    	Restangular.setBaseUrl($rootScope.baseURL);
    	
        var apiURL = '/cliente';
        var apiURLCompleta = '/LazarusHotelaria/rest-clovis' + apiURL;

        var service = {
            obter : obter,            
            listar : listar,
            salvar : salvar,
            excluir : excluir
        };

        return service;
        // //////////////

        function obter(codCliente, filtros) {
            return Restangular.one(apiURLCompleta, codCliente).get(filtros);
        }

        function listar(filtros) {
            return Restangular.one(apiURLCompleta).get(filtros);
        }
        
        function salvar(cliente) {
            if (cliente.codCliente) {
                return Restangular.all(apiURLCompleta + "/" + cliente.codCliente).customPUT(cliente);
            } else {
            	return Restangular.all(apiURLCompleta).post(cliente);
            }
        }
        
        function excluir(codCliente) {
            return Restangular.one(apiURLCompleta, codCliente).remove();
        }
    }
})();






