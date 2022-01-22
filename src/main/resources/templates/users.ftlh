<#import "macros.ftlh" as p>

<!DOCTYPE html>
<html lang="en">
<head>
    <@p.head />
    <title>Главная</title>
</head>
<body>
<@p.header />


<div class="container">
    <h2 class="title text-center mb-5 mt-4">Все пользователи</h2>
    <div class="row no-gutters g-0">
        <#list users as user>
            <div class="col-4">
                <div class="card">
                    <div class="card-body">
                        <p class="card-text">Почта: <b>${user.email}</b></p>
                        <p class="card-text">Роль: <b>${user.role}</b></p>
                        <p class="card-text">Аккаунт: <b>${user.enabled?string("активен", "деактивен")}</b></p>
                        <label class="mt-2">Изменить роль</label>
                        <form class="d-flex justify-content-between mb-3" action="/admin/users/role" method="post">
                            <#if _csrf??>
                                <input type="hidden"
                                       name="${(_csrf.parameterName)!'csrf-param-name'}"
                                       value="${(_csrf.token)!'csrf-token'}" />
                            </#if>
                            <select class="form-control w-75" name="role">
                                <option value="USER">USER</option>
                                <option value="ADMIN">ADMIN</option>
                            </select>
                            <input type="hidden" name="userId" value="${user.id}">
                            <button class="btn btn-primary">Изменить</button>
                        </form>
                        <label>Изменить активность аккаунта</label>
                        <form class="d-flex justify-content-between mb-3" action="/admin/users/enabled" method="post">
                            <#if _csrf??>
                                <input type="hidden"
                                       name="${(_csrf.parameterName)!'csrf-param-name'}"
                                       value="${(_csrf.token)!'csrf-token'}" />
                            </#if>
                            <input type="hidden" name="userId" value="${user.id}">
                            <select class="form-control w-75" name="enabled">
                                <option value="true">Активен</option>
                                <option value="false">Деактивен</option>
                            </select>
                            <button class="btn btn-primary">Изменить</button>
                        </form>
                        <form class="my-2" action="/admin/users/delete" method="post">
                            <#if _csrf??>
                                <input type="hidden"
                                       name="${(_csrf.parameterName)!'csrf-param-name'}"
                                       value="${(_csrf.token)!'csrf-token'}" />
                            </#if>
                            <input type="hidden" name="userId" value="${user.id}">
                            <button class="btn btn-danger">Удалить</button>
                        </form>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</div>
</body>
</html>