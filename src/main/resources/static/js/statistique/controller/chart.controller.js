(function () {


var app = angular.module("myApp");
app.controller("statiqContl",statiqContl);


function statiqContl($scope,statiqDataService,$state,$rootScope,$http) {
        var i;
        var values = [];
        var nbBonne = 0;
        var nbEnPanne = 0;
        var nbEndommage = 0;
        var d = new Date();
        var monthAuj = d.getMonth()+1;
        var  yearAuj = d.getFullYear();
        var nb1=0;
        var nb2=0;
        var nb3=0;
        var nb4=0;
        statiqDataService.searchMateriels().then(function(response) {
            $scope.materiels = response.data;
            console.log(response.data);
            console.log("éééééééééééééééééééééé");
            console.log(d);
            console.log(monthAuj);
            console.log(yearAuj);
            var lenght = $scope.materiels.length;
            for (i = 0; i < $scope.materiels.length; i++) {

                values.push(response.data[i]['etatMateriel']);

                $scope.etatDuMateriel = response.data[i]['etatMateriel'];
                $scope.dateAjoutMateriel = response.data[i]['dateAjoutMateriel'];

                switch ($scope.etatDuMateriel) {
                    case "bonne etat":
                        nbBonne = nbBonne+1;
                        break;
                    case "en panne":
                        nbEnPanne = nbEnPanne+1;
                        break;
                    case "endommagé" :
                        nbEndommage = nbEndommage+1;
                        break;
                }



                console.log($scope.etatDuMateriel);
                console.log($scope.dateAjoutMateriel);
                // var format = d3.time.format("%b %d");
                var dateMateriel = new Date($scope.dateAjoutMateriel);
                var monthMateriel = dateMateriel.getMonth()+1;
                var yearMateriel = dateMateriel.getFullYear();

                console.log(monthMateriel);
                console.log(yearMateriel);
                if(monthMateriel==monthAuj && yearMateriel==yearAuj){
                    nb1=nb1+1;
                }
                if(monthMateriel==monthAuj-1 && yearMateriel==yearAuj){
                    nb2=nb2+1;
                }
                if(monthMateriel==monthAuj-2 && yearMateriel==yearAuj){
                    nb3=nb3+1;
                }
                if(monthMateriel==monthAuj-3 && yearMateriel==yearAuj){
                    nb4=nb4+1;
                }



            }
            console.log(nbBonne);
            console.log(nbEnPanne);
            console.log(nbEndommage);
            $scope.labels = ["bonne etat", "en panne", "endommagé"];
            $scope.data = [nbBonne, nbEnPanne, nbEndommage];


             $scope.lab = [monthMateriel-3, monthMateriel-2, monthMateriel-1, monthMateriel];
            $scope.series = ['Series A'];
            $scope.data1 = [
                [nb4/lenght, nb3/lenght, nb2/lenght, nb1/lenght]
             ];
             $scope.onClick = function (points, evt) {
                console.log(points, evt);
             };

        }, function myerror(err) {
            console.log(err);
        });

    statiqDataService.searchEmprunts().then(function(response) {
        $scope.emprunts = response.data;
        console.log(response);

    }, function myerror(err) {
        console.log(err);
    });
}


})();