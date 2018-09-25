(function () {


var app = angular.module("myApp");
app.controller("statiqContl",statiqContl);


function statiqContl($scope,statiqDataService,$state,$rootScope,$http) {
	$scope.dataEmpr = [];
	$scope.dataNvClient = [];
	$scope.dataNvEmpr = [];
	$scope.dataNvMat = [];
	$scope.etaMatLabels = [];
	$scope.dataNbrEmprClient = [];
	$scope.dataetatMat = [];
	 $scope.seriesEmpru = ['ssRetard', 'retard'];
	 $scope.labels = [];
	 $scope.ClientLabels = [];
	 // option pour emprunt avec et sans retard
	 $scope.options = { legend: { display: true , position:'right'}  ,
	title: {
         display: true,
         text: ' Emprunts avec et sans retard',
         fontSize:18,
         padding:20,
         fontStyle :'italic'
     },
     scales: {
    	    xAxes: [{
    	      scaleLabel: {
    	        display: true,
    	        labelString: "année-mois",
    	        fontSize:16,
    	        borderDash: [2, 5],
    	      }
    	    }],
    	    yAxes: [{
    	      scaleLabel: {
    	        display: true,
    	        labelString: "nbr emprunt",
    	        fontSize:16,
    	        position:'top'
    	      }
    	    }]
    	  }
     };
	 //option pour client nbr emprunt
	 $scope.opNbrEmCl = { 
				title: {
			         display: true,
			         text: ' nbr emprunt par client Mois courant ',
			         fontSize:18,
			         padding:20,
			         fontStyle :'italic'
			     },
			     scales: {
			    	    xAxes: [{
			    	      scaleLabel: {
			    	        display: true,
			    	        labelString: "nbr emprunt",
			    	        fontSize:16,
			    	        
			    	      }
			    	    }],
			    	    yAxes: [{
			    	      scaleLabel: {
			    	        display: true,
			    	        labelString: "clients",
			    	        fontSize:16,
			    	        position:'top'
			    	      }
			    	    }]
			    	  }
			     };
	 

	 
	//option pour client nbr emprunt
	 $scope.opNvClient = { 
				title: {
			         display: true,
			         text: ' nbr Client ajouté par mois',
			         fontSize:18,
			         padding:20,
			         fontStyle :'italic'
			     },
			     scales: {
			    	    xAxes: [{
			    	      scaleLabel: {
			    	        display: true,
			    	        labelString: "année-mois",
			    	        borderDash: [2, 5],
			    	        fontSize:16,
			    	        position:'top'
			    	      }
			    	    }],
			    	    yAxes: [{
			    	      scaleLabel: {
			    	        display: true,
			    	        labelString: "nbr client",
			    	        fontSize:16,
			    	      }
			    	    }]
			    	  }
			     };
	 
		//option pour  nbr emprunt
		 $scope.opNvEmpr = { 
					title: {
				         display: true,
				         text: ' nbr Emprunt  par mois',
				         fontSize:18,
				         padding:20,
				         fontStyle :'italic'
				     },
				     scales: {
				    	    xAxes: [{
				    	      scaleLabel: {
				    	        display: true,
				    	        labelString: "année-mois",
				    	        borderDash: [2, 5],
				    	        fontSize:16,
				    	        position:'top'
				    	      }
				    	    }],
				    	    yAxes: [{
				    	      scaleLabel: {
				    	        display: true,
				    	        labelString: "nbr emprunt",
				    	        fontSize:16,
				    	      }
				    	    }]
				    	  }
				     };
			//option pour  nbr emprunt
		 $scope.opNvMat = { 
					title: {
				         display: true,
				         text: ' nbr nV Materiel  par mois',
				         fontSize:18,
				         padding:20,
				         fontStyle :'italic'
				     },
				     scales: {
				    	    xAxes: [{
				    	      scaleLabel: {
				    	        display: true,
				    	        labelString: "année-mois",
				    	        borderDash: [2, 5],
				    	        fontSize:16,
				    	        position:'top'
				    	      }
				    	    }],
				    	    yAxes: [{
				    	      scaleLabel: {
				    	        display: true,
				    	        labelString: "nv materiel",
				    	        fontSize:16,
				    	      }
				    	    }]
				    	  }
				     };
		 // option pour emprunt avec et sans retard
		 $scope.opEtatMat = { legend: { display: true , position:'bottom'}  ,
		title: {
	         display: true,
	         text: ' Etat Materiel',
	         fontSize:18,
	         padding:20,
	         fontStyle :'italic'
	     }
	     };
			// les  3 mois dernier
			statiqDataService.getLastMonths().then(function (data) {
						for(var j=data.length; j>0; j--)
			$scope.labels.push(data[j-1].substring(0, 7))
		    });

	statiqDataService.getempruntR().then(function (data) {

		var series = $scope.seriesEmpru;
		var arr = Object.keys(data)

      var tab1 = [];
		for(var i=0; i<2; i++){
			
			for(var j=0; j<arr.length; j++)
        	{
        	tab1.push(data[arr[j]][series[i]]);
        	}
			$scope.dataEmpr.push(tab1)
			tab1 = []
		}
  

    });
	
	// le nombre des client ajouter pendant 3 mois dernier
	statiqDataService.getNvClient().then(function (data) {
		
		var arr = Object.keys(data)
	 
       for(var j=0; j<arr.length; j++)
			$scope.dataNvClient.push(data[arr[j]])
    });
	
	
	// le nombre des client ajouter pendant 3 mois dernier
	statiqDataService.getEmprByclient().then(function (data) {
		
		var arr = Object.keys(data)
	for(var j=0; j<arr.length; j++)
		$scope.ClientLabels.push(arr[j])
       for(var j=0; j<arr.length; j++)
			$scope.dataNbrEmprClient.push(data[arr[j]])
			

    });
	
	// recuperer les nouveau emprunt par mois 
	statiqDataService.getNvEmp().then(function (data) {
		
		var arr = Object.keys(data)
		 
	       for(var j=0; j<arr.length; j++)
				$scope.dataNvEmpr.push(data[arr[j]])

		
    });
	// recuperer les nouveau Materiel par mois 
	statiqDataService.getNvMat().then(function (data) {
		
		var arr = Object.keys(data)
		 
	       for(var j=0; j<arr.length; j++)
				$scope.dataNvMat.push(data[arr[j]])

		
    });
	// comapraison l'etat des materiels 
	statiqDataService.getEtatMat().then(function (data) {
		
		var arr = Object.keys(data)
		 	for(var j=0; j<arr.length; j++)
		$scope.etaMatLabels.push(arr[j])
	       for(var j=0; j<arr.length; j++)
				$scope.dataetatMat.push(data[arr[j]])

    });
}

})();