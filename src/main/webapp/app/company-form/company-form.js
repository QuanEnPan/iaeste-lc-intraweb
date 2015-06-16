(function(){
    var app = angular.module("companyForm", [ ]);

    app.directive("companyForm", function(){
        return {
            restrict: "E",
            templateUrl: "company-form/company-form.html"
        };
    });

})();
