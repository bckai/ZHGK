
<div class='JT_from'>
	<form id='dicForm' action='${ctx.contextPath}/rest/dictionary' method='POST'>
		<input type='hidden' name='id' value='${dictionary.code!''}'/>
		<input type='hidden' name='code' value='${dictionary.code!''}'  />
		<input type='hidden' name='dicType'  value='${dictionary.dicType!''}'/>
		<img src='${ctx.contextPath}/webUI/assets/top_input_icon_search.png'/><input type='text' value='${dictionary.value}' name='value' id='value' />
		<input name='sumbit' type='button' value='修改' onClick='editParams()'>
	</form>
</div>
