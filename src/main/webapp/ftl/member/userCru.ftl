<#include "../../common/meta.ftl">
		<script type="text/javascript" src="${ctx.contextPath}/js/member/member.js"></script>
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
				<#if (member.id)??>
					用户信息修改
				<#else>
					添加用户
				</#if>
			</div>
		</div>
			<div class="conPanel">
				<input type="button" class="btn_12" onclick="saveUser()" value="保存"></input>
				<input type="button" class="btn_12" onclick="history.back()" value="返回"></input>
			</div>
			<form id="userForm" action="${ctx.contextPath}/rest/member/save" method="POST">
				<input type="hidden" name="id" value="${(member.id)!''}"/>
				<input type="hidden" id="ctxPath" value="${ctx.contextPath}"/>
				<div id=tagContent>
					<div class="tagContent selectTag" id=tagContent0>
						<table class="tb_6">
							<tr>
								<th>手机号码：</th>
								<td><input type="text" id="mobile" <#if (member.id)??>readOnly="true"</#if> name="mobile" value="${(member.mobile)!''}" 
									class="easyui-validatebox inp_3" validType="mobile"/></td>					
							</tr>
							<tr>
								<th>姓 &nbsp;&nbsp;名：</th>
								<td><input type="text" name="realName" value="${(member.realName)!''}" maxlength="20"
									class="easyui-validatebox inp_3" class="inp_3" required="true"/></td>					
							</tr>
							<tr>
								<th>性 &nbsp;&nbsp;别：</th>
								<td>
									<#if (member.gender)!'1'=="0">
										<input type="radio" name="gender" id="sex" value="1"/>男
										<input type="radio" name="gender" id="sex" checked value="0"/>女
									<#else>
										<input type="radio" name="gender" id="sex" value="1" checked/>男
										<input type="radio" name="gender" id="sex" value="0"/>女
									</#if>
								</td>
							</tr>
							<tr>
								<th>电子邮箱：</th>
								<td><input type="text" name="email" value="${(member.email)!''}" 
									class="easyui-validatebox inp_3" validType="email"/></td>					
							</tr>
							<tr>
								<th>住 址：</th>
								<td><input type="text" name="address" value="${(member.address)!''}" maxlength="50"
									class="inp_3" /></td>					
							</tr>
								<tr>
									<th>用户类型：</th>
									<td>
										<select id="userType" name="type" style="width:120px;">
										<#list memberTypes as type>
											<#if (member.type)!''==type.name()>
												<option value="${type.name()}" selected>${type.name()}</option>
											<#else>
												<option value="${type.name()}">${type.name()}</option>
											</#if>
										</#list>
										</select>
									</td>
								</tr>
								<tr>
									<th>所属组织结构：</th>
									<!--<td><input type="text" value="${(member.depId)!''}" class="inp_3"/></td>-->
									<td>
										<select id="deptId" name="deptId" style="width:150px; height: 20px;" />
										<input id="deptVal" type="hidden" value="${(member.deptId)!''}"/>
									</td>
								</tr>
								<tr>
									<th>是否可用：</th>
									<td>
										<#if (member.isValid)??&&(member.isValid)!''=="0">
											<input type="radio" name="isValid" value="1"/>是
											<input type="radio" name="isValid" checked value="0"/>否
										<#else>
											<input type="radio" name="isValid" checked value="1"/>是
											<input type="radio" name="isValid" value="0"/>否
										</#if>
									</td>
								</tr>
								
						</table>
					</div>
				</div>
			</form>
	</body>
</html>