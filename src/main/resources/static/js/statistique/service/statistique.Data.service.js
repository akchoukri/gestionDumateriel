(function () {


var app = angular.module("myApp");

// service pour les statistique
app.service("statiqDataService",function ($http) {
	 // les  mois 
    this.getLastMonths = function(){

    var promise1    =   $http({
        method : "GET",
        url : "http://localhost:8080/mois"
    });


    var promise2 = promise1.then(function mySuccess(response) {

        return response.data;

    }, function myError(response) {

    });

    return promise2;
    };
	 // recuperer dernier  operation du mois 
    this.getempruntR = function(){

    var promise1    =   $http({
        method : "GET",
        url : "http://localhost:8080/nbrEmprun"
    });


    var promise2 = promise1.then(function mySuccess(response) {

        return response.data;

    }, function myError(response) {

    });

    return promise2;
    };
    
	 // les nv client les 3 mois derniers 
    this.getNvClient = function(){

    var promise1    =   $http({
        method : "GET",
        url : "http://localhost:8080/nvClient"
    });


    var promise2 = promise1.then(function mySuccess(response) {

        return response.data;

    }, function myError(response) {

    });

    return promise2;
    };
    
 // recuperer les emprunt d'un cleint 3 mois derrr 
    this.getEmprByclient = function(){

    var promise1    =   $http({
        method : "GET",
        url : "http://localhost:8080/emp"
    });


    var promise2 = promise1.then(function mySuccess(response) {

        return response.data;

    }, function myError(response) {

    });

    return promise2;
    };
    
    // recuperer les nouveau emprunt par mois 
    this.getNvEmp = function(){

    var promise1    =   $http({
        method : "GET",
        url : "http://localhost:8080/nvEmp"
    });


    var promise2 = promise1.then(function mySuccess(response) {

        return response.data;

    }, function myError(response) {

    });

    return promise2;
    };
    
    // recuperer les nouveau Materiel par mois  
    this.getNvMat = function(){

    var promise1    =   $http({
        method : "GET",
        url : "http://localhost:8080/nvMat"
    });


    var promise2 = promise1.then(function mySuccess(response) {

        return response.data;

    }, function myError(response) {

    });

    return promise2;
    };
    
    // comapraison l'etat des materiels  
    this.getEtatMat = function(){

    var promise1    =   $http({
        method : "GET",
        url : "http://localhost:8080/etatMat"
    });


    var promise2 = promise1.then(function mySuccess(response) {

        return response.data;

    }, function myError(response) {

    });

    return promise2;
    };
    
    //
    
    
})
//**********

})();