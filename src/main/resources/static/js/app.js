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

		.state('onglet3', {
			parent : 'main',
			url : '/onglet3',
			views : {
				'content@main' : {
					templateUrl : 'views/onglet3.html',
				}
			}
	})
	.state('onglet2', {
			parent : 'main',
			url : '/onglet2',
			views : {
				'content@main' : {
					templateUrl : 'views/onglet2.html',
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