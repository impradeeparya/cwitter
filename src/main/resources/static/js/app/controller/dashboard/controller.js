/**
 * Created with IntelliJ IDEA.
 * User: pradeep
 * Date: 17/7/16
 * Time: 6:34 PM
 * To change this template use File | Settings | File Templates.
 */
angular
    .module('cwitter.dashboard.controller', [ 'cwitter.dashboard.service' ])
    .controller(
    'DashboardController',
    function ($scope, $location, DashboardService) {


        $scope.tweets = [];

        DashboardService.fetchTweets().then(function (response) {
            $scope.tweets = response.data;
        });
    }
);
