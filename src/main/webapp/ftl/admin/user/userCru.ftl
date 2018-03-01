<#include "../../common/meta.ftl">
		<script type="text/javascript" src="${ctx.contextPath}/js/admin/user.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			if($('#deptId')){
				$('#deptId').combotree({url:'${ctx.contextPath}/rest/dept/combotree/public',method:'get'});
				$('#deptId').combotree('setValue',$('#deptVal').val());
			}
		});
		</script>
	</head>
	<body>
		<div class="head">
			<div class="path">当前位置：系统权限 >
				<#if (user.id!'')=="">
					添加用户
				<#else>
					用户信息修改
				</#if>
			</div>
		</div>
			<div class="conPanel">
				<input type="button" class="btn_12" onclick="saveUser()" value="保存"></input>
				<input type="button" class="btn_12" onclick="history.back()" value="返回"></input>
			</div>
			<form id="userForm" action="${ctx.contextPath}/rest/user" method="POST">
				<input type="hidden" name="id" value="${user.id!''}"/>
				<input type="hidden" id="oldName" name="oldName" value="${user.userName!'null'}"/>
				<input type="hidden" id="ctxPath" value="${ctx.contextPath}"/>
				<div id=tagContent>
					<div class="tagContent selectTag" id=tagContent0>
						<table class="tb_6">
							<tr>
								<th style="width: 130px;">账 &nbsp;&nbsp;号：</th>
								<td>
									<input type="text" id="userName" name="userName" value="${user.userName!''}" required="true"
										maxlength="20" class="easyui-validatebox inp_3" onchange="checkOnly()"/>
								</td>
							</tr>
							<tr>
								<th>姓 &nbsp;&nbsp;名：</th>
								<td><input type="text" name="realName" value="${user.realName!''}" maxlength="20"
									class="easyui-validatebox inp_3" class="inp_3" required="true"/></td>					
							</tr>
							<tr>
								<th>性 &nbsp;&nbsp;别：</th>
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
								<th>单 &nbsp;&nbsp;位：</th>
								<td>
									<input id="unitName" name="unitName" type="text" maxlength="50"
										value="${user.unitName!''}" class="inp_3"/>
								</td>
							</tr>
							<tr>
								<th>职&nbsp;&nbsp; 务：</th>
								<td><input type="text" name="post" value="${user.post!''}" class="inp_3" maxlength="20"/></td>					
							</tr>
							<tr>
								<th>手机号码：</th>
								<td><input type="text" id="mobile" name="mobile" value="${user.mobile!''}" 
									class="easyui-validatebox inp_3" validType="mobile"/></td>					
							</tr>
							<tr>
								<th>联系电话：</th>
								<td><input type="text" id="phone" name="phone" value="${user.phone!''}" 
									class="easyui-validatebox inp_3" validType="phone"/></td>					
							</tr>
							<tr>
								<th>电子邮箱：</th>
								<td><input type="text" name="email" value="${user.email!''}" 
									class="easyui-validatebox inp_3" validType="email"/></td>					
							</tr>
							<tr>
								<th>住 址：</th>
								<td><input type="text" name="address" value="${user.address!''}" maxlength="50"
									class="inp_3" /></td>					
							</tr>
								<tr>
									<th>用户类型：</th>
									<td>
										<select id="userType" name="userType" style="width:120px;">
										<#list userTypelist as li>
											<#if (user.userType!'')==li.code>
												<option value="${(li.code)!''}" selected>${(li.value)!''}</option>
											<#else>
												<option value="${(li.code)!''}">${(li.value)!''}</option>
											</#if>
										</#list>
										</select>
									</td>
								</tr>
								<tr>
									<th>权限级别：</th>
									<td>
										<select id="classType" name="classType" style="width:120px;">
										<#list classTypelist as li>
											<#if (user.classType!'')==li.code>
												<option value="${(li.code)!''}" selected>${(li.value)!''}</option>
											<#else>
												<option value="${(li.code)!''}">${(li.value)!''}</option>
											</#if>
										</#list>
										</select>
									</td>
								</tr>
								<tr>
									<th>所属组织结构：</th>
									<!--<td><input type="text" value="${user.depId!''}" class="inp_3"/></td>-->
									<td>
										<select id="deptId" name="deptId" style="width:150px; height: 20px;" />
										<input id="deptVal" type="hidden" value="${user.deptId!''}"/>
									</td>
								</tr>
								<tr>
									<th>是否可用：</th>
									<td>
										<#if user.isValid??&&(user.isValid!'')=="0">
											<input type="radio" name="isValid" value="1"/>是
											<input type="radio" name="isValid" checked value="0"/>否
										<#else>
											<input type="radio" name="isValid" checked value="1"/>是
											<input type="radio" name="isValid" value="0"/>否
										</#if>
									</td>
								</tr>
								<tr>
								<th>顺序：</th>
								<td><input type="text" name="sortNo" value="${user.sortNo!'0'}" 
									class="easyui-validatebox inp_3" /></td>					
								</tr>
								<tr>
									<th>是否关联通讯录：</th>
									<td>
										<#if user.isContactor??&&(user.isContactor!'')=="0">
											<input type="radio" name="isContactor" value="1"/>是
											<input type="radio" name="isContactor" checked value="0"/>否
										<#else>
											<input type="radio" name="isContactor" checked value="1"/>是
											<input type="radio" name="isContactor" value="0"/>否
										</#if>
									</td>
								</tr>
								
						</table>
					</div>
				</div>
			</form>
	</body>
</html>