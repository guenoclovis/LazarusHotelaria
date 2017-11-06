//-----------------------------------------------------
// SERVICE
//-----------------------------------------------------
(function() {
    'use strict';

    angular.module('quarto').factory('ReservaData', ReservaData);

    /* @ngInject */
    function ReservaData(Restangular, $q) {
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

        function obter(codReserva, filtros) {
            return Restangular.one(apiURLCompleta, codReserva).get(filtros);
        }

        function listar(filtros) {
            return Restangular.one(apiURLCompleta).get(filtros);
        }
        
        function salvar(quarto) {
            if (quarto.codReserva) {
                return Restangular.all(apiURLCompleta + "/" + quarto.codReserva).customPUT(quarto);
            } else {
            	return Restangular.all(apiURLCompleta).post(quarto);
            }
        }
        
        function excluir(codReserva) {
            return Restangular.one(apiURLCompleta, codReserva).remove();
        }
    }
})();






