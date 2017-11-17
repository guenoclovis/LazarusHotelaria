//-----------------------------------------------------
// SERVICE
//-----------------------------------------------------
(function() {
    'use strict';

    angular.module('quarto').factory('QuartoData', QuartoData);

    /* @ngInject */
    function QuartoData(Restangular, $q) {
    	Restangular.setBaseUrl('http://localhost\:8080');
        var apiURL = '/quarto';
        var apiURLCompleta = '/LazarusHotelaria/rest-clovis' + apiURL;

        var service = {
            obter : obter,            
            listar : listar,
            salvar : salvar,
            excluir : excluir
        };

        return service;
        // //////////////

        function obter(codQuarto, filtros) {
            return Restangular.one(apiURLCompleta, codQuarto).get(filtros);
        }

        function listar(filtros) {
            return Restangular.one(apiURLCompleta).get(filtros);
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






