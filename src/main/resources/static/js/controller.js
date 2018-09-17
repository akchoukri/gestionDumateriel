app.controller("MaterielController", function($rootScope, $scope, $http,
			MaterielDatasrv, $window, $state) {

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
		$scope.options = [];
		var i;
		var values = [];
	$scope.tt = [true,false];
	$scope.etatM = ["bonne etat","en panne", "endommagé"]
		//	
		$scope.id = {};
    	$scope.activetab=1;


// $state.go('editProduct', {
// id : 2
// //selectedItem and id is defined
// });
		$scope.editAccount = function () {
			$scope.modee=2;
            /*console.log("AAAAAAAAAAAAAllllllllllllll");

            $rootScope.message='ok ok';
			console.log(m);
			$rootScope.materiel = m;
		    console.log($rootScope.materiel);
		    $state.go('editProduct',{'idd':$rootScope.materiel.idMateriel});
		    $rootScope.materiel.etatMateriel = m.etatMateriel;*/
		}
		
// $scope.modifier = function(p) {
// $scope.materiel = {};
// // $scope.id = p;
// // console.log($scope.id);
// // console.log($scope.mater.reference);
// // console.log(p.idMateriel);
// // console.log($scope.mater.idMateriel);
// var stateVar="editProduct";
// $scope.message = 'ooooooo';
//
// $state.go(stateVar,{id:2});
// // MaterielDatasrv $scope.message.push($scope.id);
//			
// console.log('hada howa p=>'+p);
/*
MaterielDatasrv.searchMid($scope.id).then(function(response) {
console.log(response.data);
console.log(response);
$rootScope.materiel = response.data;
$scope.message = response.data;
console.log($rootScope.materiel.reference);
console.log("*****");

// for (i = 0; i < $scope.cat.length; i++) {
// values.push(response.data[i]['nomCategorie']);
// $scope.options = values;
// }

}, function myerror(err) {
console.log(err);
});
*/
// // console.log($scope.materiel);
// console.log("bbbaaaaaaaaaaaaaaaaaa*****");
// }

		// rechercher par mot clé et pagination
		$scope.chercherMateriels = function() {
			MaterielDatasrv.searchM($scope.motCle, $scope.pageCourante,
					$scope.size).then(function(response) {
				$scope.pagemateriels = response;
				$scope.pages = new Array(response.data.totalPages);
				console.log(response);
				console.log($scope.pages);
			}, function myerror(err) {
				console.log(err);
			});
		}

    $scope.afficher = function(id){
        $scope.modee = 1;
			$rootScope.materiell = {};
        $scope.id = id;
		console.log($scope.id);

        MaterielDatasrv.searchMid($scope.id).then(function(response) {

            console.log(response.data);
            console.log(response);
            $rootScope.materiell = response.data;
            $scope.message = response.data;
            console.log($rootScope.materiell.reference);
            console.log("1111111111111111111");

			// for (i = 0; i < $scope.cat.length; i++) {
			// values.push(response.data[i]['nomCategorie']);
			// $scope.options = values;
			// }

        }, function myerror(err) {
            console.log(err);
        });



        /*MaterielDatasrv.searchM($scope.motCle, $scope.pageCourante, $scope.size).then(function(response) {
            $scope.pagemateriels = response;
            $scope.pages = new Array(response.data.totalPages);
            console.log(response);
            console.log($scope.pages);
        }, function myerror(err) {
            console.log(err);
        });*/

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
				console.log(id);
				restart();
			}, function myerror(err) {
				console.log(err);
			});
            }
		}
		// fonction restart pour actualiser la page
		var restart = function() {
			console.log("11");
			MaterielDatasrv.refresh($scope.motCle, $scope.pageCourante,
					$scope.size).then(function(response) {
				console.log("22");
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
			console.log(response.data);
			for (i = 0; i < $scope.cat.length; i++) {
				values.push(response.data[i]['nomCategorie']);
				$scope.options = values;
				console.log($scope.options)
			}
		}, function myerror(err) {
			console.log(err);
		});

/*    $scope.ajouterMateriels = function() {
        console.log("aaaazzzz");
            MaterielDatasrv.add($scope.materiel).then(function(response) {
            	console.log("aaaa");

                console.log(response);
                $scope.materiel = response;
                console.log($scope.materiel);
                $scope.mode = 1;
                alert(response);
            }, function myerror(err) {
                console.log(err);
            });

    };*/

		// ajouter un materiel
    $scope.materiel={};
		$scope.ajouterMateriels = function() {
			MaterielDatasrv.add($scope.materiel).then(function(response) {
                $scope.materiel = response;
				console.log($scope.materiel);
                $scope.mode = 1;
				alert(response);
			}, function myerror(err) {
				console.log(err);
			});

		};
		$scope.modifierMateriel = function(materiell){
            console.log("*******************************************");
			console.log(materiell);
			console.log("E7EME7EM");
			$scope.materiell = materiell;
            MaterielDatasrv.edit($rootScope.materiell,$rootScope.materiell.idMateriel).then(function(response) {
                console.log("777888");
                $rootScope.materiell = response;
                console.log($rootScope.materiell);
                $scope.modee = 1;
                alert(response);
            }, function myerror(err) {
                console.log(err);
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
	});


