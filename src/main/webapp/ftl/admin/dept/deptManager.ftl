<#include "../../common/meta.ftl">
		<script type="text/javascript" src="${ctx.contextPath}/js/admin/dept.js"></script>
	</head>
	<body>
		<div class="head">
			<div class="path">当前位置：组织结构管理 > 分配组织结构负责人
			</div>
		</div>
		<#if (users?size>0)>
		    <div>
				<div>
					<input type="button" class="btn_12" value="保存" onclick="saveDeptManager()"></input>
					<input type="button" class="btn_12" value="取消" onclick="location.href='${ctx.contextPath}/rest/dept/get/pre'"></input>
				</div>
				<ul id=tags></ul>
				<form id="deptManagerForm" action="${ctx.contextPath}/rest/dept/${dept.id}/deptManager" method="post">
					<div id=tagContent>
						<div>
							<table>
				            	<tr>
					                <th>负责人：</th>
					                <td>
					                    <select id="manager" name="manager" style="width:136px;" >
					                	<option value="">--请选择人员</option>              
						                	<#list users as user>
						                		<#if dept.manager?exists&& dept.manager==user.id>
						                			<option value="${user.id}" selected="true">${user.realName}</option>
						                		<#else>
						                			<option value="${user.id}">${user.realName}</option>
						                		</#if>
						                	</#list>
					                	</select>              	
					                </td>	
					            </tr>    
							</table>
						</div>
					</div>
				</form>
			</div>
		<#else>
			<div id=con>
				<div class="conPanel">
					<input type="button" class="btn_12" value="取消" onclick="location.href='${ctx.contextPath}/rest/dept/get/pre'"></input>
				</div>
				<ul id=tags>　　对不起,没有人员可以分配!</ul>
			</div>
		</#if>
	</body>
</html>