(function() {

    var app = angular.module("BPLPredictions");

    var MainController = function($scope, $http) {
        $scope.message = "BPL Predictions";
		
        var onError = function(reason) {
			$scope.error = reason;
		}

		var onMatches = function(response) {
			$scope.matches = response.data;
		};
  
		$http.get("/matches").then(onMatches, onError);
		
		$scope.editMatch = function(match) {
			confirm("Edit this match?");
		};
    };

    app.controller("MainController", MainController);

}());