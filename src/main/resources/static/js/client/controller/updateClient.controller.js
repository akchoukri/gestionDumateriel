(function() {

	var app = angular.module("myApp");
	app.controller("clientUpdateCtrl", updateClientCtrl);

	//controller pour mis a jour client
	function updateClientCtrl($scope, clientDataService, $stateParams,
			$location, $state) {

		$scope.client = null;

		// faire appel service pour recuperer un client en precisant id
		clientDataService.getClient($stateParams.id).then(
				function(data) {
					$scope.client = data;
					$scope.client.dateNaissanceClient = new Date(
							$scope.client.dateNaissanceClient);
					//console.log(data)
				})

		//mettre a jour client
		$scope.updateClient = function() {
			//console.log($scope.client)
			clientDataService.updateClient($scope.client);
			$state.go("clients", {}, {
				reload : true
			});
		};

		//annuler la mise a jour et redirection a la vue clients
		$scope.annulerUpdate = function() {
			$location.path("clients");
		}

	}

})();