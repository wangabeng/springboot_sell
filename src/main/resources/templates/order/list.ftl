<h2>所有订单</h2>
<#list orderDTOpage.getContent() as orderDTO>
	orderId: ${orderDTO.orderId}<br>
	getPayStatusEnum: ${orderDTO.getPayStatusEnum().getMessage()}<br>
</#list>

<br>
<p>分割线</p>
<ul>
	<li><a href="#">上一页</a></li>
	<#list 1..orderDTOpage.getTotalPages() as index>
		<li><a href="#">
			${index}
		</a></li>
	</#list>
	<li><a href="#">下一页</a></li>
</ul>
