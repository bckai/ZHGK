<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="${ctx.contextPath}/res/managerres/scripts/components/prototype/prototype.lite.js"></script>
		<script type="text/javascript" src="${ctx.contextPath}/res/managerres/scripts/components/prototype/moo.fx.js"></script>
		<script type="text/javascript" src="${ctx.contextPath}/res/managerres/scripts/components/prototype/moo.fx.pack.js"></script>
		<script language="javascript">
				Array.prototype.cons =  function(el) {
					for(var i=0;i<this.length;i++)
						if(this[i]==el)return true;
					return false;
				}
				
				function getChildren(e) {
					var childList = e.childNodes;
					if(!childList||!childList.length)return [];
					
					var elements = [];
					for(var i =0;i < childList.length ;i++)
						if(childList[i].nodeType==1)
							elements.push(childList[i]);
					return elements;
				}
				
				function getTargetElement(el, outTag, targetTag) {
					if  (el.nodeName.toUpperCase()==outTag.toUpperCase())return false;
					var targetEl;
					while(el.nodeName.toUpperCase()!=outTag.toUpperCase()) {
						targetEl = el;
						el=el.parentNode;
					}
					return targetEl;
				}
				
				function doModifyCss(e, event) {
					var checkCls = 'current', unCheckCls = '';
					var targetTag = 'a' , outTag = 'dd';
					//触发事件的 element
					var srcEl = event.srcElement || event.target;
					//获取指定的 element
					srcEl = getTargetElement(srcEl, outTag, targetTag);
					//全部的子节点
					var children =  getChildren(e);
					//如果不包含子节点
					if(!children.cons(srcEl))return;
					
					var tmp;
					for(var	i =0; i<children.length;i++) {
						tmp = children[i];
						if(tmp==srcEl)
							tmp.className = checkCls;
						else
							tmp.className = unCheckCls;
					}
				}
				
				function updateCss(event, e) {
					//如果为空，直接返回
					if(!e)return;
					//执行修改
					doModifyCss(e, event);
				}
				function initMenu() {
					if(parent.topFrame.document.getElementById('clickTab').className == 'current')
						document.getElementById("a_0").click();
				}
				
				function showHideLeft(obj){
					var mainFrame = parent.document.getElementById('mainFrame');
					if (mainFrame.cols=="200,*"){
					   mainFrame.cols="12,*";
					   document.getElementById('leftMenu').style.display="none";
					   document.getElementById('headDiv').className="menuHead_hide";
					} else {
					   mainFrame.cols="200,*";
					   document.getElementById('leftMenu').style.display="block";
					   document.getElementById('headDiv').className="menuHead_show";
					}
				}
				
			</script>
			<style>
			* { padding:0; margin:0;}
			a, button {
			 outline: none; /*適用Firefox*/
			 hlbr:expression(this.onFocus=this.blur()); /*適用IE*/
			 }
			 html {_overflow-x:hidden;}
			 body { background:url(${ctx.contextPath}/images/ui20120918_49.jpg) right top repeat-y #F7F7F7; overflow-x:hidden}
			.menuHead { background:url(${ctx.contextPath}/images/ui20120918_29.jpg) no-repeat; width:200px; height:39px;}
			.menuHead_show { background:url(${ctx.contextPath}/images/ui20120918_29_1.jpg) no-repeat; width:200px; height:39px;}
			.menuHead_hide { background:url(${ctx.contextPath}/images/ui20120918_29_2.jpg) no-repeat; width:200px; height:39px;}
			.menu { width:200px;}
			.menu dt { width:200px;}
			.menu dt a { font-size:14px; text-decoration:none; display:block; line-height:34px; font-weight:bold; text-indent:45px; overflow:hidden;}
			.tit { background:url(${ctx.contextPath}/images/ui20120918_39.jpg) no-repeat; height:32px;font-size:13px; line-height:32px;vertical-align:middle;font-weight:bold;text-indent:20px; text-decoration:none; }
			.titCurrent { background:url(${ctx.contextPath}/images/ui20120918_45.jpg) no-repeat; height:32px; }
			.subCurrent { background:url(${ctx.contextPath}/images/ui20120918_49.jpg) right top repeat-y #F7F7F7; padding:10px 0 15px; }
			.tit a { color:#333;}
			.titCurrent a { color:#FFF;}
			.subCurrent a { display:block; font-size:12px; line-height:30px; background:url(${ctx.contextPath}/images/ui20120918_55.jpg) no-repeat; text-indent:46px; text-decoration:none; color:#333; text-decoration:none; font-weight:bold; height:30px;}
			.subCurrent a:hover { color:#C00;}
			.sub { display:none;}
			.subCurrent a.current { background:url(${ctx.contextPath}/images/ui20120918_55c.jpg) no-repeat #F7F7F7; color:#6E0120; }
		</style>
	</head>
	<body onload="initMenu()">
		<div id="headDiv" class="menuHead_show" onclick="showHideLeft(this)"></div>
		<#if m1??&&(m1.name)=='立案审批'>
			<dl id="leftMenu" class="menu">
				<dd class="subCurrent">
					<#list listTree as tree>
						<div class="tit">${(tree.name)!""}</div>
						<div class="content">
							<#list tree.children as m>
								<a id="a_${(m_index)}" href="${ctx.contextPath}${(m.defaultURL)!''}" target="rightFrame">${(m.name)!''}</a>
							</#list>
						</div>
					</#list>
				</dd>
			</dl>
			<script type="text/javascript">
				var contents = document.getElementsByClassName('content');
				var toggles = document.getElementsByClassName('tit');
			
				var myAccordion = new fx.Accordion(
					toggles, contents, {opacity: true, duration: 400}
				);
				myAccordion.showThisHideOpen(contents[0]);
			</script>
		<#else>
			<dl id="leftMenu" class="menu">
				<dd class="subCurrent" onclick="updateCss(event, this)">
					<#list moduleList as m>
						<a id="a_${m_index}" href="${ctx.contextPath}${m.defaultURL}" target="rightFrame">${m.name}</a>
					</#list>
				</dd>
			</dl>
		</#if>	
	</body>
</html>