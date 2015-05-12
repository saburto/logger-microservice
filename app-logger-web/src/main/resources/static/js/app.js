var logViewerModule = angular.module('logViewerApp', ['ngAnimate']);

logViewerModule.controller('logViewerController', function ($scope,$http) {
	
$scope.sort = {       
            sortingOrder : "date",
            reverse : false
        };
$scope.currentPage = 0;
$scope.level = "ALL";
$scope.logName = null;

$scope.$watch("logName", function(newValue, oldValue) {
	$scope.findLog();
});

$scope.$watch("level", function(newValue, oldValue) {
	$scope.findLog();
});

 var urlBase="services";
 $http.defaults.headers.post["Content-Type"] = "application/json";

 	$scope.findLog  = function() {
        //get all tasks and display initially
        $http.get(urlBase + '/log', {
            params: {
                page:  $scope.currentPage,
                sort:  $scope.sort.sortingOrder ? $scope.sort.sortingOrder : "date",
                order: $scope.sort.reverse ? "ASC" : "DESC",
                level: $scope.level ? ($scope.level === 'ALL' ? null : $scope.level ) : $scope.level,
                logName : $scope.logName ? $scope.logName : null
            }
         }
        ).
            success(function (data) {
                console.log(data);
                if(data){
                	$scope.logs = data;	
                }else{
                	$scope.logs = [];
                }
                
            });
    };
    
    $scope.sort = function(sort){
    	$scope.sort.sortingOrder = sort;
    	$scope.sort.reverse = !$scope.sort.reverse;
    	$scope.findLog();
    }
    
    $scope.selectedCls = function(sort){
    	if(sort == $scope.sort.sortingOrder){
            return ('icon-chevron-' + (($scope.sort.reverse) ? 'up' : 'down'));
        }
        else{            
            return'icon-sort' 
        }
    }
    
    $scope.formatDate = function(dateMill){
    	return new Date(dateMill);
    };

    
    
    $scope.prevPage = function () {
        if ($scope.currentPage > 0) {
            $scope.currentPage--;
            $scope.findLog();
        }
    };
    
    $scope.nextPage = function () {
    	if($scope.logs.length > 0){
    		$scope.currentPage++;
            $scope.findLog();	
    	}
        
    };
    
    $scope.findLog();

});



