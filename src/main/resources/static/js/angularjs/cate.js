var app = angular.module('myApp',[]);
var host = "http://localhost:8080/api/cate"
app.controller("myCtrl",function($scope,$http){
    $scope.listCate = [];
    $scope.form = {};
    $scope.cateid = null;

    $scope.reset=function(){
       $scope.form={};
    }

    $scope.loadAll=function(){
        $http.get(host).then(resp =>{
            $scope.listCate = resp.data;
        }).catch(error =>{

        })
    }

    $scope.create = function(){
        const item = angular.copy($scope.form)
        $http.post(host,item).then(resp => {
            $scope.reset();
            $scope.loadAll();
        }).catch(error =>{

        })
    }

    $scope.update = function(){
        var url = host;
        const item = angular.copy($scope.form);
        $http.put(url,item).then(resp => {
            $scope.form=resp.data; 
            $scope.loadAll();
        }).catch(error =>{

        })
    }

    $scope.delete = function(id){
        var url = host +"/"+$scope.cateid
        $http.delete(url).then(resp => {
            $scope.reset();
            $scope.loadAll();
        }).catch(error =>{

        })
    }

    $scope.edit = function(id){
        var url = host +"/"+id
        $http.get(url).then(resp => {
            $scope.cateid=resp.data.cateid;
            $scope.form=resp.data; 
        }).catch(error =>{

        })
    }

    $scope.loadAll();
})