var app = angular.module('myApp', []);
var host = "http://localhost:8080/api/";
app.controller('myCtrl', function ($scope, $http) {
    $scope.listCoupon = [];
    $scope.coupon = {};
    
    $scope.reset = function () {
        $scope.coupon = {};
    }

    $scope.loadAll = function () {
        let url = host + "coupon"
        $http.get(url).then(resp => {
            $scope.listCoupon = resp.data;
        }).catch(error => {

        })
    }

    $scope.create = function () {
        let url = host + "coupon";
        const item = angular.copy($scope.coupon)
        $http.post(url, item).then(resp => {
            $scope.reset();
            $scope.loadAll();
        }).catch(error => {

        })
    }

    $scope.update = function () {
        let url = host + "coupon"
        const item = angular.copy($scope.coupon);
        $http.put(url, item).then(resp => {
            $scope.coupon = resp.data;
            $scope.loadAll();
        }).catch(error => {

        })
    }

    $scope.delete = function (id) {
        let url = host +"coupon/"+id
        $http.delete(url).then(resp => {
            $scope.reset();
            $scope.loadAll();
        }).catch(error => {

        })
    }

    $scope.edit = function (id) {
        var url = host + "coupon/" + id
        $http.get(url).then(resp => {
            $scope.coupon = resp.data;
        }).catch(error => {

        })
    }

    $scope.loadAll();
})