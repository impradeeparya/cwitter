/**
 * Created with IntelliJ IDEA.
 * User: pradeep
 * Date: 17/7/16
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
angular
    .module('cwitter.registration.controller', [ 'cwitter.registration.service' ])
    .controller(
    'RegistrationController',
    function ($scope, $location, RegistrationService, LocalStorage) {

        $scope.userName;
        $scope.password;
        $scope.emailId;
        $scope.phoneNumber;
        $scope.showMessage = false;


        $scope.doRegistration = function () {
            RegistrationService.doRegistration($scope.userName, $scope.password, $scope.emailId, $scope.phoneNumber).then(function (response) {
                $scope.registrationMessage = response.data.message;
                $scope.showMessage = true;
            });
        }

        $scope.showLogon = function () {
            $location.path("/login");
        }
    }
)
;

