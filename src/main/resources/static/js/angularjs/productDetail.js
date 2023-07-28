var app = angular.module('myApp', []);
var host = "http://localhost:8080/api/"
app.controller('myCtrl', function ($scope, $http) {
    $scope.prdDetail = {};
    $scope.listProductDetail = [];
    $scope.listProduct=[];

    $scope.loadListProduct = function () {
        var url = host + "product";
        $http.get(url).then(resp => {
            $scope.listProduct = resp.data;
        })
    }

    $scope.loadListDetail = function () {
        var url = host + "product-list-detail/"+document.getElementById('productid').value;
        $http.get(url).then(resp => {
            $scope.listProductDetail = resp.data;
            $scope.resetDetail();
        })
    }

    $scope.createPrdDetail = function () {
        var url = host + "product-detail";
        var item = angular.copy($scope.prdDetail);
        $http.post(url, item).then(resp => {
            $scope.resetDetail();
            $scope.loadListDetail();
        }).catch(error => {
            console.log("Errors", error);
        });

    }

    $scope.updatePrdDetail = function () {
        var url = host + "product-detail";
        var item = angular.copy($scope.prdDetail);
        $http.put(url, item).then(resp => {
            $scope.prdDetail=resp.data; 
            $scope.loadListDetail();
        }).catch(error => {
            console.log("Errors", error);
        });
    }

    $scope.deletePrdDetail = function (id) {
        var url = host + "product-detail/"+id;
        $http.delete(url).then(resp => {
            $scope.resetDetail();
            $scope.loadListDetail();
        }).catch(error => {
            console.log("Errors", error);
        });
    }

    $scope.edit = function (id) {
        var url = host + "product-detail/"+id;
        $http.get(url).then(resp => {
            $scope.prdDetail=resp.data;
        }).catch(error => {
            console.log("Errors", error);
        });
    }

    $scope.reset = function () {
        $scope.prdDetail = {};
        $scope.loadListDetail();
    }

    $scope.resetDetail = function(){
        $scope.prdDetail.id=null;
        $scope.prdDetail.quantity=null;
        $scope.prdDetail.size=null;
    }

    $scope.loadListProduct();
})