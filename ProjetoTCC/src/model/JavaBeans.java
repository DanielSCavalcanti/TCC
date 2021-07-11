package model;

// TODO: Auto-generated Javadoc
/**
 * The Class JavaBeans.
 */
public class JavaBeans {
	
	/** The idlivro. */
	private String idlivro;
	
	/** The nome. */
	private String nome;
	
	/** The autor. */
	private String autor;
	
	/** The editora. */
	private String editora;
	
	/** The anodepublicacao. */
	private String anodepublicacao;
	
	/** The numerodepaginas. */
	private String numerodepaginas;
	
	
	
	/**
	 * Instantiates a new java beans.
	 */
	public JavaBeans() {
		super(); 
	}
	
	/**
	 * Instantiates a new java beans.
	 *
	 * @param idlivro the idlivro
	 * @param nome the nome
	 * @param autor the autor
	 * @param editora the editora
	 * @param anodepublicacao the anodepublicacao
	 * @param numerodepaginas the numerodepaginas
	 */
	public JavaBeans(String idlivro, String nome, String autor, String editora, String anodepublicacao,
			String numerodepaginas) {
		super();
		this.idlivro = idlivro;
		this.nome = nome;
		this.autor = autor;
		this.editora = editora;
		this.anodepublicacao = anodepublicacao;
		this.numerodepaginas = numerodepaginas;
	}


	/**
	 * Gets the idlivro.
	 *
	 * @return the idlivro
	 */
	public String getIdlivro() {
		return idlivro;
	}
	
	/**
	 * Sets the idlivro.
	 *
	 * @param idlivro the new idlivro
	 */
	public void setIdlivro(String idlivro) {
		this.idlivro = idlivro;
	}
	
	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Gets the autor.
	 *
	 * @return the autor
	 */
	public String getAutor() {
		return autor;
	}
	
	/**
	 * Sets the autor.
	 *
	 * @param autor the new autor
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	/**
	 * Gets the editora.
	 *
	 * @return the editora
	 */
	public String getEditora() {
		return editora;
	}
	
	/**
	 * Sets the editora.
	 *
	 * @param editora the new editora
	 */
	public void setEditora(String editora) {
		this.editora = editora;
	}
	
	/**
	 * Gets the anodepublicacao.
	 *
	 * @return the anodepublicacao
	 */
	public String getAnodepublicacao() {
		return anodepublicacao;
	}
	
	/**
	 * Sets the anodepublicacao.
	 *
	 * @param anodepublicacao the new anodepublicacao
	 */
	public void setAnodepublicacao(String anodepublicacao) {
		this.anodepublicacao = anodepublicacao;
	}
	
	/**
	 * Gets the numerodepaginas.
	 *
	 * @return the numerodepaginas
	 */
	public String getNumerodepaginas() {
		return numerodepaginas;
	}
	
	/**
	 * Sets the numerodepaginas.
	 *
	 * @param numerodepaginas the new numerodepaginas
	 */
	public void setNumerodepaginas(String numerodepaginas) {
		this.numerodepaginas = numerodepaginas;
	}
	
	
}
