/**
 * Created by kbiao on 2017/2/7.
 */

/* 2.........
angular.module('hello',[])
 .controller('home',function ($scope,$http) {
     $http.get('/resource/').success(function (data) {
         $scope.greeting = data ;

     })
    //1. $scope.greeting={id:'kbiao',content:'hello world!'}

 })
********2*********/

angular.module('hello',['ngRoute'])
 .config(function ($routeProvider,$httpProvider) {
     $routeProvider.when('/', {
         templateUrl : 'home.html',
         controller : 'home',
         controllerAs: 'controller'
     })
        /* .when('/login', {
         templateUrl : 'login.html',
         controller : 'navigation',
         controllerAs: 'controller'
     })*/
         .otherwise('/');

     //使得spring在拿到401响应时候不发送'www_authenticate' 头，不弹出登录验证框，以便以定制登录过程
     $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
     $httpProvider.defaults.headers.common['Accept'] = 'application/json';
 })
.controller('navigation',
    function($rootScope, $http, $location, $route) {
    //function($rootScope,$scope,$http,$location) {

    var self = this ;
        self.tab = function(route) {
            return $route.current && route === $route.current.controller;
        };

        $http.get('user').then(function(response) {
            if (response.data.name) {
                $rootScope.authenticated = true;
            } else {
                $rootScope.authenticated = false;
            }
        }, function() {
            $rootScope.authenticated = false;
        });
    /*var authenticate = function (credentials, callback) {
        var headers = credentials ? {authorization : "Basic " + 
            btoa(credentials.username + ":" + credentials.password)} : {};

        $http.get('user', {headers : headers}).then(function(response) {
            if (response.data.name) {
                $rootScope.authenticated = true;
            } else {
                $rootScope.authenticated = false;
            }
            callback && callback($rootScope.authenticated);
        }, function() {
            $rootScope.authenticated = false;
            callback && callback(false);
        });
            


    }
    authenticate();
    self.credentials = {};
    self.login = function() {
        authenticate(self.credentials, function(authenticated) {
            if (authenticated) {
                console.log("Login succeeded")
                $location.path("/");
                self.error = false;
                $rootScope.authenticated = true;
            } else {
                console.log("Login failed")
                $location.path("/login");
                self.error = true;
                $rootScope.authenticated = false;
            }
        });
    }*/
     self.credentials = {};
    self.logout = function() {
        $http.post('logout', {}).finally(function() {
            $rootScope.authenticated = false;
            $location.path("/");
        });
    }


}).controller('home', function($http) {
    var self = this;
    /***1. **/
    $http.get('resource/').then(function (response) {
    //   2.  $http.get('http://localhost:9000/').then(function (response) {
        self.greeting = response.data;
    });

    /*$http.get('token').then(function(response) {
        $http({
            url : 'http://localhost:9000',
            method : 'GET',
            headers : {
                'X-Auth-Token' : response.data.token
            }
        }).then(function(response) {
            self.greeting = response.data;
        });
    });*/
});