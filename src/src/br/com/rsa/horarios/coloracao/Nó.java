package src.br.com.rsa.horarios.coloracao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstrata que representa um nó
 * 
 * @author felipe
 *
 */
public abstract class Nó<T extends Cor> implements Serializable{
	// Usado internamente para verificar se a aula é a mesma
	private static long contador = 0;

	private long código;

	public abstract boolean adjacente(Nó<T> nó);

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

	public Nó(List<T> coresDisponiveis) {
		this.desejaveis = new ArrayList<T>();
		this.indesejaveis = new ArrayList<T>();
		this.código = contador++;
		this.preferencial = false;
		this.coresDisponiveis = new ArrayList<T>();
		this.coresDisponiveis.addAll(coresDisponiveis);
	}

	/**
	 * Dois nós serão iguais se possuírem o mesmo código
	 * 
	 * @param nó
	 * @return true se os nós possuem o mesmo código e false caso contrario
	 */
	public boolean equals(Nó<T> nó) {
		return this.código == nó.código;
	}

	/**
	 * Verifica se o nó é desejavel.
	 * 
	 * @return true se o nó é desejavel e false caso contrario.
	 */
	public boolean isPreferencial() {
		return preferencial;
	}
	
	void setPreferencial(boolean preferencial) {
		this.preferencial = preferencial;
	}

	/**
	 * Se o método Nó.equalsIgnoreCode retornar true, o programa ira tentar
	 * priorizar outras cores para aquele nó. Esta cor ainda pode ser utilizada em
	 * último caso.
	 * 
	 * @param nó
	 *            o nó a ser comparado
	 * @return true se os nós forem iguais e false caso contrario.
	 */
	public abstract boolean equalsIgnoreCode(Nó<T> nó);
}
