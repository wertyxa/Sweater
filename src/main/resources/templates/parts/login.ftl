<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> User Name : </label>
            <div class="col-sm-6">
                <input type="text" name="username" placeholder="Username" class="form-control"/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <#if isRegisterForm>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Email:</label>
            <div class="col-sm-6">
                <input type="email" name="email" placeholder="some@some.com" class="form-control"/>
            </div>
        </div>
        </#if>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password: </label>
            <div class="col-sm-6">
                <input type="password" name="password" placeholder="Password" class="form-control"/>
            </div>
        </div>
        <div class="form-group"><input type="submit" class="btn btn-primary" value="<#if isRegisterForm >Create<#else>Sign In</#if>"/></div>
        <#if !isRegisterForm><div class="form-group"><a href="/register">Register</a>
        </div></#if>
    </form>
</#macro>
<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit"  class="btn btn-primary ml-3" value="Sign Out"/>
    </form>

</#macro>