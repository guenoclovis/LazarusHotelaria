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
			'cliente', 
			'filial', 
			'atributo', 
			'tipoquarto',
			'usuario',
			'showcase']);

})();