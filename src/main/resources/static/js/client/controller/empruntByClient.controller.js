(function() {

	var app = angular.module("myApp");
	app.controller("empruntclientCtrl", empruntclientCtrl);

	//controller pour mis a jour client
	function empruntclientCtrl($scope, clientDataService, $location, $state) {
		$scope.clients = null;
		$scope.client = null;

		// recuperer list client 
		clientDataService.allClients().then(function(data) {
			$scope.clients = data;
			console.log($scope.clients)
		})

		//pour visualiser etat d'emprunt
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