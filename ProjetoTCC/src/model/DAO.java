package model;

// Classes e interfaces do pacote java.sql - JDBC
import java.sql.Connection; //Interface de conexão ao banco de dados  
import java.sql.DriverManager; //Oferece métodos estáticos para gerenciar um driver JDBC
import java.sql.PreparedStatement; //Usado para criar um objeto que representa a instrução SQL
import java.sql.ResultSet; //Representa o conjunto de resultados de uma tabela no banco de dados
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	                      
                      	/**  Módulo de conexão *. */
	
	// 1ª Parte: Parâmetros de conexão //
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/**  Dentro de url: ip do servidor, nome do bd e fuso horário de referência 	 universal *. */
	private String url = "jdbc:mysql://127.0.0.1:3306/projeto?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "13111996";
	
	// 2ª Parte: Metódo de conexão //
	/**
	 *  A classe Connection faz parte do JDBC *.
	 *
	 * @return the connection
	 */
	private Connection conectar() {
		/** Usando a classe Connection, criamos o objeto con atribuindo um valor nulo */
		Connection con = null;
		/** Para se conectar ao banco de dados, é necessário usar o try catch. O try catch
		 * faz tratamento de excessões no Java.
		 * try = na tentativa de conectar com o banco de dados / catch = caso aconteça
		 * uma exceção */
		try {
			// vai ler o driver do banco de dados, buscando a variável driver
			Class.forName(driver);
			// con : estabele uma conexão com o banco de dados / DriverManager: vai
			// gerenciar o driver / .getConnection: obtém os parâmetros de conexão
			con = DriverManager.getConnection(url, user, password);
			// Estabeleça a conexão:
			return con;

			// Caso ocorra uma exceção:
		} catch (Exception e) {
			// Mostrar qual é a exceção:
			System.out.println(e);
			return null;
		}
	}
	

	/**
	 * Inserir livro.
	 *
	 * @param livro the livro
	 */
	/* INSERIR LIVRO */
	public void inserirLivro(JavaBeans livro) {
		String create = "insert into livros (nome, autor, editora, anodepublicacao, numerodepaginas) values (?,?,?,?,?)";
		try {
			// Abrindo a conexão com o banco MySql:
			Connection con = conectar();
			// Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// Substituindo os parâmetros (?), com setString/setInt, pelo conteúdo das
			// variáveis JavaBeans, obtendo através do comando getNome, getAutor...
			pst.setString(1, livro.getNome());
			pst.setString(2, livro.getAutor());
			pst.setString(3, livro.getEditora());
			pst.setString(4, livro.getAnodepublicacao());
			pst.setString(5, livro.getNumerodepaginas());
			// Executando a query
			pst.executeUpdate();
			// Encerrando a conexão com o banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 *  LISTAR LIVROS *.
	 *
	 * @return the array list
	 */
	// ArrayList é usado para criar um vetor dinâmico
	public ArrayList<JavaBeans> listarLivros() {
		// Criando um objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> livros = new ArrayList<>();
		String read = "select * from livros order by nome";
		try {
			// Abrindo conexão com o BD
			Connection con = conectar();
			// Preparando a query para o Java executar este comando no servidor de BD MySql.
			// Vai preparar a execução da variável read, que contém o comando: "read * from
			// livros order by nome"
			PreparedStatement pst = con.prepareStatement(read);
			// ResultSet é usado para armazenar o retorno do banco de dados temporariamente
			// em um objeto. O objeto rs executa a query, ou seja, executa "read * from
			// livros order by nome".
			ResultSet rs = pst.executeQuery();
			// O laço abaixo será executado enquanto houver livros. rs é usado para
			// recuperar todos os dados do Banco de Dados. O método next está dentro da
			// classe ResultSet e é usado para recuperar todos os livros do BD.
			while (rs.next()) {
				// Variáveis de apoio (idlivro, nome...) recebem o campo 1,2,3... do banco de
				// dados
				String idlivro = rs.getString(1);
				String nome = rs.getString(2);
				String autor = rs.getString(3);
				String editora = rs.getString(4);
				String anodepublicacao = rs.getString(5);
				String numerodepaginas = rs.getString(6);
				// Armazenando no ArrayList. O objeto livros adiciona nas variáveis da classe
				// JavaBeans, o conteúdos destas variáveis de apoio: idlivro, nome, editora,
				// anodepublicacao, numerodepaginas
				livros.add(new JavaBeans(idlivro, nome, autor, editora, anodepublicacao, numerodepaginas));
			}
			con.close();
			return livros;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 *  SELECIONAR LIVRO *.
	 *
	 * @param livro the livro
	 */
	//Método que vai selecionar o livro
	public void selecionarLivro(JavaBeans livro) {
		String read2 = "select * from livros where idlivro = ?";
		try {
			//Conectando com o banco de dados
			Connection con = conectar();
			//Preparando a query para ser executada no banco de dados
			PreparedStatement pst = con.prepareStatement(read2);
			//Substituindo a ? pelo idlivro do livro desejado
			pst.setString(1, livro.getIdlivro());
			//Trazendo as informações do livro do banco
			ResultSet rs = pst.executeQuery();
			//Enviando às variáveis da classe JavaBeans
			while(rs.next()) {
				//Setando as variáveis JavaBeans
				livro.setIdlivro(rs.getString(1));
				livro.setNome(rs.getString(2));
				livro.setAutor(rs.getString(3));
				livro.setEditora(rs.getString(4));
				livro.setAnodepublicacao(rs.getString(5));
				livro.setNumerodepaginas(rs.getString(6));
			}
			//Encerrando a conexão com o banco de dados
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}		
	}
	
	/**
	 *  Alterar livro *.
	 *
	 * @param livro the livro
	 */
	//Editar o livro (método alterarLivro recebe os valores da classe JavaBeans através do objeto livro)
	public void alterarLivro(JavaBeans livro) {
		String update = "update livros set nome=?,autor=?,editora=?,anodepublicacao=?,numerodepaginas=? where idlivro=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			//getnome, ...: Método requisita os dados do livro da classe JavaBeans. pst.setString: os dados do livro que estão na classe JavaBeans são encaminhados ao método responsável pelo update no banco de dados.
			pst.setString(1, livro.getNome());
			pst.setString(2, livro.getAutor());
			pst.setString(3, livro.getEditora());
			pst.setString(4, livro.getAnodepublicacao());
			pst.setString(5, livro.getNumerodepaginas());
			pst.setString(6, livro.getIdlivro());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 *  DELETAR LIVRO *.
	 *
	 * @param livro the livro
	 */
	//Recebendo o objeto livro. Obs: as variáveis são encapsuladas e só conseguimos acessas essas variáveis através desse objeto livro.
	public void deletarLivro(JavaBeans livro) {
		String delete = "delete from livros where idlivro=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			//O objeto livro consegue acessar os métodos públicos da classe JavaBeans e obtém o conteúdo da variável idlivro
			pst.setString(1, livro.getIdlivro());
			//Executando a query no banco de dados:
			pst.executeUpdate();
			//Finalizando a conexão com o banco de dados:
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	

}
