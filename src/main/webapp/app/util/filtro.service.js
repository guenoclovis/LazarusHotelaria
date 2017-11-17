(function() {
    'use strict';

    angular.module('lazarusApp').factory('FiltroService', FiltroService);

    /* @ngInject */
    function FiltroService() {
        var vm = this;
        vm.filtros = undefined;
        vm.restaurar = false;
        vm.pesquisar = false;
        vm.rotaRetorno = 'ALGUM VALOR DEFAULT';

        var service = {
            marcarParaRestaurar : marcarParaRestaurar,
            marcarRestaurado : marcarRestaurado,
            deveRestaurar : deveRestaurar,
            salvarFiltros : salvarFiltros,
            obterFiltros : obterFiltros,
            devePesquisar : devePesquisar,
            setRotaRetorno : setRotaRetorno,
            getRotaRetorno : getRotaRetorno
        };
        return service;

        // //////////////
        
        function getRotaRetorno(){
            return vm.rotaRetorno;
        }
        
        function setRotaRetorno(r){
            vm.rotaRetorno = r;
        }
        
        function marcarParaRestaurar() {
            vm.restaurar = true;
        }

        function marcarRestaurado() {
            vm.restaurar = false;
            vm.filtros = null;
        }

        function deveRestaurar() {
            return vm.restaurar && !angular.isUndefined(vm.filtros);
        }

        function salvarFiltros(filtros, devePesquisar) {
            vm.filtros = angular.copy(filtros);
            vm.pesquisar = devePesquisar;
        }

        function obterFiltros() {
            return vm.filtros;
        }

        function devePesquisar() {
            return vm.pesquisar;
        }

    }

})();