(function() {

	var app = angular.module("myApp");
	app.controller("clientNewCtrl", newclientCtrl);
	//controller pour ajouter un client
	function newclientCtrl($scope, $http, clientDataService, $location, $state,
			$timeout, $interval, $rootScope) {

		$scope.client = null;
		$scope.ph_numbr = /^\+?\d{10}$/;
		$scope.eml_add = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/;
		$scope.count = 0;
		//fonction pour ajouter un client
		$scope.save = function() {

			//console.log($scope.client)
			clientDataService.newClient($scope.client).then(
					function(data) {

						$state.go("clients", {}, {
							reload : true
						});
						$rootScope.msgClient = "le client " + data.nomClient
								+ " est ajoutée avec succès"
						stop = $interval(function() {

							$scope.count = $scope.count + 1;
							
							if ($scope.count == 5)
								$scope.stopmsg();
						}, 500);

					});
			//$rootScope.pageClient.content.push($scope.client);	
			$scope.reset();

		};

		//reset de la formulaire
		$scope.reset = function() {
			$scope.client = null;
		}
		$scope.stopmsg = function() {
			if (angular.isDefined(stop)) {
				$interval.cancel(stop);
				stop = undefined;
				$rootScope.msgClient = null;
			}
		};
	}

})();