(function() {
	'use strict';

	var myApp = angular.module('lazarusApp', [ 
            //Sub-modulos que sao bibliotecas externas
            'ui.router', 
            'restangular',
            'checklist-model',
            'ngMask',
            'datepicker-localdate',
            //'ng-file-upload',            
            'ui.bootstrap',
            'msgcenter',
            
            //Sub-modulos que sao funcionalidades do sistema
			'inicio',
			'cliente', 
			'filial', 
			'atributo', 
			'tipoquarto',
			'usuario',
			'quarto',
			'reserva',
			'foto',
			'contato',
			'login',
			'relatorio'
//			'showcase'
	]);
	
	//Variavel que indica qual a URL que sera concatenada com todos os servicos
	//Ex: localmente vai ser algo do tipo http://localhost:8080/
	//    em producao algo do tipo:      https://servidor.fjsdkl.fjskdl:8083/	
	angular.module('lazarusApp').run(function($rootScope, $location) {
		
	    $rootScope.baseURL = $location.protocol() + "://" + $location.host() + ":" + "8080" + "/";	    
	    $rootScope.format = 'dd/MM/yyyy';
	    $rootScope.formatBtn = '../lib/templateHTMLparaDatepicker/popup.html';
	})
})();