<#include "../../common/metacommon.ftl">
<script>
$(document).ready(function() {
	$(".rb_mainframe_middle").css("width",FRAMEWIDTH-20+"px");
	$(".rb_mainframe_middle_content").mCustomScrollbar(); 
	$(".rb_mainframe_middle_content").css("height",$(".rb_mainframe_middle").height()-40+"px");	
	$("#report tr:odd").addClass("odd");
	$("#report tr:not(.odd)").hide();
	$("#report tr:first-child").show();
});
var roleid;
function JT_editParamsName(linkId,title,roleId,httpUrl){
	if(title == false)title="&nbsp;";
	var de = document.documentElement;
	var w = self.innerWidth || (de&&de.clientWidth) || document.body.clientWidth;
	var hasArea = w - getAbsoluteLeft(linkId);
	var clickElementy = getAbsoluteTop(linkId) - 3; //set y position
	var params = 350;
	var height = 150;
	$.ajax({
		url:httpUrl,
		async:false, 
	 	success: function(data){                      
			$("body").append("<div class='rb_mask'></div><div id='JT' style='width:"+params+"px;height:"+height+"px'><div id='JT_arrow_right' style='left:"+(params+1)+"px'></div><div id='JT_close_right'>"+title+"<div id='JT_close_btn' onclick='jt_close()'></div></div><div id='JT_copy'><div class='JT_loader'><div>"+data+"</div>");
          }
         });
	roleid=roleId;
	var clickElementx = getAbsoluteLeft(linkId) - (params + 20);
	
	$('#JT').css({left: clickElementx+"px", top: clickElementy+"px"});
	$('#JT').show();
	$(".JT_loader").mCustomScrollbar();
	$(".JT_loader div").css("overflow", "hidden");

}
//修改系统参数
function editParams(){
	var roleName = $("#value").val();
	//==提交请求
	 var url ="${ctx.contextPath}/rest/dictionary/edit";
	    $.post(url, $('#dicForm').serialize(), function(data){
	    data=eval("("+data+")");
	        if (data.respCode =="0") {
	            if(roleName.length>0){
					$("#"+roleid+" td:nth-child(2)").text(roleName);
				}
				jt_close();
					
	        }else {
	            alert(data.respMessage);
	        }
	    });
}

</script>
<style type="text/css">
a{
	color 				: #ff0000;	
	text-decoration		: none;
}

a:hover{
	text-decoration		: underline;
}
#report { border-collapse:collapse;}
#report h4 { margin:0px; padding:0px;}
#report ul { margin:10px 0 10px 10px; padding:0px;}
#report ul li {
	float:left;
	width:30%;
	height:40px;
	list-style-type:none;
	padding:10px;
	font-size:13px;
}
#report ul li div {
	float:left;
	height:40px;
	line-height:40px;
	margin-left:10px;
	
}
#report ul li div:nth-child(2){
	font-weight:bold;
	width:50%;
}
#report table{ width:100%;}
#report th { background:#6e8187; color:#fff; padding:7px 15px; text-align:left;width:42%;height:30px;}
#report td { background:#fbe7ce none repeat-x scroll center left; color:#000; padding:20px 10px;color:#5a3c44; }
#report tr.odd td { background:#fff; cursor:pointer;color:#837076; }
#report tr.odd td.up { background:#fbe7ce; cursor:pointer; font-weight:bold; border-top:#FFF 1px solid; color:#5a3c44; }
#report tr.odd td.color { color:#fbe7ce;}
#report div.arrow { background:transparent url(assets/arrows.png) no-repeat scroll 0px -16px; width:16px; height:16px; display:block;}
#report div.up { background-position:0px 0px;}
.report_tx{
	width:40px;
	height:40px;
	background-size:40px 40px;
	float:left;
	border-radius:20px;
}
.rb_mainframe_middle_title{
	background-color:#6e8187;
}
</style>
</head>

<body onselectstart="return false"> 
<div class="rb_mainframe_top">
	<div class="rb_mainframe_top_title_first">
    	系统参数设置
    </div>
    <div class="rb_mainframe_top_title_second">
    	参数列表
    </div>    



</div>

<div class="rb_mainframe_content">

    <div class="rb_mainframe_middle">
        <div class="rb_mainframe_middle_content">
            <table id="report">
                <tr>
                  <th>数据字典代码</th>
		           <th>数据字典值</th>
		           <th>数据字典类型</th>
		           <th>操作</th>
                </tr>
               <#list page.data as pages>				
		                <tr id="role_${pages_index}">
		                   <#if pages.code=='31'>
			                 <td>上班</td>
			               <#elseif pages.code=='31a'>
			                 <td>上午上班</td>
			               <#elseif pages.code=='31b'>
			                 <td>上午下班</td>
			               <#elseif pages.code=='apmSplitTime'>
			                 <td>上下午考勤分割时间</td>
			               <#elseif pages.code=='32'>
			                 <td>下班</td>
			                <#elseif pages.code=='32a'>
			                 <td>下午上班</td>
			                <#elseif pages.code=='32b'>
			                 <td>下午下班</td>
			               <#elseif pages.code=='33'> 
			                 <td>定位时间间隔(分钟)</td>
			                <#elseif pages.code=='34'>
			                 <td>中心经度</td>
			                 <#elseif pages.code=='35'>
			                 <td>中心纬度</td>
			                  <#elseif pages.code=='36'>
			                 <td>半径长度(米)</td>
			                 <#elseif pages.code=='37'>
			                 <td>位置名称</td>
			                 <#elseif pages.code=='38'>
			                 <td>是否启用绑定手机</td>
			                 <#elseif pages.code=='39'>
			                 <td>绑定手机到期日期</td>
			                 <#elseif pages.code=='310'>
			                 <td>定位开始时间</td>
			                 <#elseif pages.code=='311'>
			                 <td>定位结束时间</td>
			               </#if>
			                <td>${pages.value!''}</td>
			                <td>${pages.dicType!''}</td>
			                <td>
								<a href="javascript:JT_editParamsName('aaaa_${pages_index}','修改参数','role_${pages_index}','${ctx.contextPath}/rest/dictionary/${pages.code}/put/pre')" id="aaaa_${pages_index}">修改</a>
							</td>
		            	</tr>
		            </#list>
            </table>
        </div>
    </div>
</div>
</body>
</html>
