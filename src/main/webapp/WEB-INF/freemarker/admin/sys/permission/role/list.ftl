<div class="breadcrumbs" id="breadcrumbs">
    <script type="text/javascript">
        try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
    </script>

    <ul class="breadcrumb">
        <li>
            <i class="icon-home home-icon"></i>
            <a href="#">Home</a>
        </li>

        <li>
            <a href="#">系统设置</a>
        </li>
        <li class="active">角色列表</li>
    </ul><!-- .breadcrumb -->

    <div class="nav-search" id="nav-search">
        <form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="icon-search nav-search-icon"></i>
								</span>
        </form>
    </div><!-- #nav-search -->
</div>

<div class="page-content">
    <div class="page-header">
        <h1>
            jqGrid
            <small>
                <i class="icon-double-angle-right"></i>
                Dynamic tables and grids using jqGrid plugin
            </small>
        </h1>
    </div><!-- /.page-header -->

    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->

            <div id="alert-info" class="alert alert-info">
                <i class="icon-hand-right"></i>

                Please note that demo server is not configured to save the changes, therefore you may get an error message.
                <button class="close" data-dismiss="alert">
                    <i class="icon-remove"></i>
                </button>
            </div>

            <table id="grid-table"></table>

            <div id="grid-pager"></div>

            <script type="text/javascript">
                var $path_base = "${rc.getContextPath()}";//this will be used in gritter alerts containing images
            </script>

            <!-- PAGE CONTENT ENDS -->
        </div><!-- /.col -->
    </div><!-- /.row -->
</div><!-- /.page-content -->
<!-- inline scripts related to this page -->

<script type="text/javascript">
eval('debugger;');
jQuery(function($) {
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";
    jQuery(grid_selector).jqGrid({
        //direction: "rtl",
        url:$path_base+'/admin/sys/permission/role',
        datatype: "json",
        mtype: 'GET',
        //data: grid_data,
        //datatype: "local",
        height: 350,
        colNames:[' ', 'ID','角色名','角色', '描述','创建时间','修改时间', '是否可用'],
        colModel:[
            {name:'myac',index:'', width:120, fixed:true, sortable:false, resize:false,
                formatter:'actions',
                formatoptions:{
                    keys:true,
                    delOptions:{url:$path_base+"/admin/sys/permission/role/delete",recreateForm: true, beforeShowForm:beforeDeleteCallback},
                    //editformbutton:true,
                    //editOptions:{url:$path_base+"/admin/sys/user/update",recreateForm: true, beforeShowForm:beforeEditCallback},
                    onSuccess: function(response) {
                        console.log("response > " + response.responseText);
                        if (response.responseText.toLocaleLowerCase() == "ok" ) {
                            return [true];
                        }
                        jQuery("#alert-info").html("<i class='icon-hand-right'></i> "+ response.responseText
                                +"<button class='close' data-dismiss='alert'><i class='icon-remove'></i></button>")
                        return [false];
                    }
                }
            },
            {name:'id',index:'id', width:30, editable:false,sorttype:"int"},
            {name:'name',index:'name', width:120,editable: true,editoptions:{size:"20",maxlength:"50"}},
            {name:'role',index:'role', width:120, editable: true,editoptions:{size:"20",maxlength:"50"}},
            {name:'description',index:'desc', width:200, editable: true,editoptions:{rows:"2",cols:"10"}},
            {name:'createTime',index:'cdate',width:90, editable:true,sorttype:"date", formatter:dateFormatter,unformat: pickDate},
            {name:'modifyTime',index:'mdate',width:90, editable:false,sorttype:"date", formatter:dateFormatter, unformat: pickDate},
            {name:'show',index:'show', width:30, editable: true, edittype:"checkbox",  editoptions:{value:"true:false"},unformat: aceSwitch}


        ],

        viewrecords : true,
        rowNum:10,
        rowList:[10,20,30],
        pager : pager_selector,
        altRows: true,
        //toppager: true,

        multiselect: true,
        //multikey: "ctrlKey",
        multiboxonly: true,

        loadComplete : function() {
            var table = this;
            setTimeout(function(){
                styleCheckbox(table);
                customButton(table);
                updateActionIcons(table);
                updatePagerIcons(table);
                enableTooltips(table);
            }, 0);
        },

        editurl: $path_base+"/admin/sys/permission/role/update",//nothing is saved
        caption: "jqGrid with inline editing",

        autowidth: true
    });

    //enable search/filter toolbar
    //jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})

    //switch element when editing inline
    function aceSwitch( cellvalue, options, cell ) {
        setTimeout(function(){
            $(cell) .find('input[type=checkbox]')
                    .wrap('<label class="inline" />')
                    .addClass('ace ace-switch ace-switch-5')
                    .after('<span class="lbl"></span>');
        }, 0);
    }

    //format date
    function dateFormatter(cellvalue, options, rowObject) {
        return cellvalue.split(" ")[0];
    }

    function formatShow(cellvalue, options, rowObject) {
        if (cellvalue) {
            return "Yes";
        }
        return "No";
    }

    function getColumnIndexByName(grid, columnName) {
        var cm = jQuery(grid).jqGrid('getGridParam', 'colModel'), i, l = cm.length;
        for (i = 0; i < l; i++) {
            if (cm[i].name === columnName) {
                return i; // return the index
            }
        }
        return -1;
    }
    //enable datepicker
    function pickDate( cellvalue, options, cell ) {
        setTimeout(function(){
            $(cell).find('input[type=text]')
                    .datepicker({format:'yyyy-mm-dd' , language:'zh-CN', autoclose:true});
        }, 0);
    }

    //navButtons
    jQuery(grid_selector).jqGrid('navGrid',pager_selector,
            { 	//navbar options
                edit: true,
                editicon : 'icon-pencil blue',
                add: true,
                addicon : 'icon-plus-sign purple',
                del: true,
                delicon : 'icon-trash red',
                search: true,
                searchicon : 'icon-search orange',
                refresh: true,
                refreshicon : 'icon-refresh green',
                view: true,
                viewicon : 'icon-zoom-in grey'
            },
            {
                //edit record form
                closeAfterEdit: true,
                url:$path_base+"/admin/sys/permission/role/update",
                recreateForm: true,
                beforeShowForm : function(e) {
                    var form = $(e[0]);
                    form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                    style_edit_form(form);
                },
                afterSubmit: function (response, postdata) {
                    if (response.responseText.toLocaleLowerCase() == "ok" ) {
                        return [true,response.responseText];
                    }
                    return [false,response.responseText];
                }
            },
            {
                //new record form
                url:$path_base+"/admin/sys/permission/role/add",
                closeAfterAdd: true,
                recreateForm: true,
                modal:true,
                viewPagerButtons: false,
                beforeShowForm : function(e) {
                    var form = $(e[0]);
                    form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                    style_edit_form(form);

                },
                serializeEditData: function(data){
                    //新增数据的时候把默认的id='_empty'设置为id='0'
                    return $.param($.extend({},data,{id:0}));
                },
                afterSubmit: function(response, postdata) {
                    console.log("postdata : " + postdata);
                    console.log(response.responseText);
                    if (response.responseText.toLocaleLowerCase() == "ok" ) {
                        return [true,response.responseText,''];
                    }
                    return [false,response.responseText];
                }
            },
            {
                //delete record form
                url:$path_base+"/admin/sys/permission/role/delete",
                recreateForm: true,
                beforeShowForm : function(e) {
                    var form = $(e[0]);
                    if(form.data('styled')) return false;

                    form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
                    style_delete_form(form);

                    form.data('styled', true);
                },
                afterSubmit: function (response, postdata) {
                    if (response.responseText.toLocaleLowerCase() == "ok" ) {
                        return [true,response.responseText];
                    }
                    return [false,response.responseText];
                }
                /**
                 errorTextFormat: function (response) {
                    return '<span class="ui-icon ui-icon-alert" ' +
                            'style="float:left; margin-right:.3em;"></span>' +
                            response.responseText;
                }
                 **/
            },
            {
                //search form
                url:$path_base+"/admin/sys/permission/role/search",
                recreateForm: true,
                afterShowSearch: function(e){
                    var form = $(e[0]);
                    form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                    style_search_form(form);
                },
                afterRedraw: function(){
                    style_search_filters($(this));
                }
                ,
                multipleSearch: true
                /**
                 multipleGroup:true,
                 showQuery: true
                 */
            },
            {
                //view record form
                recreateForm: true,
                beforeShowForm: function(e){
                    var form = $(e[0]);
                    form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
                }
            }
    )



    function style_edit_form(form) {
        //enable datepicker on "sdate" field and switches for "stock" field
        form.find('input[name=createTime]').datepicker({format:'yyyy-mm-dd' ,language:'zh-CN', autoclose:true})
                .end().find('input[name=show]')
                .addClass('ace ace-switch ace-switch-5').wrap('<label class="inline" />').after('<span class="lbl"></span>');
        //update buttons classes
        var buttons = form.next().find('.EditButton .fm-button');
        buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
        buttons.eq(0).addClass('btn-primary').prepend('<i class="icon-ok"></i>');
        buttons.eq(1).prepend('<i class="icon-remove"></i>')

        buttons = form.next().find('.navButton a');
        buttons.find('.ui-icon').remove();
        buttons.eq(0).append('<i class="icon-chevron-left"></i>');
        buttons.eq(1).append('<i class="icon-chevron-right"></i>');
    }

    function style_delete_form(form) {
        var buttons = form.next().find('.EditButton .fm-button');
        buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
        buttons.eq(0).addClass('btn-danger').prepend('<i class="icon-trash"></i>');
        buttons.eq(1).prepend('<i class="icon-remove"></i>')
    }

    function style_search_filters(form) {
        form.find('.delete-rule').val('X');
        form.find('.add-rule').addClass('btn btn-xs btn-primary');
        form.find('.add-group').addClass('btn btn-xs btn-success');
        form.find('.delete-group').addClass('btn btn-xs btn-danger');
    }
    function style_search_form(form) {
        var dialog = form.closest('.ui-jqdialog');
        var buttons = dialog.find('.EditTable')
        buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'icon-retweet');
        buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'icon-comment-alt');
        buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'icon-search');
    }

    function beforeDeleteCallback(e) {
        var form = $(e[0]);
        if(form.data('styled')) return false;
        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
        style_delete_form(form);

        form.data('styled', true);
    }

    function beforeEditCallback(e) {
        var form = $(e[0]);
        form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
        style_edit_form(form);
    }

    function customButton(table) {
        var iCol = getColumnIndexByName(table, 'myac');
        $(table).find(">tbody>tr.jqgrow>td:nth-child(" + (iCol + 1) + ")")
                .each(function() {
                    $("<div>", {
                                title: "角色授权",
                                mouseover: function() {
                                    $(this).addClass('ui-state-hover');
                                },
                                mouseout: function() {
                                    $(this).removeClass('ui-state-hover');
                                },
                                click: function(e) {
                                    alert("'Custom' button is clicked in the rowis="+
                                            $(e.target).closest("tr.jqgrow").attr("id") +" !");
                                    return false;
                                }
                            }
                    ).css({"margin-right": "5px", float: "left", cursor: "pointer"})
                            .addClass("ui-pg-div ui-inline-custom")
                            .append('<span class="ui-icon  icon-cogs"></span>')
                            .prependTo($(this).children("div"));
                });
    }


    //it causes some flicker when reloading or navigating grid
    //it may be possible to have some custom formatter to do this as the grid is being created to prevent this
    //or go back to default browser checkbox styles for the grid
    function styleCheckbox(table) {
        /**
         $(table).find('input:checkbox').addClass('ace')
         .wrap('<label />')
         .after('<span class="lbl align-top" />')


         $('.ui-jqgrid-labels th[id*="_cb"]:first-child')
         .find('input.cbox[type=checkbox]').addClass('ace')
         .wrap('<label />').after('<span class="lbl align-top" />');
         */
    }


    //unlike navButtons icons, action icons in rows seem to be hard-coded
    //you can change them like this in here if you want
    function updateActionIcons(table) {
         /**
         var replacement =
         {
             'ui-icon-pencil' : 'icon-pencil blue',
             'ui-icon-pencil' : 'icon-pencil blue',
             'ui-icon-trash' : 'icon-trash red',
             'ui-icon-disk' : 'icon-ok green',
             'ui-icon-cancel' : 'icon-remove red'
         };
         $(table).find('.ui-pg-div span.ui-icon').each(function(){
						var icon = $(this);
						var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
						if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
					})
          **/
    }

    //replace icons with FontAwesome icons like above
    function updatePagerIcons(table) {
        var replacement =
        {
            'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
            'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
            'ui-icon-seek-next' : 'icon-angle-right bigger-140',
            'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
        };
        $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
            var icon = $(this);
            var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

            if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
        })
    }

    function enableTooltips(table) {
        $('.navtable .ui-pg-button').tooltip({container:'body'});
        $(table).find('.ui-pg-div').tooltip({container:'body'});
    }

    //var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');


});
</script>
