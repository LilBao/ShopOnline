var app = angular.module('myApp',[]);
var host="http://localhost:8080/api";
app.controller('myCtrl',function($scope,$http){
    $scope.signUp={}
    $scope.signIn={};
    
    $scope.create=function(){
        var url = host+"/auth"
        var item = angular.copy($scope.signUp)
        $http.post(url,item).then(resp => {
            $scope.signUp = resp.data;
            alert("Dang ky thanh cong");
            $scope.reset();
        }).catch(error => {
            console.log("Dang ky that bai");
        })
    }

    $scope.get=function(username){
        var url = host + "/auth/"+ username;
        $http.get(url).then(resp=>{

        })
    }
    $scope.reset=function(){
        $scope.signUp={};
    }

})