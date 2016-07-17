/**
 * Created with IntelliJ IDEA.
 * User: pradeep
 * Date: 17/7/16
 * Time: 6:51 PM
 * To change this template use File | Settings | File Templates.
 */
angular.module('cwitter.dashboard.service', []).service('DashboardService',
    function ($http) {

        this.fetchTweets = function () {

            return $http({
                url: "/fetchTweets",
                method: "GET"
            });
        }

    });



