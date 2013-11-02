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
    <div class="profile-user-info profile-user-info-striped">
        <div class="profile-info-row">
            <div class="profile-info-name"> Username</div>

            <div class="profile-info-value">
                <span class="editable editable-click" id="username">alexdoe</span>
            </div>
        </div>

        <div class="profile-info-row">
            <div class="profile-info-name"> Location</div>

            <div class="profile-info-value">
                <i class="icon-map-marker light-orange bigger-110"></i>
                <span class="editable editable-click" id="country">Netherlands</span>
                <span class="editable editable-click" id="city">Amsterdam</span>
            </div>
        </div>

        <div class="profile-info-row">
            <div class="profile-info-name"> Age</div>

            <div class="profile-info-value">
                <span class="editable editable-click" id="age">38</span>
            </div>
        </div>

        <div class="profile-info-row">
            <div class="profile-info-name"> Joined</div>

            <div class="profile-info-value">
                <span class="editable editable-click" id="signup">20/06/2010</span>
            </div>
        </div>

        <div class="profile-info-row">
            <div class="profile-info-name"> Last Online</div>

            <div class="profile-info-value">
                <span class="editable editable-click" id="login">3 hours ago</span>
            </div>
        </div>

        <div class="profile-info-row">
            <div class="profile-info-name"> About Me</div>

            <div class="profile-info-value">
                <span class="editable editable-click" id="about">Editable as WYSIWYG</span>
            </div>
        </div>
    </div>
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