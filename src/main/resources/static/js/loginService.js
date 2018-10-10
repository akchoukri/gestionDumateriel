(function() {

	var app = angular.module('myApp');
	app.service("LoginService", function($http) {
		var that = this;
		that.roles = [];
		that.token;
		that.username;
		that.user={};

		this.loginUser = function(username, password) {
			var user = {
				"username" : username,
				"password" : password
			}
			return $http.post("http://localhost:8080/login", user).then(
					function(response) {
						console.log(response.headers());
						return response;
					});
		}

		that.saveToken = function(jwt) {
			that.token = jwt;
			localStorage.setItem('token', jwt);
			var parsedJwt = that.parseJwt(jwt);
			that.roles = parsedJwt.roles;
			that.username = parsedJwt.sub;
			that.user.username=that.username;
			that.user.roles=that.roles;
		}
		


		that.loadToken = function(jwt) {
			that.token = localStorage.getItem('token');
		}
		
		that.parseJwt = function(token) {
            var base64Url = token.split('.')[1];
            var base64 = base64Url.replace('-', '+').replace('_', '/');
            return JSON.parse(window.atob(base64));
        };
		      // retourner l'utilisateur
		// chercher un client par son nom
		that.getUser = function(username) {
			var promise1 = $http({
				method : 'GET',
				url : "localhost:8080/user?username=" + username
			});
			var promise2 = promise1.then(function(response) {
				return response;
			}, function(err) {
				console.log(err);
			});
			return promise2;

}
        
        
	})

})();