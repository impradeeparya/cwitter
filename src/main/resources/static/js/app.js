/**
 * Created with IntelliJ IDEA.
 * User: pradeep
 * Date: 17/7/16
 * Time: 3:15 PM
 * To change this template use File | Settings | File Templates.
 */


angular.module(
        'cwitter',
        [ 'ngRoute', 'cwitter.logon', 'cwitter.dashboard', 'cwitter.util', 'cwitter.registration']).config(
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

        if ($location.path() == '' || $location.path() == '/') {
            $location.path('/login');
        }
        $rootScope.$on('$routeChangeStart', function () {

            var path = $location.path();

            if (path == '/login' || path == '/dashboard' || path == '/registration') {

                if (!LocalStorage.get('token')) {
                    if (path == '/registration') {
                        $location.path('/registration');
                    } else {
                        $location.path('/login');
                    }
                } else {
                    LogonService.validateToken().then(function (response) {
                        if (response.data.authenticated === false) {
                            if (path == '/registration') {
                                $location.path('/registration');
                            } else if (path == '/login') {
                                $location.path('/login');
                            }
                        } else {
                            $rootScope.userName = response.data.username;
                            $location.path('/dashboard');
                        }
                    });
                }

            }

        })

    });

