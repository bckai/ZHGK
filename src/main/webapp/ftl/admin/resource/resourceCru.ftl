<#include "../../common/meta.ftl">
		<script type="text/javascript" src="${ctx.contextPath}/js/admin/resource.js"></script>
	</head>
	<body>
		<div class="head">
			<div class="path">当前位置：资源信息管理 >
				<#if (resource.id!'')=="">
					模块资源信息添加
				<#else>
					模块资源信息修改
				</#if>
			</div>
		</div>
		 <div>
			<div>
				<input type="button" class="btn_12" value="保存" onclick="saveResource()"></input>
				<input type="button" class="btn_12" value="取消" onclick="location.href='${ctx.contextPath}/rest/module/${resource.modId}/resource/get/pre'"></input>
			</div>
			<form id="resourceForm" action="${ctx.contextPath}/rest/resource/${module.id!''}" method="POST">
				<input type="hidden" name="id" value="${resource.id!''}"/>
				<div>
					<div  id=tagContent0>
						<table class="tb_6">
							<tr>
				                <th>资源名称：</th>
				                <td>
				                	<input type="hidden" name="modId" value="${resource.modId!''}" />
				                	<input type="text" name="name" value="${resource.name!''}"  />
				                </td>
				            </tr>
				            <tr>
				                <th>资源路径：</th>
				                <td><input type="text" name="url" value="${resource.url!''}"  /></td>
				            </tr>
							<tr>
								<th>资源方法：</th>
								<td>
									<select id="method" name="method"  required="true" editable="false" style="width:136px" >
									
										<option value="get" <#if resource.method=="get">selected</#if>>GET</option>
										<option value="post" <#if resource.method=="post">selected</#if>>POST</option>
										<option value="put" <#if resource.method=="put">selected</#if>>PUT</option>
										<option value="delete" <#if resource.method=="delete">selected</#if>>DELETE</option>
									</select>
									<input type="hidden" id="methodVal" value="${resource.method!''}" />
								</td>
							</tr>
				            <tr>
				                <th>排序号：</th>
				                <td><input type="text" name="sortNo" value="${resource.sortNo!''}" /></td>
				            </tr>
				            <tr>
				                <th>是否可用：</th>
				                <td>
				                	<#if (resource.isValid!'1')=="0">
										<input type="radio" name="isValid" value="1"/>是
										<input type="radio" name="isValid" checked value="0" />否
									<#else>
										<input type="radio" name="isValid" value="1" checked/>是
										<input type="radio" name="isValid" value="0" />否
									</#if>
				                </td>
				            </tr>
						</table>
					</div>
				</div>
			</form>
		</div>
	</body>
	<script>
	$(document).ready(function(){
		$('#method').combobox('setValue',$('#methodVal').val());
	})
	</script>
</html>