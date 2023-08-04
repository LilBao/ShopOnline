var app = angular.module('myApp',[]);
var host="http://localhost:8080/api";
app.controller('myCtrl',function($scope,$http){
    $scope.signUp={};
    $scope.order={};
    
    $scope.confirmOrder=function(id){
        $http.get(host+"/order/"+id).then(resp => {
            $scope.order = resp.data;
            $scope.order.status=1;
            $http.put(host+"/order",$scope.order).then(resp => {
                $scope.reset()
            })
        }).catch(error => {
            console.log("Dang ky that bai");
        })
    }
    $scope.reset=function(){
        $scope.order={};
    }

    $scope.findOrder= function(id){
        $http.get(host+"/order/"+id).then(resp => {
            $scope.order = resp.data;
        }).catch(error => {
            console.log("Dang ky that bai");
        })
    }

    $scope.deleteOrder=function(id){
        $http.delete(host+"/order/"+id).then(resp => {
        }).catch(error => {
            console.log("Dang ky that bai");
        })
    }

    $scope.deleteProduct=function(id){
        $http.delete(host+"/order-detail/"+id).then(resp => {
            
        }).catch(error => {
            console.log("Dang ky that bai");
        })
    }
})