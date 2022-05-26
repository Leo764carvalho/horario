package src.br.com.rsa.horarios.coloracao;

import java.io.Serializable;

/**
 * Uma cor a ser usada na coloração.
 * 
 * @author felipe
 *
 */
public abstract class Cor implements Serializable{
	private String nome;

	public String getNome() {
		return nome;
	}

	public Cor(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}

	/**
	 * Verifica se duas cores entram em choque, isto é, possuem o mesmo código e
	 * índices consecutivos
	 * 
	 * @param c
	 *            a cor
	 * @return true se as cores tem o mesmo código e false caso contrario
	 */
	public abstract boolean choque(Cor c);

	/**
	 * Ao colorir um nó com a cor atual, as cores indicadas por esta função se
	 * tornam desejaveis para os nós idênticos ao atual.
	 * 
	 * @param c
	 *            a cor
	 * @return true se as cores tem o mesmo código e false caso contrario
	 */
	public abstract boolean desejavelAoColorir(Cor cor);
}
