<#import "macros.ftlh" as p>

<!DOCTYPE html>
<html lang="en">
<head>
    <@p.head />
    <title>Подробнее о заведений</title>
    <#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <meta name="_csrf_token" content="${_csrf.token}"/>
</head>
<body>
<@p.header />


<div class="d-flex justify-content-between">
    <div class="container">
        <h3 class="title text-center mt-4">Подробнее р ${place.name}</h3>
        <div class="row no-gutters g-0 mb-5">
            <div class="col-6">
                <p>Название: <b>${place.name}</b></p>
                <p>Описание: <b>${place.description}</b></p>
            </div>
            <div class="col-5">
                <img src="${place.image}" class="img-fluid" />
            </div>
        </div>
        <h4 class="title text-center my-3">Все блюда данного заведения</h4>
        <div class="row">
            <#list foods as food>
                <div class="col-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${food.name}</h5>
                            <h6 class="card-subtitle mb-2 text-muted">${food.price}</h6>
                            <p class="card-text">${food.description}</p>
                            <@security.authorize access="isAuthenticated()">
                                <button onclick="addToBasket(${food.id}, ${place.id})" class="btn btn-primary my-2">В корзину</button>
                            </@security.authorize>
                            <@security.authorize access="hasAuthority('ADMIN')">
                                <form action="/admin/foods/delete" method="post">
                                    <#if _csrf??>
                                        <input type="hidden"
                                               name="${(_csrf.parameterName)!'csrf-param-name'}"
                                               value="${(_csrf.token)!'csrf-token'}" />
                                    </#if>
                                    <input type="hidden" name="foodId" value="${food.id}">
                                    <button class="btn btn-danger">Удалить</button>
                                </form>
                            </@security.authorize>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>

    <aside class="cart_aside">
        <h4>Корзина: ${place.name}</h4>
        <div class="cart_aside_wrapper"></div>
        <div class="cart_aside_sum"></div>
    </aside>
</div>


<script>
    $(document).ready(function (){
        getBasket(${place.id})
    })
</script>
</body>
</html>