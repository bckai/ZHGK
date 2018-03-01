<#include "../../common/meta.ftl">
		<script type="text/javascript" src="${ctx.contextPath}/js/admin/role.js"></script>
	</head>
	<body>
		<input id="ctxPath" value="${ctx.contextPath}" type="hidden" />
		<div class="head">
			<div class="path">当前位置：角色信息管理 >
			<#if (role.id)?? && (role.id=="")>
				添加角色信息
			<#else>
				角色信息修改
			</#if>
			 > 角色[${(role.name)!''}]的资源分配
			</div>
		</div>
		<div id=con>
			<div>
				<input type="button" class="btn_12" value="保存" onclick="saveResAssign('${role.id!''}')"></input>
				<input type="button" class="btn_12" value="取消" onclick="location.href='${ctx.contextPath}/rest/role/get/pre'"></input>
			</div>
			<div style="padding:10px;">
				<input id="resTreeData" value='${resTreeData!""}' type="hidden" />
				<ul id="resTree"></ul>
			</div>
		</div>
	</body>
	<script>
		$(document).ready(function(){
		//==获取资源树
			$('#resTree').tree({
				checkbox: true,
				data:eval($('#resTreeData').val()),
				onLoadSuccess:function() {
					$('#resTree span.tree-icon').remove();
					//==修改子节点的排列方式
					var nodes = $('#resTree').tree('getChildren');
					for(var i=0; i<nodes.length; i++){
						node = nodes[i];
						if ($('#resTree').tree('isLeaf', node.target)) {
							$(node.target).parent().css('float','left').css('margin-top','3px');
						} else {
							$(node.target).css('clear','both').css('margin-top','6px');
						}
					}
				}
			});
		});
	</script>
</html>