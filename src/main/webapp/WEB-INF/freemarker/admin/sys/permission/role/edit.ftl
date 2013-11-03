<!DOCTYPE html>
<html lang="en">
<#include "../../../../commons/header.ftl" >

<body>
<div class="modal-scrollable">
    <div id="ajax-modal" class="modal fade in modal-overflow" tabindex="-1" data-width="760" style="display: block; "
         aria-hidden="false">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h4 class="modal-title">角色授权</h4>
        </div>
        <div class="modal-body">
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
                                授权信息
                                <span class="badge badge-danger">4</span>
                            </a>
                        </li>

                        <li class="dropdown">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                Dropdown &nbsp;
                                <i class="icon-caret-down bigger-110 width-auto"></i>
                            </a>

                            <ul class="dropdown-menu dropdown-info">
                                <li>
                                    <a data-toggle="tab" href="#dropdown1">@fat</a>
                                </li>

                                <li>
                                    <a data-toggle="tab" href="#dropdown2">@mdo</a>
                                </li>
                            </ul>
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
                            <h3 class="header smaller lighter green">
                                <i class="icon-bullhorn"></i>
                                资源对应权限
                            </h3>

                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="widget-box">
                                        <div class="widget-header header-color-blue2">
                                            <h4 class="lighter smaller">资源信息</h4>
                                        </div>

                                        <div class="widget-body">
                                            <div class="widget-main padding-8" >
                                                <ul id="treeDemo" class="ztree" style="height: 240px;width: 300px;margin-top: 0px;">
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-6">
                                    <div class="widget-box">
                                        <div class="widget-header header-color-green2">
                                            <h4 class="lighter smaller">操作权限</h4>
                                        </div>

                                        <div class="widget-body">
                                            <div class="widget-main padding-8">
                                                <select class="form-control" style="height:240px" id="form-field-select-2" multiple="multiple">
                                                    <option value="AL">Alabama</option>
                                                    <option value="AK">Alaska</option>
                                                    <option value="AZ">Arizona</option>
                                                    <option value="AR">Arkansas</option>
                                                    <option value="CA">California</option>
                                                    <option value="CO">Colorado</option>
                                                    <option value="CT">Connecticut</option>
                                                    <option value="DE">Delaware</option>
                                                    <option value="FL">Florida</option>
                                                    <option value="GA">Georgia</option>
                                                    <option value="HI">Hawaii</option>
                                                    <option value="ID">Idaho</option>
                                                    <option value="IL">Illinois</option>
                                                    <option value="IN">Indiana</option>
                                                    <option value="IA">Iowa</option>
                                                    <option value="KS">Kansas</option>
                                                    <option value="KY">Kentucky</option>
                                                    <option value="LA">Louisiana</option>
                                                    <option value="ME">Maine</option>
                                                    <option value="MD">Maryland</option>
                                                    <option value="MA">Massachusetts</option>
                                                    <option value="MI">Michigan</option>
                                                    <option value="MN">Minnesota</option>
                                                    <option value="MS">Mississippi</option>
                                                    <option value="MO">Missouri</option>
                                                    <option value="MT">Montana</option>
                                                    <option value="NE">Nebraska</option>
                                                    <option value="NV">Nevada</option>
                                                    <option value="NH">New Hampshire</option>
                                                    <option value="NJ">New Jersey</option>
                                                    <option value="NM">New Mexico</option>
                                                    <option value="NY">New York</option>
                                                    <option value="NC">North Carolina</option>
                                                    <option value="ND">North Dakota</option>
                                                    <option value="OH">Ohio</option>
                                                    <option value="OK">Oklahoma</option>
                                                    <option value="OR">Oregon</option>
                                                    <option value="PA">Pennsylvania</option>
                                                    <option value="RI">Rhode Island</option>
                                                    <option value="SC">South Carolina</option>
                                                    <option value="SD">South Dakota</option>
                                                    <option value="TN">Tennessee</option>
                                                    <option value="TX">Texas</option>
                                                    <option value="UT">Utah</option>
                                                    <option value="VT">Vermont</option>
                                                    <option value="VA">Virginia</option>
                                                    <option value="WA">Washington</option>
                                                    <option value="WV">West Virginia</option>
                                                    <option value="WI">Wisconsin</option>
                                                    <option value="WY">Wyoming</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div id="dropdown1" class="tab-pane">
                            <p>Etsy mixtape wayfarers, ethical wes anderson tofu before they sold out mcsweeney's
                                organic lomo retro fanny pack lo-fi farm-to-table readymade.</p>
                        </div>

                        <div id="dropdown2" class="tab-pane">
                            <p>Trust fund seitan letterpress, keytar raw denim keffiyeh etsy art party before they sold
                                out master cleanse gluten-free squid scenester freegan cosby sweater. Fanny pack
                                portland seitan DIY, art party locavore wolf cliche high life echo park Austin.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" data-dismiss="modal" class="btn">Close</button>
            <button type="button" class="btn btn-primary">Ok</button>
        </div>
    </div>
</div>
<script type="text/javascript">
    var $path_base = "${rc.getContextPath()}";//this will be used in gritter alerts containing images
</script>
<link rel="stylesheet" href="${rc.getContextPath()}/assets/css/demo.css" type="text/css">
<link rel="stylesheet" href="${rc.getContextPath()}/assets/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${rc.getContextPath()}/assets/js/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="${rc.getContextPath()}/assets/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${rc.getContextPath()}/assets/js/jquery.ztree.excheck-3.5.min.js"></script>

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
    var zNodes =[
        { id:1, pId:0, name:"随意勾选 1", open:true},
        { id:11, pId:1, name:"随意勾选 1-1", open:true},
        { id:111, pId:11, name:"随意勾选 1-1-1"},
        { id:112, pId:11, name:"随意勾选 1-1-2"},
        { id:12, pId:1, name:"随意勾选 1-2", open:true},
        { id:121, pId:12, name:"随意勾选 1-2-1"},
        { id:122, pId:12, name:"随意勾选 1-2-2"},
        { id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
        { id:21, pId:2, name:"随意勾选 2-1"},
        { id:22, pId:2, name:"随意勾选 2-2", open:true},
        { id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
        { id:222, pId:22, name:"随意勾选 2-2-2"},
        { id:23, pId:2, name:"随意勾选 2-3"}
    ];

    $(document).ready(function(){
        $.ajax({
            type: "GET",
            url: $path_base + '/admin/sys/resource/ajax/load',
            dataType: "json",
            global: false,
            async: false,
            success: function (strReult) {
                zNodes=eval(strReult);
            },
            error: function () {
                alert("Ajax请求数据失败!");
            }
        });
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        //zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        //$("#submit").bind('click',getNodeValue);
    });
    function getNodeValue (){
        var nodes = zTreeObj.getCheckedNodes();
        var s = '';//选中节点ids
        //遍历选中的节点，为s赋值
        for(var i=0; i<nodes.length; i++){
            var isParent = nodes[i].isParent;
            if(isParent!=true){
                if (s != '')s += ',';
                s += nodes[i].id;
            }
        }
        $("#content").val(s);
    }
</script>

</body>
</html>