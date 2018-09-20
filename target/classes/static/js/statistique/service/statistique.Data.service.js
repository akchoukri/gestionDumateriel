(function () {


var app = angular.module("myApp");

// service pour les statistique
app.service("statiqDataService",function ($http) {
// La recherche de tous les materiel
    this.searchMateriels = function () {
        return $http.get("http://localhost:8080/materiels");
    }
    this.searchEmprunts = function () {
        return $http.get("http://localhost:8080/allEmprunts");
    }
})
//**********
})();