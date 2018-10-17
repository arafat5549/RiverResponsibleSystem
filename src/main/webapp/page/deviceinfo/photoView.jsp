<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%
    String deviceinfoId = request.getParameter("deviceinfoId");
%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.css" />
<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- 轮播（Carousel）项目 -->
    <div class="carousel-inner">
       <%-- <div class="item active">
            <img src="files/upload/photo/1537860216762.jpeg" onmouseover="$(this).next().show()" onmouseout="$(this).next().hide()">
            <div class="carousel-caption" hidden="true"><a class="easyui-linkbutton" data-options="iconCls:'icon-delete'" onclick="removeImage()"></a></div>
        </div>
        <div class="item">
            <img src="files/upload/photo/1537865216008.jpeg">
            <div class="carousel-caption">标题 2</div>
        </div>
        <div class="item">
            <img src="files/upload/photo/1537865216039.jpeg">
            <div class="carousel-caption">标题 3</div>
        </div>--%>
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

<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript">
    myForm.ajaxGetRequest(basePath+'/api/deviceinfos/getPhotoById',{id:<%=deviceinfoId%>},function (r) {
        var inner = $('.carousel-inner');
        if(r.length>0){
            for(i=0;i<r.length;i++){
                var src = "'"+r[i].name+"'";
                if(i==0){
                    inner.append( '<div class="item active">\n' +
                        '            <img src="files/'+r[i].name+'" onmouseover="$(this).next().show()" onmouseout="$(this).next().hide()">\n' +
                        '            <div class="carousel-caption" hidden="true"><a class="easyui-linkbutton" data-options="iconCls:\'icon-delete\'" onclick="removeImage(this,'+r[i].id+','+src+','+r[i].deviceinfoId+');"></a></div>\n' +
                        '        </div>')
                }else{
                    inner.append('<div class="item">\n' +
                        '            <img src="files/'+r[i].name+'" onmouseover="$(this).next().show()" onmouseout="$(this).next().hide()">\n' +
                        '            <div class="carousel-caption" hidden="true"><a class="easyui-linkbutton" data-options="iconCls:\'icon-delete\'" onclick="removeImage(this,'+r[i].id+','+src+','+r[i].deviceinfoId+')"></a></div>\n' +
                        '        </div>')
                }

            }
            $.parser.parse(inner);
        }else{
            $('.window-body').dialog("destroy");
            $.messager.show({
                title:'提示信息',
                msg:'暂无照片!'
            });
        }
        
    })

    function removeImage(a,id,src,deviceinfoId) {
        if(!confirm("确定要删除该图片")){
            return;
        }
       myForm.ajaxRequest(basePath+'/api/deviceinfos/'+id+'/photos',{src:src,_method:"DELETE"},function (r) {
            if(r.result){
                $.messager.alert('信息',"删除成功！");
                grid.datagrid('reload');
                $('.window-body').dialog("destroy");
                image_click(deviceinfoId);

            }else{
                $.messager.show({
                    title:'提示信息',
                    msg:r.msg
                });
            }
        })
    }

</script>