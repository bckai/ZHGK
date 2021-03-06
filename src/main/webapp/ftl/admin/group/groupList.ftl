<#include "../../common/meta.ftl">
    <title>用户组信息管理</title>
	<script type="text/javascript" src="${ctx.contextPath}/js/admin/group.js"></script>
</head>
<body>
	<input id="ctxPath" value="${ctx.contextPath}" type="hidden" />
		<input id="serverPath" value="${ctx.contextPath}/rest/group/get/pre" type="hidden" />
		<div class="head"><div class="path">当前位置：用户组信息管理  &gt; 用户组列表</div></div>
		<dl class="box_1">
			<dt>
				<div>用户组列表</div>
				<a class="a_3" href="#" onclick="location.href='${ctx.contextPath}/rest/group/post/pre'">添加</a><div class="img_2"></div>
			</dt>
		<dd>
		<table class="tb_2">
        	<tr>
            	<th>用户组名称</th>
            	<th>用户组描述</th>
                <th>操作</th>
            </tr>
            <#list pageModel.data as group>				
                <tr>
	                <td>${group.name}</td>
	                <td>${group.description}</td>
	                <td align="center">
		                <a href="${ctx.contextPath}/rest/group/${group.id}/put/pre">修改</a>
		                / <a href="${ctx.contextPath}/rest/group/${group.id}/assign/post/pre">分配用户</a>
						/ <a href="#" onclick="deleteGroup('${group.id}')">删除</a>
					</td>
            	</tr>
            </#list>
        </table>
        </dd>
        <#if (page.pageIndex >= page.pages) && (page.pages != 0)>
				<#assign index = page.pages />
			<#else>
				<#assign index = page.pageIndex />
			</#if>
			<ul class="box_2">
				<li class="home">
					<#if page.firstPage>首页<#else><a href="${ctx.contextPath}/rest/group/get/pre?pageIndex=1">首页</a></#if>
				</li>
				<li class="pageUP">
					<#if !(page.hasPrev)>上一页<#else><a href="${ctx.contextPath}/rest/group/get/pre?pageIndex=${index-1}">上一页</a></#if>
				</li>
				<li>共计${page.recCount}条数据</li>
				<li>第${index}页/共${page.pages}页</li>
				<li class="pageDown">
					<#if !(page.hasNext)>下一页<#else><a href="${ctx.contextPath}/rest/group/get/pre?pageIndex=${index+1}">下一页</a></#if>
				</li>
				<li class="end">
					<#if page.lastPage>尾页<#else><a href="${ctx.contextPath}/rest/group/get/pre?pageIndex=${page.pages}">尾页</a></#if>
				</li>
			</ul>
        </dl>
</body>
</html>
