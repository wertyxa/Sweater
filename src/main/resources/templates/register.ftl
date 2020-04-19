<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <div class="mb-1">Add new User</div>
<p ${message?ifExists}></p>
<@l.login "/register" true/>
</@c.page>