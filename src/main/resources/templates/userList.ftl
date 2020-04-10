<#import "parts/common.ftl" as c>
<@c.page>
    List of User
    <table>
        <thead>
        <th>

            <td>Name</td>
            <td>Role</td>
        </th>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/user/${user.id}">Edit</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>