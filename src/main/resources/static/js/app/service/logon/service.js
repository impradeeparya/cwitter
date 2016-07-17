/**
 * Created with IntelliJ IDEA.
 * User: pradeep
 * Date: 17/7/16
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
angular.module('cwitter.logon.service', []).service('LogonService',
    function ($http) {

        this.doLogin = function (userName, pwd) {

            return $http({
                url: "/logon",
                method: "POST",
                params: {username: userName, password: pwd}
            });
        }

        this.validateToken = function () {
            return $http({
                url: "/validateToken",
                method: "GET"
            });
        }
    });

