(function() {
	'use strict';

	angular.module('login')
		.service('AuthInterceptor', AuthInterceptor)
		.config(configure);


	 /* @ngInject */
    function AuthInterceptor($q, $window, $rootScope, $injector, MsgCenter) {
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
                
                if (status === 401) {
                    var $auth = $injector.get('$auth');
                    $auth.removeToken();
                	$window.location.href = $rootScope.baseURL + "/login";
                }
                
                if (status === 403) {
                    var $state = $injector.get('$state');
                    MsgCenter.addNext(response.data.severity, response.data.message);
                    $state.go('erropermissao');                	
                }
                
                return $q.reject(response);
            },
            
            response: function (response) { 
            	if(/.*rest-clovis.*/.test(response.config.url)){
            		
            		var segurancaTokenService = $injector.get('SegurancaTokenService');            		
            		segurancaTokenService.start();            		
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