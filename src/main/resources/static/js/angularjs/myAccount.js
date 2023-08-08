var host = "http://localhost:8080/api/"
var app = angular.module('myApp', []);
app.controller('myCtrl', function ($scope, $http) {
    $scope.account = {};


    $scope.setAvatar = function (files) {
        var imagesData = new FormData();
        for (var i = 0; i < files.length; i++) {
            imagesData.append("files", files[i]);
        }
        $http.post(host + "file/avatar", imagesData, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.account.avatar = resp.data.toString();
        }).catch(error => {
            console.log("Errors", error);
        });
    }
    $scope.findUser = function () {
        var username = document.getElementById('username').value;
        $http.get(host + "auth/" + username).then(resp => {
            $scope.account = resp.data;
        })
    }
    $scope.findUser();

    $scope.updateUser = function () {
        $scope.findUser();
        $scope.account.fullname = document.getElementById('fullname').value
        $scope.account.email = document.getElementById('email').value
        $scope.account.phone = document.getElementById('phone').value
        $scope.account.birthday = document.getElementById('date').value
        $http.put(host + "auth", $scope.account).then(resp => {
            $scope.account = resp.data;
            document.getElementById('fullname').value = resp.data.fullname;
            document.getElementById('email').value = resp.data.email;
            document.getElementById('phone').value = resp.data.phone;
            document.getElementById('date').value = resp.data.birthday;
            console.log(resp.data);
        })
    }

    $scope.readFile = function (files) {
        return host + "file/avatar/" + files;
    }
})