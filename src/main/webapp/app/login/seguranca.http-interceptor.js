(function() {
	'use strict';

	angular.module('login')
		.service('AuthInterceptor', AuthInterceptor)
		.config(configure);


	 /* @ngInject */
    function AuthInterceptor($q, $window, $state, $rootScope, $injector, MsgCenter) {
        return {
        	
            request: function(request){
            	if(/.*obterImgCaptcha.*/.test(request.url) ||
            	   /.*obterSomcaptcha.*/.test(request.url) ||
            	   (/.*login.*/.test(request.url) && request.method == 'POST')){
            		if(request.headers.Authorization){
            			delete request.headers.Authorization;            			
            		}
            	}
            	
            	return request;            	
            },
            
        	responseError: function (response) {
                var status = response.status;
                
                //---trecho igual ao do msgcenter.httpinterceptor---
                var headerTarget = response.config.headers.target;
		    	
                if(response.data != undefined && 
                	response.data != null &&
                	response.data.mensagens != undefined && 
                	response.data.mensagens != null){
                	
                	var msgs = response.data.mensagens;
                	for (var i = 0; i < msgs.length; i++) {
                		var msg = msgs[i];
                		MsgCenter.add(msg.severity, msg.message, msg.path, {target: headerTarget}); 
                	}
                	MsgCenter.notify();                	
                }
                
                //---trecho igual ao do msgcenter.httpinterceptor---
                
                if (status === 401) {
                	
                	$state.go('login');
                	
                	//TODO: CODIGO FUTURO para tratar authenticacao usando token
                    /*
                    var $auth = $injector.get('$auth');
                    $auth.removeToken();
                	$window.location.href = $rootScope.baseURL + "/login";*/
                }
                
                if (status === 403) {
                	
                	//TODO: CODIGO FUTURO para tratar autorizacao
                    /*var $state = $injector.get('$state');
                    MsgCenter.addNext(response.data.severity, response.data.message);
                    $state.go('erropermissao');*/
                }
                
                return $q.reject(response);
            },
            
            response: function (response) { 
            	if(/.*rest-clovis.*/.test(response.config.url)){
            		
//            		var segurancaTokenService = $injector.get('SegurancaTokenService');            		
//            		segurancaTokenService.start();            		
            	}
            	
                return response;
            }
        };
    }

	/* @ngInject */
	function configure($httpProvider) {
		$httpProvider.interceptors.push('AuthInterceptor');
	}
})();