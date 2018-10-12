var app = angular.module("myApp",['ui.router','ngMaterial',,"chart.js" ]);

app.run(function(LoginService, $rootScope, $state) {
	$rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
		if (!LoginService.token) {
		
			if (toState.name != 'login' && toState.name!='register') {
				event.preventDefault();
				$state.go('login');
			}
		} 
		else {
			
			
			if (toState.data && toState.data.role) {
				console.log(toState.data.role);
				var hasAccess = false;
				for (var i = 0; i < LoginService.user.roles.length; i++) {
					var role = LoginService.user.roles[i].authority;
					if (toState.data.role == role) {
						hasAccess = true;
						break;
					}
				}
				if (!hasAccess) {
					event.preventDefault();
					$state.go('access-denied');
				}

			}
		}
	});
});

// la configuration des des route (les chemin des vues)
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
					controller: 'NavController'
					
				}
			}
		
		})	.state('login', {
		    
		    url: '/login',
		    views: {
		        'main': {
		            templateUrl: 'views/login.html',
		            controller: 'LoginController'
		        }
		    }
		}).state('register', {
			url : '/register',
			views : {
				'main' : {
					templateUrl : 'views/register.html',
					controller : 'RegisterController'
				}
			}
		})
		.state('chart', {
			parent : 'main',
			url : '/chart',
			views : {
				'content@main' : {
					templateUrl : 'views/statistique/chart.html',
					controller : "statiqContl",
				}
			}
	})

		.state('pret', {
			parent : 'main',
			url : '/pret',
			views : {
				'content@main' : {
					templateUrl : 'views/emprunt/pret.html',
					controller : "empruntclientCtrl",
				}
			}
	})
	.state('emprunt', {
			parent : 'main',
			url : '/emprunt',
			views : {
				'content@main' : {
					templateUrl : 'views/emprunt/emprunt.html',
					controller : "empruntclientCtrl",
				}
			}
	})
			.state('gestionDuMateriel',{
				parent : 'main',
				url:'/gestionDuMateriel',
				views : {
					'content@main' : {
						templateUrl : 'views/materiels.html',
						controller:'MaterielController'
					},
                    'add@gestionDuMateriel' : {
                        templateUrl : 'views/newMateriel.html',
                        controller:'MaterielController'
                    },
                    'home@gestionDuMateriel' : {
                        templateUrl : 'views/search2.html',
                        controller:'MaterielController'
                    }
				}
				
			})
			
			.state('search',{
				parent : 'main',
				url:'/chercherUnMateriel',
				views : {
					'content@main' : {
						templateUrl : 'views/search2.html',
						controller:'MaterielController'

					}
				}

			})
			.state('newProduct',{
				parent : 'main',
				url:'/ajouterMateriel',
				views : {
					'content@main' : {
						templateUrl : 'views/newMateriel.html',
						controller:'MaterielController'

					}
				}
	})
			.state('editProduct',{
				parent : 'main',
				url:'/modifierMateriel?idd',
				views : {
					'content@main' : {
						templateUrl : 'views/newMateriel.html'

					}
				}
	})
    //			.state('newProduct',{
//				parent : 'main',
//				url:'/ajouterMateriel',
//				views : {
//					'content@main' : {
//						templateUrl : 'addNewMatriel.html',
//						controller:'MaterielController'
//
//					}
//				}
//	})

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
	.state('empruntClient', {
		parent : 'main',
		url : '/empruntClient',
		views : {
			'content@main' : {
				templateUrl : 'views/empruntClient.html',
				controller:'EmpruntClient'
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

});



});
