package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
// classe Controller � a implementa��o do Servlet 

/**
 * The Class Controller.
 */
//Requisi��es que o servlet vai trabalhar. As requisi��es s�o recebidas atrav�s de urls configuradas aqui abaixo. Essas urls s�o caminhos que definimos em formul�rios, links, bot�es.
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete" })


public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The dao. */
	// Criando novos objetos
	DAO dao = new DAO();
	
	/** The livro. */
	// Este objeto livro, consegue acessar os m�todos p�blicos da classe JavaBeans
	JavaBeans livro = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Redirecionar as requisi��es que foram configuragas no @WebServlet para m�todos espec�ficos, que encaminham para a camada model.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// A vari�vel action vai armazenar o caminho da requisi��o
		String action = request.getServletPath();
		System.out.println(action);
	    // Recebendo a requisi��o pelo objeto action e direcionando para o m�todo definido:
		if (action.equals("/main")) {
			// Invoca o m�todo livros
			livros(request, response);
			// Se o conte�do da vari�vel action for insert, ent�o ser� redirecionado ao
			// m�todo novoLivro
		} else if (action.equals("/insert")) {
			novoLivro(request, response);
		} else if (action.equals("/select")) {
			listarLivro(request, response);
		} else if (action.equals("/update")) {
			editarLivro(request, response);
		}else if (action.equals("/delete")) {
			removerLivro(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	/**
	 * Livros.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Listar livros
	protected void livros(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto de nome lista que ir� receber os dados JavaBeans dentro de
		// um vetor din�mico, executando o m�todo listarLivros(). Ir� exibir todos os
		// dados armazenados no Banco de Dados.
		ArrayList<JavaBeans> lista = dao.listarLivros();
		// Encaminhar a lista ao documento biblioteca.jsp
		request.setAttribute("livros", lista);
		RequestDispatcher rd = request.getRequestDispatcher("biblioteca.jsp");
		rd.forward(request, response);
	}

	/**
	 * Novo livro.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Novo Livro
	protected void novoLivro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Setando as vari�veis JavaBeans:
		livro.setNome(request.getParameter("nome"));
		livro.setAutor(request.getParameter("autor"));
		livro.setEditora(request.getParameter("editora"));
		livro.setAnodepublicacao(request.getParameter("anodepublicacao"));
		livro.setNumerodepaginas(request.getParameter("numerodepaginas"));
		// Invocando o m�todo inserirLivro da classe DAO passando o objeto livro
		dao.inserirLivro(livro);
		// Redirecionando para o documento biblioteca.jsp
		response.sendRedirect("main");
	}
	
	/**
	 * Listar livro.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//Listar livro
	protected void listarLivro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idlivro = request.getParameter("idlivro");
		System.out.println(idlivro);
		livro.setIdlivro(idlivro);
		//Executando o m�todo selecionarLivro (DAO)
		dao.selecionarLivro(livro);
		//Teste de recebimento
			System.out.println(livro.getIdlivro());
			System.out.println(livro.getNome());
			System.out.println(livro.getAutor());
			System.out.println(livro.getEditora());
			System.out.println(livro.getAnodepublicacao());
			System.out.println(livro.getNumerodepaginas());
		//Setando os atributos do formul�rio do documento editar.jsp com o conte�do JavaBeans
		request.setAttribute("idlivro", livro.getIdlivro());
		request.setAttribute("nome", livro.getNome());
		request.setAttribute("autor", livro.getAutor());
		request.setAttribute("editora", livro.getEditora());
		request.setAttribute("anodepublicacao", livro.getAnodepublicacao());
		request.setAttribute("numerodepaginas", livro.getNumerodepaginas());
		//Enviando para o documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}
	
	
	/**
	 * Editar livro.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//Editar livro
	protected void editarLivro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Setando as vari�veis JavaBeans. O objeto livro consegue acessar os m�todos que v�o armazenar esses valores nas vari�veis JavaBeans. 1�getParameter: manda os dados do formul�rio � camada controller. 2� setidlivro... encaminha os dados do formul�rio a classe JavaBeans.
		livro.setIdlivro(request.getParameter("idlivro"));
		livro.setNome(request.getParameter("nome"));
		livro.setAutor(request.getParameter("autor"));
		livro.setEditora(request.getParameter("editora"));
		livro.setAnodepublicacao(request.getParameter("anodepublicacao"));
		livro.setNumerodepaginas (request.getParameter("numerodepaginas"));
		//Teste de recebimento
		System.out.println(livro.getIdlivro());
		System.out.println(livro.getNome());
		System.out.println(livro.getAutor());
		System.out.println(livro.getEditora());
		System.out.println(livro.getAnodepublicacao());
		System.out.println(livro.getNumerodepaginas());
		//Executando o m�todo alterarLivro da classe dao
		dao.alterarLivro(livro);
		//Redirecionando para o documento biblioteca.jsp, atualizando as altera��es:
		response.sendRedirect("main");
	}
	
	/**
	 * Remover livro.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//REMOVER UM LIVRO
	protected void removerLivro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Setando a vari�vel idlivro JavaBeans
		livro.setIdlivro(request.getParameter("idlivro"));
		//Executando o m�todo deletarLivro, da classe DAO, passando o objeto livro
		dao.deletarLivro(livro);
		//Redirecionando para o documento biblioteca.jsp, atualizando as altera��es:
		response.sendRedirect("main");
	}
}
