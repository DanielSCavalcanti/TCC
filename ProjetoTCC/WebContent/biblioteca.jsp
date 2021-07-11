<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans" %>
<%@ page import="java.util.ArrayList" %>
<%
	@SuppressWarnings ("unchecked")
	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("livros");		
	

%>


<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Biblioteca Pessoal</title>
<link rel="icon" href="imagens/livro.png">
<link rel="stylesheet" href="style.css">
</head>

<body>
	<h1>Biblioteca Pessoal de Livros</h1>
	<a href="novolivro.html" class="Botao1">Novo livro</a>
	<table id="tabelabiblioteca">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Autor</th>
				<th>Editora</th>
				<th>Ano de publicação</th>
				<th>Número de páginas</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<% for(int i=0; i<lista.size(); i++){ %>
				<tr>
					<td><%=lista.get(i).getIdlivro() %></td>
					<td><%=lista.get(i).getNome() %></td>
					<td><%=lista.get(i).getAutor() %></td>
					<td><%=lista.get(i).getEditora() %></td>
					<td><%=lista.get(i).getAnodepublicacao() %></td>
					<td><%=lista.get(i).getNumerodepaginas() %></td>
					<td>
						<a href="select?idlivro=<%=lista.get(i).getIdlivro() %>" class="Botao1">Editar</a>
						<a href="javascript: confirmar(<%=lista.get(i).getIdlivro() %>)" class="Botaoexcluir">Excluir</a>
					</td>
				</tr>
			
			<% } %>
		
		</tbody>
	</table>
	<script src="scripts/confirmador.js"></script>
</body>
</html>