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

    $scope.pager={
        page: 0,
        size: 10,
        get items(){
            var start = this.page * this.size;
            return $scope.listCoupon.slice(start,start+this.size)
        },
        get count(){
            return Math.ceil(1.0* $scope.listCoupon.length / this.size)
        },
        prev(){
            this.page--;
            if(this.page <0){
                this.page = 0; 
            }
        },
        next(list){
            this.page++;
            if(this.page >= this.count){
                this.page=this.count-1;
            }
        },
        getNumbers(n) {
            var rangeArray = [];
            for (var i = 1; i <= n; i++) {
                rangeArray.push(i);
            }
            return rangeArray;
        }
    }
})