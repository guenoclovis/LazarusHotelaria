(function() {
    'use strict';

    angular.module('app', [
        /*
         * Angular modules
         */
         'appLocalized'
    ]);
})();

(function() {
    'use strict';
    angular
        .module('appLocalized',[
          'ngAnimate',
          'ui.bootstrap'
    ]);
    
    angular
        .module('appLocalized')
        .controller('LocalizedController', LocalizedController);

    LocalizedController.$inject = [];

    function LocalizedController() {
        /*jshint validthis: true */
        var vm = this;


        vm.events = [
          {
            startsAt: new Date(),
            endsAt: new Date(),
            startOpen: false
          }
        ];

        vm.toggle = toggle;

        init();

        function init(){
           
        }

        function toggle($event, field, event) {
            $event.preventDefault();
            $event.stopPropagation();
            event[field] = !event[field];
        }
        
    }
})();