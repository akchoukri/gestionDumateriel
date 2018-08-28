(function () {


var app = angular.module("myApp");
app.controller("clientNewCtrl",newclientCtrl);
//controller pour ajouter un client
function newclientCtrl($scope,$http,clientDataService,$location,$state) {
	
    $scope.client = null;
    $scope.ph_numbr = /^\+?\d{10}$/;
    $scope.eml_add = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/;

    //fonction pour ajouter un client
    $scope.save= function () {
//console.log($scope.client)
    clientDataService.newClient( $scope.client);
	 
    	 $scope.reset();
    	 $state.go("clients", {}, {reload: true});


    };

    //reset de la formulaire
    $scope.reset = function () {
        $scope.client = null;
    }


}

})();