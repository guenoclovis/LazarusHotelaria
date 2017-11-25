//-----------------------------------------------------
// SERVICE
//-----------------------------------------------------
(function() {
    'use strict';

    angular.module('atributo').factory('AtributoData', AtributoData);

    /* @ngInject */
    function AtributoData(Restangular, $q, $rootScope) {
    	
    	Restangular.setBaseUrl($rootScope.baseURL);
    	
        var apiURL = '/atributo';
        var apiURLCompleta = '/LazarusHotelaria/rest-clovis' + apiURL;

        var service = {
            obter : obter,            
            listar : listar,
            salvar : salvar,
            excluir : excluir
        };

        return service;
        // //////////////

        function obter(codAtributo, filtros) {
            return Restangular.one(apiURLCompleta, codAtributo).get(filtros);
        }

        function listar(filtros) {
            return Restangular.one(apiURLCompleta).get(filtros);
        }
        
        function salvar(atributo) {
            if (atributo.codAtributo) {
                return Restangular.all(apiURLCompleta + "/" + atributo.codAtributo).customPUT(atributo);
            } else {
            	return Restangular.all(apiURLCompleta).post(atributo);
            }
        }
        
        function excluir(codAtributo) {
            return Restangular.one(apiURLCompleta, codAtributo).remove();
        }
    }
})();
