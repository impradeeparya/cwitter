/**
 * Created with IntelliJ IDEA.
 * User: pradeep
 * Date: 17/7/16
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
angular.module('cwitter.registration', [ 'cwitter.registration.controller' ]).config(
    [ '$routeProvider', function ($routeProvider) {
        $routeProvider.when('/registration', {
            controller: 'RegistrationController',
            templateUrl: '../../../../view/registration.html'
        });
    } ]);
