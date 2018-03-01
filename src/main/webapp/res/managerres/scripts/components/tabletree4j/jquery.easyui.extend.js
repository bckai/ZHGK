/**
 * jquery EasyUi的扩展方法
 *
 * @author ibm
 */

(function($) {
	$.fn.extend({
		//==查询条件用的Panel,关键解决Collapse问题
		findPanel:function(){
			$(this).panel({
				onBeforeCollapse : function(){
					this.panelHeight = $(this).parent().height();
					this.bodyHeight = $(this).height();
				},
				onCollapse : function(){
					$(this).parent().parent().css("height",this.panelHeight-this.bodyHeight);//设置容器高度
					$('div[region="center"]').panel('resize', {top:this.panelHeight-this.bodyHeight+10,height:$('div[region="center"]').parent().height()+this.bodyHeight});
				},
				onBeforeExpand: function(){
					$(this).parent().parent().css("height",this.panelHeight);//恢复容器高度
					$('div[region="center"]').panel('resize', {top:this.panelHeight+10,height:$('div[region="center"]').parent().height()-this.bodyHeight});
				}
			})
		}
	});	
	
})(jQuery);


