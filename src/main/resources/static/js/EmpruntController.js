(function(){
	var app = angular.module('myApp');
	app
			.controller(
					"EmpruntController",
					function($scope, $http,EmpruntDatasrv,$window) {
						$scope.idClient = null;
						$scope.emprunt = {};
						$scope.client = {};
						$scope.name = "";
						$scope.designation = "";
						$scope.designations =[];
						$scope.materiels = [];
						$scope.materielsAemprunte = [];
						$scope.emprunt.prixTotal = 0;
						$scope.mode=0;
						$scope.quantiteNbr = /^\+?\d{1}/;
						$scope.prixNbr = /^\+?\d{1}/;
						$scope.materielsDesignation=[];
						$scope.emprunt.dateEmprunt=new Date();
						$scope.date=$scope.emprunt.dateRetourPrevu;
						$scope.dateRetour=new Date();
						$scope.msgQuantite="La quantite disponible est :";
						//valider le champs date d'emprunt et date de retour
						$scope.endDate = new Date();
						var date = $scope.endDate.getTime()-(60*60*24) * 1000
						$scope.jour = new Date(date);
						
						
						
						
						$scope.minDateRetour=$scope.emprunt.dateEmprunt;
						
						// afficher liste des clients
						EmpruntDatasrv.getClients().then(function(data) {
							$scope.client = data.data;
							$scope.options = [];
							var values = [];
							for (var i = 0; i < $scope.client.length; i++) {
								values.push(data.data[i]['nomClient']);
								$scope.options = values;
							}
						}); 

						// afficher la liste des materiels disponible
						EmpruntDatasrv
								.getMateriels()
								.then(
										function(data) {
											$scope.materiels = data.data;
											console.log(data)
											$scope.materielsDesignation
													.push($scope.materiels[0].designation);
											for (var i = 1; i < $scope.materiels.length; i++) {
												if ($scope.materielsDesignation
														.indexOf($scope.materiels[i].designation) < 0) {
													$scope.materielsDesignation
															.push($scope.materiels[i].designation);
												}
											}
										});
						// fonction pour chercher un materiel par sa designation
						$scope.chercherMateriel = function(designation) {
							$scope.materielChoisis = [];
							EmpruntDatasrv
									.getMateriel(designation)
									.then(
											function(data) {
												$scope.nbrMax = data.length;
												for (var i = 0; i < $scope.nbrMax; i++) {
													if (data[i].disponible == true) {
														$scope.materielChoisis
																.push(data[i]);
													}
												}
											}, function(err) {
												console.log(err);
											});
						}
						// ajouter un nv materiel
						$scope.ajouterMateriel = function() {
							if(Math.abs($scope.prix) >= 0){
								for (var i = 0; i < $scope.nbr; i++) {
									$scope.materielsAemprunte
											.push($scope.materielChoisis[i]);
									$scope.materielChoisis[i].disponible = false;
									EmpruntDatasrv.updateMateriel(
											$scope.materielChoisis[i],
											$scope.materielChoisis[i].idMateriel);
									$scope.nbrMax = $scope.nbrMax - $scope.nbr;
								}
								$scope.emprunt.prixTotal = $scope.emprunt.prixTotal
										+ ($scope.prix * $scope.nbr);
							}else{
//								alert("veuillez vérifier le champs prix");
								$scope.mode=1;
							}
							
						}
						// supprimer un materiel dans la liste
						$scope.removeMateriel = function(index) {
							if ($window
									.confirm("Êtes-vous sûr de vouloir supprimer cet enregistrement?")) {
								$scope.materielsAemprunte[index].disponible = true;
								EmpruntDatasrv
										.updateMateriel(
												$scope.materielsAemprunte[index],
												$scope.materielsAemprunte[index].idMateriel);
								$scope.materielsAemprunte.splice(index, 1);
								console.log($scope.materielsAemprunte);
							} else {
							}
						}

						// ajouter un nv emprunt
						$scope.ajouterEmprunt = function() {
							console.log($scope.materielsAemprunte);
							//tester si l'emprunt et vide
							if($scope.materielsAemprunte[0]==null){
								alert("Veuillez Remplir les champs pour Enregistrer l'emprunt")
							}else{
								// chercher le client par son nom
								EmpruntDatasrv
										.getClient($scope.name)
										.then(
												function(data) {
													for (var i = 0; i < data.length; i++) {
														$scope.client = data[i];
													}
													// ajouter l'Objet client a
													// l'emprunt
													$scope.emprunt.client = $scope.client;
													// ajoutet la liste des
													// materiels a l'objet emprunt
													$scope.emprunt.materiels = $scope.materielsAemprunte;
													var id = $scope.client.idClient;
													// post l'objet emprunt
													EmpruntDatasrv.newEmprunt(id,
															$scope.emprunt).then(
															function() {
																console.log(err.data.message);
															}, function(err) {
																
																alert(err.data.message);
																
															});
												}, function(err) {
													
													console.log(err.data.message);
												});
								
							
							}
//							alert("L'emprunt pour le client  :"+$scope.client[0].nomClient+" "+"est enregistrée avec succès")
							$scope.modeForm();
						}
						// fonction pour restart la page
						$scope.modeForm = function() {
							$window.location.reload();
						}
						// fonction annuler
						$scope.annuler = function() {
							console.log($scope.materielsAemprunte);
							if ($window
									.confirm("Êtes-vous sûr de vouloir annuler cet enregistrement?")) {
								for (var i = 0; i < $scope.materielsAemprunte.length; i++) {
									$scope.materielsAemprunte[i].disponible = true;
									EmpruntDatasrv
											.updateMateriel(
													$scope.materielsAemprunte[i],
													$scope.materielsAemprunte[i].idMateriel);
								}
								$window.location.reload();
							}
						}
						
						//la fonction vider
						
						$scope.deselect = function() {
							$scope.name = "";
							$scope.designation = "";
							$scope.nbr=null;
							$scope.prix=null;
							$scope.emprunt.dateEmprunt=new Date();
							$scope.emprunt.dateRetourPrevu="";
							$scope.nbrMax=null;
							$scope.msgQuantite="";
							};
						
						
					});
})();