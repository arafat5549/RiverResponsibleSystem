<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">图片浏览</h4>
</div>

<div class="modal-body">
	<div class="row">
		<div class="col-md-12">

            <div id="myCarousel" class="carousel slide">
                <!-- 轮播（Carousel）项目 -->
                <div class="carousel-inner">
					<#if list??>
						<#list list as item>
                         <div class="item <#if item_index == 0 >active</#if>">
                             <img src="${item.name}" alt="图片缺失" onmouseover="$(this).next().show()" onmouseout="$(this).next().hide()"><#-- -->
                             <div class="carousel-caption"  aria-hidden="true"><a onclick="removeImage(this,${item.id},'${item.name}',${id})"><i style="color:#F00" class="fa fa-trash-o"></i></a></div>
                         </div>
						</#list>
					</#if>
                </div>
                <!-- 轮播（Carousel）导航 -->
                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
		</div>
	</div>
</div>

<div class="modal-footer">
	<button type="button" class="btn default" data-dismiss="modal">关闭</button>
<#--	<button type="button" class="btn blue" onclick="javascript:$('#form_cl').submit();">保存</button>-->
</div>

<script>
    jQuery(document).ready(function() {
       FormCl.init();
    });
</script>
<script type="text/javascript">
    function removeImage(a,id,name,devicainfoId){
        Deviceinfo.removeImage(a,id,name,devicainfoId);
    }
  /*  function show(div) {
        if(div.is(':hidden')){
            div.show();
        }else{
            return;
        }
    }
    function hide(div) {
        if(!div.is(':hidden')){
            div.hide();
        }else {
            return;
        }
    }*/

</script>