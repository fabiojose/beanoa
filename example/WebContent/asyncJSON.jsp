

<html>
	<head>
		<script src="json.js"></script>
		<script>
			var _customer;
			function getJSON(){
				alert('start get JSON');
				
				var xmlHTTP;
				if(window.XMLHttpRequest){
					xmlHTTP = new XMLHttpRequest();
				} else {
					xmlHTTP = new ActiveXObject('Microsoft.XMLHTTP');
				}
				
				xmlHTTP.onreadystatechange = function(){
			
					if(xmlHTTP.readyState == 4 && xmlHTTP.status == 200){
						alert(xmlHTTP.responseText);

						_customer = eval('(' + xmlHTTP.responseText + ')');
						alert(_customer);
						
						document.getElementById('setbt').disabled = false;
						document.getElementById('name').value = _customer.customer.name;
						document.getElementById('lastName').value = _customer.customer.lastName;
						document.getElementById('age').value = _customer.customer.age;
					}
				}
				
				xmlHTTP.open('POST', '/beanoa/asyncJSON', true);
				xmlHTTP.send('');
			}
			
			function setJSON(){
				alert('start set JSON');
				
				var xmlHTTP;
				if(window.XMLHttpRequest){
					xmlHTTP = new XMLHttpRequest();
				} else {
					xmlHTTP = new ActiveXObject('Microsoft.XMLHTTP');
				}
				
				xmlHTTP.onreadystatechange = function(){
			
					if(xmlHTTP.readyState == 4 && xmlHTTP.status == 200){
						
						alert(xmlHTTP.responseText);
					}
				}
				
				xmlHTTP.open('POST', '/beanoa/receiveJSON', true);
				_customer.customer.name = document.getElementById('name').value;
				_customer.customer.lastName = document.getElementById('lastName').value;
				_customer.customer.age = document.getElementById('age').value;
				
				alert(_customer.toJSONString());
				xmlHTTP.send(_customer.toJSONString());
			}
		</script>
		
	</head>
	<body>
		<form name="json-form">
			
			<input type="button" onclick="getJSON()" value="get JSON" />
			<input type="button" onclick="setJSON()" value="set JSON" id="setbt" disabled="true"/>
			
			<br/>
			<input type="text" id="name"/>
			<br/>
			<input type="text" id="lastName"/>
			<br/>
			<input type="text" id="age"/>
		</form>
	</body>
</html>