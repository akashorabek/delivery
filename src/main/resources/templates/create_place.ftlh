<#import "macros.ftlh" as p>

<!DOCTYPE html>
<html lang="en">
<head>
    <@p.head />
    <title>Добавить заведение</title>
</head>
<body>
<@p.header />


<div class="container">
    <div class="d-flex align-items-center justify-content-center">
        <form action="/admin/places/add" method="post" class="my_form">
            <h3 class="title text-center mb-5">Добавление заведения</h3>
            <div class="mb-3">
                <label class="form-label">Название</label>
                <input name="name" type="text" class="form-control" placeholder="Введите название заведения" required>
            </div>
            <div class="mb-3">
                <label" class="form-label">Изображение</label>
                <input name="image" type="text" class="form-control" placeholder="Вставьте ссылку на изображение" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Описание</label>
                <textarea name="description" class="form-control" placeholder="Введите описание заведения"></textarea>
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