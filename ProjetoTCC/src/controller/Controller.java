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
// classe Controller é a implementação do Servlet 

/**
 * The Class Controller.
 */
//Requisições que o servlet vai trabalhar. As requisições são recebidas através de urls configuradas aqui abaixo. Essas urls são caminhos que definimos em formulários, links, botões.
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete" })


public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The dao. */
	// Criando novos objetos
	DAO dao = new DAO();
	
	/** The livro. */
	// Este objeto livro, consegue acessar os métodos públicos da classe JavaBeans
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
	// Redirecionar as requisições que foram configuragas no @WebServlet para métodos específicos, que encaminham para a camada model.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// A variável action vai armazenar o caminho da requisição
		String action = request.getServletPath();
		System.out.println(action);
	    // Recebendo a requisição pelo objeto action e direcionando para o método definido:
		if (action.equals("/main")) {
			// Invoca o método livros
			livros(request, response);
			// Se o conteúdo da variável action for insert, então será redirecionado ao
			// método novoLivro
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
		// Criando um objeto de nome lista que irá receber os dados JavaBeans dentro de
		// um vetor dinâmico, executando o método listarLivros(). Irá exibir todos os
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

		// Setando as variáveis JavaBeans:
		livro.setNome(request.getParameter("nome"));
		livro.setAutor(request.getParameter("autor"));
		livro.setEditora(request.getParameter("editora"));
		livro.setAnodepublicacao(request.getParameter("anodepublicacao"));
		livro.setNumerodepaginas(request.getParameter("numerodepaginas"));
		// Invocando o método inserirLivro da classe DAO passando o objeto livro
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
		//Executando o método selecionarLivro (DAO)
		dao.selecionarLivro(livro);
		//Teste de recebimento
			System.out.println(livro.getIdlivro());
			System.out.println(livro.getNome());
			System.out.println(livro.getAutor());
			System.out.println(livro.getEditora());
			System.out.println(livro.getAnodepublicacao());
			System.out.println(livro.getNumerodepaginas());
		//Setando os atributos do formulário do documento editar.jsp com o conteúdo JavaBeans
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
		//Setando as variáveis JavaBeans. O objeto livro consegue acessar os métodos que vão armazenar esses valores nas variáveis JavaBeans. 1ºgetParameter: manda os dados do formulário à camada controller. 2º setidlivro... encaminha os dados do formulário a classe JavaBeans.
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
		//Executando o método alterarLivro da classe dao
		dao.alterarLivro(livro);
		//Redirecionando para o documento biblioteca.jsp, atualizando as alterações:
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
		//Setando a variável idlivro JavaBeans
		livro.setIdlivro(request.getParameter("idlivro"));
		//Executando o método deletarLivro, da classe DAO, passando o objeto livro
		dao.deletarLivro(livro);
		//Redirecionando para o documento biblioteca.jsp, atualizando as alterações:
		response.sendRedirect("main");
	}
}
