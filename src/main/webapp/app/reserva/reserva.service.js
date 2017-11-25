//-----------------------------------------------------
// SERVICE
//-----------------------------------------------------
(function() {
    'use strict';

    angular.module('reserva').factory('ReservaData', ReservaData);

    /* @ngInject */
    function ReservaData(Restangular, $q, $rootScope) {
    	
    	Restangular.setBaseUrl($rootScope.baseURL);
    	
        var apiURL = '/reserva';
        var apiURLCompleta = '/LazarusHotelaria/rest-clovis' + apiURL;

        var service = {
            obter : obter,            
            listar : listar,
            salvar : salvar,
            excluir : excluir,
            solicitarReserva : solicitarReserva
        };

        return service;
        // //////////////

        function obter(codReserva, filtros) {
            return Restangular.one(apiURLCompleta, codReserva).get(filtros);
        }

        function listar(filtros) {
            return Restangular.one(apiURLCompleta).get(filtros);
        }
        
        function salvar(reserva) {
            if (reserva.codReserva) {
                return Restangular.all(apiURLCompleta + "/" + reserva.codReserva).customPUT(reserva);
            } else {
            	return Restangular.all(apiURLCompleta).post(reserva);
            }
        }
        
        function solicitarReserva(reserva) {
        	return Restangular.all(apiURLCompleta + "/solicitar").post(reserva);
        }
        
        function excluir(codReserva) {
            return Restangular.one(apiURLCompleta, codReserva).remove();
        }
    }
})();






