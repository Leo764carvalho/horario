package br.com.rsa;

import java.util.List;

/**
 * Classe contendo fun��es auxiliares de uso geral.
 * 
 * @author felipe
 *
 */
public final class Util {

	/**
	 * Troca dois elementos da lista
	 * 
	 * @param lista
	 *            uma lista de elementos
	 * @param i
	 *            um �ndice
	 * @param j
	 *            um �ndice
	 */
	public static <T> void troca(List<T> lista, int i, int j) {
		T aux = lista.get(i);
		lista.set(i, lista.get(j));
		lista.set(j, aux);
	}

}
