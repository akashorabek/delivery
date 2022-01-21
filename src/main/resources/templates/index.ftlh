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
    <h2 class="title text-center mb-5 mt-4">Все заведения</h2>
    <div class="row no-gutters g-0">
        <#list places as place>
            <div class="col-4">
                <div class="card">
                    <img class="card-img-top card_img" src="${place.image}" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title">${place.name}</h5>
                        <a href="/places/${place.id}" class="btn btn-primary">Подробнее</a>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</div>
</body>
</html>