<#import "parts/common.ftl" as c>
<@c.page>
    <div>
        <a class="btn btn-primary my-2" data-toggle="collapse" href="#addMassage" role="button" aria-expanded="false" aria-controls="collapseExample">
            Add New Message
        </a>
    </div>
    <div class="collapse" id="addMassage">
        <div class="form-group mb-3">
            <form method="post" action="/main" enctype="multipart/form-data">
                <input  type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <input class="form-control col-sm-6" type="text" name="text" placeholder="Введіть текст повідомлення">
                </div>
                <div  class="form-group">
                    <input class="form-control col-sm-6" type="text" name="tag" placeholder="Введіть тег">
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" id="file" name="file">
                        <label for="file" class="custom-file-label col-sm-6">Виберати файл</label>
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary my-2">Добавити</button>
                </div>
            </form>
        </div>
    </div>

    <div class="form-row my-2">
        <div class="form-group ">
            <form method="get" action="/main" class="form-inline">
                <input type="text" class="form-control col-md-8" name="filter" value="${filter?ifExists}" placeholder="Знайти по тегу">
                <button type="submit" class="btn btn-primary mx-1">Знайти</button>
            </form>
        </div>
    </div>
    <div>
    </div>

    <h3>Список повідомлень</h3>
    <div class="card-columns">
    <#list messages as message>
        <div class="card my-3" >
        <div>
            <#if message.filename??>
                <img src="/img/${message.filename}" class="card-img-top">
            </#if>
            <div class="m-2">
                <span >${message.text}</span>
                <i >${message.tag}</i>
            </div>
            <div class="card-footer text-muted">
                ${message.authorName}
            </div>
        </div>
        </div>
        <#else >
        No message
    </#list>
    </div>

</@c.page>