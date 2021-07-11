/**
 * Confirmador de exclusão de um livro
 */

function confirmar(idlivro){
	let resposta = confirm("Confirma a exclusão deste livro?")
	if (resposta === true){
		window.location.href = "delete?idlivro=" + idlivro
	}
}
