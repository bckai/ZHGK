<#include "../../common/meta.ftl">
    	<title>人员信息管理</title>
		<script type="text/javascript" src="${ctx.contextPath}/js/member/member.js"></script>
	</head>
	<body>
		<input id="ctxPath" type="hidden" value="${ctx.contextPath}" />
		<input id="userId" type="hidden" value="${user.id}" />
		<div class="head">
			<div class="path">当前位置：系统权限 > 人员信息管理 > 用户角色分配
			</div>
		</div>
		<div>
			<div>
				<input type="button" class="btn_12" value="保存" onclick="saveRoleAssign()"></input>
				<input type="button" class="btn_12" value="返回" onclick="history.back()"></input>
			</div>
			<ul id=tags>
			</ul>
			<div id=tagContent>
				<div id=tagContent0>
					<table class="box_4" style="width:500px; border: 1px;
						margin-left:auto; margin-right:auto;">
						<tr>
							<th>未分配角色列表</th>
							<th style="border: 0px;"></th>
							<th>已分配角色列表</th>
						</tr>
						<tr>
							<td style="width: 200px; text-align: center;">
					        	<select id="srcRole" ondblclick="roleSelect(this.id,'desRole')"
					        		multiple="multiple" style="width:150px;height:200px;" scroll="yes">
					            	<#if unAssignRoles??>
										<#list unAssignRoles as roleList>
											<option value="${roleList.id}">${roleList.name}</option>
										</#list>
									</#if>
					          	</select>
					        </td>
					        <td style="text-align: center; border: 0px;">
						        <input type="button" style="width:40px" value="<<" onclick="roleSelect('desRole','srcRole','all')" /><br />
						        <input type="button" style="width:40px" value="<" onclick="roleSelect('desRole','srcRole')" /><br />
						        <input type="button" style="width:40px" value=">" onclick="roleSelect('srcRole','desRole')" /><br />
						        <input type="button" style="width:40px" value=">>" onclick="roleSelect('srcRole','desRole','all')" /><br />
					        </td>
					        <td style="width: 200px; text-align: center;">
					          	<select id="desRole" ondblclick="roleSelect(this.id,'srcRole')"
					          		multiple="multiple" style="width:150px;height:200px;">
									<#if assignRoles??>
										<#list assignRoles as roleList>
											<option value="${roleList.id}">${roleList.name}</option>
										</#list>
									</#if>
								</select>
					        </td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>
