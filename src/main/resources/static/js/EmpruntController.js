(function(){
	var app = angular.module('myApp');
	app.controller("EmpruntController",function($scope,$http,EmpruntDatasrv){
		 $scope.idClient=null;
		 $scope.emprunt={};
		 $scope.client={};
		 $scope.materiel={};
		 $scope.name="";
		 $scope.designation="";
		 // afficher liste des clients
       
        	EmpruntDatasrv.getClients()
         .then(function(data){
           $scope.client= data.data;
           $scope.options = [];
             var i;
             var values = [];
             for(i=0;i<$scope.client.length;i++){
                 values.push(data.data[i]['nomClient']);
                 $scope.options = values;
             }
      
         }); 
			 // afficher liste des materiels
		     EmpruntDatasrv.getMateriels()
	    	 .then(function(data){
	    	  $scope.materiel= data.data;
	    	  
	    	  var etat=$scope.materiel[2].etatMateriel;
	    	  console.log(etat);
	    	  $scope.materiels = [];
	    	  var values = [];
	    		for(var i=0;i<$scope.materiel.length;i++){
	    				values.push(data.data[i]['designation']);
		    			$scope.materiels = values;
	    			}	
	    			

	    		}
	    	}); 
//	      $scope.ajouterEmprunt=function(id){
//	    	  EmpruntDatasrv.newEmprunt($scope.emprunt)
//              .then(function(){
//               console.log("le contact est ajouté");
//               
//           },function(err){
//               console.log(err);
//           });
//          }
	      //avoir un client par son nom
	      $scope.searchClient=function(){
	    	  EmpruntDatasrv.getClient($scope.name)
	 			.then(function (data) {
	 				for(var i=0;i<data.length;i++){
	 					$scope.client=data[i];
		    		}
	 				$scope.emprunt.client=$scope.client;
	 				var id=$scope.client.idClient;
	 				console.log("http://localhost:8080/client/"+id+"/emprunts");
	 				  EmpruntDatasrv.getMateriel($scope.designation)
	 		 			.then(function (data) {
	 	               	for(var i=0;i<data.length;i++){
	 	 					$scope.materiel=data[i];
	 		    		}
//	 	               	  $scope.emprunt.materiel=$scope.materiel;
	 	               	  var idMateriel=$scope.materiel.idMateriel;
		 		    	  EmpruntDatasrv.newEmprunt(id,idMateriel,$scope.emprunt)
		 	              .then(function(){
		 	               console.log("l'emprunt est ajouté");
		 	           },function(err){
		 	               console.log(err);
		 	           });
	 		 			}, function (err) {
	 		 				console.log(err);
	 		 			});
	 			}, function (err) {
	 				console.log("erreur");
	 			});
          } 
	      //avoir un client par son nom
	      $scope.searchMateriel=function(){
	    	  
	    	  EmpruntDatasrv.getMateriel($scope.designation)
	 			.then(function (data) {
               	for(var i=0;i<data.length;i++){
 					$scope.materiel=data[i];
	    		}
               	$scope.test=$scope.materiel;
	 			}, function (err) {
	 				console.log(err);
	 			});
	    	  
          }
	      //ajouter un contact
	      $scope.saveEmprunt=function(){
	    	  $scope.searchClient();
	    	  
	    	
          } 
//	      
	      
			
    });
})();