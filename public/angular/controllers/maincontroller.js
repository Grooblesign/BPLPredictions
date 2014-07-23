(function() {

    var app = angular.module("BPLPredictions");

    var MainController = function($scope, $http) {
        $scope.message = "BPL Predictions";
		
		var onMatches = function(response) {
			$scope.matches = response.data;
		};
  
		$http.get("/matches").then(onMatches);
    };

    app.controller("MainController", MainController);

}());