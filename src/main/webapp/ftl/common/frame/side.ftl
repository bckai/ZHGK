<html>
<head>
		<script type="text/javascript">
			function showHideLeft(objtd){
			//alert(parent.document.getElementById('mainFrame'));
			//alert(top.document.getElementsByTagName("frameset")[0].cols);
			t = parent.document.getElementById('mainFrame');
			if (t.cols=="200,30,*"){
			   t.cols="0,30,*";
			   objtd.innerHTML = '<B>&gt;&gt;</B>';
			}
			else{
			   t.cols="200,30,*";
			   objtd.innerHTML = '<B>&lt;&lt;</B>';
			}
			}
		</script>
		<style>
			body {
				<!-- background:url() repeat; -->
			}
		</style>
	</head>
	<body>
		<table width="100%" height="100%" cellpadding="0" cellspacing="0" border="0">
			<tr align="left">
				<td title="显示隐藏左菜单" onClick="showHideLeft(this);"><B>&lt;&lt;</B></td>
			</tr>
		</table>
	</body>
</html>