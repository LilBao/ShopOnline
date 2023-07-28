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

    $scope.readFile = function (files) {
        return host+"file/images/"+files;
    }

    $scope.loadCate();
    $scope.loadProduct();
})