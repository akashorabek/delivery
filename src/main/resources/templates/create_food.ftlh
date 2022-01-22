<#import "macros.ftlh" as p>

<!DOCTYPE html>
<html lang="en">
<head>
    <@p.head />
    <title>Добавить блюдо</title>
</head>
<body>
<@p.header />


<div class="container">
    <div class="d-flex align-items-center justify-content-center">
        <form action="/admin/foods/add" method="post" class="my_form">
            <h3 class="title text-center mb-5">Добавление блюда</h3>
            <div class="mb-3">
                <label class="form-label">Название</label>
                <input name="name" type="text" class="form-control" placeholder="Введите название блюда" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Цена</label>
                <input name="price" type="number" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Описание</label>
                <textarea name="description" class="form-control" placeholder="Введите описание блюда"></textarea>
            </div>
            <div class="mb-3">
                <label class="form-label">Заведение</label>
                <select name="placeId" class="form-control">
                    <#list places as place>
                        <option value="${place.id}">${place.name}</option>
                    </#list>
                </select>
            </div>
            <div class="text-center mb-5">
                <#if bindErrors??>
                    <#list bindErrors as error>
                        <div class="alert alert-danger" role="alert">${error}</div>
                    </#list>
                </#if>
                <#if _csrf??>
                    <input type="hidden"
                           name="${(_csrf.parameterName)!'csrf-param-name'}"
                           value="${(_csrf.token)!'csrf-token'}" />
                </#if>
                <button type="submit" class="btn btn-primary">Добавить</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>