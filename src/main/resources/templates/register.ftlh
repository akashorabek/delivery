<#import "macros.ftlh" as p>

<!DOCTYPE html>
<html lang="en">
<head>
    <@p.head />
    <title>Войти</title>
</head>
<body>
<@p.header />


<div class="container">
    <h2 class="title text-center mb-5 mt-4">Регистрация</h2>
    <div class="d-flex align-items-center justify-content-center">
        <form class="my_form" action="/register" method="post">
            <#if hasError??>
                <div class="alert alert-danger" role="alert">

                </div>
            </#if>
            <div class="form-group">
                <label for="exampleInputEmail1">Email address</label>
                <input name="email" type="email" class="form-control" aria-describedby="emailHelp" placeholder="Enter email" required>
                <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input name="password" type="password" class="form-control" placeholder="Password" required>
            </div>
            <#if _csrf??>
                <input type="hidden"
                       name="${(_csrf.parameterName)!'csrf-param-name'}"
                       value="${(_csrf.token)!'csrf-token'}" />
            </#if>
            <button type="submit" class="btn btn-primary">Регистрироваться</button>
        </form>
    </div>
</div>
</body>
</html>