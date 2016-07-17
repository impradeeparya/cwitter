/**
 * Created with IntelliJ IDEA.
 * User: pradeep
 * Date: 17/7/16
 * Time: 6:33 PM
 * To change this template use File | Settings | File Templates.
 */
angular.module('cwitter.dashboard', [ 'cwitter.dashboard.controller' ]).config(
    [ '$routeProvider', function ($routeProvider) {
        $routeProvider.when('/dashboard', {
            controller: 'DashboardController',
            templateUrl: '../../../../view/dashboard.html'
        });
    } ]);
