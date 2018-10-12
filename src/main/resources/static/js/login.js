angular
		.module('myApp')
		// Creating the Angular Controller
		.controller(
				'LoginController',
				function($http, $scope, $state, $rootScope,
						LoginService) {
				
					$rootScope.mode=0;
					$rootScope.username="";
					$scope.login = function() {
						LoginService
								.loginUser($scope.username, $scope.password)
								.then(
										function(res, status, headers) {
											var token = res.headers()["authorization"];
											$scope.password = null;
											if (token) {
												$scope.message = '';
												$http.defaults.headers.common['Authorization'] = token;
												LoginService.saveToken(token);
												$rootScope.username=LoginService.user.username;

												 for (var i = 0; i <LoginService.roles.length; i++) {
													   if( LoginService.roles[i].authority=="ADMIN"){
														   $rootScope.mode=1;
													   }
													   
												   }
												$rootScope
														.$broadcast('LoginSuccessful');
												$state.go('chart');
												
											} else {
												$scope.message = 'Authetication Failed !';
											}
										},
										function(err) {
											console.log("err est      :" + err);
											$scope.message = 'Authetication Failed !';
										});
					}

				});
