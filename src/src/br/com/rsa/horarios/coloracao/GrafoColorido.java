package src.br.com.rsa.horarios.coloracao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.com.rsa.Util;
import src.br.com.rsa.horarios.coloracao.Cor;
import src.br.com.rsa.horarios.coloracao.CoresInsuficientesException;
import src.br.com.rsa.horarios.coloracao.N�;

/**
 * Grafo a ser usado na coloracao.
 * 
 * @author felipe
 *
 * @param <T>
 *            o tipo dos n�s do grafo
 * @param <U>
 *            o tipo das cores do grafo
 */
public class GrafoColorido<T extends N�<U>, U extends Cor> {
	private Map<T, U> coloracao;
	private boolean colorido;
	private Map<T, List<T>> n�s;

	public List<T> getN�s() {
		List<T> lista = new ArrayList<T>();
		lista.addAll(n�s.keySet());
		return lista;
	}

	public void addN�(T n�) {
		List<T> conjunto = new ArrayList<T>();

		for (T n�1 : n�s.keySet()) {
			if (n�1.adjacente(n�)) {
				// Adiciona o n� existente � lista de adjacentes do novo n�
				conjunto.add(n�1);

				// Adiciona o novo n� � lista de adjacentes do n� existente
				n�s.get(n�1).add(n�);
			}
		}

		n�s.put(n�, conjunto);
	}

	public void addN�s(List<T> n�s) {
		for (T n� : n�s) {
			addN�(n�);
		}
	}

	public void removeN�(T n�) {
		List<T> conjunto = n�s.remove(n�);
		for (T adjacente : conjunto) {
			n�s.get(adjacente).remove(n�);
		}
	}

	public List<T> adjacentes(T n�) {
		return n�s.get(n�);
	}

	/**
	 * Construtor
	 * 
	 * @param n�s
	 * @param cores
	 */
	public GrafoColorido(List<T> n�s) {
		super();
		this.n�s = new HashMap<T, List<T>>();
		coloracao = new HashMap<T, U>();
		for (T n� : n�s) {
			addN�(n�);
		}
		colorido = false;
	}

	/**
	 * Retorna o mapa contendo as cores de cada n�
	 * 
	 * @return o mapa contendo as cores de cada n�
	 * @throws CoresInsuficientesException
	 */
	public Map<T, U> getcoloracao() throws CoresInsuficientesException {
		if (!colorido) {
			colorir();
		}
		return coloracao;
	}

	/**
	 * Obt�m os n�s coloridos adjacentes ao n� indicado, juntamente com suas cores.
	 * 
	 * @param n�
	 *            o n� a ser usado como refer�ncia
	 * @return um mapa contendo os n�s adjacentes e suas respectivas cores
	 */
	public Map<T, U> adjacentesColoridos(T n�) {
		Map<T, U> adjacentes = new HashMap<T, U>();
		for (T aux : adjacentes(n�)) {
			if (coloracao.containsKey(aux)) {
				adjacentes.put(aux, coloracao.get(aux));
			}
		}
		return adjacentes;
	}

	/**
	 * Obt�m os n�s descoloridos adjacentes ao n� indicado
	 * 
	 * @param n�
	 *            o n� a ser usado como refer�ncia
	 * @return uma lista de n�s
	 */
	public List<T> adjacentesDescoloridos(T n�) {
		List<T> adjacentes = new ArrayList<T>();
		for (T aux : adjacentes(n�)) {
			if (!coloracao.containsKey(aux)) {
				adjacentes.add(aux);
			}
		}
		return adjacentes;
	}

	/**
	 * Retorna a lista de cores disponiveis para um determinado n�. Retorna apenas
	 * cores que n�o estejam em n�s adjacentes. A primeira cor sera sempre uma que
	 * n�o possua o mesmo c�digo de outras cores da lista, se isto for possivel.
	 * 
	 * @param n�
	 * @return a lista de cores disponiveis
	 */
	public List<U> coresDisponiveis(T n�) {
		List<U> result = n�.getCoresDisponiveis();

		Map<T, U> adjacentes = adjacentesColoridos(n�);

		for (U c1 : adjacentes.values()) {
			result.remove(c1);
		}

		// Coloca as cores desejaveis no come�o e indesejaveis no fim da lista
		ordenaCores(result, n�);

		// Coloca uma cor que n�o possua o mesmo c�digo das cores da mesma disciplina no
		// come�o da lista
		int conflitos[] = new int[result.size()];
		Arrays.fill(conflitos, 0);

		for (int i = 0; i < result.size(); i++) {
			U cor1 = result.get(i);
			for (Entry<T, U> entry : adjacentes.entrySet()) {

				// Se for a mesma disciplina
				T aux = entry.getKey();
				if (aux.equalsIgnoreCode(n�)) {

					// Havera conflito se as cores se chocarem
					U cor2 = entry.getValue();
					if (cor1.choque(cor2)) {
						conflitos[i]++;
					}
				}
			}

			/*
			 * for (U cor3 : adjacentes.values()) { if (cor1.choque(cor3)) { conflitos[i]++;
			 * } }
			 */
		}

		int melhor = 0;

		for (int i = 1; i < result.size(); i++) {
			if (conflitos[i] < conflitos[melhor]) {
				melhor = i;
			}
		}

		// Coloca a melhor cor no come�o
		if (melhor < result.size()) {
			U aux = result.get(0);
			result.set(0, result.get(melhor));
			result.set(melhor, aux);
		}

		return result;
	}

	/**
	 * Coloca as cores desejaveis no come�o e remove as indesejaveis
	 * 
	 * @param cores
	 *            uma lista de cores
	 * @param n�
	 *            o n�
	 */
	private void ordenaCores(List<U> cores, T n�) {
		int come�o = 0;
		for (int i = 0; i < cores.size(); i++) {
			U c1 = cores.get(i);
			if (n�.isDesejavel(c1)) {
				Util.troca(cores, come�o, i);
				come�o++;
			}
		}

		int i = 0;
		while (i < cores.size()) {
			U cor = cores.get(i);
			if (n�.isIndesejavel(cor)) {
				cores.remove(cor);
			} else {
				i++;
			}
		}
	}

	/**
	 * Colore o n� com a cor desejada
	 * 
	 * @param n�
	 * @param cor
	 */
	private void colore(T n�, U cor) {
		coloracao.put(n�, cor);

		if (Configuracoes.desejavelAoColorir) {
			for (U corAux : n�.getCoresDisponiveis()) {
				if (cor.desejavelAoColorir(corAux)) {
					boolean preferencial = n�.isPreferencial();
					n�.addDesejavel(corAux);
					n�.setPreferencial(preferencial);
				}
			}
		}
	}

	/**
	 * Retorna a cor do n�
	 * 
	 * @param n�
	 * @return a cor do n� ou null se o n� n�o estiver colorido
	 */
	public U getCor(T n�) {
		if (coloracao.containsKey(n�)) {
			return coloracao.get(n�);
		} else {
			return null;
		}
	}

	/**
	 * Realiza a coloracao dos n�s
	 * 
	 * @param n�s:
	 *            a lista de n�s a serem coloridos
	 * @param cores:
	 *            a lista de cores disponiveis
	 * @return um mapa contendo os n�s com suas respectivas cores.
	 * @throws CoresInsuficientesException
	 *             caso as cores indicadas n�o sejam suficientes para colorir o
	 *             grafo
	 */
	public Map<T, U> colorir() throws CoresInsuficientesException {
		List<T> n�s = getN�s();

		// Inicia com todos os n�s descoloridos
		int totalDescoloridos = n�s.size();

		while (totalDescoloridos > 0) {
			// Encontra o n� a colorir: o de maior grau de satura��o e com mais v�rtices
			// adjacentes
			T n� = null;
			List<U> coresN� = null;
			int adjN� = 0;
			for (T aux : n�s) {
				// Procura um n� descolorido
				if (!this.coloracao.containsKey(aux)) {
					if (n� == null) {// Inicializa com o primeiro n� descolorido que encontrar
						n� = aux;
						coresN� = this.coresDisponiveis(n�);
						adjN� = this.adjacentesDescoloridos(n�).size();
					} else if ((!n�.isPreferencial()) && aux.isPreferencial()) {// Se for preferencial
						n� = aux;
						coresN� = this.coresDisponiveis(n�);
						adjN� = this.adjacentesDescoloridos(n�).size();
					} else {
						List<U> coresAux = this.coresDisponiveis(aux);
						int disponiveisAux = coresAux.size();
						int disponiveisN� = coresN�.size();

						// Encontra o n� com maior grau de satura��o, ou seja, aquele com menor n�mero
						// de cores disponiveis
						if (disponiveisAux < disponiveisN�) {
							n� = aux;
							coresN� = coresAux;
							adjN� = this.adjacentesDescoloridos(n�).size();
						} else if (disponiveisAux == disponiveisN�) {
							int adjAux = this.adjacentesDescoloridos(aux).size();

							// Em caso de empate, seleciona o n� com maior n�mero de adjacentes
							// n�o-coloridos
							if (adjAux > adjN�) {
								n� = aux;
								coresN� = coresAux;
								adjN� = adjAux;
							}
						}
					}
				}
			}

			// Se houver pelo menos um n� n�o-colorido
			if (n� != null) {

				if (coresN� != null && !coresN�.isEmpty()) {
					int indice = 0;

					// Colore
					U cor = coresN�.get(indice);
					this.colore(n�, cor);
				} else {
					throw new CoresInsuficientesException(n�);
				}
			}
			totalDescoloridos--;
		}
		colorido = true;
		return this.coloracao;
	}
}
