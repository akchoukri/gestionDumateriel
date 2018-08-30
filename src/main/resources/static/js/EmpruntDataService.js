(function(){
	
	var app = angular.module('myApp');
	
	app.service("EmpruntDatasrv",function($http){
		
		//cherContacts: liste de tout
		this.getEmprunt=function(){
	        var promise1=$http({
	            method: 'GET',
	            url: "http://localhost:8080/chercherEmprunts"
	            });
	        var promise2=promise1.then(function(response){
	        	return response.data;
	        },function(err){
	            console.log(err);
	        });
	     return promise2;
	    }
		//liste des clients
		this.getClients=function(){
	        var promise1=$http({
	            method: 'GET',
	            url: "http://localhost:8080/listClients"
	            });
	        var promise2=promise1.then(function(response){
	        	return response;
	        },function(err){
	            console.log(err);

	        });
	     return promise2;
	    }
		//liste des materiels
		this.getMateriels=function(){
	        var promise1=$http({
	            method: 'GET',
	            url: "http://localhost:8080/chercherMateriels"
	            });
	        var promise2=promise1.then(function(response){
	        	return response;
	        },function(err){
	            console.log(err);
	        });
	     return promise2;
	    }
  
	 	 //chercher un client par son nom
		   this.getClient=function(name){
		        var promise1=$http({
		            method: 'GET',
		            url: "http://localhost:8080/client?mc="+name
		            });
		        var promise2=promise1.then(function(response){
		        	return response.data;
		        },function(err){
		            console.log(err);

		        });
		     return promise2;
		    }
		 	 //chercher un materiel par sa designation
		   this.getMateriel=function(designation){
		        var promise1=$http({
		            method: 'GET',
		            url: "http://localhost:8080/materiel?mc="+designation
		            });
		        var promise2=promise1.then(function(response){
		        	return response.data;
		        },function(err){
		            console.log(err);

		        });
		     return promise2;
		    }


               //ajouter un emprunt
		   this.newEmprunt=function(id,emprunt){
			   return $http.post("http://localhost:8080/client/"+id+"/emprunts",emprunt)
		       .then(function(response){
		         console.log(response.data);
		       });
		      }
      
   
    }); 
		
		
})();