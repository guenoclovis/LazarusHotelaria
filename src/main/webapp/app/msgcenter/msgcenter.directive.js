(function() {
	'use strict';

	angular
		.module('msgcenter')
		.directive('mcMessages', MsgCenterDirective);

	/* @ngInject */
	function MsgCenterDirective($rootScope, MsgCenter, $window) {

		var directive = {
			restrict : 'EA',
			scope: true,
			templateUrl : 'app/msgcenter/msgcenter.html',
			link : link
		};
		return directive;
		
		////////////////
		
        function link(scope, element, attrs) {
        	
        	scope.target = attrs.target;
        	
			// Evento "on change route"
			var changeReaction = function(event, to, from) {
				// Atualizar mensagens 'unseen' para o status 'shown'.
				MsgCenter.markShown();
				// Remover as mensagens com status 'shown'.
				MsgCenter.removeShown();
			};
			if (MsgCenter.offlistener === undefined) {
				MsgCenter.offlistener = $rootScope.$on('$stateChangeSuccess', changeReaction);
			}
			
			// Evento "notify"
			var notifyReaction = function(event, args) {
				// limpar marcação de erro
				angular.element('.has-error').removeClass('has-error');
                
                var primeiroElemento = undefined;
                var possuiTarget = false;
				
				for (var i = MsgCenter.mcMessages.length -1; i >= 0 ; i--) {
					var msg = MsgCenter.mcMessages[i];
					if (msg.field) {
					    var fieldEscape = msg.field;
					    fieldEscape = fieldEscape.replace(/\[|\]|\./g, '');
					    
						var idCampo = '#' + fieldEscape;
						
						// Obter o elemento mc-messages correto de acordo com o target da mensagem
						var elemento = undefined;
						if (msg.target == null) {
							elemento = angular.element('mc-messages:not([target])');
						} else {
							elemento = angular.element('mc-messages[target="' + msg.target +'"]');
                            possuiTarget = true;
						}
                        
                        if (angular.isUndefined(primeiroElemento)) {
                            primeiroElemento = elemento;
                        }
						
						var div = elemento.parent().find(idCampo).parent();
						if (div.length > 0) {
						    div.addClass('has-error');
						}
					}
				}
                
                if (angular.isDefined(primeiroElemento) && possuiTarget) {
                    primeiroElemento[0].scrollIntoView();
                }
			};
			if (MsgCenter.watch === undefined) {
				MsgCenter.watch = $rootScope.$on('msgCenter-notify', notifyReaction);
			}
			
			scope.animation = attrs.animation || 'fade in';

			// Scroll up
			var scrollReaction = function(event, args) {
				$window.scrollTo(0, 0);
			};
			
			if (MsgCenter.scrolllistener === undefined) {
				MsgCenter.scrolllistener = $rootScope.$on('newMessage-notify', scrollReaction);
			}
			
        }
	}
	
})();
