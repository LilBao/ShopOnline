var app = angular.module('myApp', []);
var host = "http://localhost:8080/api/";
app.controller("myCtrl", function ($scope, $http) {
    $scope.items = localStorage.getItem('cart') ? JSON.parse(localStorage.getItem('cart')) : [];
    $scope.orderdetail = {};
    $scope.orderid = document.getElementById('orderid').innerText;
    // Lấy URL hiện tại
    const url = window.location.href;
    // Tạo một đối tượng URLSearchParams từ URL
    const urlSearchParams = new URLSearchParams(new URL(url).search);
    //        // Tạo một đối tượng JavaScript từ URLSearchParams
    const params = Object.fromEntries(urlSearchParams.entries());
    //        // Hiển thị kết quả
    console.log(params);
    $http.get(host + "order/" + $scope.orderid).then(resp => {
        $scope.items.forEach(item => {
            $scope.orderdetail.order = resp.data;
            $scope.orderdetail.product = item.product;
            $scope.orderdetail.productname = item.product.name;
            $scope.orderdetail.price = item.product.price;
            $scope.orderdetail.quantity = item.quantity;
            $http.post(host + "order-detail", $scope.orderdetail).then(resp => {
                $scope.UpdateQuantity(item.id, item.quantity);
            })
        });
    })
    localStorage.removeItem('cart');
    $http.get(host + "coupon-code/" + params.code).then(resp => {
        resp.data.quantity = resp.data.quantity - 1;
        $http.put(host + "coupon", resp.data).then(resp => {
        })
    })
    $scope.UpdateQuantity = function (id,quantity) {
        $http.get(host + "product-detail/" + id).then(resp => {
            resp.data.quantity = resp.data.quantity - quantity;
            $http.put(host + "product-detail" ,resp.data).then(resp => {

            })
        })
    }
})