$(function(){
	//支持debug
	var shopApi=window.shopApi||{
		openHtml:function(link,title){
			alert("标题："+title+"\r\n链接："+link);
			window.open (link);
		},
		openProItem:function(itemId){
			alert('商品ID：'+itemId);
		},
		openProType:function(typeId){
			//alert('商品分类ID：'+typeId);
            window.open ("");
		},
		openBaojia:function(type){
			alert('报价分类'+type);
		}
	};

	//处理网页链接
	$('.shopPageLink').click(function(){
		var link = this.href;
		var title = this.title;
		shopApi.openHtml(link,title);
		return false;
	});

	//处理商品链接
	$('.shopPageProItem').click(function(){
		var itemId = $(this).attr('shopkey');
		shopApi.openProItem(itemId);
		return false;
	});

	//处理商品分类链接
	$('.shopPageProType').click(function(){
		var typeId = $(this).attr('shopkey');
		shopApi.openProType(typeId);
		return false;
	});

	//处理报价链接
	$('.shopPageBaojia').click(function(){
		var type = $(this).attr('shopkey');
		shopApi.openBaojia(type||'');
		return false;
	});
});