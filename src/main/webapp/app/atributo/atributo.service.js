//-----------------------------------------------------
// SERVICE
//-----------------------------------------------------
(function() {
    'use strict';

    angular.module('atributo').factory('AtributoData', AtributoData);

    /* @ngInject */
    function AtributoData(Restangular, $q) {
<<<<<<< HEAD
    	Restangular.setBaseUrl('http://localhost\:8080');
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
=======
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






>>>>>>> branch 'master' of https://github.com/guenoclovis/LazarusHotelaria.git
