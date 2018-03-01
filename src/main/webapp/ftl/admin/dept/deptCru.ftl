<#include "../../common/meta.ftl">
		<link rel="stylesheet" href="${ctx.contextPath}/coms/easyui/easyui.css" type="text/css" />
		<script type="text/javascript" src="${ctx.contextPath}/coms/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${ctx.contextPath}/coms/easyui/jquery.easyui.extend.js"></script>
		<script type="text/javascript" src="${ctx.contextPath}/js/admin/dept.js"></script>
		<script>
			$(document).ready(function(){
				if($('#superDep')){
					$('#superDep').combotree({url:'${ctx.contextPath}/rest/dept/combotree/public',method:'get'});
					$('#superDep').combotree('setValue',$('#superDepVal').val());
				}
			})
		</script>
	</head>
	<body>
		<div class="head">
			<div class="path">当前位置：组织结构管理  >
			<#if (dept.id!'')=="">
				添加组织结构信息
			<#else>
				组织结构信息修改
			</#if>
			</div>
		</div>
		<div id=con>
			<div class="conPanel">
				<input type="button" class="btn_12" value="保存" onclick="saveDept()"></input>
				<input type="button" class="btn_12" value="取消" onclick="location.href='${ctx.contextPath}/rest/dept/get/pre'"></input>
			</div>
			<form id="deptForm" action="${ctx.contextPath}/rest/dept" method="POST">
				<input type="hidden" id="ctxPath" value="${ctx.contextPath}"/>
				<input type="hidden" id="oldName" name="oldName" value="${dept.name!''}"/>
				<input type="hidden" name="id" value="${dept.id!''}"/>
				<div id=tagContent>
					<div class="tagContent selectTag" id=tagContent0>
						<table class="tb_6">
							<tr>
				                <th style="width: 120px;">机构编码：</th>
				                <td><input type="text" name="code" value="${dept.code!''}" class="input" /></td>
				            </tr>
				            <tr>
				                <th>机构名称：</th>
				                <td><input type="text" name="name" onchange="checkOnly()" value="${dept.name!''}"/></td>
				            </tr>
				            <tr>
				                <th>上级机构：</th>
								<td>
									<select id="superDep" name="superDep" style="width:136px;" />
									<input id="superDepVal" type="hidden" value="${dept.superDep!''}"/>
								</td>
				            </tr>
				            <tr>
				                <th>负责人：</th>
								<td>
									<input type="text" name="manager" value="${dept.manager!''}" />
								</td>
				            </tr>
				            <tr>
				                <th>联系电话：</th>
				                <td><input type="text" name="phone" value="${dept.phone!''}" /></td>
				            </tr>
				            <tr>
				                <th>地址：</th>
				                <td><input type="text" name="address" value="${dept.address!''}"/></td>
				            </tr>
							<tr>
				                <th>是否可用：</th>
				                <td>
				                	<#if  dept.isValid >
				                    	<input type="radio" name="isValid" checked value="1"/>是
										<input type="radio" name="isValid" value="0"/>否
									<#else>
				                    	<input type="radio" name="isValid" value="1"/>是
										<input type="radio" name="isValid" checked value="0"/>否
									</#if>
				                </td>
				            </tr>
						</table>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>