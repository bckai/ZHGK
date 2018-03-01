<#include "../../common/meta.ftl">
    	<title></title>
		<script type="text/javascript" src="${ctx.contextPath}/js/admin/user.js"></script>
	
	<script type="text/javascript">
	   $(document).ready(function(){
				if($('#deptId')){
					$('#deptId').combotree({url:'${ctx.contextPath}/rest/dept/combotree/public',method:'get'});
					$('#deptId').combotree('setValue',$('#deptVal').val());
				}
			});
			
	   function doSerach(){
	      location.href = "${ctx.contextPath}/rest/user/get/pre?userName="+$('#userName').val()+"&deptId="+
	                       $('#deptId').combotree('getValue');
	   }
	   
	</script> 
	</head>
	
	
	<body>
		<form id="userForm" action="${ctx.contextPath}/rest/user/get/pre" method="POST">
			<input id="ctxPath" type="hidden" value="${ctx.contextPath}" />
			<input id="page" name="page" type="hidden" value="1" />
			<div class="head"><div class="path">当前位置：人员信息管理  > 人员列表</div></div>
		    <dl class="box_1">
	<!-- 查询 -->
	        		
			<div class="contents">   		
				<table class="tb_1">
					<tr>
					    <th>账号：</th>
							<td><input type="text" id="userName"  value="${userName!''}" name="userName" class="inp_3"/></td>
						<th>部门：</th>		            
				         <td>
							<select id="deptId" name="deptId" style="width:135px; height: 20px;"/></select>
							<input type="hidden" id="deptVal" value="${deptId!''}"/>
						</td>

						<td>
							<a href="#" onclick="doSerach()" class="btn_1" ></a>
						</td>
					</tr>
				</table>
			</div>
			
		 <dt>    	
		    		<div>人员列表</div>
	    			<a class="a_3" href="#" onclick="location.href='${ctx.contextPath}/rest/user/post/pre'">添加</a>
	     </dt>
	    		<dd>
				<table class="tb_2">
		        	<tr>
		            	<th>账号</th>
		            	<th>姓名</th>
		                <th>单位</th>              
		                <th>职务</th>
		                <th>手机号码</th>
		                <th>邮箱</th>
		                 <th>部门</th>
		            </tr>
		            <#list page.data as user>		
		                <tr>
			                <td>${user.username!''}</td>
			                <td>${user.realname!''}</td>
			                <td>${user.unitname!''}</td>
			                <td>${user.post!''}</td>
			                <td>${user.mobile!''}</td>
			                <td>${user.email!''}</td>
			                 <td>${(user.deptname)!''}</td>
		            	</tr>
		            	<input id="realName" type="hidden" value="${user.realname}" />
		            </#list>
		        </table>
		        </dd>
		        <#if search?? && search>
				   <#assign searchStr = search />
			    </#if>
		        
		        <#if (page.pageIndex >= page.pages) && (page.pages != 0)>
				<#assign index = page.pages />
			<#else>
				<#assign index = page.pageIndex />
			</#if>
			<ul class="box_2">
				<li class="home">
					<#if page.firstPage>首页<#else><a href="${ctx.contextPath}/rest/user/get/pre?pageIndex=1${searchStr!}">首页</a></#if>
				</li>
				<li class="pageUP">
					<#if !(page.hasPrev)>上一页<#else><a href="${ctx.contextPath}/rest/user/get/pre?pageIndex=${index-1}${searchStr!}">上一页</a></#if>
				</li>
				<li>共计${page.recCount}条数据</li>
				<li>第${index}页/共${page.pages}页</li>
				<input type="hidden" id="pageIndex" value="${index}">
				<li class="pageDown">
					<#if !(page.hasNext)>下一页<#else><a href="${ctx.contextPath}/rest/user/get/pre?pageIndex=${index+1}${searchStr!}">下一页</a></#if>
				</li>
				<li class="end">
					<#if page.lastPage>尾页<#else><a href="${ctx.contextPath}/rest/user/get/pre?pageIndex=${page.pages}${searchStr!}">尾页</a></#if>
				</li>
			</ul>
		        </dl>
		 </from>
	</body>
</html>
