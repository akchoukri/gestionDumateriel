angular.module('myApp')
// Creating the Angular Controller
.controller('NavController', function($http, $scope, LoginService,$rootScope) {
	$scope.mode=0;
	$scope.$on('LoginSuccessful', function() {
		$scope.user = LoginService.user;
	   for (var i = 0; i < $scope.user.roles.length; i++) {
		   if( $scope.user.roles[i].authority=="ADMIN"){
			   $scope.mode=1;
		   }
		   
	   }
	console.log($scope.mode);
	});
	
	$scope.$on('LogoutSuccessful', function() {
		$scope.user = null;
	});
	$scope.logout = function() {
		LoginService.user = null;
		$rootScope.$broadcast('LogoutSuccessful');
		$state.go('login');
	};
	
//	$scope.mode=0;
//	$scope.roles=LoginService.roles;
//	
//	console.log($scope.roles.length);
//	for(i=0;i<$scope.roles.length; i++){
//		if($scope.roles[i].authority=="ADMIN"){
//			$scope.mode=1;
//		}
//	}
});

