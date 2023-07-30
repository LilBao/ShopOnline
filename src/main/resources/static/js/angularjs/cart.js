var host = "http://localhost:8080/api/";
var app = angular.module('shoppingCart',[]);
app.controller("cartCtrl", function ($scope, $http) {
    $scope.cart={
        items: [],
        addToCart(idProd){
            var item = this.items.find(item => item.id == idProd)
            if(item){
                item.quantity+=$scope.quantity;
                this.saveToLocalStorage();
            }else{
                $http.get(host+'product-detail/'+idProd).then(resp =>{
                    resp.data.quantity = $scope.quantity;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }
        },

        remove(id){
            var item = this.items.findIndex(item => item.id == id);
            this.items.splice(item,1);
            this.saveToLocalStorage();
        },

        getCount(){
            return this.items.map(item => item.quantity).reduce((total,quantity) => total +=quantity,0);
        },

        getAmount(){
            return this.items.map(item =>item.quantity * item.product.price).reduce((total,amount)=>total +=amount,0);
        },
        
        saveToLocalStorage(){
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart",json);
        },

        loadFromLocalStorage(){
            var json = localStorage.getItem('cart');
            this.items = json ? JSON.parse(json) : [];
        },
        clear(){
            this.items=[];
            this.saveToLocalStorage()
        }
    }
    $scope.readFile = function (files) {
        return host+"file/images/"+files;
    }

    $scope.quantity=0;
    $scope.maxQuantity = function (id) {
        $http.get(host+"product-detail/"+id).then(resp=>{
            $scope.quantity=resp.data.quantity;
        })
    }

    $scope.cart.loadFromLocalStorage();
})