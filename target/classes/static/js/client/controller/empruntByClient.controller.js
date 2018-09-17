(function() {

	var app = angular.module("myApp");
	app.controller("empruntclientCtrl", empruntclientCtrl);

	//controller pour mis a jour client
	function empruntclientCtrl($scope, clientDataService, EmpruntDatasrv,
			$location, $state, $interval, $rootScope) {
		$scope.clients = null;
		$scope.client = null;
		$scope.empruntsRetard = null;
		$scope.empruntsNnRetourne = null;
		$scope.emprunt = null;
		$scope.slctEmprunt = null;
		$scope.count = 0;
		$scope.etaMaterials = ["bonne etat","en panne", "endommagé"];

		// recuperer list client 
		clientDataService.allClients().then(function(data) {
			$scope.clients = data;
			$scope.client = $scope.clients[0]
			//	console.log($scope.client)
			$scope.getEmp($scope.client.idClient);
		})

		//les emprunts nn retourné
		$scope.getEmp = function(id) {

			EmpruntDatasrv.getEmpruntNnRetourne(id).then(function(resp) {
				$scope.empruntsNnRetourne = resp.data;
				$scope.slctEmprunt = $scope.empruntsNnRetourne[0]
				//console.log(resp.data )
			})
		}
		// on changeant client
		$scope.clientHasChanged = function(client) {
			$scope.getEmp(client.idClient);
			// console.log(client)
		}
		//les emprunts en retard
		EmpruntDatasrv.getEmpruntRetard().then(function(resp) {
			$scope.empruntsRetard = resp.data;

			//console.log($scope.empruntsRetard[0])
		})
		//  visualiser emprunt
		$scope.empruntSelected = function(emprunt) {
			$scope.slctEmprunt = emprunt;
		}

		//  tester si emprunt est en retars
		$scope.ifRetard = function(emprunt) {
			if ((emprunt != null) && (emprunt.dateRetourPrevu != null)) {
				var dateRprevu = new Date(emprunt.dateRetourPrevu)
				var dateNow = new Date();
				dateNow.setHours(0);
				dateNow.setMinutes(0);
				dateNow.setSeconds(0);
				dateNow.setMilliseconds(0);
				if (dateRprevu.getTime() < dateNow.getTime())
					return true;

			}

			return false;
		}
		//  add retour prêt 
		$scope.addRetourPret = function() {
			//la date de retour
			$scope.slctEmprunt.dateRetour = new Date();
			$scope.slctEmprunt.dateRetour.setHours(0);
			$scope.slctEmprunt.dateRetour.setMinutes(0);
			$scope.slctEmprunt.dateRetour.setSeconds(0);
			$scope.slctEmprunt.dateRetour.setMilliseconds(0);
			//mettre a jour emprunt
			$scope.slctEmprunt.materiels.forEach(function(material) {
				material.disponible = true;
			});
			EmpruntDatasrv.updateEmprunt($scope.slctEmprunt, $scope.client)
			var index = $scope.empruntsNnRetourne.indexOf($scope.slctEmprunt);

			$scope.empruntsNnRetourne.splice(index, 1);

			$scope.slctEmprunt = null;
			$rootScope.msgClient = "Enregistrements du retour de prêt pour le client "
					+ $scope.client.nomClient + " est effectuée  avec succès"
			stop = $interval(function() {

				$scope.count = $scope.count + 1;
				//console.log($scope.count)
				if ($scope.count == 5)
					$scope.stopmsg();
			}, 500);
			//$state.reload();
		}

		//  anuller ajout de retours prêt  emprunt
		$scope.annulerPret = function(emprunt) {
			$scope.slctEmprunt = null;
		}

		//  editer etat de material
		$scope.editer = function(material) {
			material.modeEdit = true;
			$scope.selectEtatMateriel = material.etatMateriel;
		}
		//  confirmer edit etat de material
		$scope.confirmerEditer = function(material) {
			material.modeEdit = false;
			material.etatMateriel = $scope.selectEtatMateriel;

		}
		//  annuler l'edit etat de material
		$scope.annulerEditer = function(material) {
			material.modeEdit = false;

		}
		$scope.etatHasChanged = function(selectEtatMateriel) {
			$scope.selectEtatMateriel = selectEtatMateriel;

		}
		// annuler affichage de msg
		$scope.stopmsg = function() {
			if (angular.isDefined(stop)) {
				$interval.cancel(stop);
				stop = undefined;
				$rootScope.msgClient = null;
				$scope.count = 0;
			}
		};

		//pour visualiser status d'emprunt
		$scope.getStatus = function(emprunt) {

			var dateRprevu = new Date(emprunt.dateRetourPrevu)
			var dateRetour = new Date(emprunt.dateRetour)
			var dateNow = new Date();
			dateNow.setHours(0);
			dateNow.setMinutes(0);
			dateNow.setSeconds(0);
			dateNow.setMilliseconds(0);
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