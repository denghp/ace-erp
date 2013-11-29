<div class="page-content">
<div class="page-header">
    <h1>
        企业档案信息
    </h1>
</div><!-- /.page-header -->

<div class="row">
<div class="col-xs-12">
<!-- PAGE CONTENT BEGINS -->
    <div class="tabbable">
        <ul class="nav nav-tabs" id="myTab">
            <li class="active">
                <a data-toggle="tab" href="#home">
                    <i class="green icon-home bigger-110"></i>
                    企业档案基本信息
                </a>
            </li>

            <li>
                <a data-toggle="tab" href="#profile">
                    <i class="green icon-cogs bigger-110"></i>
                    上传LOGO
                </a>
            </li>

        </ul>

        <div class="tab-content">
            <div id="home" class="tab-pane in active">
                <form class="form-horizontal" id="validation-form" role="form">
                    <div class="row">
                        <div class="col-xs-3">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">当前企业logo预览</h3>
                                </div>
                                <div class="panel-body center" >
                                    <img src="${rc.getContextPath()}/assets/images/pic_bg.png">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-4">

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="rogan-id">
                                    公司帐号： </label>

                                <div class="col-sm-9">
                                    <input type="text" name="name" id="id" disabled="true" placeholder="企业帐号" value="<#if organ.id??>${organ.id}</#if>"
                                           class="col-xs-10 col-sm-5">
                                </div>
                            </div>

                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="organ-name">
                                    公司名称： </label>

                                <div class="col-sm-9">
                                    <input type="text" name="name" id="name" placeholder="公司名称" value="<#if organ.name??>${organ.name}</#if>"
                                           class="col-xs-10 col-sm-5">
                                </div>
                            </div>

                            <div class="space-4"></div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-2">
                                    所属行业： </label>

                                <div class="col-sm-9">
                                    <input type="text" name="role" id="role" value="<#if organ.tradeCodeId??>${organ.tradeCodeId}</#if>" placeholder="所属行业"
                                           class="col-xs-10 col-sm-5">
                                </div>
                            </div>

                            <div class="space-4"></div>

                            <div class="form-group">

                                <label class="col-sm-3 control-label no-padding-right" for="userCount">最大用户数：</label>

                                <div class="col-sm-9">
                                    <input type="text" name="user_count" disabled="true" id="user_count" value="<#if organ.userCount??>${organ.userCount}</#if>" placeholder="最大用户数"
                                           class="col-xs-10 col-sm-5">
                                </div>
                            </div>

                            <div class="space-4"></div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-2">
                                    开户时间： </label>

                                <div class="col-sm-9">
                                    <input type="text" name="createTime" disabled="true" id="create_time" value="<#if organ.createTime??>${organ.createTime}</#if>" placeholder="开户时间"
                                           class="col-xs-10 col-sm-5">
                                </div>
                            </div>

                            <div class="space-4"></div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-2">
                                    到期时间： </label>

                                <div class="col-sm-9">
                                    <input type="text" name="expireTime" disabled="true" id="expireTime" value="<#if organ.expireTime??>${organ.expireTime}</#if>" placeholder="开户时间"
                                           class="col-xs-10 col-sm-5">
                                </div>
                            </div>

                            <div class="space-4"></div>

                            <div class="form-group">

                                <label class="col-sm-3 control-label no-padding-right" for="status">状态：</label>

                                <div class="col-sm-9">
                                    <input type="text" name="role" id="role" value="" placeholder="状态"
                                           class="col-xs-10 col-sm-5">
                                </div>
                            </div>

                            <div class="space-4"></div>

                            <div class="form-group">

                                <label class="col-sm-3 control-label no-padding-right" for="status">企业网站：</label>

                                <div class="col-sm-9">
                                    <input type="text" name="site" id="site" value="<#if organ.site??>${organ.site}</#if>" placeholder="企业网站"
                                           class="col-xs-10 col-sm-5">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="space-4"></div>

                            <div class="form-group">

                                <label class="col-sm-3 control-label no-padding-right" for="address">地址：</label>

                                <div class="col-sm-9">
                                    <input type="text" name="address" id="address" value="<#if organ.address??>${organ.address}</#if>" placeholder="地址"
                                           class="col-xs-10 col-sm-5">
                                </div>
                            </div>

                            <div class="space-4"></div>

                            <div class="form-group">

                                <label class="col-sm-3 control-label no-padding-right" for="telephone">电话：</label>

                                <div class="col-sm-9">
                                    <input type="text" name="telephone" id="telephone" value="<#if organ.telephone??>${organ.telephone}</#if>" placeholder="电话"
                                           class="col-xs-10 col-sm-5">
                                </div>
                            </div>

                            <div class="space-4"></div>

                            <div class="form-group">

                                <label class="col-sm-3 control-label no-padding-right" for="email">电子邮件：</label>

                                <div class="col-sm-9">
                                    <input type="text" name="email" id="email" value="<#if organ.email??>${organ.email}</#if>" placeholder="电子邮件"
                                           class="col-xs-10 col-sm-5">
                                </div>
                            </div>
                            <div class="space-4"></div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-field-2">
                                    管理员帐号： </label>

                                <div class="col-sm-9">
                                    <input type="text" name="role" id="role" value="" placeholder="管理员帐号"
                                           class="col-xs-10 col-sm-5">
                                </div>
                            </div>

                            <div class="space-4"></div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right" for="form-input-readonly">
                                    详细描述：
                                </label>

                                <div class="col-sm-9">
                                    <textarea class="form-control" name="description" id="description"
                                              placeholder="Default Text"><#if organ.description??>${organ.description}</#if></textarea>
                                </div>
                            </div>
                        </div>


                    </div>
                    <div class="clearfix form-actions">
                        <div class="col-md-offset-3 col-md-9">
                            <button class="btn btn-info" type="button">
                                <i class="icon-ok bigger-110"></i>
                                Save
                            </button>

                            &nbsp; &nbsp;
                            <button class="btn" type="reset">
                                <i class="icon-undo bigger-110"></i>
                                Reset
                            </button>
                        </div>
                    </div>
                </form>
            </div>

            <div id="profile" class="tab-pane">

                <div class="row">
                    <div class="col-xs-8 ">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">
                                    <button class="btn btn-info" type="button">
                                    选择图片
                                </button>仅支持JPG、JPEG、PNG格式（2M以下）</h3>
                            </div>
                            <div class="panel-body" >
                                        <img src="${rc.getContextPath()}/assets/images/sago-1.jpg" id="target" alt="[Jcrop Example]" />

                                        <div id="preview-pane">
                                            <div class="preview-container">
                                                <img src="${rc.getContextPath()}/assets/images/sago.jpg" class="jcrop-preview" alt="Preview" />
                                            </div>
                                        </div>
                                        <span>
                                                头像预览区
                                            生成头像大小200px*200px，
                                            上传图片后，左侧选取图片合适大小，
                                            点击下面的提交按钮。
                                            </span>
                            </div>
                        </div>
                        <div class="clearfix form-actions">
                            <div class="col-md-offset-3 col-md-9">
                                <button class="btn btn-info" type="button">
                                    <i class="icon-ok bigger-110"></i>
                                    Save
                                </button>
                            </div>
                        </div>
                    </div>


                </div>
            </div>

        </div>
    </div>

<!-- PAGE CONTENT ENDS -->
</div><!-- /.col -->
</div><!-- /.row -->
</div>

<style type="text/css">

        /* Apply these styles only when #preview-pane has
           been placed within the Jcrop widget */
    .jcrop-holder #preview-pane {
        display: block;
        position: absolute;
        z-index: 2000;
        top: 10px;
        right: -290px;
        padding: 6px;
        border: 1px rgba(0,0,0,.4) solid;
        background-color: white;

        -webkit-border-radius: 6px;
        -moz-border-radius: 6px;
        border-radius: 6px;

        -webkit-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
        -moz-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
        box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
    }

        /* The Javascript code will set the aspect ratio of the crop
           area based on the size of the thumbnail preview,
           specified here */
    #preview-pane .preview-container {
        width: 250px;
        height: 170px;
        overflow: hidden;
    }

</style>


<script src="${rc.getContextPath()}/assets/js/jquery.Jcrop.js"></script>
<script type="text/javascript">
    jQuery(function($){

        // Create variables (in this scope) to hold the API and image size
        var jcrop_api,
                boundx,
                boundy,

        // Grab some information about the preview pane
                $preview = $('#preview-pane'),
                $pcnt = $('#preview-pane .preview-container'),
                $pimg = $('#preview-pane .preview-container img'),

                xsize = $pcnt.width(),
                ysize = $pcnt.height();

        $('#target').Jcrop({
            onChange: updatePreview,
            onSelect: updatePreview,
            aspectRatio: xsize / ysize
        },function(){
            // Use the API to get the real image size
            var bounds = this.getBounds();
            boundx = bounds[0];
            boundy = bounds[1];
            // Store the API in the jcrop_api variable
            jcrop_api = this;

            // Move the preview into the jcrop container for css positioning
            $preview.appendTo(jcrop_api.ui.holder);
        });

        function updatePreview(c)
        {
            if (parseInt(c.w) > 0)
            {
                var rx = xsize / c.w;
                var ry = ysize / c.h;

                $pimg.css({
                    width: Math.round(rx * boundx) + 'px',
                    height: Math.round(ry * boundy) + 'px',
                    marginLeft: '-' + Math.round(rx * c.x) + 'px',
                    marginTop: '-' + Math.round(ry * c.y) + 'px'
                });
            }
        };

    });


</script>