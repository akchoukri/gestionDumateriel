(function() {

	var app = angular.module("myApp");
	app.controller("listClientContl", clientCtrl);

	//controller pour client
	function clientCtrl($scope, clientDataService, $state, $rootScope,
			$interval) {

		$scope.pageClient = {};
		$scope.motCle = "";
		$scope.currentPage = 0;
		$scope.size = 3;
		$scope.totalePages = 0;
		$scope.pages = [];
		$scope.count = 0;
		//fonction qui permet rcuperer les client au demarage de la vue 
		$scope.init = function() {

			//faire appel au service client pour recuperer les clients
			clientDataService.getClients($scope.motCle, $scope.currentPage,
					$scope.size).then(function(data) {

				$scope.pageClient = data;
				$scope.totalePages = data.totalPages;
				$scope.pages = new Array(data.totalPages);

			});
		}

		//fonction pour chercher un cllient avec l'evenemnt key up
		$scope.keyUp = function(event) {

			$scope.motcle = event.target.value;
			$scope.init();
		}

		//fonction permet d'incrementer les numero de la page
		$scope.gotonext = function() {

			if ($scope.currentPage == $scope.totalePages - 1) {

				var d = document.getElementById("linknext");
				d.className = "disabled";

			} else
				$scope.currentPage = $scope.currentPage + 1;
			$scope.init();
		}

		//désincrémenter  les numero de la page
		$scope.gotoprevious = function() {

			if ($scope.currentPage != 0)
				$scope.currentPage = $scope.currentPage - 1;

			$scope.init();
		}

		//acceder a une page
		$scope.gotopage = function(p) {

			$scope.currentPage = p;
			$scope.init();
		}

		$scope.clientAsupp = function(client) {

			$scope.clientSupp = client;
		}
		// suppression d'un client
		$scope.supprimerClient = function(item) {

			//var item = $scope.pageClient.content[c];
			item.archive = true;

			clientDataService.updateClient(item).then(
					function(data) {

						$rootScope.msgClient = "le client " + data.nomClient
								+ " est supprimé avec succès"
						$scope.clientSupp = null;
						$state.reload();
						stop = $interval(function() {
							$scope.count = $scope.count + 1;

							if ($scope.count == 5)
								$scope.stopmsg();
						}, 500);

					});

		}

		$scope.stopmsg = function() {
			if (angular.isDefined(stop)) {
				$interval.cancel(stop);
				stop = undefined;
				$rootScope.msgClient = null;
			}
		};

		$scope.confirmationDialogConfig = {};

		$scope.confirmationDialog = function() {
			$scope.confirmationDialogConfig = {
				title : "Caution!!!",
				message : "Are you sure you want to delete?",
				buttons : [ {
					label : "Delete",
					action : "delete"
				} ]
			};
			$scope.showDialog(true);
		};

		$scope.showDialog = function(flag) {
			jQuery("#confirmation-dialog .modal").modal(flag ? 'show' : 'hide');
		};
		$scope.executeDialogAction = function(action) {
			if (typeof $scope[action] === "function") {
				$scope[action]();
			}
		};
	}

})();