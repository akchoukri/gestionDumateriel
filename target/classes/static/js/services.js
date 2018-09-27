
	app.service("MaterielDatasrv",function($http){
		
		// La recherche par mot clé 
		this.searchM = function (motCle, pageCourante, size) {
		    return $http.get("http://localhost:8080/cherchermateriels?mc=" + motCle + "&page=" + pageCourante + "&size=" + size);
		}
		
		// Ajouter un materiel
		this.add = function(materiel) {
			return $http.post("http://localhost:8080/materiel", materiel);
		}
		// La recherche d'un materiel par id
		this.searchMid = function (id) {
		    return $http.get("http://localhost:8080/materiel/"+id);
		}
		
		// La recherche de la liste des catégories
		this.searchC = function () {
		    return $http.get("http://localhost:8080/categories");
		}
		
		//La suppression d'un materiel
		this.deleteM=function(id){
			 return $http.delete("http://localhost:8080/materiel/"+id)
		}
		
		//actualiser la page
		this.refresh=function(motCle, pageCourante, size){
			return $http.get("http://localhost:8080/cherchermateriels?mc=" + motCle + "&page=" + pageCourante + "&size=" + size);
		}
		// modifier un materiel
		this.edit = function(materiel,id) {
			return $http.put("http://localhost:8080/materials/"+id,materiel);
		}
		
		this.goToRouter = function (state, $state) {
			alert('accès service router');
			console.log('pppppppppp');
		    return $state.go(state, {reload: true});
		}
	});
	
	