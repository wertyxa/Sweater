<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
<h3>Add new User</h3>
<p ${message?ifExists}></p>
<@l.login "/register"/>
</@c.page>