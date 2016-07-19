/**
 * Created with IntelliJ IDEA.
 * User: pradeep
 * Date: 17/7/16
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
angular.module('cwitter.registration.service', []).service('RegistrationService',
    function ($http) {

        this.doRegistration = function (userName, pwd, emailId, phoneNumber) {

            return $http.post("/register", {
                userName: userName,
                password: pwd,
                emailId: emailId,
                phoneNumber: phoneNumber
            })
        }

    });

