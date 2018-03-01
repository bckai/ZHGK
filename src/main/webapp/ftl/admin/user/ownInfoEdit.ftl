<#include "../../common/meta.ftl">
	<title>个人信息维护</title>
	<script type="text/javascript" src="${ctx.contextPath}/js/admin/owninfo.js"></script>
</head>
<body>
		<div class="head">
			<div class="path">当前位置：个人信息>
					用户信息修改

			</div>
		</div>
			<div class="conPanel">
				<input type="button" class="btn_12" onclick="saveOwnInfo();" value="确定"></input>
				<input type="button" class="btn_12" onclick="updatePwd()" value="修改密码"></input>
			</div>
			<form id="ownForm" method="POST">
				<input type="hidden" name="id" value="${user.id!''}"/>
				<input type="hidden" id="ctxPath" value="${ctx.contextPath}"/>
				<div id=tagContent>
					<div class="tagContent selectTag" id=tagContent0>
						<table class="tb_6">
							<tr>
								<th style="width: 130px;">账 号：</th>
								<td>
									<input type="text" id="userName" name="userName" value="${user.userName!''}" required="true"
										maxlength="20" class="easyui-validatebox inp_2" onchange="checkOnly()"/>
								</td>
							</tr>
							<tr>
								<th>姓 名：</th>
								<td><input type="text" name="realName" value="${user.realName!''}" maxlength="20"
									class="easyui-validatebox inp_2" class="inp_2" required="true"/></td>					
							</tr>
							<tr>
								<th>性 别：</th>
								<td>
									<#if (user.sex!'1')=="0">
										<input type="radio" name="sex" id="sex" value="1"/>男
										<input type="radio" name="sex" id="sex" checked value="0"/>女
									<#else>
										<input type="radio" name="sex" id="sex" value="1" checked/>男
										<input type="radio" name="sex" id="sex" value="0"/>女
									</#if>
								</td>
							</tr>
							<tr>
								<th>单 位：</th>
								<td>
									<input id="unitName" name="unitName" type="text" maxlength="50"
										value="${user.unitName!''}" class="inp_2"/>
								</td>
							</tr>
							<tr>
								<th>所属组织结构：</th>
								<td><input type="text" value="${user.depId!''}" class="inp_2"/></td>
							</tr>
							<tr>
								<th>职 务：</th>
								<td><input type="text" name="post" value="${user.post!''}" class="inp_2" maxlength="20"/></td>					
							</tr>
							<tr>
								<th>手机号码：</th>
								<td><input type="text" id="mobile" name="mobile" value="${user.mobile!''}" 
									class="easyui-validatebox inp_2" validType="mobile"/></td>					
							</tr>
							<tr>
								<th>联系电话：</th>
								<td><input type="text" id="phone" name="phone" value="${user.phone!''}" 
									class="easyui-validatebox inp_2" validType="phone"/></td>					
							</tr>
							<tr>
								<th>电子邮箱：</th>
								<td><input type="text" name="email" value="${user.email!''}" 
									class="easyui-validatebox inp_2" validType="email"/></td>					
							</tr>
							<tr>
								<th>住 址：</th>
								<td><input type="text" name="address" value="${user.address!''}" maxlength="50"
									class="inp_2" /></td>					
							</tr>
				
						</table>
					</div>
				</div>
			</form>
	</body>
</html>
