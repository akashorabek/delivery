<#macro head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/styles.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <script src="/js/script.js"></script>
    <#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
</#macro>

<#macro header>

    <header class="bg-light mb-5">
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-light">
                <a class="navbar-brand" href="/">Delivery</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <@security.authorize access="hasRole('ROLE_ANONYMOUS')">
                            <li class="nav-item">
                                <a class="nav-link" href="/login">Войти</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/register">Регистрация</a>
                            </li>
                        </@security.authorize>
                        <@security.authorize access="hasAuthority('ADMIN')">
                            <li class="nav-item">
                                <a class="nav-link" href="/admin/places/add">Добавить заведения</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/admin/foods/add">Добавить блюдо</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/admin/users">Все пользователи</a>
                            </li>
                        </@security.authorize>
                        <@security.authorize access="isAuthenticated()">
                            <li id="create_products_link" class="nav-item">
                                <form action="/logout" method="post">
                                    <input type="hidden"
                                           name="${(_csrf.parameterName)!'csrf-param-name'}"
                                           value="${(_csrf.token)!'csrf-token'}" />
                                    <button class="btn btn-primary"  href="/places/add">Выйти</button>
                                </form>
                            </li>
                        </@security.authorize>
                    </ul>
                </div>
            </nav>
        </div>
    </header>
</#macro>