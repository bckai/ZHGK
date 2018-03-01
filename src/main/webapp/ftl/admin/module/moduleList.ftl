<#include "../../common/meta.ftl">
    	<title></title>
		<script type="text/javascript" src="${ctx.contextPath}/js/admin/module.js"></script>
	</head>
	<#macro buildNode child parent level parId> 
   		<#if child?? && child?size gt 0> 
	        <#list child as tt> 
	        	<tr id="${parId}-${tt_index}" onMouseover="this.style.background='#F9F9F9'"
			            onMouseout="this.style.background='#FFFFFF'">
			    	<td>
	            	<#list 0..level as i>　　</#list> 
	                <#if tt.children?? && tt.children?size gt 0>
	               		<img src="${ctx.contextPath}/images/folderopen.gif" onclick="disaplay('${parId}-${tt_index}')"/>
	                <#else>
	                 	<img src="${ctx.contextPath}/images/tree_file.gif" />
	                </#if> 
	                ${tt.name}
	                </td>
	                <td>${tt.sortNo!""}</td>
	                <td>${tt.defaultURL!""}</td>
	                <td>
	                 <#if tt.isValid=="1">
	               		是
	                <#else>
	               		否	
	                </#if> 
	                </td>
	                <td align="center">
		                <a href="${ctx.contextPath}/rest/module/${tt.id}/put/pre">修改</a>/
		                 <#if tt.isLeaf=="1">
						<a href="${ctx.contextPath}/rest/module/${tt.id}/resource/get/pre" >资源管理</a>/</#if> 
						<a href="#" onclick="deleteModule('${tt.id}')">删除</a>
				    </td>
	            </tr>
            	<#if tt.children?? && tt.children?size gt 0> 
	            	<@buildNode child=tt.children parent=tt level=(level+1) parId=(parId+"-"+tt_index)/>
                </#if> 
	        </#list> 
	    </#if> 
	</#macro> 
	<body>
		<input id="ctxPath" type="hidden" value="${ctx.contextPath}" />
		<div class="head"><div class="path">当前位置：模块信息管理  > 模块列表</div></div>
		<dl class="box_1">
			<dt>
			<div>模块列表</div>
			<a href="#" class="a_3" onclick="location.href='${ctx.contextPath}/rest/module/post/pre'">添加</a><div class="img_2"></div>
			</dt>
		<dd>
		<table class="tb_2">
        	<tr>
            	<th>模块名称</th>
            	<th>排序号</th>
                <th>模块地址</th>
                <th>是否可用</th>
                <th>操作</th>
            </tr>
            <@buildNode child=listTree parent="" level=0 parId=" "/>
        </table>
        </dd>
        </dl>
	</body>
</html>