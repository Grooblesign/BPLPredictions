(function() {

    var app = angular.module("BPLPredictions");

    var TeamController = function($scope, $http, $routeParams) {
		
		String.prototype.toProperCase = function () {
			return this.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
		};
		
		$scope.teamName = $routeParams.teamname.toProperCase();		
		
        var onError = function(reason) {
			$scope.error = reason;
		}

		var onMatches = function(response) {
			$scope.matches = response.data;
		};
  
		$http.get("/matches").then(onMatches, onError);
		
		$scope.editMatch = function(match) {
			if (confirm("Edit this match?")) {
				console.log("Posting...");
				$http({
					url: '/match/' + match.id,
					method: 'POST',
					headers: { 'Content-Type': 'application/json' },
					data: {score: "1-0", prediction: "2-0"}
				});	
				match.score = "1 - 0";
			}
		};
    };

    app.controller("TeamController", TeamController);

}());