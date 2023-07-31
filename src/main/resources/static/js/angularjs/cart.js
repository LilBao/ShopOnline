var host = "http://localhost:8080/api/";
var app = angular.module('shoppingCart', []);
app.controller("cartCtrl", function ($scope, $http) {
    $scope.quantity = 0;
    $scope.cart = {
        items: [],
        addToCart(idProd) {
            var item = this.items.find(item => item.id == idProd)
            if (item) {
                item.quantity += $scope.quantity;
                this.saveToLocalStorage();
            } else {
                $http.get(host + 'product-detail/' + idProd).then(resp => {
                    resp.data.quantity = $scope.quantity;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }
        },

        remove(id) {
            var item = this.items.findIndex(item => item.id == id);
            this.items.splice(item, 1);
            this.saveToLocalStorage();
        },

        getCount() {
            return this.items.map(item => item.quantity).reduce((total, quantity) => total += quantity, 0);
        },

        getAmount() {
            return this.items.map(item =>item.product.promotionprice > 0 ? item.quantity*(item.product.price - (item.product.price*item.product.promotionprice/100)) 
                                : item.quantity * item.product.price).reduce((total, amount) => total += amount, 0);
        },

        saveToLocalStorage() {
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },

        loadFromLocalStorage() {
            var json = localStorage.getItem('cart');
            this.items = json ? JSON.parse(json) : [];
        },
        clear() {
            this.items = [];
            this.saveToLocalStorage()
        }
    }
    $scope.readFile = function (files) {
        return host + "file/images/" + files;
    }


    $scope.maxQuantity = function (id) {
        $http.get(host + "product-detail/" + id).then(resp => {
            $scope.quantity = resp.data.quantity;
        })
    }
    $scope.cart.loadFromLocalStorage();

    $scope.Discount = function(){
        const code = $scope.voucher;
        $http.get(host+"coupon-code/"+code).then(resp => {
            console.log($scope.cart.getAmount());
            if(resp.data.type === "percentage"){
                var discount =$scope.cart.getAmount() - ($scope.cart.getAmount() * resp.data.value/100)
                document.getElementById('total').innerText=discount;
                document.getElementById('discount').innerText=($scope.cart.getAmount() * resp.data.value/100);
                document.getElementById('useVoucher').setAttribute('hidden',true);
            }else{
                var discount =$scope.cart.getAmount() - resp.data.value;
                document.getElementById('total').innerText=discount;
                document.getElementById('discount').innerText=resp.data.value;
                document.getElementById('useVoucher').setAttribute('hidden',true);
            }
        })
    };
})
app.controller("orderCtrl", function ($scope, $http) {
    $scope.listProvinces = [];
    $scope.listDistricts = [];
    $scope.listWards = [];
    $scope.infor = {};
    $scope.items=[];
    $scope.items = localStorage.getItem('cart') ? JSON.parse(localStorage.getItem('cart')) : [];
    $scope.orderdetail={};
    $scope.Provinces = function () {
        $http.get("https://provinces.open-api.vn/api/p/").then(resp => {
            $scope.listProvinces = resp.data;
        })
    }

    $scope.Districts = function () {
        var district = $scope.province;
        $http.get("https://provinces.open-api.vn/api/p/" + district + "?depth=2").then(resp => {
            $scope.listDistricts = resp.data.districts;
        })
    }

    $scope.Wards = function () {
        var ward = $scope.district;
        $http.get("https://provinces.open-api.vn/api/d/" + ward + "?depth=2").then(resp => {
            $scope.listWards = resp.data.wards;
        })
    }

    $scope.Provinces();

    $scope.payment = function () {
        var TypePayment =document.getElementById('cod').checked;
        const province = document.getElementById('province');
        const district = document.getElementById('district');
        const wards = document.getElementById('ward');
        $scope.infor.address = $scope.infor.address + ", " + wards.options[wards.selectedIndex].text + ", " + district.options[district.selectedIndex].text + ", " + province.options[province.selectedIndex].text;
        $scope.infor.subtotal =Number(document.getElementById('subtotal').innerText);
        $scope.infor.discount=Number(document.getElementById('discount').innerText);
        $scope.infor.total = Number(document.getElementById('total').innerText);
        if (TypePayment) {
            $http.post(host+"order",$scope.infor).then(resp =>{
                $scope.items.forEach(item => {
                    console.log(item)
                    $scope.orderdetail.order=resp.data;
                    $scope.orderdetail.product=item.product;
                    $scope.orderdetail.productname=item.product.name;
                    $scope.orderdetail.price=item.product.price;
                    $scope.orderdetail.quantity=item.quantity;
                    $http.post(host+"order-detail",$scope.orderdetail).then(resp =>{
                        alert("thanh toán thành công");
                    })
                });
                localStorage.removeItem('cart')
            })
        }else{
            alert("Dang chuyển qua vnPay")
        }
    }

})