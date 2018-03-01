<#include "../../common/meta.ftl">
		<script type="text/javascript" src="${ctx.contextPath}/js/admin/user.js"></script>
	</head>
	<body>
		<div class="head">
			<div class="path">当前位置：系统权限 > 账户管理 > 修改密码
			</div>
		</div>
		<div id=con>
			<div>
				<input type="button" class="btn_12" value="保存" onclick="savePwd()"></input>
				<input type="button" class="btn_12" value="取消" onclick="location.href='${ctx.contextPath}/rest/user/own/info/update/put/pre'"></input>
			</div>
			<ul id=tags>
			</ul>
			<form id="pwdForm" action="${ctx.contextPath}/rest/user/password/update" method="PUT">
				<input type="hidden" id="ctxPath" value="${ctx.contextPath}"/>
				<div id=tagContent>
					<div class="tagContent  selectTag" id=tagContent0>
						<table class="tb3 b">
							<tr>
								<th style="width:120px;">原始密码：</th>
								<td><input type="password" id="oldpassword" name="oldpassword" value="" 
									class="easyui-validatebox inp_2" class="inp_2" required="true"/></td>					
							</tr>
							<tr>
								<th>新 密 码：</th>
								<td><input type="password" id="newpassword" name="newpassword" value="" 
									validType="password"
									class="easyui-validatebox inp_2" class="inp_2" required="true" maxlength="20"/></td>					
							</tr>
							<tr>
								<th>再次输入新密码：</th>
								<td><input type="password" id="confirmpassword" name="confirmpassword" value="" 
									validType="pword('newpassword')"
									class="easyui-validatebox inp_2" class="inp_2" required="true" maxlength="20"/></td>					
							</tr>
						</table>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>