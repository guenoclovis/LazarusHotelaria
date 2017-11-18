(function() {
    'use strict';
    
    var ATIVAR_DEBUG = true;
    
    
    if(ATIVAR_DEBUG){
    	angular.module('lazarusApp').directive('debug', function(){
 		   return {
 			      restrict:'E',
 			      scope: {
 			         objeto: '@'
 			      },
 		   		  template:'<div><pre>{{objeto}}</pre></div>'
 			   };
 			});	
    }

})();