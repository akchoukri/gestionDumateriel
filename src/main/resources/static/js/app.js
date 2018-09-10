var app = angular.module("myApp",['ui.router','ngMaterial']);



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

		.state('pret', {
			parent : 'main',
			url : '/pret',
			views : {
				'content@main' : {
					templateUrl : 'views/pret.html',
					controller : "empruntclientCtrl",
				}
			}
	})
	.state('emprunt', {
			parent : 'main',
			url : '/emprunt',
			views : {
				'content@main' : {
					templateUrl : 'views/emprunt.html',
					controller : "empruntclientCtrl",
				}
			}
	})

		.state('nouveauEmprunt', {
			parent : 'main',
			url : '/addNewEmpunt',
			views : {
				'content@main' : {
					templateUrl : 'views/nouveauEmprunt.html',
					controller:'EmpruntController'
				}
			}
	})
	.state('clients', {
	parent : 'main',
	url : '/clients',
	views : {
		'content@main' : {
			templateUrl : 'views/client/clients.html',
			controller : "listClientContl",
		}
	}
})
//vue pour creer nouveau client
.state('newClient', {
	parent : 'clients',
url : '/add',
templateUrl : 'views/client/newClient.html',
controller : "clientNewCtrl"		

})
// vue pour mise a jour le client
.state('clients.updateClient', {

url : '/:id',

			
templateUrl : 'views/client/updateClient.html',
controller : "clientUpdateCtrl"

})

   
});