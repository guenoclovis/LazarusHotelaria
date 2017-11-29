//-----------------------------------------------------
// SERVICE
//-----------------------------------------------------
(function() {
    'use strict';

    angular.module('cliente').factory('ClienteData', ClienteData);

    /* @ngInject */
    function ClienteData(Restangular, $q, $rootScope, $http) {
    	
    	Restangular.setBaseUrl($rootScope.baseURL);
    	
        var apiURL = '/cliente';
        var apiURLCompleta = '/LazarusHotelaria/rest-clovis' + apiURL;

        var service = {
            obter : obter,            
            listar : listar,
            salvar : salvar,
            excluir : excluir,
            gerarRelatorioPDF : gerarRelatorioPDF
        };

        return service;
        // //////////////

        function obter(codCliente, filtros) {
            return Restangular.one(apiURLCompleta, codCliente).get(filtros);
        }
        
        function listar(filtros) {
            return Restangular.one(apiURLCompleta).get(filtros);
        }
        
        function salvar(cliente) {
            if (cliente.codCliente) {
                return Restangular.all(apiURLCompleta + "/" + cliente.codCliente).customPUT(cliente);
            } else {
            	return Restangular.all(apiURLCompleta).post(cliente);
            }
        }
        
        function excluir(codCliente) {
            return Restangular.one(apiURLCompleta, codCliente).remove();
        }
        
        function gerarRelatorioPDF() {
        	$http({
                method : 'GET',
                cache : false,
                url : apiURLCompleta + "/pdf",
                params : undefined
            }).success(function(data, status, headers) {

                // Recuperar os headers da response
                var listaHeaders = headers();

                // Recuperar o nome do arquivo
                var filename = listaHeaders['filename'] || 'download';

                // Salvar o arquivo
                var data64 = b64.decode(data);
                var file = new Blob([ data64 ], {
                    type : 'application/octet-stream'
                });
                saveAs(file, filename);
            });
        }
    }
})();






