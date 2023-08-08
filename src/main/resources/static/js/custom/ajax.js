$(document).ready(function () {
    window.detail = function (id, size) {
        $.ajax({
            url: '/product/' + id + '?size=' + size,
            type: 'get',
            success: function (data) {
                $('#detail').html($(data).find('#detail').children());
                
                // Dòng này sẽ lấy scope của phần tử có id="detail" sử dụng AngularJS.
                var scope = angular.element($('#detail')).scope();

                //Dòng này sẽ lấy service $compile từ injector của AngularJS.
                var $compile = angular.element($('#detail')).injector().get('$compile');

                // Dòng này sẽ biên dịch lại các phần tử con của #detail với scope đã lấy được và áp dụng các tính năng của AngularJS vào phần tử vừa biên dịch.
                $compile($('#detail').contents())(scope);
            }
        })
    }

    window.addWishList = function (auth, idPrd) {
        if(auth==null){
            window.location.href="/auth";
        }
        $.ajax({
            url: '/api/addWishlist?auth=' + auth + '&idProduct=' + idPrd,
            type: 'get',
            success: function (data) {
                alert("Add wishlish successful");
            }
        })
    }

    window.unLike = function (id) {
        $.ajax({
            url: '/api/wishlist/'+ id,
            type: 'delete',
            success: function (data) {
                alert("Unlike successful");
                $('#favor').html($(data).find('#favor').children());
            }
        })
    }
})