<#include "../../common/meta.ftl">
		<script type="text/javascript" src="${ctx.contextPath}/js/admin/group.js"></script>
	</head>
	<body>
		<div class="head">
			<div class="path">
				当前位置：用户组信息管理  > 
				<#if (group.id!'')=="">
					用户组信息添加
				<#else>
					用户组信息修改
				</#if>
			</div>
		</div>
			<div>
				<input type="button" class="btn_12" onclick="saveGroup()" value="保存"></input>
				<input type="button" class="btn_12" onclick="location.href='${ctx.contextPath}/rest/group/get/pre'" value="取消"></input>
			</div>
			<form id="groupForm" action="${ctx.contextPath}/rest/group" method="POST">
				<input type="hidden" name="id" value="${group.id!''}"/>
				<input type="hidden" id="oldName" name="oldName" value="${group.name!''}"/>
				<input type="hidden" id="ctxPath" value="${ctx.contextPath}"/>
				<div>
					<div>
						<table class="tb_6">
				            <tr>
				                <th style="width:120px;">用户组名称：</th>
				                <td><input type="text" id="groupName" name="name" value="${group.name!''}" onchange="checkOnly()" /></td>
				            </tr>
				            <tr>
				                <th>是否可用：</th>
				                <td>
				                	<#if (group.isValid!'1')=="0">
										<input type="radio" name="isValid" value="1"/>是
										<input type="radio" name="isValid" checked value="0" />否
									<#else>
										<input type="radio" name="isValid" value="1" checked/>是
										<input type="radio" name="isValid" value="0" />否
									</#if>
				                </td>
				            </tr>
				            <tr>
				                <th style="line-height: 60px;">用户组描述：</th>
				                <td>
				                	<textarea  name="description" cols="40" rows="3">${group.description!''}</textarea>
				                </td>
				            </tr>
						</table>
					</div>
				</div>
		</div>
	</body>
</html>
