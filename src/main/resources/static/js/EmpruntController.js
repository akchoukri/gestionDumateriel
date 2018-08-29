(function(){
	var app = angular.module('myApp');
	app.controller("EmpruntController",function($scope,$http,EmpruntDatasrv){
		 $scope.idClient=null;
		 $scope.emprunt={};
		 $scope.client={};
		 $scope.name="";
		 $scope.designation="";
		 // afficher liste des clients
         EmpruntDatasrv.getClients()
         .then(function(data){
           $scope.contacts= data.data;
           $scope.options = [];
             var i;
             var values = [];
             for(i=0;i<$scope.contacts.length;i++){
                 values.push(data.data[i]['nomClient']);
                 $scope.options = values;
             }
      
         }); 
			 // afficher liste des materiels
		     EmpruntDatasrv.getMateriels()
	    	 .then(function(data){
	    	  $scope.contacts= data.data;
	    	  $scope.materiels = [];
	    		var i;
	    		var values = [];
	    		for(i=0;i<$scope.contacts.length;i++){
	    			values.push(data.data[i]['designation']);
	    			$scope.materiels = values;
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
	 				for(i=0;i<data.length;i++){
	 					$scope.client=data[i];
		    		}
	 				console.log(data);
	 				$scope.emprunt.client=$scope.client;
	 				var id=$scope.client.idClient;
	 				console.log("http://localhost:8080/client/"+id+"/emprunts");
	 		    	  EmpruntDatasrv.newEmprunt(id,$scope.emprunt)
	 	              .then(function(){
	 	               console.log("l'emprunt est ajouté");
	 	           },function(err){
	 	               console.log(err);
	 	           });
	 			}, function (err) {
	 				console.log("erreur");
	 			});
          } 
	      //avoir un client par son nom
	      $scope.searchMateriel=function(){
	    	  EmpruntDatasrv.getClient($scope.designation)
	 			.then(function (data) {
                   console.log(data);

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