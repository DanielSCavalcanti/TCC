<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Biblioteca</title>
<link rel="icon" href="imagens/livro.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar livro</h1>
	<form name="frmNovolivro" action="update">
		<table>
			<tr>
				<td><input type="text" name="idlivro" id="caixa3" readonly
					value="<%out.print(request.getAttribute("idlivro"));%>"></td>
			</tr>
			
			<tr>
				<td><input type="text" name="nome" class="Caixa1" value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>
			
			<tr>
				<td><input type="text" name="autor" class="Caixa1" value="<%out.print(request.getAttribute("autor"));%>"></td>
			</tr>
			
			<tr>
				<td><input type="text" name="editora" class="Caixa1" value="<%out.print(request.getAttribute("editora"));%>"></td>
			</tr>
			
			<tr>
				<td><input type="text" name="anodepublicacao" class="Caixa2" value="<%out.print(request.getAttribute("anodepublicacao"));%>"></td>
			</tr>
			
			<tr>
				<td><input type="text" name="numerodepaginas" class="Caixa2" value="<%out.print(request.getAttribute("numerodepaginas"));%>"></td>
			</tr>	
			
		</table>	
		<input type="button" value="Salvar" class="Botao1" onclick="validar()">
	</form>
<script src="scripts/validador.js"></script>
</body>
</html>