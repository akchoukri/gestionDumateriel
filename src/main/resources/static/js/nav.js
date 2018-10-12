angular.module('myApp')
// Creating the Angular Controller
.controller('NavController', function($http, $scope, LoginService,$rootScope,$state) {
	$scope.logout = function() {
		LoginService.user = null;
		$rootScope.$broadcast('LogoutSuccessful');
		$state.go('login');
	};
});

