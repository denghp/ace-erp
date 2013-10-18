<#macro errorMesage>
<#if error?exists >
<div class="alert alert-error">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <span class="icon-remove-sign icon-large"></span>&nbsp;${error}
</div>
</#if>
</#macro>