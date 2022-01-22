<#import "macros.ftlh" as p>

<!DOCTYPE html>
<html lang="en">
<head>
    <@p.head />
    <title>Подробнее о заведений</title>
    <#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
</head>
<body>
<@p.header />


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
    <div class="row">
        <#list place.foods as food>
            <div class="col-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${food.name}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">${food.price}</h6>
                        <p class="card-text">${food.description}</p>
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
</body>
</html>