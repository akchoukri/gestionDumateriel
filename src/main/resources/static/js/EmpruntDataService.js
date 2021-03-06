(function(){
	

	var app = angular.module('myApp');
	app.service("EmpruntDatasrv", function($http) {
		// cherContacts: liste de tout
		this.getEmprunt = function() {
			var promise1 = $http({
				method : 'GET',
				url : "http://localhost:8080/chercherEmprunts"
			});
			var promise2 = promise1.then(function(response) {
				return response.data;
			}, function(err) {
				console.log(err);
			});
			return promise2;
		}
		// liste des clients
		this.getClients = function() {
			var promise1 = $http({
				method : 'GET',
				url : "http://localhost:8080/chercherClients"
			});
			var promise2 = promise1.then(function(response) {
				return response;
			}, function(err) {
				console.log(err);

			});
			return promise2;
		}
		// liste des materiels
		this.getMateriels = function() {
			var promise1 = $http({
				method : 'GET',
				url : "http://localhost:8080/chercherMateriels"
			});
			var promise2 = promise1.then(function(response) {
				return response;
			}, function(err) {
				console.log(err);
			});
			return promise2;
		}
		 	 // chercher un client par son nom
		this.getClient = function(name) {
			var promise1 = $http({
				method : 'GET',
				url : "http://localhost:8080/client?mc=" + name
			});
			var promise2 = promise1.then(function(response) {
				return response.data;
			}, function(err) {
				console.log(err);

			});
			return promise2;
		}
		 	 // chercher un materiel par sa designation
		this.getMateriel = function(designation) {
			var promise1 = $http({
				method : 'GET',
				url : "http://localhost:8080/materiel?mc=" + designation
			});
			var promise2 = promise1.then(function(response) {
				return response.data;
			}, function(err) {
				console.log(err);

			});
			return promise2;
		}
		               // ajouter un emprunt
		this.newEmprunt = function(id, emprunt, materiels) {
			return $http.post(
					"http://localhost:8080/client/" + id + "/emprunts",
					emprunt, materiels).then(function(response) {
				console.log("hello");
				console.log(response.data);
			});
		}
			  // update un materiel
		this.updateMateriel = function(materiel, id) {
			console.log(materiel);
			return $http.put("http://localhost:8080/materiel/" + id, materiel)
					.then(function(response) {
						console.log(response);
					});
		}
      
   
    
					//les emprunts en retard
					this.getEmpruntRetard=function(){
						var promise1=$http({
							method: 'GET',
							url: "http://localhost:8080/empruntRetard"
							});
						var promise2=promise1.then(function(response){
							return response;
						},function(err){
							console.log(err);
		
						});
					 return promise2;
					}
					
					//les emprunts non  retourné
					this.getEmpruntNnRetourne=function(id){
						var promise1=$http({
							method: 'GET',
							url: "http://localhost:8080//client/"+id+"/nnretourne"
							});
						var promise2=promise1.then(function(response){
							return response;
						},function(err){
							console.log(err);
		
						});
					 return promise2;
					}
					
					// mise a jour emprunt
					this.updateEmprunt = function(emprunt,client){
		
		
						$http.put("http://localhost:8080/client/"+client.idClient+"/emprunts",emprunt)
						.then(function mySuccess(response) {
		
						}, function myError(response) {
						   
		
						});
					};
					
		
					// mise a jour materiels
					this.updateMateriels = function(materiels){
						$http.put("http://localhost:8080/materiels",materiels)
						.then(function mySuccess(response) {
		
						}, function myError(response) {
						   
		
						});
					};
	}); 
})();