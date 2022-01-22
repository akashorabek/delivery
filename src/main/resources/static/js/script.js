function handleBasket(response) {
    $('.cart_aside_wrapper').empty()
    $('.cart_aside_sum').empty()
    response.basketItems.map(i => {
        let cartItem = $(`
             <div class="cart_item">
                 <h5>${i.food.name}</h5>
                 <h5>Цена: ${i.food.price}</h5>
                 <h5>Порций: ${i.count}</h5>
             </div>
        `);
        let deleteBtn = $('<button class="btn btn-warning">Удалить порцию</button>');
        deleteBtn.click(function (){
            deleteFromBasket(i.id, response.place.id)
        })
        cartItem.append(deleteBtn)
        $('.cart_aside_wrapper').append(cartItem)
    })

    $('.cart_aside_sum').append($(`<h3>Сумма: ${response.sum}</h3>`))
}

function deleteFromBasket(basketItemId, placeId) {
    var _csrf_header = $('meta[name=_csrf_header]').attr('content');
    var _csrf_token = $('meta[name=_csrf_token]').attr('content');
    $.ajax({
        method: 'POST',
        beforeSend: function (xhr) {
            xhr.setRequestHeader(_csrf_header, _csrf_token);
        },
        url: "http://localhost:8080/basket/delete",
        data: {
            basketItemId: basketItemId,
            placeId: placeId
        },
        success: (response) => {
            handleBasket(response)
        },
        error: (response) => {
            alert(`${response['responseText']}`)
        }
    });
}

function getBasket(placeId) {
    $.ajax({
        method: 'GET',
        url: "http://localhost:8080/basket/" + placeId,
        success: (response) => {
            handleBasket(response)
        },
        error: (response) => {
            alert(response)
        }
    });
}

function addToBasket(foodId) {
    var _csrf_header = $('meta[name=_csrf_header]').attr('content');
    var _csrf_token = $('meta[name=_csrf_token]').attr('content');
    $.ajax({
        method: 'POST',
        beforeSend: function (xhr) {
            xhr.setRequestHeader(_csrf_header, _csrf_token);
        },
        url: "http://localhost:8080/basket/add",
        data: {
            foodId: foodId
        },
        success: (response) => {
            handleBasket(response)
        },
        error: (response) => {
            alert(`${response['responseText']}`)
        }
    });
}
