var app = angular.module('myApp', []);
var host = "http://localhost:8080/api";
app.controller('myCtrl', function ($scope, $http) {
    $scope.order = {};
    $scope.listOrderApprove = [];
    $scope.listOrder = [];
    $scope.listOrderDecline = [];

    $scope.load = function () {
        $http.get(host + "/order-status/0").then(resp => {
            $scope.listOrderApprove = resp.data
        })
    }
    $scope.load1 = function () {
        $http.get(host + "/order-status/1").then(resp => {
            $scope.listOrder = resp.data
        })
    }
    $scope.load3 = function () {
        $http.get(host + "/order-status/3").then(resp => {
            $scope.listOrderDecline = resp.data
        })
    }

    $scope.confirmOrder = function (id) {
        $http.get(host + "/order/" + id).then(resp => {
            $scope.order = resp.data;
            $scope.order.status = 1;
            $http.put(host + "/order", $scope.order).then(resp => {
                $scope.SendMailConfirm($scope.order.orderid, "XÁC NHẬN")
                $scope.load();
            })

        })
    }
    $scope.reset = function () {
        $scope.order = {};
    }

    $scope.deleteOrder = function (id) {
        $scope.SendMailConfirm(id, "HUY")
        $http.delete(host + "/order/" + id).then(resp => {
            $scope.load3();
        }).catch(error => {
            console.log("that bai");
        })
    }

    $scope.deleteProduct = function (id) {
        $http.delete(host + "/order-detail/" + id).then(resp => {

        }).catch(error => {
            console.log("hat bai");
        })
    }

    $scope.SendMailConfirm = function (id, type) {
        $http.post(host + "/confirm-request-order/" + id + "?type=" + type).then(resp => {

        }).catch(error => {
            console.log("that bai");
        })
    }
    $scope.load();
    $scope.load1();
    $scope.load3();
})