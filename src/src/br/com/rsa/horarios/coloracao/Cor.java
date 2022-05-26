package src.br.com.rsa.horarios.coloracao;

import java.io.Serializable;

/**
 * Uma cor a ser usada na colora��o.
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
	 * Verifica se duas cores entram em choque, isto �, possuem o mesmo c�digo e
	 * �ndices consecutivos
	 * 
	 * @param c
	 *            a cor
	 * @return true se as cores tem o mesmo c�digo e false caso contrario
	 */
	public abstract boolean choque(Cor c);

	/**
	 * Ao colorir um n� com a cor atual, as cores indicadas por esta fun��o se
	 * tornam desejaveis para os n�s id�nticos ao atual.
	 * 
	 * @param c
	 *            a cor
	 * @return true se as cores tem o mesmo c�digo e false caso contrario
	 */
	public abstract boolean desejavelAoColorir(Cor cor);
}
