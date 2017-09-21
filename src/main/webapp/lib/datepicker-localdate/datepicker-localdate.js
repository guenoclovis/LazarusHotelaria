(function() {
    'use strict';
    
    angular
    .module('datepicker-localdate',[]);
    
    angular
    .module('datepicker-localdate')
    .directive('datepickerLocaldate', DatepickerLocaldate);

    /* @ngInject */
    function DatepickerLocaldate($parse, $filter) {    

        var directive = {
            restrict : 'A',
            require : [ 'ngModel' ],
            link : link
        };
        return directive;
        
        
        function link(scope, element, attr, ctrls) {
            var ngModelController = ctrls[0];
            // called with a JavaScript Date object when picked from the
            // datepicker
            ngModelController.$parsers.push(function(viewValue) {
                if (!viewValue) {
                    return undefined;
                }
                // undo the timezone adjustment we did during the formatting
                //viewValue.setMinutes(viewValue.getMinutes() - viewValue.getTimezoneOffset());
                // we just want a local date in ISO format
                return $filter('date')(viewValue, "dd/MM/yyyy");
            });
            // called with a 'yyyy-mm-dd' string to format
            ngModelController.$formatters.push(function(modelValue) {
                if (!modelValue) {
                    return undefined;
                }
                // date constructor will apply timezone deviations from UTC
                // (i.e. if locale is behind UTC 'dt' will be one day behind)
                var st =  modelValue.split('/');
                var dt = new Date(st[2],st[1]-1,st[0]);
                // 'undo' the timezone offset again (so we end up on the
                // original date again)
                //dt.setMinutes(dt.getMinutes() + dt.getTimezoneOffset());
                return dt;
            });
        }
    }

})();
