(function(){
    var app = angular.module("companySelect", [ ]);

    app.directive('companySelect', function(){
        return {
            restrict: 'E',
            templateUrl: 'company-select/company-select.html',
        };
    });

})();
