<#include "../../common/meta.ftl">
	<script>
		function delMobileBinding(id){
		    var url ="${ctx.contextPath}/rest/mobilebinding/delete/" + id;
		    if (confirm("确定要删除么？")) {
		        $.get(url, null, function(data){
		            if (data.status == "success") {
		                alert(data.message);
		                location.reload();
		            }
		            else {
		                alert(data.message);
		            }
		        });
		    }
		}
		function saveMobileBinding(index,id){
			    var url ="${ctx.contextPath}/rest/mobilebinding/save";
			    var dat;
			    if(id!=null){
			    	dat="id="+id+"&imsiCode="+document.getElementById("imsiCode_"+index).value+"&isValidDevice="+getChecked("isValidDevice_"+index)+"&isSendMsg="+getChecked("isSendMsg_"+index);
			    }else{
			    	dat="userId="+document.getElementById("userId_"+index).value+"&imsiCode="+document.getElementById("imsiCode_"+index).value+"&isValidDevice="+getChecked("isValidDevice_"+index)+"&isSendMsg="+getChecked("isSendMsg_"+index);
			    }
			    $.postJSON(url,dat,function(data){
			        if (data.status == "success") {
			            alert(data.message);
			            location.reload();
			        }
			        else {
			            alert(data.message);
			        }
			    });
		}
		function getChecked(name){
			var names=document.getElementsByName(name);
			for(var i=0;i<names.length;i++){
				if(names[i].checked){
					return names[i].value;
				}
			}
		}
		var count=${(list?size)!'0'};
		var table;
		function addTables(){
			table=document.getElementById("table");
			var tr = document.createElement("tr");
			//var tr=table.insertRow();
			for(var i=0;i<6;i++){
				//var td=tr.insertCell();
				 var td = document.createElement("td");
		         tr.appendChild(td);
				if(i==0){
					td.innerHTML="${(user.userName)!''}<input type='hidden' name='userId' value='${(user.id)!""}' id='userId_"+count+"'>";
				}else if(i==1){
					td.innerHTML="<input type='text' name='imsiCode' id='imsiCode_"+count+"'>";
				}else if(i==3){
					td.innerHTML="<input type='radio' name='isValidDevice_"+count+"' checked value='1'/>是<input type='radio' name='isValidDevice_"+count+"' value='0'/>否";
				}else if(i==4){
					td.innerHTML="<input type='radio' name='isSendMsg_"+count+"' checked value='1'/>是<input type='radio' name='isSendMsg_"+count+"' value='0'/>否";
				}else if(i==5){
					td.style.textAlign="center";
					td.innerHTML="<a href='#' onclick='saveMobileBinding("+count+",null)'>保存</a> /  <a href='#' onclick='delBinding(this)'>删除</a>";
				}
			}
			 table.appendChild(tr);
			count++;
		}
		function delBinding(obj){
				var r = obj.parentNode.parentNode;
	   			var x=document.getElementById('table');  
 				x.removeChild(r);
		}
	</script>
	</head>
	<body>
		<div class="head">
			<div class="path">当前位置：系统权限 >绑定账号
			</div>
		</div>
		<div class="conPanel">
				<input type="button" class="btn_12" onclick="history.back()" value="返回"></input>
		</div>
			 	<div class="box_2" style="cursor:pointer" >
        		<span style="float:left; margin: 0 6px; font-weight: bolder; font-size:14px;">账户：${(user.userName)!''} 姓名：${(user.realName)!''}</span>
        		<span style="float:right; margin: 0 6px; font-weight: bolder; font-size:14px;"><a href="javascript:addTables();">添加</a></span>
        	</div>
		       <table class="tb_2" id="table">
					<tr>
						<th>用户名</th>
						<th>手机绑定标示</th>
						<th>时间</th>
						<th>设备是否可用</th>
						<th>是否发短信</th>
						<th width="100px;">操作</th>
					</tr>
						<#list list as datas>
							<tr <#if ((datas_index+1)%2==0)> bgcolor="#F9F9F9" </#if>>
								<td>
								${(datas.userName)!''}
							</td>
							<td>
								<input name="imsiCode" value="${(datas.imsiCode)!''}" id="imsiCode_${datas_index}"class="easyui-validatebox " required="true"/>
							</td>
							<td>
								<!--日期型-->
							${(datas.loginTime)?string("yyyy-MM-dd HH:mm:ss")}
							</td>
							<td>
								<#if (datas.isValidDevice!'')=="1">
											<input type="radio" name="isValidDevice_${datas_index}" checked value="1"/>是
											<input type="radio" name="isValidDevice_${datas_index}" value="0"/>否
								<#else>
											<input type="radio" name="isValidDevice_${datas_index}" value="1"/>是
											<input type="radio" name="isValidDevice_${datas_index}" checked value="0"/>否
								</#if>
							</td>
							<td>
								<#if (datas.isSendMsg!'')=="1">
											<input type="radio" name="isSendMsg_${datas_index}" checked value="1"/>是
											<input type="radio" name="isSendMsg_${datas_index}" value="0"/>否
								<#else>
											<input type="radio" name="isSendMsg_${datas_index}" value="1"/>是
											<input type="radio" name="isSendMsg_${datas_index}" checked value="0"/>否
								</#if>
							</td>
							<td style="text-align:center;">
								<a href="#" onclick="saveMobileBinding(${datas_index},'${datas.id}')">保存</a>
								  /  <a href="#" onclick="delMobileBinding('${datas.id}');">删除</a>
							</td>
						</tr>
						</#list>
		        </table>
	</body>
</html>