<#import "parts/common.ftl" as c>
<@c.page>
   User Editor
   <form action="/user" method="post">
      <input type="hidden" value="${_csrf.token}" name="_csrf">
      <input type="text" name="username" value="${user.username}">
      <#list roles as role>
         <label ><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked","")}>${role}</label>
      </#list>
      <input type="hidden" name="userId" value="${user.id}">
      <button type="submit">Save</button>
   </form>
</@c.page>