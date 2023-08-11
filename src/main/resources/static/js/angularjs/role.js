var app = angular.module('myApp',[]);
var host = "http://localhost:8080/api/"
app.controller("myCtrl",function($scope,$http){
    $scope.list = [];
    $scope.form = {};

    $scope.reset=function(){
       $scope.form={};
    }


    $scope.create = function(api){
        var url = host + api;
        const item = angular.copy($scope.form)
        $http.post(url,item).then(resp => {
            $scope.reset();
            $scope.loadAll();
        }).catch(error =>{

        })
    }

    $scope.update = function(api){
        var url = host + api;
        const item = angular.copy($scope.form);
        $http.put(url,item).then(resp => {
            $scope.form=resp.data; 
            $scope.loadAll();
        }).catch(error =>{

        })
    }

    $scope.delete = function(api,id){
        var url = host +api+'/'+id
        $http.delete(url).then(resp => {
            $scope.reset();
            $scope.loadAll();
        }).catch(error =>{

        })
    }

    $scope.edit = function(api,id){
        var url = host +api+'/'+id
        $http.get(url).then(resp => {
            $scope.form=resp.data; 
        }).catch(error =>{

        })
    }
    $scope.loadAll = function(){
        $http.get(host+'role').then(resp =>{
            $scope.list = resp.data;
        }).catch(error => {
            console.log("fail");
        })
    }
    $scope.loadAll();

    //Authorize
    $scope.listAcc=[]
    $http.get(host+"authorities").then(resp => {
        $scope.db = resp.data;
        console.log(resp.data)
    })
   
    $scope.indexOf = function(username, role){
        return $scope.db.authorities.findIndex(item => item.account.username==username && item.role.id == role)
    }

    $scope.updateRole = function(username,role){
        var index = $scope.indexOf(username,role);
        if(index>=0){
            var id = $scope.db.authorities[index].id;
            $http.delete(host+'authorities/'+id).then(resp =>{
                $scope.db.authorities.splice(index,1);
            })
        }else{
            var authority ={
                account: {username: username},
                role:{id:role}
            };
            $http.post(host+'authorities',authority).then(resp => {
                $scope.db.authorities.push(resp.data);
            })
        }
    }
})
