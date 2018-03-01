<#include "../../common/meta.ftl">
	<script type="text/javascript" src="${ctx.contextPath}/js/admin/module.js"></script>
	<script>
		$(document).ready(function(){
			if($('#superMod')){
				$('#superMod').combotree({url:'${ctx.contextPath}/rest/module/combotree/public',method:'get'});
				$('#superMod').combotree('setValue',$('#superModVal').val());
			}
		})
	</script>
	</head>
	<body><div class="head">
			<div class="path">当前位置： 模块信息管理 >
				<#if (module.id!'')=="">
					添加模块信息
				<#else>
					模块信息修改
				</#if>
			</div>
		</div>
		<div id=con>
			<div class="conPanel">
				<input type="button" class="btn_12" value="保存" onclick="saveModule()"></input>
				<input type="button" class="btn_12" value="取消" onclick="location.href='${ctx.contextPath}/rest/module/get/pre'"></input>
			</div>
			<form id="moduleForm" action="${ctx.contextPath}/rest/module" method="POST">
				<input type="hidden" name="id" value="${module.id!''}"/>
				<div id=tagContent>
					<div class="tagContent selectTag" id=tagContent0>
						<table class="tb_6">
							<tr>
				                <th style="width: 120px;">模块名称：</th>
				                <td><input type="text" name="name" value="${module.name!''}" class="easyui-validatebox input" required="true" /></td>
				            </tr>
				            <tr>
				                <th>上级模块：</th>
								<td>
									<select id="superMod" name="superMod" style="width:152px;" value=""/>
									<input id="superModVal" type="hidden" value="${(module.superMod)!''}" />
								</td>
				            </tr>
				            <tr>
				                <th>排序号：</th>
				                <td><input type="text" name="sortNo" value="${module.sortNo!''}" class="easyui-validatebox input" required="true" /></td>
				            </tr>
				            <tr>
				                <th>叶子结点：</th>
				                <td>
				                	<#if (module.isLeaf!'1')=="0">
					                	<input type="radio" name="isLeaf" value="1" />是
										<input type="radio" name="isLeaf" value="0" checked />否
									<#else>
					                	<input type="radio" name="isLeaf" value="1" checked />是
										<input type="radio" name="isLeaf" value="0" />否
									</#if>
				                </td>
				            </tr>
				            </tr>
								<th>模块路径：</th>
								<td>
									<#if (module.isLeaf!'1')=="0">
										<input type="text" name="defaultURL" value="${module.defaultURL!''}" class="easyui-validatebox input" disabled="true" />
									<#else>
										<input type="text" name="defaultURL" value="${module.defaultURL!''}" class="easyui-validatebox input" />
									</#if>
								</td>
							</tr>
				            <tr>
				                <th>是否可用：</th>
				                <td>
									<#if (module.isValid!'1')=='0'>
				                		<input type="radio" name="isValid" value="1" />是
										<input type="radio" name="isValid" value="0" checked />否
									<#else>
				                		<input type="radio" name="isValid" value="1" checked />是
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
</html>