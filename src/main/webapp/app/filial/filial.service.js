//-----------------------------------------------------
// SERVICE
//-----------------------------------------------------
(function() {
    'use strict';

    angular.module('filial').factory('FilialData', FilialData);

    /* @ngInject */
    function FilialData(Restangular, $q, $http, $rootScope) {
    	
    	Restangular.setBaseUrl($rootScope.baseURL);
    	
        var apiURL = '/filial';
        var apiURLCompleta = '/LazarusHotelaria/rest-clovis' + apiURL;

        var service = {
            obter : obter,            
            listar : listar,
            salvar : salvar,
            excluir : excluir           
        };

        return service;


        function obter(id, filtros) {
            return Restangular.one(apiURLCompleta, id).get(filtros);
        }

        function listar(filtros) {
            return Restangular.one(apiURLCompleta).get(filtros);
        }
        
        function salvar(filial) {
            if (filial.id) {
                return Restangular.all(apiURLCompleta + "/" + filial.id).customPUT(filial);
            } else {
            	return Restangular.all(apiURLCompleta).post(filial);
            }
        }
        
        function excluir(codFilial) {
            return Restangular.one(apiURLCompleta, codFilial).remove();
        }
    }
})();






