/**
 * Created with IntelliJ IDEA.
 * User: pradeep
 * Date: 17/7/16
 * Time: 3:15 PM
 * To change this template use File | Settings | File Templates.
 */


angular.module(
        'cwitter',
        [ 'ngRoute', 'cwitter.logon', 'cwitter.util']).config(
    function ($httpProvider) {
        $httpProvider.interceptors.push(function (LocalStorage) {
            return {
                request: function (config) {
                    config.headers = config.headers || {};
                    if (LocalStorage.get('token')) {
                        config.headers.Authorization = LocalStorage
                            .get('token');
                    }
                    return config;
                }
            };
        });

    }).run(function ($rootScope, $location, LocalStorage, LogonService) {

        if ($location.path() == '') {
            $location.path('/login');
        }

        $rootScope.$on('$routeChangeStart', function () {

            if ($location.path() == '/login' || $location.path() == '/dashboard') {
                if (!LocalStorage.get('token')) {
                    $location.path('/login');
                } else {
                    LogonService.validateToken().then(function (response) {
                        if (response.data.authenticated === false) {
                            $location.path('/login');
                        } else {
                            $location.path('/dashboard');
                        }
                    });
                }
            }

        })

    });

