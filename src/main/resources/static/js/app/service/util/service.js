angular.module('cwitter.util.service', []).factory('LocalStorage', function () {

    return {
        set: function (key, val) {
            localStorage.setItem(key, val);
        },
        get: function (key) {
            return localStorage.getItem(key);
        },
        remove: function (key) {
            localStorage.removeItem(key);
        }
    }
})