var app = angular.module("myApp",["ui.router"]);



//la configuration des des route (les chemin des vues)
app.config(function ($stateProvider, $urlRouterProvider) {
	 // chemin par defaut pour vue accueil
	 $urlRouterProvider.otherwise('/');
	 
		$stateProvider.state('main', {
			
			url : '',
			views : {
				'main' : {
					templateUrl : 'views/content.html',
				},
				'nav@main' : {
					templateUrl : 'views/nav.html',
				}
			}
		
		})
		.state('onglet1', {
			parent : 'main',
			url : '/onglet1',
			views : {
				'content@main' : {
					templateUrl : 'views/onglet1.html',
				}
			}
	})
			.state('onglet2', {
			parent : 'main',
			url : '/onglet2',
			views : {
				'content@main' : {
					templateUrl : 'views/onglet2.html',
				}
			}
	});
   
});