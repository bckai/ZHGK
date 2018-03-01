<#include "../../common/meta.ftl">
    	<title>用户组信息管理</title>
		
		<script type="text/javascript" src="${ctx.contextPath}/coms/zTree/jquery.ztree.core-3.4.js"></script>
			<script type="text/javascript" src="${ctx.contextPath}/coms/zTree/jquery.ztree.excheck-3.4.js"></script>
			<link rel="stylesheet" href="${ctx.contextPath}/coms/zTree/demo.css" type="text/css">
			<link rel="stylesheet" href="${ctx.contextPath}/coms/zTree/zTreeStyle/zTreeStyle.css" type="text/css">
			<script type="text/javascript" src="${ctx.contextPath}/js/admin/group.js"></script>
		<script>
		 	$(document).ready(function() {
				var nodes = eval($("#groupJson").val());
				$.fn.zTree.init($("#treeDemo"), setting, nodes);
				$("#menuContent").css({left:$("#userIds").offset().left + "px", top:$("#userIds").offset().top + "px"}).slideDown("fast");
			
			
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				var userIds = "";
				for ( var i = 0; i < nodes.length; i++) {
					if(nodes[i].onCheck == "1"){
						zTree.getNodeByParam("id",nodes[i].id).checked=true;
						userIds += nodes[i].id + ",";
					}
				}
				if (userIds.length > 0){
					userIds = userIds.substring(0, userIds.length - 1);
				}
				$("#userIds").val(userIds);
			});
		 </script> 
	</head>
	<body>
		<input id="ctxPath" type="hidden" value="${ctx.contextPath}" />
		<div class="head">
			<div class="path">当前位置：系统权限 > 用户组信息管理 > 分配人员</div>
		</div>
		<div>
			<div>
				<input type="button" class="btn_12" value="保存" onclick="saveUserAssign('${groupId}')"></input>
				<input type="button" class="btn_12" value="返回" onclick="history.back()"></input>
			</div>
			<input type="hidden" value='${groupJson!''}' id="groupJson"/>
			<ul id=tags>
			</ul>
			<div id=tagContent>
				<div id=tagContent0>
					<input id="userIds" name="userIds" type="hidden" />
					<div id="menuContent" class="menuContent" style="display:none; text-align:center;">
						<ul id="treeDemo" class="ztree"  style="width:96%; height:100%; margin-left:auto; margin-right: auto; background:#fff;"></ul>
					</div>							
				</div>
			</div>
		</div>
	</body>
</html>
