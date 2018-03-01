<#include "../../common/meta.ftl">
		<script type="text/javascript" src="${ctx.contextPath}/js/admin/role.js"></script>
	</head>
	<body>
		<div class="head">
			<div class="path">
				当前位置：角色信息管理  > 
				<#if (role.id!'')=="">
					添加角色信息
				<#else>
					角色信息修改
				</#if>
			</div>
		</div>
			<div>
				<input type="button" class="btn_12" onclick="saveRole()" value="保存"></input>
				<input type="button" class="btn_12" onclick="location.href='${ctx.contextPath}/rest/role/get/pre'" value="取消"></input>
			</div>
			<form id="roleForm" action="${ctx.contextPath}/rest/role" method="POST">
				<input type="hidden" name="id" value="${role.id!''}"/>
				<input type="hidden" id="oldName" name="oldName" value="${role.name!''}"/>
				<input type="hidden" id="ctxPath" value="${ctx.contextPath}"/>
				<div>
					<div>
						<table class="tb_6">
				            <tr>
				                <th style="width:120px;">角色名称：</th>
				                <td><input type="text" id="roleName" name="name" value="${role.name!''}" onchange="checkOnly()" /></td>
				            </tr>
				            <tr>
				                <th>是否可用：</th>
				                <td>
				                	<#if (role.isValid!'1')=="0">
										<input type="radio" name="isValid" value="1"/>是
										<input type="radio" name="isValid" checked value="0" />否
									<#else>
										<input type="radio" name="isValid" value="1" checked/>是
										<input type="radio" name="isValid" value="0" />否
									</#if>
				                </td>
				            </tr>
				            <tr>
				                <th>是否流程角色：</th>
				                <td>
				                	<#if (role.isValid!'1')=="0">
										<input type="radio" name="isFlow" value="1"/>是
										<input type="radio" name="isFlow" checked value="0" />否
									<#else>
										<input type="radio" name="isFlow" value="1" checked/>是
										<input type="radio" name="isFlow" value="0" />否
									</#if>
				                </td>
				            </tr>
				            <tr>
				                <th style="line-height: 60px;">角色描述：</th>
				                <td>
				                	<textarea  name="description" cols="40" rows="3">${role.description!''}</textarea>
				                </td>
				            </tr>
						</table>
					</div>
				</div>
		</div>
	</body>
</html>
