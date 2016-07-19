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
        console.log("outer")

        $rootScope.$on('$routeChangeStart', function () {

            var path = $location.path();
            console.log("1")

            if (path == '/login' || path == '/dashboard' || path == '/registration') {
                console.log("2")

                if (!LocalStorage.get('token')) {
                    console.log("3")
                    if (path == '/registration') {
                        console.log("4")
                        $location.path('/registration');
                    } else if (path == '/login') {
                        console.log("5")
                        $location.path('/login');
                    }
                } else {
                    console.log("6")
                    LogonService.validateToken().then(function (response) {
                        if (response.data.authenticated === false) {
                            console.log("7")
                            if (path == '/registration') {
                                console.log("8")
                                $location.path('/registration');
                            } else if (path == '/login') {
                                console.log("9")
                                $location.path('/login');
                            }
                        } else {
                            console.log("10")
                            $rootScope.userName = response.data.username;
                            $location.path('/dashboard');
                        }
                    });
                }

                console.log($rootScope.userName + "-")
            }

        })

    });

