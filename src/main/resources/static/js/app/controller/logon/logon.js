/**
 * Created with IntelliJ IDEA.
 * User: pradeep
 * Date: 17/7/16
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
angular.module('cwitter.logon', [ 'cwitter.logon.controller' ]).config(
    [ '$routeProvider', function ($routeProvider) {
        $routeProvider.when('/login', {
            controller: 'LogonController',
            templateUrl: '../../../../view/logon.html'
        });
    } ]);
