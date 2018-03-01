<#include "../../common/meta.ftl">
		<script type="text/javascript" src="${ctx.contextPath}/coms/jquery.validate.js"></script>
   		<script type="text/javascript" src="${ctx.contextPath}/coms/jquery.validate.extend.js"></script>
		<script type="text/javascript" src="${ctx.contextPath}/js/info/book/book.js"></script>
		<script type="text/javascript" src="${ctx.contextPath}/coms/m97Date/WdatePicker.js"></script>
		<script>
			function hidShow(tabId){
				var obj = document.getElementById(tabId);
				obj.style.display = obj.style.display=='none' ? '' : 'none';
			}
			var a=0;
			$(document).ready(function(){
				if($('#superDepId')){
					$('#superDepId').combotree({url:'${ctx.contextPath}/rest/dept/comb/public',method:'get',onSelect:function(node) {
                           $('#deptId').combotree({url:'${ctx.contextPath}/rest/dept/comb/public?superDepId='+node.id,method:'get'});
						if(a==0){
							$('#deptId').combotree('setValue',$('#deptVal').val());
							a=1;
                           }
                        }
                        });
					$('#superDepId').combotree('setValue',$('#superDepVal').val());
				
				}
			})
			
			function doSerach(){
	            location.href = "${ctx.contextPath}/rest/basaddressbook/list?name="+$('#name').val()+"&deptId="+
	                       $('#deptId').combotree('getValue')+"&superDepId="+$('#superDepId').combotree('getValue');
	        }
	        
	      
		</script>
		</script>
</head>
<body>
	<input id="ctxPath" value="${ctx.contextPath}" type="hidden" />
	<!-- 新建页面 -->
	<div id="newDialog" title="新增"></div>
	<!-- 查看页面 -->
	<div id="infoDialog" title="修改"></div>
	
	<div class="head">
		<div class="path">信息公告 &gt; 通讯录</div>
	</div>
	<div class="contents">
<!-- 查询 -->
		<table class="tb_1">
			<tr>
			    <th>姓名：</th>
					<td><input type="text" id="name" value="${name!''}" name="name" class="inp_3"/></td>
				<th>所属部门：</th>
				 	<td>
						<select id="superDepId" name="superDepId" style="width:200px; height: 20px;"/></select>
						 <input type="hidden" id="superDepVal" value="${superDepId!''}"/>
					</td>
				     <td>
						<select id="deptId" name="deptId" style="width:200px; height: 20px;"/></select>
						 <input type="hidden" id="deptVal" value="${deptId!''}"/>
					</td>
				
				<td>
					<a href="#" onclick="doSerach()" class="btn_1" ></a>
					</a>
				</td>
			</tr>
		</table>
<!-- 列表 -->
		<dl class="box_1">
			<dt>
				<div>列表详情</div>
				<a class="a_3" href="javascript:newBasbook()">添加</a><div class="img_2"></div>
				<a class="a_0" href="#" onclick="delMulti();">批量删除</a><div class="img_2"></div>
			</dt>
        <#list basAllList as basDept>
        	<div class="box_2" style="cursor:pointer" onClick="hidShow('Tab_${basDept_index}')">
        		<span style="float:left; margin: 0 6px; font-weight: bolder; font-size:14px;">${basDept.deptName!''}</span>
        	</div>
		       <table class="tb_2" id="Tab_${basDept_index}">
					<tr>
						<th width="50px;"><input type="checkbox" name="check_all" id="check_all_${basDept_index}" onclick="checkAll(this.id)"/></th>
						<th width="100px;">姓名</th>
						<th width="150px;">所属部门</th>
						<th width="150px;">职务</th>
						<th>手机号</th>
						<th>固话</th>
						<th width="150px;">缩位号</th>
						<th width="100px;">操作</th>
					</tr>
						<#list basDept.bookList as datas>
							<tr ondblclick="infoDinotice('${datas.id}');" onclick="checkThis(this);" <#if ((datas_index+1)%2==0)> bgcolor="#F9F9F9" </#if>>
							<#if (basDept.bookList?size > 0)>
								<td align="center">
									<input type="checkbox" name="check_li_${basDept_index}" class="check_li" value="${datas.id}" />
								</td>
							</#if>
								<td>${(datas.name)!}</td>
								<td>${(datas.dept)!}</td>
								<td>${(datas.position)!}</td>
								<td>${(datas.tel)!}</td>
								<td>${(datas.phone)!}</td>
								<td>${(datas.abNumber)!}</td>
								<td style="text-align:center;">
									<a href="javascript:editBasbook('${datas.id}')">修改</a>&nbsp;
									<a href="#" onclick="delBasbook('${datas.id}'); stopBubble(event);">删除</a>
								</td>
							</tr>
						</#list>
		        </table>
    	</#list>
		</dl>
	</div>
</body>
</html>
			

