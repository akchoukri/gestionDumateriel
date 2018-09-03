(function() {

	var app = angular.module("myApp");
	app.controller("empruntclientCtrl", empruntclientCtrl);

	//controller pour mis a jour client
	function empruntclientCtrl($scope, clientDataService,EmpruntDatasrv, $location, $state) {
		$scope.clients = null;
		$scope.client = null;
		$scope.empruntsRetard = null;
		$scope.empruntsNnRetourne = null;
		$scope.emprunt= null;
		$scope.slctEmprunt= null;
				// recuperer list client 
				clientDataService.allClients().then(function(data) {
					$scope.clients = data;
					$scope.client = $scope.clients[0]
				//	console.log($scope.client)
					$scope.getEmp($scope.client.idClient);
				})
				
				//les emprunts en retard	
			 $scope.getEmp = function(id) {
					
					EmpruntDatasrv.getEmpruntNnRetourne(id).then(function(resp) {
						$scope.empruntsNnRetourne  = resp.data;
						console.log(resp.data )
					})
         }
				// on changeant client
			 $scope.hasChanged = function(client) {
				 $scope.getEmp(client.idClient);
				 //console.log(client)
		         }
				//les emprunts en retard
				EmpruntDatasrv.getEmpruntRetard().then(function(resp) {
					$scope.empruntsRetard  = resp.data;
					//console.log($scope.empruntsRetard )
				})
				//  visualiser emprunt
			 $scope.empruntSelected = function(emprunt) {
				 $scope.slctEmprunt = emprunt;
		         }
			 
				//  tester si emprunt est en retars
			 $scope.ifRetard = function(emprunt) {
				 if((emprunt != null)&&(emprunt.dateRetourPrevu != null)){
						var dateRprevu = new Date(emprunt.dateRetourPrevu)
						var dateNow = new Date();
						if (dateRprevu.getTime() < dateNow.getTime())
							return true;
							
				 }

					return false;
		         }
				//  add retour prêt 
			 $scope.addRetourPret = function() {
				 $scope.slctEmprunt.dateRetour = new Date();
				EmpruntDatasrv.updateEmprunt($scope.slctEmprunt,$scope.client)
					
				$scope.slctEmprunt=null;
				$state.reload();
		         }
			 
				//  anuller ajout de retours prêt  emprunt
			 $scope.annulerPret = function(emprunt) {
				 $scope.slctEmprunt = null;
		         }
			 
		//pour visualiser status d'emprunt
		$scope.getStatus = function(emprunt) {

			var dateRprevu = new Date(emprunt.dateRetourPrevu)
			var dateRetour = new Date(emprunt.dateRetour)
			var dateNow = new Date();

			var jour = 24 * 60 * 60 * 1000;

			var retard = (dateRprevu.getTime() - dateNow.getTime()) / (jour)
			var retourneAvecRetard = (dateRprevu.getTime() - dateRetour
					.getTime())
					/ (jour)
			var ssRetard = Math.abs((dateRetour.getTime() - dateRprevu
					.getTime())
					/ (jour))

			if (emprunt.dateRetour != null) {
				// materiel retourné sans retard
				if (dateRprevu.getTime() >= dateRetour.getTime()) {
					if ((ssRetard <= 180) && (ssRetard > 0))
						return "gris";//avant 3 mois
					else if (ssRetard == 0)
						return "bleu"; // meme journé
				} else {// materiel retourné avec retard

					if (retourneAvecRetard >= -15)// retard 15 jours
						return "orange";
					else
						return "rouge";
				}
			} else {
				// materiel non  retourné sans retard
				if (dateRprevu.getTime() >= dateNow.getTime()) {
					if (Math.abs(retard) >= 0) {
						return "bleu";
					}
				} else {
					// materiel non  retourné avec retard
					if (retard >= -15) //
						return "orange";
					else
						return "rouge";

				}

			}

		}
	}

})();