package model;

// Classes e interfaces do pacote java.sql - JDBC
import java.sql.Connection; //Interface de conex�o ao banco de dados  
import java.sql.DriverManager; //Oferece m�todos est�ticos para gerenciar um driver JDBC
import java.sql.PreparedStatement; //Usado para criar um objeto que representa a instru��o SQL
import java.sql.ResultSet; //Representa o conjunto de resultados de uma tabela no banco de dados
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	                      
                      	/**  M�dulo de conex�o *. */
	
	// 1� Parte: Par�metros de conex�o //
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/**  Dentro de url: ip do servidor, nome do bd e fuso hor�rio de refer�ncia 	 universal *. */
	private String url = "jdbc:mysql://127.0.0.1:3306/projeto?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "13111996";
	
	// 2� Parte: Met�do de conex�o //
	/**
	 *  A classe Connection faz parte do JDBC *.
	 *
	 * @return the connection
	 */
	private Connection conectar() {
		/** Usando a classe Connection, criamos o objeto con atribuindo um valor nulo */
		Connection con = null;
		/** Para se conectar ao banco de dados, � necess�rio usar o try catch. O try catch
		 * faz tratamento de excess�es no Java.
		 * try = na tentativa de conectar com o banco de dados / catch = caso aconte�a
		 * uma exce��o */
		try {
			// vai ler o driver do banco de dados, buscando a vari�vel driver
			Class.forName(driver);
			// con : estabele uma conex�o com o banco de dados / DriverManager: vai
			// gerenciar o driver / .getConnection: obt�m os par�metros de conex�o
			con = DriverManager.getConnection(url, user, password);
			// Estabele�a a conex�o:
			return con;

			// Caso ocorra uma exce��o:
		} catch (Exception e) {
			// Mostrar qual � a exce��o:
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
			// Abrindo a conex�o com o banco MySql:
			Connection con = conectar();
			// Preparar a query para execu��o no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// Substituindo os par�metros (?), com setString/setInt, pelo conte�do das
			// vari�veis JavaBeans, obtendo atrav�s do comando getNome, getAutor...
			pst.setString(1, livro.getNome());
			pst.setString(2, livro.getAutor());
			pst.setString(3, livro.getEditora());
			pst.setString(4, livro.getAnodepublicacao());
			pst.setString(5, livro.getNumerodepaginas());
			// Executando a query
			pst.executeUpdate();
			// Encerrando a conex�o com o banco
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
	// ArrayList � usado para criar um vetor din�mico
	public ArrayList<JavaBeans> listarLivros() {
		// Criando um objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> livros = new ArrayList<>();
		String read = "select * from livros order by nome";
		try {
			// Abrindo conex�o com o BD
			Connection con = conectar();
			// Preparando a query para o Java executar este comando no servidor de BD MySql.
			// Vai preparar a execu��o da vari�vel read, que cont�m o comando: "read * from
			// livros order by nome"
			PreparedStatement pst = con.prepareStatement(read);
			// ResultSet � usado para armazenar o retorno do banco de dados temporariamente
			// em um objeto. O objeto rs executa a query, ou seja, executa "read * from
			// livros order by nome".
			ResultSet rs = pst.executeQuery();
			// O la�o abaixo ser� executado enquanto houver livros. rs � usado para
			// recuperar todos os dados do Banco de Dados. O m�todo next est� dentro da
			// classe ResultSet e � usado para recuperar todos os livros do BD.
			while (rs.next()) {
				// Vari�veis de apoio (idlivro, nome...) recebem o campo 1,2,3... do banco de
				// dados
				String idlivro = rs.getString(1);
				String nome = rs.getString(2);
				String autor = rs.getString(3);
				String editora = rs.getString(4);
				String anodepublicacao = rs.getString(5);
				String numerodepaginas = rs.getString(6);
				// Armazenando no ArrayList. O objeto livros adiciona nas vari�veis da classe
				// JavaBeans, o conte�dos destas vari�veis de apoio: idlivro, nome, editora,
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
	//M�todo que vai selecionar o livro
	public void selecionarLivro(JavaBeans livro) {
		String read2 = "select * from livros where idlivro = ?";
		try {
			//Conectando com o banco de dados
			Connection con = conectar();
			//Preparando a query para ser executada no banco de dados
			PreparedStatement pst = con.prepareStatement(read2);
			//Substituindo a ? pelo idlivro do livro desejado
			pst.setString(1, livro.getIdlivro());
			//Trazendo as informa��es do livro do banco
			ResultSet rs = pst.executeQuery();
			//Enviando �s vari�veis da classe JavaBeans
			while(rs.next()) {
				//Setando as vari�veis JavaBeans
				livro.setIdlivro(rs.getString(1));
				livro.setNome(rs.getString(2));
				livro.setAutor(rs.getString(3));
				livro.setEditora(rs.getString(4));
				livro.setAnodepublicacao(rs.getString(5));
				livro.setNumerodepaginas(rs.getString(6));
			}
			//Encerrando a conex�o com o banco de dados
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
	//Editar o livro (m�todo alterarLivro recebe os valores da classe JavaBeans atrav�s do objeto livro)
	public void alterarLivro(JavaBeans livro) {
		String update = "update livros set nome=?,autor=?,editora=?,anodepublicacao=?,numerodepaginas=? where idlivro=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			//getnome, ...: M�todo requisita os dados do livro da classe JavaBeans. pst.setString: os dados do livro que est�o na classe JavaBeans s�o encaminhados ao m�todo respons�vel pelo update no banco de dados.
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
	//Recebendo o objeto livro. Obs: as vari�veis s�o encapsuladas e s� conseguimos acessas essas vari�veis atrav�s desse objeto livro.
	public void deletarLivro(JavaBeans livro) {
		String delete = "delete from livros where idlivro=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			//O objeto livro consegue acessar os m�todos p�blicos da classe JavaBeans e obt�m o conte�do da vari�vel idlivro
			pst.setString(1, livro.getIdlivro());
			//Executando a query no banco de dados:
			pst.executeUpdate();
			//Finalizando a conex�o com o banco de dados:
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	

}
