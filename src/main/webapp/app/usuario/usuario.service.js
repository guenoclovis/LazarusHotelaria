//-----------------------------------------------------
// SERVICE
//-----------------------------------------------------
(function() {
    'use strict';

    angular.module('usuario').factory('UsuarioData', UsuarioData);

    /* @ngInject */
    function UsuarioData(Restangular, $q, $rootScope) {
    	
    	Restangular.setBaseUrl($rootScope.baseURL);
    	
        var apiURL = '/usuario';
        var apiURLCompleta = '/LazarusHotelaria/rest-clovis' + apiURL;

        var service = {
            obter : obter,            
            listar : listar,
            salvar : salvar,
            excluir : excluir
        };

        return service;
        // //////////////

        function obter(codUsuario, filtros) {
            return Restangular.one(apiURLCompleta, codUsuario).get(filtros);
        }

        function listar(filtros) {
            return Restangular.one(apiURLCompleta).get(filtros);
        }
        
        function salvar(usuario) {
            if (usuario.codUsuario) {
                return Restangular.all(apiURLCompleta + "/" + usuario.codUsuario).customPUT(usuario);
            } else {
            	return Restangular.all(apiURLCompleta).post(usuario);
            }
        }
        
        function excluir(codUsuario) {
            return Restangular.one(apiURLCompleta, codUsuario).remove();
        }
    }
})();






