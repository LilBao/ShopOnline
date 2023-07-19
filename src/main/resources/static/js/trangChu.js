var myApp = angular.module('myApp', []);
// danhMuc
myApp.controller('dmCtrl', function ($scope, $http) {
  $scope.danhMuc = [];
  $http.get('js/trangChu.json').then(function (response) {
    $scope.danhMuc = response.data.dmjs;
  })
})
// banner
myApp.controller('bannerCtrl', function ($scope, $http) {
  $scope.banners = [];
  $http.get('js/trangChu.json').then(function (response) {
    $scope.banners = response.data.bannerjs;
  })
})
// bestseller
myApp.controller('imgSpCtrl', function ($scope,$http) {
  $scope.imgSp = []
  $http.get('js/trangChu.json').then(function(response){
    $scope.imgSp=response.data.imgSpJs;
  })
})

myApp.controller('desCtrl', function ($scope,$http) {
  $scope.des = []
  $http.get('js/trangChu.json').then(function(response){
    $scope.des=response.data.desJs;
  })
})

myApp.controller('sizeCtrl', function ($scope,$http) {
  $scope.size = []
  $http.get('js/trangChu.json').then(function(response){
    $scope.size=response.data.sizeJs;
  })
})

// feedback
myApp.controller('fbCtrl', function ($scope,$http) {
  $scope.fb = []
  $http.get('js/trangChu.json').then(function(response){
    $scope.fb=response.data.fbjs;
  })
})
// ins toast
myApp.controller('insCtrl', function ($scope,$http) {
  $scope.ins = [];
  $http.get('js/trangChu.json').then(function(response){
    $scope.ins=response.data.insjs
  })
})
// SPNB
myApp.controller('spnbCtrl', function ($scope,$http) {
  $scope.spnb = []
  $http.get('js/trangChu.json').then(function(response){
    $scope.spnb=response.data.spnbjs;
  })
})
// Chính sách
myApp.controller('csCtrl', function ($scope,$http) {
  $scope.cs = []
  $http.get('js/trangChu.json').then(function(response){
    $scope.cs=response.data.csjs;
  })
})
// Footer
myApp.controller('footerCtrl', function ($scope,$http) {
  $scope.ft = []
  $http.get('js/trangChu.json').then(function(response){
    $scope.ft=response.data.ftjs;
  })
})

myApp.controller('formCtrl', function($scope) {
    $scope.master = {user:"vanbao", pass:"123"};
    $scope.submit = function() {
        if($scope.account===$scope.master){
          alert('Đăng nhập thành công')
        }else{
          alert('Kiểm tra lại tài khoản')    
        }
    };
  
});

function toast() {
  var toastElList = [].slice.call(document.querySelectorAll('.toast'))
  var toastList = toastElList.map(function(toastEl) {
    return new bootstrap.Toast(toastEl)
  })
  toastList.forEach(toast => toast.show()) 
}
