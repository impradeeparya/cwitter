/**
 * Created with IntelliJ IDEA.
 * User: pradeep
 * Date: 17/7/16
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
angular
    .module('cwitter.logon.controller', [ 'cwitter.logon.service' ])
    .controller(
    'LogonController',
    function ($rootScope, $scope, $location, LogonService, LocalStorage) {

        $scope.userName = "";
        $scope.password = "";


        $scope.doLogin = function () {
            LogonService.doLogin($scope.userName, $scope.password).then(function (response) {
                if (response.data.authenticated == true) {
                    LocalStorage
                        .set(
                            'token',
                            response.data.token);
                    $rootScope.userName = response.data.username
                    $location
                        .path('/dashboard');
                } else {
                    $scope.loginFailedMessage = response.data.message;
                }
            });
        }

        $scope.showRegistration = function () {
            $location.path("/registration");
        }
    }
)
;

