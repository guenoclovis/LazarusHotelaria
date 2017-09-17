//-----------------------------------------------------
// SERVICE
//-----------------------------------------------------
(function() {
    'use strict';

    angular.module('atributo').factory('AtributoData', AtributoData);

    /* @ngInject */
    function AtributoData(Restangular, $q) {
        var apiURL = '/atributo';
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
        
        function salvar(atributo) {
            if (atributo.id) {
                return Restangular.all(apiURLCompleta + "/" + atributo.id).customPUT(atributo);
            } else {
            	return Restangular.all(apiURLCompleta).post(atributo);
            }
        }
    }
})();






