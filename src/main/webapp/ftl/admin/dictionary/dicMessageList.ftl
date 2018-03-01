<#include "../../common/meta.ftl">
    	<script type="text/javascript" src="${ctx.contextPath}/js/admin/module.js"></script>
	</head>
	<body>
		<form id="userForm" action="${ctx.contextPath}/rest/dictionary/get/pre" method="POST">
			<input id="ctxPath" type="hidden" value="${ctx.contextPath}" />
			<input id="page" name="page" type="hidden" value="1" />
			<div class="head"><div class="path">当前位置：系统参数设置  > 参数列表</div></div>
		    <dl class="box_1">
		    	<dt>
		    		<div>参数列表</div>
	    			<!-- <a class="a_3" href="#" onclick="location.href='${ctx.contextPath}/rest/dictionary/post/pre'">添加</a> -->
	    		</dt>
	    		<dd>
				<table class="tb_2">
		        	<tr>
		        		<th>数据字典类型</th>
		            	<th>数据字典代码</th>
		            	<th>数据字典值</th>
		                <th>操作</th>
		            </tr>
		            <#list page.data as pages>				
		                <tr>
		                	<td>${pages.dicType!''}</td>
		                 	<td>${pages.code!''}</td>
			                <td>${pages.value!''}</td>
			                <td align="center" width="300">
				                <!-- / <a href="${ctx.contextPath}/rest/dictionary/${pages.code}/put/pre">修改</a>-->
								<!-- / <a href="#" onclick="deleteDic('${pages.code}')">删除</a>-->
							</td>
		            	</tr>
		            </#list>
		        </table>
		        </dd>
		        </dl>
		 </from>
	</body>
</html>
