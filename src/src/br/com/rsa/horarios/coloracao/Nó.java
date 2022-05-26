package src.br.com.rsa.horarios.coloracao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstrata que representa um n�
 * 
 * @author felipe
 *
 */
public abstract class N�<T extends Cor> implements Serializable{
	// Usado internamente para verificar se a aula � a mesma
	private static long contador = 0;

	private long c�digo;

	public abstract boolean adjacente(N�<T> n�);

	private List<T> desejaveis;

	private List<T> indesejaveis;

	private List<T> coresDisponiveis;

	private boolean preferencial;

	public boolean isIndesejavel(T cor) {
		for (T aux : indesejaveis) {
			if (aux == cor) {
				return true;
			}
		}
		return false;
	}

	public boolean isDesejavel(T cor) {
		for (T aux : desejaveis) {
			if (aux == cor) {
				return true;
			}
		}
		return false;
	}

	public void addDesejavel(T cor) {
		desejaveis.add(cor);
		preferencial = true;
	}

	public void addDesejavel(List<T> cores) {
		desejaveis.addAll(cores);
		preferencial = true;
	}

	public void addIndesejavel(List<T> cores) {
		indesejaveis.addAll(cores);
	}

	public void addIndesejavel(T cor) {
		indesejaveis.add(cor);
	}

	public List<T> getCoresDisponiveis() {
		return coresDisponiveis;
	}

	public N�(List<T> coresDisponiveis) {
		this.desejaveis = new ArrayList<T>();
		this.indesejaveis = new ArrayList<T>();
		this.c�digo = contador++;
		this.preferencial = false;
		this.coresDisponiveis = new ArrayList<T>();
		this.coresDisponiveis.addAll(coresDisponiveis);
	}

	/**
	 * Dois n�s ser�o iguais se possu�rem o mesmo c�digo
	 * 
	 * @param n�
	 * @return true se os n�s possuem o mesmo c�digo e false caso contrario
	 */
	public boolean equals(N�<T> n�) {
		return this.c�digo == n�.c�digo;
	}

	/**
	 * Verifica se o n� � desejavel.
	 * 
	 * @return true se o n� � desejavel e false caso contrario.
	 */
	public boolean isPreferencial() {
		return preferencial;
	}
	
	void setPreferencial(boolean preferencial) {
		this.preferencial = preferencial;
	}

	/**
	 * Se o m�todo N�.equalsIgnoreCode retornar true, o programa ira tentar
	 * priorizar outras cores para aquele n�. Esta cor ainda pode ser utilizada em
	 * �ltimo caso.
	 * 
	 * @param n�
	 *            o n� a ser comparado
	 * @return true se os n�s forem iguais e false caso contrario.
	 */
	public abstract boolean equalsIgnoreCode(N�<T> n�);
}
