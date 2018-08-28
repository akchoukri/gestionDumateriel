


var app = angular.module("myApp");

// service pour gerer CRUD de client
app.service("clientDataService",function ($http,$location) {

	this.modeUpdate = 0;
  
    // recuperer la liste des clients
    this.getClients = function(motCle,currentPage,size){

    var promise1    =   $http({
        method : "GET",
        url : "http://localhost:8080/clients?mc="+motCle+"&page="+currentPage+"&size="+size
    });

    var promise2 = promise1.then(function mySuccess(response) {

        return response.data;

    }, function myError(response) {

    });

    return promise2;
    };

    // ajouter un nouveau Client
    this.newClient = function(client){


        $http.post("http://localhost:8080/clients/add",client)
            .then(function mySuccess(response) {

            }, function myError(response) {
               

            });
    };

    // mise a jour d'un client
    this.updateClient = function(client){


        $http.put("http://localhost:8080/clients/"+client.idClient,client)
        .then(function mySuccess(response) {

        }, function myError(response) {
           

        });
    };

    // supprimer un client
    this.deleteClient = function(id){


        $http.delete("http://localhost:8080/clients/"+id)
            .then(function mySuccess(response) {
            }, function myError(response) {
            });
    };

    // recuperer un client

    this.getClient = function (id) {
        var promise1    =   $http({
            method : "GET",
            url: "http://localhost:8080/clients/"+id
        });

        
        var promise2 = promise1.then(function mySuccess(response) {

            return response.data;

        }, function myError(response) {
              
        });
      
        return promise2;
    };

    // recuperer la liste des clients
    this.allClients = function(){

    var promise1    =   $http({
        method : "GET",
        url : "http://localhost:8080/listClients"
    });


    var promise2 = promise1.then(function mySuccess(response) {

        return response.data;

    }, function myError(response) {

    });

    return promise2;
    };


})
