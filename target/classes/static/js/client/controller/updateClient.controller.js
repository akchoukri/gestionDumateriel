(function() {

	var app = angular.module("myApp");
	app.controller("clientUpdateCtrl", updateClientCtrl);

	//controller pour mis a jour client
	function updateClientCtrl($scope, clientDataService, $stateParams,
			$location, $state, $interval, $rootScope) {

		$scope.client = null;
		$scope.ph_numbr = /^\+?\d{10}$/;
		$scope.eml_add = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/;
		// faire appel service pour recuperer un client en precisant id
		clientDataService.getClient($stateParams.id).then(
				function(data) {
					$scope.client = data;
					$scope.client.dateNaissanceClient = new Date(
							$scope.client.dateNaissanceClient);
					
				})

		//mettre a jour client
		$scope.updateClient = function() {

			clientDataService.updateClient($scope.client).then(
					function(data) {

						$state.go("clients", {}, {
							reload : true
						});
						$rootScope.msgClient = "le client " + data.nomClient
								+ " est modifié avec succès"
						stop = $interval(function() {

							$scope.count = $scope.count + 1;

							if ($scope.count == 5)
								$scope.stopmsg();
						}, 500);

					});
		};

		//annuler msg 
		$scope.stopmsg = function() {
			if (angular.isDefined(stop)) {
				$interval.cancel(stop);
				stop = undefined;
				$rootScope.msgClient = null;
			}
		};
		//annuler la mise a jour et redirection a la vue clients
		$scope.annulerUpdate = function() {
			$location.path("clients");
		}

	}

})();