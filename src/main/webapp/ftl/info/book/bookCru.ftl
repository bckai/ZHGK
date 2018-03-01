<#include "../../common/meta.ftl">
   <script type="text/javascript" src="${ctx.contextPath}/coms/jquery.validate.js"></script>
   <script type="text/javascript" src="${ctx.contextPath}/coms/jquery.validate.extend.js"></script>
   <script type="text/javascript" src="${ctx.contextPath}/js/info/book/book.js"></script>
   <script type="text/javascript">
     $(document).ready(function(){
				if($('#deptId')){
					$('#deptId').combotree({url:'${ctx.contextPath}/rest/dept/combotree/public',method:'get'});
				    $('#deptId').combotree('setValue',$('#deptVal').val());
				}
			});
   </script>
<body>
       <div class="head">
			<div class="path">当前位置：通讯录 >
				<#if (basBook.id!'')=="">
					通讯录新增
				<#else>
					通讯录更新
				</#if>
			</div>
		</div>
		
<div class="conPanel">
		<input type="button" id="saveBtn" onclick="saveBasbook()" class="btn_12" icon="icon-save" value="保存"></input>
		<!--<a onclick="savenotice('2')" class="easyui-linkbutton" icon="icon-save">保存发布</a>-->
		<input type="button" onclick="cancel()" class="btn_12" icon="icon-cancel" value="取消"></input>

</div>

<form id="bookForm" action="${ctx.contextPath}/rest/basaddressbook" method="POST">
    <input id="ctxPath" value="${ctx.contextPath}" type="hidden" />
    <input type="hidden" name="id" value="${basBook.id!''}" />
	<table class="tb_6" style="margin-top:1px;" align="center">	
			<tr>
				<th>姓名:<font color="red">*</font></th>	
				<td>
					<input type="text" id="name" name="name"   value="${basBook.name!''}" maxlength="120" class="inp_3"/>
				</td>	
			</tr>
			<tr>
				<th>单位:</th>	
				<td>
					<input type="text" id="unitName" name="unitName" value="${basBook.unitName!''}" maxlength="120" class="inp_3"/>
				</td>	
			</tr>
			<tr>
				<th>职务:</th>	
				<td>
					<input type="text" id="position" name="position" value="${basBook.position!''}" maxlength="120" class="inp_3"/>
				</td>	
			</tr>
			<tr>
				<th>手机号:<font color="red">*</font></th>	
				<td>
					<input type="text" id="tel" name="tel" value="${basBook.tel!''}" maxlength="120" class="inp_3"/>
				</td>	
			</tr>
			<tr>
				<th>固话:<font color="red">*</font></th>	
				<td>
					<input type="text" id="phone" name="phone" value="${basBook.phone!''}" maxlength="120" class="inp_3"/>
				</td>	
			</tr>
			<tr>
				<th>缩位号:</th>	
				<td>
					<input type="text" id="abNumber" name="abNumber" value="${basBook.abNumber!''}" maxlength="120" class="inp_3"/>
				</td>	
			</tr>
			<tr>
				<th>性别:</th>	
				<td>
					<#if basBook.sex??&&basBook.sex=="0">
					<input type="radio" name="sex" value="1" > 男 <input type="radio" value="0" name="sex" checked/> 女
					<#else>
					<input type="radio" value="1" name="sex" checked> 男 <input type="radio" name="sex" value="0" /> 女
					</#if>
				</td>	
			</tr>
			<tr>
				<th>顺序:</th>	
				<td>
					<input type="text" id="remark" name="remark" value="${basBook.remark!'0'}" class="inp_3"/>
				</td>	
			</tr>
			<tr>
				<th>组织结构:<font color="red">*</font></th>	
				<td>
					<select id="deptId" name="deptId" style="width:250px; height: 20px;" />
					<input id="deptVal" type="hidden" value="${basBook.deptId!''}"/>
				</td>
			</tr>
	</table>
</form>
</body>