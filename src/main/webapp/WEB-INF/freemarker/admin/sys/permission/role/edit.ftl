<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h4 class="modal-title">角色授权</h4>
</div>
<div class="modal-body">
    <div class="row">
    <div class="col-sm-12">
        <div class="tabbable">
            <ul class="nav nav-tabs" id="myTab">
                <li class="active">
                    <a data-toggle="tab" href="#home">
                        <i class="green icon-home bigger-110"></i>
                        角色基本信息
                    </a>
                </li>

                <li>
                    <a data-toggle="tab" href="#profile">
                        <i class="green icon-cogs bigger-110"></i>
                        授权信息
                    </a>
                </li>

            </ul>

            <div class="tab-content">
                <div id="home" class="tab-pane in active">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-1">
                                角色名称 </label>

                            <div class="col-sm-9">
                                <input type="text" id="form-field-1" placeholder="Username" value="${role.name}"
                                       class="col-xs-10 col-sm-5">
                            </div>
                        </div>

                        <div class="space-4"></div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-field-2">
                                角色标识 </label>

                            <div class="col-sm-9">
                                <input type="text" id="form-field-2" value="${role.role}" placeholder="Password"
                                       class="col-xs-10 col-sm-5">
                            </div>
                        </div>

                        <div class="space-4"></div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="form-input-readonly">
                                详细描述
                            </label>

                            <div class="col-sm-9">
                                <textarea class="form-control" id="form-field-8"
                                          placeholder="Default Text">${role.description}</textarea>
                            </div>
                        </div>

                        <div class="space-4"></div>

                        <div class="form-group">

                            <label class="col-sm-3 control-label no-padding-right" for="form-field-4">状
                                态</label>

                            <div class="col-sm-9">
                                <div class="control-group">
                                <#if role.show ? string('true','false') == 'true'>
                                    <div class="radio">
                                        <label>
                                            <input name="form-field-radio" checked="checked" type="radio"
                                                   class="ace">
                                            <span class="lbl"> 可用</span>
                                        </label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input name="form-field-radio" type="radio" class="ace">
                                            <span class="lbl"> 不可用</span>
                                        </label>
                                    </div>
                                <#else>
                                    <div class="radio">
                                        <label>
                                            <input name="form-field-radio" type="radio" class="ace">
                                            <span class="lbl"> 可用</span>
                                        </label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input checked="checked" name="form-field-radio" type="radio"
                                                   class="ace">
                                            <span class="lbl"> 不可用</span>
                                        </label>
                                    </div>
                                </#if>
                                </div>
                            </div>
                        </div>

                    </form>
                </div>

                <div id="profile" class="tab-pane">
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="widget-box">
                                <div class="widget-header header-color-blue2">
                                    <h4 class="lighter smaller">资源信息</h4>
                                </div>

                                <div class="widget-body">
                                    <div class="widget-main padding-8">
                                        <ul id="treeview" class="ztree"
                                            style="height: 240px;width: 450px;margin-top: 0px;">
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /span -->
                        <!--
                                <div class="col-sm-6">
                                    <div class="widget-box">
                                        <div class="widget-header header-color-green2">
                                            <h4 class="lighter smaller">资源权限</h4>
                                        </div>
                                        <div class="widget-body">
                                            <div class="widget-main padding-8" >
                                                <select class="form-control" id="form-field-select"
                                                        multiple="multiple" style="height: 240px">
                                                <#if permissionList?exists>
                                                     <#list permissionList as permission>
                                                         <option value="${permission.permission}">${permission.name} ( ${permission.permission} )</option>
                                                     </#list>
                                                </#if>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                -->
                        <!-- /span -->
                    </div>
                </div>

            </div>
        </div>
    </div>
    </div>
</div>
<div class="modal-footer">
    <button type="button" data-dismiss="modal" class="btn">Close</button>
    <button type="button" id="okBtn" class="btn btn-primary">Ok</button>
</div>


<script type="text/javascript">
    var $path_base = "${rc.getContextPath()}";//this will be used in gritter alerts containing images
</script>

<script type="text/javascript">
    debugger;
    var setting = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };
    var zTreeObj;
    var zNodes = [];
    var permissNodes = [];
    $(document).ready(function () {
        $.ajax({
            type: "GET",
            url: $path_base + '/admin/sys/resource/ajax/load?roleId='+${role.id},
            dataType: "json",
            global: false,
            async: false,
            success: function (strReult) {
                zNodes = eval(strReult);
            },
            error: function () {
                alert("Ajax请求数据失败!");
            }
        });
        zTreeObj = $.fn.zTree.init($("#treeview"), setting, zNodes);
    });

    $('#okBtn').on('click', function(e){
       alert("OK");
        var nodes = zTreeObj.getCheckedNodes();
        for (var i = 0 ; i < nodes.length; i ++) {
            alert("id : "+nodes[i].id+"name : " + nodes[i].name + " checked : " + nodes[i].checked);
        }
    });
</script>