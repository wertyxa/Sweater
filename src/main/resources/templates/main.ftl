<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <div>
        <@l.logout/>
    </div>
    <div>
        <span><a href="/user"> User List</a></span>
    </div>
    <div>
        <form method="post" action="/main">
            <input type="text" name="text" placeholder="Введіть текст повідомлення">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="text" name="tag" placeholder="Введіть тег">
            <button type="submit">Добавити</button>
        </form>
    </div>
    <div>
        <form method="get" action="/main">
            <input type="text" name="filter" value="${filter?ifExists}">
            <button type="submit">Знайти</button>
        </form>
    </div>

    <h3>Список повідомлень</h3>
    <#list messages as message>
        <div>
            <b >${message.id}</b>
            <span >${message.text}</span>
            <i >${message.tag}</i>
            <strong >${message.authorName}</strong>
        </div>
        <#else >
        No message
    </#list>

</@c.page>