var app = angular.module('myApp', []);
var host = "http://localhost:8080/api/"
app.controller('myCtrl', function ($scope, $http) {
    $scope.form = {};
    $scope.listCate = [];
    $scope.listProduct = [];

    $scope.loadCate = function () {
        var url = host + "cate";
        $http.get(url).then(resp => {
            $scope.listCate = resp.data;
        })
    }

    $scope.loadProduct = function () {
        var url = host + "product";
        $http.get(url).then(resp => {
            $scope.listProduct = resp.data;
        })
    }

    $scope.createPrd = function () {
        var url = host + "product";
        var item = angular.copy($scope.form);
        $http.post(url, item).then(resp => {
        }).catch(error => {
            console.log("Errors", error);
        });
    }

    $scope.updatePrd = function () {
        var url = host + "product";
        var item = angular.copy($scope.form);
        $http.put(url, item).then(resp => {
        }).catch(error => {
            console.log("Errors", error);
        });
    }

    $scope.delete = function (id) {
        var url = host + "product/"+id;
        var item = angular.copy($scope.form);
        $http.delete(url).then(resp => {
            $scope.reset();
        }).catch(error => {
            console.log("Errors", error);
        });
    }

    $scope.edit = function (id) {
        var url = host + "product/"+id;     
        $http.get(url).then(resp => {
            $scope.form =resp.data;
        }).catch(error => {
            console.log("Errors", error);
        });
    }

    $scope.reset = function () {
        $scope.form ={};
    }

    $scope.imageThumbnail = function (files) {
        var imagesData = new FormData();
        for (var i = 0; i < files.length; i++) {
            imagesData.append("files", files[i]);
        }

        $http.post(host + "file/images", imagesData, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.form.thumbnail = resp.data.toString();
        }).catch(error => {
            console.log("Errors", error);
        });
    }

    $scope.images = function (files) {
        var imagesData = new FormData();
        for (var i = 0; i < files.length; i++) {
            imagesData.append("files", files[i]);
        }
        $http.post(host + "file/images", imagesData, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.form.listimage =[...$scope.form.listimage, ...resp.data] ;
        }).catch(error => {
            console.log("Errors", error);
        });
    }

    $scope.removeImage=function(file){
        let url = host +"file/images"+file;
        $scope.form.listimage = $scope.form.listimage.filter(item => item !==file);
        $scope.updatePrd();
        $http.delete(url).then(resp => {
            alert("xóa ảnh thành công")
        }).catch(error => {
            console.log("Errors", error);
        });
        return file;
    }

    $scope.sort = function (field) {
        $scope.direction = $scope.direction === "asc" ? "desc" : "asc";
            if ($scope.direction === "asc") {
                $scope.listProduct.sort((a, b) => a[field].localeCompare(b[field]))
            } else {
                $scope.listProduct.sort((a, b) => b[field].localeCompare(a[field]))
            }
    }
    $scope.sortNumber = function (field) {
        $scope.direction = $scope.direction === "asc" ? "desc" : "asc";
            if ($scope.direction === "asc") {
                $scope.listProduct.sort((a, b) => a[field] - (b[field]))
            } else {
                $scope.listProduct.sort((a, b) => b[field] - (a[field]))
            }
    }

    $scope.readFile = function (files) {
        return host+"file/images/"+files;
    }

    $scope.loadCate();
    $scope.loadProduct();

    $scope.pager={
        page:0,
        size:5,
        get items(){
            var start = this.page * this.size;
            return $scope.listProduct.slice(start,start+this.size)
        },
        get count(){
            return Math.ceil(1* $scope.listProduct.length / this.size)
        },
        prev(){
            this.page--;
            if(this.page <0){
                this.page = this.count-1; 
            }
        },
        next(){
            this.page++;
            if(this.page >= this.count){
                this.page=0;
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