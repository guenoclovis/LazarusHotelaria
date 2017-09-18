//-----------------------------------------------------
// SERVICE
//-----------------------------------------------------
(function() {
    'use strict';

    angular.module('tipoquarto').factory('TipoQuartoData', TipoQuartoData);

    /* @ngInject */
    function TipoQuartoData(Restangular, $q) {
    	Restangular.setBaseUrl('http://localhost\:8080');
        var apiURL = '/tipoquarto';
        var apiURLCompleta = '/LazarusHotelaria/rest-clovis' + apiURL;

        var service = {
            obter : obter,            
            listar : listar,
            salvar : salvar,
            excluir : excluir
        };

        return service;

        function obter(codTipoQuarto, filtros) {
            return Restangular.one(apiURLCompleta, codTipoQuarto).get(filtros);
        }

        function listar(filtros) {
            return Restangular.one(apiURLCompleta).get(filtros);
        }
        
        function salvar(tipoquarto) {
            if (tipoquarto.codTipoQuarto) {
                return Restangular.all(apiURLCompleta + "/" + tipoquarto.codTipoQuarto).customPUT(tipoquarto);
            } else {
            	return Restangular.all(apiURLCompleta).post(tipoquarto);
            }
        }
        
        function excluir(codTipoQuarto) {
            return Restangular.one(apiURLCompleta, codTipoQuarto).remove();
        }
    }
})();
