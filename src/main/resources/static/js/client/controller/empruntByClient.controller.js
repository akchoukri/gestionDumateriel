(function() {

	var app = angular.module("myApp");
	app.controller("empruntclientCtrl", empruntclientCtrl);

	//controller pour mis a jour client
	function empruntclientCtrl($scope, clientDataService, 
			$location, $state) {
		$scope.clients = null;
		$scope.client = null;

		$scope.data = {
			    availableOptions: [
			      {id: '1', name: 'Option A'},
			      {id: '2', name: 'Option B'},
			      {id: '3', name: 'Option C'}
			    ],
			    selectedOption: {id: '3', name: 'Option C'} //This sets the default value of the select in the ui
			    };
		
		// recuperer list client 
		clientDataService.allClients().then(
				function(data) {
					$scope.clients = data;
					console.log($scope.clients)
				})



	}

})();