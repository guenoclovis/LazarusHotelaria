//-----------------------------------------------------
// SERVICE
//-----------------------------------------------------
(function() {
    'use strict';

    angular.module('cliente').factory('ClienteData', ClienteData);

    /* @ngInject */
    function ClienteData(Restangular, $q) {
    	Restangular.setBaseUrl('http://localhost\:8080');
        var apiURL = '/cliente';
        var apiURLCompleta = '/LazarusHotelaria/rest-clovis' + apiURL;

        var service = {
            obter : obter,            
            listar : listar,
            salvar : salvar
        };

        return service;
        // //////////////

        function obter(id, filtros) {
            return Restangular.one(apiURLCompleta, id).get(filtros);
        }

        function listar(filtros) {
            return Restangular.one(apiURLCompleta).get(filtros);
        }
        
        function salvar(cliente) {
            if (cliente.id) {
                return Restangular.all(apiURLCompleta + "/" + cliente.id).customPUT(cliente);
            } else {
            	return Restangular.all(apiURLCompleta).post(cliente);
            }
        }
    }
})();






