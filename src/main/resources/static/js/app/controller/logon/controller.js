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
    function ($scope, $location, LogonService, LocalStorage) {

        $scope.luserName = "";
        $scope.lpassword = "";


        $scope.doLogin = function () {
            LogonService.doLogin($scope.luserName, $scope.lpassword).then(function (response) {
                if (response.data.authenticated == true) {
                    LocalStorage
                        .set(
                            'token',
                            response.data.token);
                    $location
                        .path('/dashboard');
                } else {
                    $scope.userNameEmptyMessage = '';
                }
            });
        }
    }
)
;

