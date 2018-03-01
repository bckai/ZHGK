<#include "../../common/meta.ftl">
    	<title>资源列表信息管理</title>
		<script type="text/javascript" src="${ctx.contextPath}/js/admin/resource.js"></script>
	</head>
	<body>
		<input id="ctxPath" type="hidden" value="${ctx.contextPath}" />
		<div class="head"><div class="path">当前位置：资源信息管理</div></div>
		<dl class="box_1">
			<dt>
				<div>资源列表</div>
				<a class="a_3" href="#" onclick="location.href='${ctx.contextPath}/rest/resource/${module.id}/post/pre'">添加</a><div class="img_2"></div>
				<a href="#" onclick="location.href='${ctx.contextPath}/rest/module/get/pre'">返回</a>
			</dt>
			<dd>
		<table class="tb_2">
        	<tr>
            	<th>资源名称</th>
            	<th>资源路径</th>
                <th>资源方法</th>
                <th>排序号</th>
                <th>操作</th>
            </tr>
            <#list listResource as res>			
                <tr>
                <td>${res.name!''}</td>
                <td>${res.url!''}</td>
                <td>${res.method!''}</td>
                <td>${res.sortNo!''}</td>
                <td align="center">
	                <a href="${ctx.contextPath}/rest/resource/${res.id}/put/pre">修改</a>
					 / <a href="#" onclick="deleteResource('${res.id}','${res.modId}')">删除</a>
				</td>
            	</tr>
            </#list>
        </table>
        </dd>
        </dl>
	</body>
</html>
