<#import "parts/common.ftl" as c>
<@c.page>
    <h5>${username}</h5>

    <a href="${message?ifExists}"> підтвердити Емайл</a>

    <form method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Email:</label>
                <div class="col-sm-6">
                    <input type="email" name="email" placeholder="some@some.com" class="form-control" value="${email!''}"/>
                </div>
            </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password: </label>
            <div class="col-sm-6">
                <input type="password" name="password" placeholder="Password" class="form-control"/>
            </div>
        </div>
        <div class="form-group"><input type="submit" class="btn btn-primary" value="Save"/></div>
    </form>

</@c.page>