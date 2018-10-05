app.controller("MaterielController", function($rootScope, $scope, $http,
			MaterielDatasrv, $window, $state,$timeout, $interval) {

		$scope.pagemateriels = null;
		$scope.motCle = "";
		$scope.pageCourante = 0;
		$scope.size = 4;
		$scope.pages = [];
		$scope.page = [];
		$scope.err = 0;
    // $rootScope.mode = 0;
		$scope.cat = null;
    	$scope.modee = 0;
		$scope.mode = 0;
		$scope.count = 0;
		$scope.options = [];
		$scope.motCle = "";
		var i;
		var values = [];
	$scope.tt = [true,false];
	$scope.etatM = ["bonne etat","en panne", "endommagé"]
		//	
		$scope.id = {};
    	$scope.activetab=1;
    	$scope.designations = [];
    	$scope.ModeAjoutdesig = false;
    	$scope.ModeAjoutCat = false;
    	$scope.categorie = {};
    	$scope.pageCourante=0;
// $state.go('editProduct', {
// id : 2
// //selectedItem and id is defined
// });
    	//recuperer les designations 
    	MaterielDatasrv.getDesignation().then(function(response) {
			$scope.designations = response.data;
		}, function myerror(err) {
			console.log(err);
		});
    	$scope.addDign = function () {
    		$scope.ModeAjoutdesig = false;
    		$scope.designations.push($scope.materiel.designation);
    		
    	}
    	$scope.reset = function () {
    		$scope.materiel = {}
    		
    	}
    	$scope.addCat = function () {
    		MaterielDatasrv.addCat($scope.materiel.categorie).then(function(response) {
    			$scope.ModeAjoutdesig = false;
    			$scope.cat.push(response.data);
    			$scope.categorie = {};
    			$scope.msg = "categorie "+categorie.nomCategorie+" ajouter avec succes";
    			stop = $interval(function() {

					$scope.count = $scope.count + 1;
					
					if ($scope.count == 5)
						$scope.stopmsg();
				}, 500);

    		}, function myerror(err) {
    			$scope.msg = err.data.message;
    			stop = $interval(function() {

					$scope.count = $scope.count + 1;
					
					if ($scope.count == 5)
						$scope.stopmsg();
				}, 500);
    		
    		});
    		
    	}
		$scope.editAccount = function () {
			$scope.modee=2;
           
		}
		


		// rechercher par mot clé et pagination
		$scope.chercherMateriels = function() {
			MaterielDatasrv.searchM($scope.motCle, $scope.pageCourante,
					$scope.size).then(function(response) {
				$scope.pagemateriels = response;
				$scope.pages = new Array(response.data.totalPages);
				
			}, function myerror(err) {
				console.log(err);
			});
		}

    $scope.afficher = function(id){

        $scope.modee = 1;
        $rootScope.materiell = {};
        $scope.id = id;

        MaterielDatasrv.searchMid($scope.id).then(function(response) {

            
            $rootScope.materiell = response.data;
            $scope.message = response.data;
            


        }, function myerror(err) {
            console.log(err);
        });





    }
		// changer les pages
		$scope.goToPage = function(p) {
			$scope.pageCourante = p;
			$scope.chercherMateriels();
		};

		// supprimer un materiel
		$scope.supprimer = function(id) {
			if ( $window.confirm("vous voulez vraiment supprimer ce materiel?")){
            $scope.modee = 0;
			MaterielDatasrv.deleteM(id).then(function(response) {
				restart();
			}, function myerror(err) {
				console.log(err);
			});
            }
		}
		// fonction restart pour actualiser la page
		var restart = function() {
			
			MaterielDatasrv.refresh($scope.motCle, $scope.pageCourante,
					$scope.size).then(function(response) {
				$scope.pagemateriels = response;
				$scope.pages = new Array(response.data.totalPages);
			});
		};

		// redirection vers la page d'un new produit
		$scope.newProduct = function() {
			$rootScope.materiel = {};
			$state.go("newProduct");
		}

		// charger les nom des catégorie
		MaterielDatasrv.searchC().then(function(response) {
			$scope.cat = response.data;
		}, function myerror(err) {
			console.log(err);
		});



		// ajouter un materiel
    $scope.materiel={};
		$scope.ajouterMateriels = function() {
			MaterielDatasrv.add($scope.materiel).then(function(response) {
                $scope.materiel = response.data;
				console.log($scope.materiel);
				$scope.msg = "materiel  "+$scope.materiel.designation+" ajouter avec succes";
    			stop = $interval(function() {

					$scope.count = $scope.count + 1;
					
					if ($scope.count == 5)
						$scope.stopmsg();
				}, 500);
    			$scope.materiel = {};
			}, function myerror(err) {
				$scope.msg = err.data.message;
    			stop = $interval(function() {

					$scope.count = $scope.count + 1;
					
					if ($scope.count == 5)
						$scope.stopmsg();
				}, 500);
			});

		};
		
		// edit materiel
		$scope.modifierMateriel = function(materiell){
			$rootScope.materiell = materiell;
            MaterielDatasrv.edit($rootScope.materiell,$rootScope.materiell.idMateriel).then(function(response) {
                $rootScope.materiell = response.data;
                $scope.modee = 1; 
                $scope.msg = "materiel  "+$rootScope.materiell.designation+" modifier avec succes";
    			stop = $interval(function() {

					$scope.count = $scope.count + 1;
					
					if ($scope.count == 5)
						$scope.stopmsg();
				}, 500);
			}, function myerror(err) {
				$scope.msg = err.data.message;
    			stop = $interval(function() {

					$scope.count = $scope.count + 1;
					
					if ($scope.count == 5)
						$scope.stopmsg();
				}, 500);
            }, function myerror(err) {
            	$scope.msg = err.data.message;
    			stop = $interval(function() {

					$scope.count = $scope.count + 1;
					
					if ($scope.count == 5)
						$scope.stopmsg();
				}, 500);
            });
		};

/*else {
        MaterielDatasrv.edit($scope.materiell,$scope.materiell.idMateriel,).then(function(response) {
            $scope.materiell = response;
            console.log($scope.materiel);
            $scope.modee = 1;
            alert(response);
        }, function myerror(err) {
            console.log(err);
        });
    }*/

		$scope.newAdd = function() {
			$scope.mode = 0;
		}

    	/*$scope.nav = function(){
        navItems: ['home', 'add'],
        selectedIndex: 0,
        navClick: function ($index) {
            $scope.nav.selectedIndex = $index;
        }
    };*/
//		nv********************************
		
		
//		.controller('MaterielController', function ($scope) {
//			  $scope.nav = {
//			    navItems: ['home', 'add'],
//			    selectedIndex: 0,
//			    navClick: function ($index) {
//			      $scope.nav.selectedIndex = $index;
//			    }
//			  };
//			})
//		
//		function ($scope, ContactService){
//			  $scope.contacts = ContactService.getContacts();
//
//			  $scope.removeContact = function (item) {
//			    var index = $scope.contacts.indexOf(item);
//			    $scope.contacts.splice(index, 1);
//			    $scope.removed = 'Contact successfully removed.';
//			  };
//
//			}
//		function ($scope, $routeParams){
//			  var index = $routeParams.contact_index;
//			  $scope.currentContact = $scope.contacts[index];
//			}
//		function ($scope, $location) {
//			  //needed to show the correct button on the contact form
//			  $scope.path = $location.path();
//
//			  $scope.addContact = function () {
//			    var contact = $scope.currentContact;
//			    contact.id = $scope.contacts.length;
//			    $scope.contacts.push(contact);
//			  };
//
//			}
//		function ($scope, $routeParams){
//			  $scope.index = $routeParams.contact_index;
//			  $scope.currentContact = $scope.contacts[$scope.index];
//			}
//		


    // new Vue({
    //     el: '#tabs',
    //     data: { activetab: 1 },
    // });
		$scope.keyUp = function(event) {

			$scope.motcle = event.target.value;
			$scope.chercherMateriels();
		}
		//fonction permet d'incrementer les numero de la page
		$scope.gotonext = function() {

			if ($scope.currentPage == $scope.totalePages - 1) {

				var d = document.getElementById("linknext");
				d.className = "disabled";

			} else
				$scope.pageCourante = $scope.pageCourante + 1;
			$scope.chercherMateriels();
		}

		//désincrémenter  les numero de la page
		$scope.gotoprevious = function() {

			if ($scope.pageCourante != 0)
				$scope.pageCourante = $scope.pageCourante - 1;

			$scope.chercherMateriels();
		}

		//acceder a une page
		$scope.gotopage = function(p) {

			$scope.pageCourante = p;
			$scope.chercherMateriels();
		}
		$scope.stopmsg = function() {
			if (angular.isDefined(stop)) {
				$interval.cancel(stop);
				stop = undefined;
				$scope.msg = null;
				$scope.count = 0;
			}
		};
	});


