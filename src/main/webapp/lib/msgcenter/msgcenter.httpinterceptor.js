(function () {
    'use strict';

    angular
    	.module('msgcenter')
    	.service('ExceptionValidacaoInterceptor', ExceptionValidacaoInterceptor)
        .config(configure);

    /* @ngInject */
    function ExceptionValidacaoInterceptor($q, $injector, MsgCenter) {
        return {
            responseError: function (response) {
                
                // Tratar erros de validação
                if (response.status == 422) {
                    var headerTarget = response.config.headers.target;
			    	
			    	var msgs = response.data.mensagens;
			    	for (var i = 0; i < msgs.length; i++) {
			    		var msg = msgs[i];
			    		MsgCenter.add(msg.severidade, msg.texto, msg.path, {target: headerTarget}); 
			    	}
			    	MsgCenter.notify();
                }
                return $q.reject(response);
            }
        };
    }
    
    /* @ngInject */
    function configure($httpProvider) {
        $httpProvider.interceptors.push('ExceptionValidacaoInterceptor');
    }

})();