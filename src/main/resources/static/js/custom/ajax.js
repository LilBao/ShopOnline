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
        if (auth == null) {
            window.location.href = "/auth";
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
            url: '/api/wishlist/' + id,
            type: 'delete',
            success: function (data) {
                alert("Unlike successful");
                $('#favor').html($(data).find('#favor').children());
            }
        })
    }

    window.deleteAccount = function (username) {
        $.ajax({
            url: '/admin-account/delete/' + username,
            type: 'get',
            success: function (data) {
                $('#tblAccount').html($(data).find('#tblAccount').children());
            }
        })
    }

    window.changeInfor = function () {
        var address = $('#addressChange').val;
        var phone = $('#phoneChange').val;
        $.ajax({
            url: 'change-infor?address=' + address + '&phone=' + phone,
            type: 'get',
            success: function (data) {
                $('#changeInfor').html($(data).find('#changeInfor').children());
            }
        })
    }

    window.searchProduct = function (key) {
        $.ajax({
            url: 'search?keyword=' + key,
            type: 'get',
            success: function (data) {
                $('#resultcontent').html($(data).find('#resultcontent').children());
            }
        })
    }

    window.sort = function (cate,pageNo,pageSize,sortField,sortDir, size, min, max) {
        $.ajax({
            url: '/product-collections?cate=' + cate + '&pageNo=' + pageNo + '&pageSize=' + pageSize + '&sortField=' + sortField + '&sortDir=' + sortDir + '&size=' + size+'&min=' + min+'&max=' + max,
            type: 'get',
            success: function(data){
                $('#product').html($(data).find('#product').children());
                $.getScript('../js/categories_custom.js', function() {
                });  
            }
        })
    }
})