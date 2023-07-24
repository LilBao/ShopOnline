var apps = angular.module('myApp',[]);
var host = "http://localhost:8080/api/role"
apps.controller("myCtrl",function($scope,$http){
    $scope.list = [];
    $scope.loadAll = function(){
        $http.get(host).then(resp =>{
            $scope.list = resp.data;
        }).catch(error => {
            console.log("fail");
        })
    }
    $scope.loadAll();
})
