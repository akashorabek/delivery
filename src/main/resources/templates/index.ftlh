<#import "macros.ftlh" as p>

<!DOCTYPE html>
<html lang="en">
<head>
    <@p.head />
    <title>Главная</title>
    <#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
</head>
<body>
<@p.header />


<div class="container">
    <h2 class="title text-center mb-5 mt-4">Все заведения</h2>
    <#if psqlError??>
        <div class="alert alert-warning my-2" role="alert">
            ${psqlError}
        </div>
    </#if>
    <div class="row no-gutters g-0">
        <#list places as place>
            <div class="col-4">
                <div class="card">
                    <img class="card-img-top card_img" src="${place.image}" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title">${place.name}</h5>
                        <a href="/places/${place.id}" class="btn btn-primary">Подробнее</a>
                        <@security.authorize access="hasAuthority('ADMIN')">
                            <form action="/admin/places/delete" method="post">
                                <#if _csrf??>
                                    <input type="hidden"
                                           name="${(_csrf.parameterName)!'csrf-param-name'}"
                                           value="${(_csrf.token)!'csrf-token'}" />
                                </#if>
                                <input type="hidden" name="placeId" value="${place.id}">
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