var logViewerModule = angular.module('logViewerApp', ['ngAnimate']);

logViewerModule.controller('logViewerController', function ($scope,$http) {

 var urlBase="services";
 $http.defaults.headers.post["Content-Type"] = "application/json";

    function findLog() {
        //get all tasks and display initially
        $http.get(urlBase + '/log').
            success(function (data) {
                console.log(data);
                $scope.logs = data;
            });
    }

    findLog();

});
