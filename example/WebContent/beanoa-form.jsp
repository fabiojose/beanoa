<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Beanoa - Form</title>
</head>
<body>
	<form name = "customer" id = "customer" action="beanoa.form" method="post">
		<label for = "customer.name">
			Name:
		</label>
		<input type = "text" id = "name" name = "customer.name"/>
		
		<label for = "customer.lastName">
			Last name:
		</label>
		<input type = "text" id = "lastName" name = "customer.lastName"/>
		
		<label for = "customer.age">
			Age:
		</label>
		<input type = "text" id = "customer.age" name = "customer.age" />
		
		<fieldset>
			<legend>Telephones:</legend>
			<div>
				Type:<br/>
				<select id = "customer.telephone[0].type" name = "customer.telephone[0].type">
					<option value="" label=""/>
				</select>
				<br/>
				
				Number:<br/>
				<input type="text" id = "customer.telephone[0].number" name = "customer.telephone[0].number"/>
			</div>
		</fieldset>
		
		<fieldset>
			<legend>Home address:</legend>
			
			<label for = "customer.home.country">Country:</label>
			<input type = "text" id = "customer.home.country" name = "customer.home.country"/>
			
			<label for = "customer.home.state">State:</label>
			<input type = "text" id = "customer.home.state" name = "customer.home.state"/>
			
			<label for = "customer.home.line1">Line1:</label>
			<input type = "text" id = "customer.home.line1" name = "customer.home.line1"/>
			
			<label for = "customer.home.line2">Line2:</label>
			<input type = "text" id = "customer.home.line2" name = "customer.home.line2"/>
		</fieldset>
		
		<input type = "submit" />
	</form>
</body>
</html>