(function() {
    
    var app = angular.module("BPLPredictions", ["ngRoute"]);
	
    app.config(function($routeProvider) {
        $routeProvider
            .when("/", {
                templateUrl: "assets/angular/views/main.html",
                controller: "MainController"
            })
            .when("/team/:teamname", {
                templateUrl: "assets/angular/views/team.html",
                controller: "TeamController"
            })
            .otherwise({redirectTo: "/"});
    });
        
}());