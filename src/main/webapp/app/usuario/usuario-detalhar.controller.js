//-----------------------------------------------------
// CONTROLADOR DETALHAMENTO
//-----------------------------------------------------
(function () {
    'use strict';

    // Adicionando um Controlador para a tela 'detalhar' do modulo 'usuario'
    angular.module('usuario').controller(
            'DetalharUsuarioController',
            DetalharUsuarioController);

    // Definindo atributos e operacoes do Controlador da tela 'detalhar' do modulo 'usuario'
    /* @ngInject */
    function DetalharUsuarioController($controller, $scope, $state, $stateParams,
    		UsuarioData) {

        //////// ATRIBUTOS DO CONTROLADOR ////////////////////
        var vm = this;

        vm.usuario = {};
        vm.usuario.codUsuario = $stateParams.codUsuario;

        vm.msgs = "";

        vm.voltarParaTelaAnterior = voltarParaTelaAnterior;
        vm.irParaTelaEdicao = irParaTelaEdicao;
        vm.excluir = excluir;

        activate();

        //////// OPERACOES DO CONTROLADOR ////////////////////

        function activate() {
            obter();
        }

        function limpar() {
            $state.reload();
        }

        function voltarParaTelaAnterior() {
            $state.go('usuarioConsultar');
        }

        function irParaTelaEdicao(codUsuario) {
            $state.go('usuarioEditar', {
                'codUsuario': codUsuario
            });
        }

        function obter() {
        	UsuarioData.obter(vm.usuario.codUsuario).then(function (data) {
                vm.usuario = data.plain();
            });
        }
        
        function excluir() {
        	UsuarioData.excluir(vm.usuario.codUsuario).then(function (data) {                
                $state.go('usuarioConsultar');
            });
        }

    }

})();