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
<script>
	var webSocket = null;
	if ('WebSocket' in window) {
		webSocket = new WebSocket('ws://localhost:8080/websocket');
		console.log("cunzai");
	} else {
		alert("浏览器不支持websocket");
	}
	
	webSocket.onopen = function (e) {
		console.log("建立连接/n");
		
		// 前端发送消息 在合适的时机触发
		webSocket.send("这是前端发送的消息");
	};
	
	webSocket.onclose = function (e) {
		console.log("连接关闭/n");
	};
	
	webSocket.onmessage = function (e) {
		console.log("收到消息："  + e.data);
	};
	
	

</script>