(function() {
	'use strict';

	angular.module('msgcenter')
		.provider('MsgCenterProvider', MsgCenterProvider);

	function MsgCenterProvider() {
		var vm = this;
		vm.options = {};
		vm.setGlobalOptions = setGlobalOptions;
		vm.getOptions = getOptions;
		vm.$get = $get;

		//////////////////
		
		function $get() {
			return {
				setGlobalOptions : vm.setGlobalOptions,
				options : vm.options,
				getOptions : vm.getOptions
			};
		}

		function setGlobalOptions(options) {
			vm.options = options;
		}

		function getOptions(options) {
			return vm.options;
		}
	}

})();
