/**
 * Validação de formulário
 */

function validar(){
	//A variável chamada nome vai receber do formulario "frmNovoLivro" do campo nome o valor.
	let nome = frmNovolivro.nome.value
	let autor = frmNovolivro.autor.value
	let editora = frmNovolivro.editora.value
	let numerodepaginas = frmNovolivro.numerodepaginas.value
	if (nome === ""){
		alert("Preencha o campo Nome")
		frmNovolivro.nome.focus()
		return false
	} else if(autor === ""){
		alert("Preencha o campo Autor")
		frmNovolivro.autor.focus()
		return false
	} else if(editora === ""){
		alert("Preencha o campo Editora")
		frmNovolivro.editora.focus()
		return false
	} else if(numerodepaginas === ""){
		alert("Preencha o campo Número de Páginas")
		frmNovolivro.numerodepaginas.focus()
		return false
	} else{
		//Se todos os campos estiverem preenchidos, os dados do formulário novo livro serão submetidos à camada controller
		document.forms["frmNovolivro"].submit()
	}
	
}