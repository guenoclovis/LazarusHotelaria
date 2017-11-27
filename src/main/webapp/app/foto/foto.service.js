//-----------------------------------------------------
// SERVICE
//-----------------------------------------------------
(function() {
    'use strict';

    angular.module('foto').factory('FotoData', FotoData);

    /* @ngInject */
    function FotoData(Restangular, $q, $http, $rootScope) {
    	
    	Restangular.setBaseUrl($rootScope.baseURL);
    	
        var apiURL = '/foto';
        var apiURLCompleta = '/LazarusHotelaria/rest-clovis' + apiURL;

        var service = {
            obter : obter,            
            listar : listar,
            salvar : salvar,
            excluir : excluir,
            incluir : incluir
        };

        return service;
        // //////////////

        function obter(codFoto, filtros) {
            return Restangular.one(apiURLCompleta, codFoto).get(filtros);
        }

        function listar(filtros) {
            return Restangular.one(apiURLCompleta).get(filtros);
        }
        
        function salvar(foto) {
            if (foto.codReserva) {
                return Restangular.all(apiURLCompleta + "/" + foto.codReserva).customPUT(foto);
            } else {
            	return Restangular.all(apiURLCompleta).post(foto);
            }
        }
        
        function incluir(imgBlob, nomeArquivo, legenda){
        	
			var fd = new FormData();
			
			fd.append('uploadedFile', imgBlob);			
			fd.append('nomeArquivo', nomeArquivo);
			fd.append('legenda', legenda);
			
			return $http.post($rootScope.baseURL + apiURLCompleta + '?nomeArquivo=' + nomeArquivo + "&legenda=" + legenda, fd, {
				transformRequest : angular.identity,
				headers : {
					'Content-Type' : undefined					
				}
			})
        }
        
        function excluir(codReserva) {
            return Restangular.one(apiURLCompleta, codReserva).remove();
        }
    }
})();






