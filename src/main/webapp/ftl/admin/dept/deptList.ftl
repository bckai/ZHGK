<#include "../../common/meta.ftl">
    	<title>组织结构管理</title>
		<script type="text/javascript" src="${ctx.contextPath}/js/admin/dept.js"></script>
	</head>
	<#macro buildNode child parent level parId> 
	    <#if child?? && child?size gt 0> 
	        <#list child as tt> 
	        	<tr id="${parId}-${tt_index}" onMouseover="this.style.background='#F9F9F9'"
			            onMouseout="this.style.background='#FFFFFF'">
	      			<td>
					<#list 0..level as i>　　</#list> 
						<#if tt.children?? && tt.children?size gt 0>
							<a onclick="disaplay('${parId}-${tt_index}')"">
							<img src="${ctx.contextPath}/images/folderopen.gif" /></a>
						<#else>
							<#if tt.superDep??>
								<img src="${ctx.contextPath}/images/tree_file.gif" />
							<#else>
								<img src="${ctx.contextPath}/images/folderopen.gif" />
							</#if> 
						</#if> 
						${tt.name}
						</td>
						<td>${tt.code!""}</td>
						<td>${(tt.managerName)!""}</td>
						<td>${tt.phone!""}</td>
						<td align="center">
			                <a href="${ctx.contextPath}/rest/dept/${tt.id}/put/pre">修改</a>/
							<a href="${ctx.contextPath}/rest/dept/${tt.id}/deptManager/post/pre" >分配负责人</a>/
							<a href="#" onclick="deleteDept('${tt.id}')">删除</a>
						</td>
		        	</tr>
			        	<#if tt.children?? && tt.children?size gt 0> 
			            	<@buildNode child=tt.children parent=tt level=(level+1) parId=(parId+"-"+tt_index)/>
			            </#if> 
	        </#list> 
	    </#if> 
	</#macro> 
	<body>
		<input id="ctxPath" type="hidden" value="${ctx.contextPath}" />
		<div class="head"><div class="path">当前位置：组织结构管理 > 组织结构列表</div></div>
		<dl class="box_1">
			<dt>
			<div>组织结构列表</div>
			<a class="a_3" href="#" onclick="location.href='${ctx.contextPath}/rest/dept/post/pre'">添加</a><div class="img_2"></div>
			</dt>
		<dd>
		<table class="tb_2">
        	<tr>
            	<th>机构名称</th>
        		<th>机构编码</th>
            	<th>负责人</th>
                <th>联系电话</th>
                <th>操作</th>
            </tr>
            <@buildNode child=listTree parent="" level=0 parId=" "/>
		</table>
		</dd>
		</dl>
	</body>
</html>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>组织结构</title>
<script src="js/jquery-1.9.1.min.js"></script>

<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
<link href="css/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">

<script src="js/jquery.jOrgChart.js"></script>
<link href="css/jquery.jOrgChart.css" rel="stylesheet" type="text/css"/>
<link href="css/jquery.jOrgChartItem.css" rel="stylesheet" type="text/css"/>

<script src="js/jquery.jtip.js"></script>
<link href="css/jquery.jtip.css" rel="stylesheet" type="text/css">

<script src="js/rb.js"></script>
<link href="css/rb.css" rel="stylesheet" type="text/css">
<script>

$(document).ready(function() {	
	$("#wai").css("width",FRAMEWIDTH-230+"px");
	$("#info").css("width","200px");
	$("#info").css("right","10px");	
	$("#info").mCustomScrollbar();
	
	$("#org").jOrgChart({
		chartElement : '#chart',
		dragAndDrop  : true
	});	
});

function showedit(id,name){
	$('#JT').remove();
	JT_show(id,id,name+'：');
	
}
//创建部门
function createDep(){
	alert("选择的上级部门是:"+selectDepName);
	var name = $("#createDepName").val();
	var x = 0;
	var y = 99;
	var rand = parseInt(Math.random() * (x - y + 1) + y);
	jt_close();
	$("#chart").children().remove();
	$("#a ul").append("<li id='"+rand+"'>"+name+"</br><a href=\"javascript:JT_editDep('"+rand+"_1','修改部门')\" id='"+rand+"_1'>修改</a></li>");
	$("#org").jOrgChart({
		chartElement : '#chart',
		dragAndDrop  : true
	});
	//这里执行持久化操作
		
}
//修改部门
function editDep(){
	alert("选择的上级部门是:"+selectDepName);
	var name = $("#editDepName").val();
	var x = 0;
	var y = 99;
	var rand = parseInt(Math.random() * (x - y + 1) + y);
	jt_close();
	$("#chart").children().remove();
	$("#a ul").html("<li id='"+rand+"'>"+name+"</br><a href=\"javascript:JT_editDep('"+rand+"_1','修改部门')\" id='"+rand+"_1'>修改</a></li>");
	$("#org").jOrgChart({
		chartElement : '#chart',
		dragAndDrop  : true
	});
	//这里执行持久化操作
		
}

</script>
<style>
#chart{
	height:100%;
	position:absolute;
}

#wai{	
	position:absolute;
	background:url(assets/bkgd.png);
}

.rb_mainframe_middle_title{
	background-color:#6e8187;
}
</style>
</head>

<body onselectstart="return false">
<!--topbar
<div class="rb_mainframe_top">
	<div class="rb_mainframe_top_title_first">组织结构</div>
    <div class="rb_mainframe_top_title_second">部门管理</div>
    <div class="rb_mainframe_top_btn">帮助</div>
  	<div class="rb_mainframe_top_btn"><a href="javascript:JT_createDep('createDepShow','新建部门');" id="createDepShow">新建部门</a></div>
    <div class="rb_mainframe_top_input"><img src="assets/top_input_icon_search.png"/><input type="text" placeholder="输入部门名..." name="searchDep" id="searchDep" /><input name="sumbit" type="button" value="搜索" onClick="searchDep()"></div>
</div>--> 
<!--content
<div class="rb_mainframe_content">
	<!--部门操作区域
    <div class="rb_mainframe_middle" id="wai">
    	<div style="padding:10px;float:right;">可通过拖拽查找部门</div>
        <div class="orgChart" id="chart"></div>	
    </div>--> 
    <!--公司信息
    <div class="rb_mainframe_middle" id="info">
        <div class="rb_mainframe_middle_title">统计信息</div>
        <span>部门总数:</span><p>16 个</p>
        <span>人员总数:</span><p>134 人</p>
        <span>单位名称:</span><p>河南东方世纪股份有限公司</p> 
        <span>机构代码:</span><p>hndfsj</p>
        <div class="rb_mainframe_middle_title">统计信息</div>
        <span>单位名称:</span><p>河南东方世纪股份有限公司</p>
    </div>
</div> --> 


<!--初始化组织结构数据-->
<ul id="org" style="display:none">
	<li id="a">河南东方世纪股份有限公司</br>      
        <a href="javascript:JT_editDep('a_1','修改部门');" id="a_1">修改</a>               
		<ul>
        	<!--
			<li id="a1">
            	企业文化部</br>
                <a href="javascript:showedit('a1_1','修改名称')" id="a1_1">修改名称</a>
                <a href="javascript:showedit('a1_2','调整归宿')" id="a1_2">调整关系</a>
            </li>
            -->
		</ul>
	</li>
</ul>         
</body>
<script>
$("#chart").mousedown(function (e) { //e鼠标事件

	$("#chart").css("cursor", "move"); //改变鼠标指针的形状

	var offset = $(this).offset(); //DIV在页面的位置
	var x = e.pageX - offset.left; //获得鼠标指针离DIV元素左边界的距离
	var y = e.pageY - offset.top; //获得鼠标指针离DIV元素上边界的距离
	$(document).bind("mousemove", function (ev)//绑定鼠标的移动事件，因为光标在DIV元素外面也要有效果，所以要用doucment的事件，而不用DIV元素的事件
	{
		$("#chart").stop(); //加上这个之后

		var _x = ev.pageX - x; //获得X轴方向移动的值
		var _y = ev.pageY - y; //获得Y轴方向移动的值

		$("#chart").animate({ left: _x-11 + "px", top: _y-62 + "px" }, 10);
	});
	
	$(document).bind("mouseup", function (ev)//绑定鼠标的移动事件，因为光标在DIV元素外面也要有效果，所以要用doucment的事件，而不用DIV元素的事件
	{
		$("#chart").css("cursor", "default");
		$(this).unbind("mousemove");
	});

});
</script>
</html>
