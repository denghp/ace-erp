<div class="sidebar" id="sidebar">
    <script type="text/javascript">
        try {
            ace.settings.check('sidebar', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="sidebar-shortcuts" id="sidebar-shortcuts">
        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
            <button class="btn btn-success">
                <i class="icon-signal"></i>
            </button>

            <button class="btn btn-info">
                <i class="icon-pencil"></i>
            </button>

            <button class="btn btn-warning">
                <i class="icon-group"></i>
            </button>

            <button class="btn btn-danger">
                <i class="icon-cogs"></i>
            </button>
        </div>

        <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
            <span class="btn btn-success"></span>

            <span class="btn btn-info"></span>

            <span class="btn btn-warning"></span>

            <span class="btn btn-danger"></span>
        </div>
    </div>
    <!-- #sidebar-shortcuts -->

<ul class="nav nav-list">
    <li class="active">
        <a href="${rc.getContextPath()}/admin/index">
            <i class="icon-dashboard"></i>
            <span class="menu-text"> Dashboard </span>
        </a>
    </li>
<#if menuList?exists>
    <#list menuList as menu>
        <li id="${menu.id}">
            <a href="#" class="dropdown-toggle">
                <#if menu.icon??>
                    <i class="${menu.icon} "></i>
                <#else>
                    <i class="background: url(${rc.getContextPath()}/assets/img/F1050_B.png) center center no-repeat;"></i>
                </#if>
                <span class="menu-text"> ${menu.name} </span>
                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <#list menu.getChildren() as children >
                    <li id="${children.id}">
                        <!-- <a href="jqgrid.html"> -->
                        <#if (children.url?length > 0) >
                        <a href="${rc.getContextPath()}${children.url}">
                        <#else>
                        <a href="#" class="dropdown-toggle">
                        </#if>
                        <i class="icon-double-angle-right"></i>
                    ${children.name}
                        <#if (children.children?size > 0) >
                            <b class="arrow icon-angle-down"></b>
                        </#if>
                    </a>
                        <ul class="submenu">
                            <#list children.getChildren() as threeChildren>
                                <li>
                                    <a href="${rc.getContextPath()}${threeChildren.url}" class="dropdown-toggle">
                                        <i class="icon-double-angle-right"></i>
                                    ${threeChildren.name}
                                    </a>
                                </li>
                            </#list>
                        </ul>
                    </li>
                </#list>
            </ul>
        </li>
    </#list>
    <li>
        <a href="#" class="dropdown-toggle">
            <i class="icon-desktop"></i>
            <span class="menu-text"> UI Elements </span>

            <b class="arrow icon-angle-down"></b>
        </a>

        <ul class="submenu">
            <li>
                <a href="${rc.getContextPath()}/elements.html">
                    <i class="icon-double-angle-right"></i>
                    Elements
                </a>
            </li>

            <li>
                <a href="${rc.getContextPath()}/buttons.html">
                    <i class="icon-double-angle-right"></i>
                    Buttons &amp; Icons
                </a>
            </li>

            <li>
                <a href="${rc.getContextPath()}/treeview.html">
                    <i class="icon-double-angle-right"></i>
                    Treeview
                </a>
            </li>

            <li>
                <a href="${rc.getContextPath()}/jquery-ui.html">
                    <i class="icon-double-angle-right"></i>
                    jQuery UI
                </a>
            </li>

            <li>
                <a href="${rc.getContextPath()}/nestable-list.html">
                    <i class="icon-double-angle-right"></i>
                    Nestable Lists
                </a>
            </li>

            <li>
                <a href="#" class="dropdown-toggle">
                    <i class="icon-double-angle-right"></i>

                    Three Level Menu
                    <b class="arrow icon-angle-down"></b>
                </a>

                <ul class="submenu">
                    <li>
                        <a href="#">
                            <i class="icon-leaf"></i>
                            Item #1
                        </a>
                    </li>

                    <li>
                        <a href="#" class="dropdown-toggle">
                            <i class="icon-pencil"></i>

                            4th level
                            <b class="arrow icon-angle-down"></b>
                        </a>

                        <ul class="submenu">
                            <li>
                                <a href="${rc.getContextPath()}/">
                                    <i class="icon-plus"></i>
                                    Add Product
                                </a>
                            </li>

                            <li>
                                <a href="${rc.getContextPath()}/">
                                    <i class="icon-eye-open"></i>
                                    View Products
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>
        </ul>
    </li>
</ul>
</#if>
    <!-- /.nav-list -->


    <div class="sidebar-collapse" id="sidebar-collapse">
        <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
    </div>

    <script type="text/javascript">
        try {
            ace.settings.check('sidebar', 'collapsed')
        } catch (e) {
        }
    </script>
</div>