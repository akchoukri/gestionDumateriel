angular
		.module('myApp')
		// Creating the Angular Controller
		.controller(
				'LoginController',
				function($http, $scope, $state, $rootScope,
						LoginService) {
				
					$scope.mode=0;
					$scope.login = function() {
						LoginService
								.loginUser($scope.username, $scope.password)
								.then(
										function(res, status, headers) {
											var token = res.headers()["authorization"];
//											console.log(token)
											$scope.password = null;
											if (token) {
												$scope.message = '';
												$http.defaults.headers.common['Authorization'] = token;
												LoginService.saveToken(token);
												console.log(LoginService.user);
//												LoginService.getUser($scope.username)
//												.then(function(response) {
//													console.log(response);
//													$state.go('home');
//                                                }, function (response) {
//   
//                                                 });
												
////												console.log(LoginService.username);
//												
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
