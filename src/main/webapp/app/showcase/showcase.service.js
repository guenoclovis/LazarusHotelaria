//-----------------------------------------------------
// SERVICE
//-----------------------------------------------------
(function() {
    'use strict';

    angular.module('showcase').factory('ShowcaseData', ShowcaseData);

    /* @ngInject */
    function ShowcaseData(Restangular, $q, $rootScope) {
    	
    	Restangular.setBaseUrl($rootScope.baseURL);
    	
        var apiURL = '/showcase';
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
        
        function salvar(showcase) {
            if (showcase.id) {
                return Restangular.all(apiURLCompleta + "/" + showcase.id).customPUT(showcase);
            } else {
            	return Restangular.all(apiURLCompleta).post(showcase);
            }
        }
    }
})();






