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
            <label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 角色名称 </label>

            <div class="col-sm-9">
                <input type="text" id="form-field-1" placeholder="Username" value="${role.name}" class="col-xs-10 col-sm-5">
            </div>
        </div>

        <div class="space-4"></div>

        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="form-field-2"> 角色标识 </label>

            <div class="col-sm-9">
                <input type="text" id="form-field-2" value="${role.role}" placeholder="Password" class="col-xs-10 col-sm-5">
            </div>
        </div>

        <div class="space-4"></div>

        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="form-input-readonly"> 详细描述
            </label>

            <div class="col-sm-9">
                <textarea class="form-control" id="form-field-8" placeholder="Default Text">${role.description}</textarea>
            </div>
        </div>

        <div class="space-4"></div>

        <div class="form-group">

            <label class="col-sm-3 control-label no-padding-right" for="form-field-4">状 态</label>
            <div class="col-sm-9">
            <div class="control-group">
                    <#if role.show ? string('true','false') == 'true'>
                        <div class="radio">
                            <label>
                                <input name="form-field-radio" checked="checked" type="radio" class="ace">
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
                                <input name="form-field-radio"  type="radio" class="ace">
                                <span class="lbl"> 可用</span>
                            </label>
                        </div>
                        <div class="radio">
                            <label>
                                <input checked="checked" name="form-field-radio" type="radio" class="ace">
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

    <div class="widget-box">
        <div class="widget-header header-color-blue2">
            <h4 class="lighter smaller">Choose Categories</h4>
        </div>

        <div class="widget-body">
            <div class="widget-main padding-8">
                <div id="tree1" class="tree tree-selectable">
                    <div class="tree-folder" style="display:none;">
                        <div class="tree-folder-header"><i class="icon-plus"></i>

                            <div class="tree-folder-name"></div>
                        </div>
                        <div class="tree-folder-content"></div>
                        <div class="tree-loader" style="display: none;"></div>
                    </div>
                    <div class="tree-item" style="display:none;"><i class="icon-remove"></i>

                        <div class="tree-item-name"></div>
                    </div>
                    <div class="tree-folder" style="display: block;">
                        <div class="tree-folder-header"><i class="icon-plus"></i>

                            <div class="tree-folder-name">For Sale</div>
                        </div>
                        <div class="tree-folder-content"></div>
                        <div class="tree-loader" style="display: none;">
                            <div class="tree-loading"><i
                                    class="icon-refresh icon-spin blue"></i></div>
                        </div>
                    </div>
                    <div class="tree-folder" style="display: block;">
                        <div class="tree-folder-header"><i class="icon-plus"></i>

                            <div class="tree-folder-name">Vehicles</div>
                        </div>
                        <div class="tree-folder-content" style="display: none;">
                            <div class="tree-folder" style="display: block;">
                                <div class="tree-folder-header"><i class="icon-plus"></i>

                                    <div class="tree-folder-name">Cars</div>
                                </div>
                                <div class="tree-folder-content" style="display: none;">
                                    <div class="tree-item tree-selected"
                                         style="display: block;"><i class="icon-ok"></i>

                                        <div class="tree-item-name">Classics</div>
                                    </div>
                                    <div class="tree-item tree-selected"
                                         style="display: block;"><i class="icon-ok"></i>

                                        <div class="tree-item-name">Convertibles</div>
                                    </div>
                                    <div class="tree-item" style="display: block;"><i
                                            class="icon-remove"></i>

                                        <div class="tree-item-name">Coupes</div>
                                    </div>
                                    <div class="tree-item" style="display: block;"><i
                                            class="icon-remove"></i>

                                        <div class="tree-item-name">Hatchbacks</div>
                                    </div>
                                    <div class="tree-item" style="display: block;"><i
                                            class="icon-remove"></i>

                                        <div class="tree-item-name">Hybrids</div>
                                    </div>
                                    <div class="tree-item" style="display: block;"><i
                                            class="icon-remove"></i>

                                        <div class="tree-item-name">SUVs</div>
                                    </div>
                                    <div class="tree-item" style="display: block;"><i
                                            class="icon-remove"></i>

                                        <div class="tree-item-name">Sedans</div>
                                    </div>
                                    <div class="tree-item" style="display: block;"><i
                                            class="icon-remove"></i>

                                        <div class="tree-item-name">Trucks</div>
                                    </div>
                                </div>
                                <div class="tree-loader" style="display: none;">
                                    <div class="tree-loading"><i
                                            class="icon-refresh icon-spin blue"></i></div>
                                </div>
                            </div>
                            <div class="tree-item tree-selected" style="display: block;"><i
                                    class="icon-ok"></i>

                                <div class="tree-item-name">Motorcycles</div>
                            </div>
                            <div class="tree-item" style="display: block;"><i
                                    class="icon-remove"></i>

                                <div class="tree-item-name">Boats</div>
                            </div>
                        </div>
                        <div class="tree-loader" style="display: none;">
                            <div class="tree-loading"><i
                                    class="icon-refresh icon-spin blue"></i></div>
                        </div>
                    </div>
                    <div class="tree-folder" style="display: block;">
                        <div class="tree-folder-header"><i class="icon-plus"></i>

                            <div class="tree-folder-name">Rentals</div>
                        </div>
                        <div class="tree-folder-content"></div>
                        <div class="tree-loader" style="display: none;">
                            <div class="tree-loading"><i
                                    class="icon-refresh icon-spin blue"></i></div>
                        </div>
                    </div>
                    <div class="tree-folder" style="display: block;">
                        <div class="tree-folder-header"><i class="icon-plus"></i>

                            <div class="tree-folder-name">Real Estate</div>
                        </div>
                        <div class="tree-folder-content"></div>
                        <div class="tree-loader" style="display: none;">
                            <div class="tree-loading"><i
                                    class="icon-refresh icon-spin blue"></i></div>
                        </div>
                    </div>
                    <div class="tree-folder" style="display: block;">
                        <div class="tree-folder-header"><i class="icon-plus"></i>

                            <div class="tree-folder-name">Pets</div>
                        </div>
                        <div class="tree-folder-content" style="display: none;">
                            <div class="tree-item" style="display: block;"><i
                                    class="icon-remove"></i>

                                <div class="tree-item-name">Cats</div>
                            </div>
                            <div class="tree-item" style="display: block;"><i
                                    class="icon-remove"></i>

                                <div class="tree-item-name">Dogs</div>
                            </div>
                            <div class="tree-item" style="display: block;"><i
                                    class="icon-remove"></i>

                                <div class="tree-item-name">Horses</div>
                            </div>
                            <div class="tree-item" style="display: block;"><i
                                    class="icon-remove"></i>

                                <div class="tree-item-name">Reptiles</div>
                            </div>
                        </div>
                        <div class="tree-loader" style="display: none;">
                            <div class="tree-loading"><i
                                    class="icon-refresh icon-spin blue"></i></div>
                        </div>
                    </div>
                    <div class="tree-item" style="display: block;"><i class="icon-remove"></i>

                        <div class="tree-item-name">Tickets</div>
                    </div>
                    <div class="tree-item" style="display: block;"><i class="icon-remove"></i>

                        <div class="tree-item-name">Services</div>
                    </div>
                    <div class="tree-item" style="display: block;"><i class="icon-remove"></i>

                        <div class="tree-item-name">Personals</div>
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
</body>
</html>