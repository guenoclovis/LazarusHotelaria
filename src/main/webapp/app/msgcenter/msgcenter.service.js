(function() {
	'use strict';
	
	angular.module('msgcenter')
		.service('MsgCenter', MsgCenter);

	/* @ngInject */
	function MsgCenter($rootScope, $sce, $timeout, MsgCenterProvider) {
		var vm = this;
		vm.status = {
			unseen : 'unseen',
			shown : 'shown',
			/**
			 * @var Se uma mensagem for adicionada e logo em seguida um roteamento é acionado, a mensagem 
			 * será apresentada apenas por uma fração de segundo pois existe um tratamento que limpa as 
			 * mensagens na troca de tela. Então, caso deseje adicionar uma mensagem para ser exibida apenas 
			 * na página seguinte, adicione a mesma com o status 'next'.
			 */
			next : 'next',
			
			two : 'two',
			
			/**
			 * @var Mensagens desse tipo só serão removidas manualmente.
			 */
			permanent : 'permanent'
		};
		vm.availableTypes = {
			// Mapeamento dos tipos da classe java Severity
			'ERROR' : 'danger',
			'ALERT' : 'warning',
			'INFO' : 'success',
			'WARN' : 'warning',
			'FATAL' : 'danger',
			
			'info' : 'info',
			'warning' : 'warning',
			'danger' : 'danger',
			'success' : 'success'};

		var service = {
			mcMessages : [],
			offlistener : undefined,
			scrolllistener : undefined,
			watch : undefined,
			
			add : add,
			addNext : addNext,
			addMessages: addMessages,
			addMessagesNext: addMessagesNext,
			addMessagesNextTwo: addMessagesNextTwo,
			remove : remove,
			clear : clear,
			removeShown : removeShown,
			markShown : markShown,
			flush : flush,
			notify: notify
		};

		activate();
		
		return service;
		
		////////////////
		
		function activate() {
			service.flush();
		}
		
		function addMessagesNext(msgList, options) {
			options = options || {};
			options = angular.extend(options, {status : 'next'});
			service.addMessages(msgList, options);
		}
		
		function addMessagesNextTwo(msgList, options) {
			options = options || {};
			options = angular.extend(options, {status : 'two'});
			service.addMessages(msgList, options);
		}
		
		function addMessages(msgList, options) {
			if (msgList) {
				for (var i = 0; i < msgList.length; i++) {
					var msg = msgList[i];
					service.add(msg.severity, msg.message, msg.path, options);										
				}
			}
		}
		
		function addNext(type, message, field, options) {
			options = options || {};
			options = angular.extend(options, {status : 'next'});
			service.add(type, message, field, options);
		}

		function add(type, message, field, options) {
			options = options || {};
			options = angular.extend(options, MsgCenterProvider.getOptions());
			if (vm.availableTypes[type] === undefined) {
				throw 'Tipo de mensagem inválido!';
			}
			
			var messageObject = {
				type : vm.availableTypes[type],
				status : options.status || vm.status.unseen,
				field: field,
				processed : false,
				target: options.target,
				close : function() {
					return service.remove(this);
				}
			};
			
			messageObject.message = options.html ? $sce.trustAsHtml(message) : message;
			messageObject.html = !!options.html;
			
			if (angular.isDefined(options.timeout)) {
				messageObject.timer = $timeout(function() {
					messageObject.close();
				}, options.timeout);
			}
			
			service.mcMessages.push(messageObject);
			service.removeShown();
			
			if (options.target == undefined) {
				$rootScope.$broadcast('newMessage-notify', service.mcMessages);
			}			
			
			return messageObject;
		}
		
		function remove(message) {
			var index = service.mcMessages.indexOf(message);
			service.mcMessages.splice(index, 1);
		}
		
		function clear() {
		    removeAll();
			notify();
		}
		
		function removeAll() {
            for (var index = service.mcMessages.length - 1; index >= 0; index--) {
                service.remove(service.mcMessages[index]);
            }
        }
		
		function removeShown() {
			for (var index = service.mcMessages.length - 1; index >= 0; index--) {
				if (service.mcMessages[index].status === vm.status.shown) {
					service.remove(service.mcMessages[index]);
				}
			}
		}
		
		function markShown() {
			for (var index = service.mcMessages.length - 1; index >= 0; index--) {
				if (!service.mcMessages[index].processed) {
					if (service.mcMessages[index].status === vm.status.unseen) {
						service.mcMessages[index].status = vm.status.shown;
						service.mcMessages[index].processed = true;
					} else if (service.mcMessages[index].status === vm.status.next) {
						service.mcMessages[index].status = vm.status.unseen;
					} else if (service.mcMessages[index].status === vm.status.two) {
						service.mcMessages[index].status = vm.status.next;
					}
				}
			}
		}
		
		function flush() {
			$rootScope.mcMessages = service.mcMessages;
		}
		
		function notify() {
			$rootScope.$broadcast('msgCenter-notify', service.mcMessages);
		}

	}

})();
