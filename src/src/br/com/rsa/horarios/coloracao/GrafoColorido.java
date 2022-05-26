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
import src.br.com.rsa.horarios.coloracao.Nó;

/**
 * Grafo a ser usado na coloracao.
 * 
 * @author felipe
 *
 * @param <T>
 *            o tipo dos nós do grafo
 * @param <U>
 *            o tipo das cores do grafo
 */
public class GrafoColorido<T extends Nó<U>, U extends Cor> {
	private Map<T, U> coloracao;
	private boolean colorido;
	private Map<T, List<T>> nós;

	public List<T> getNós() {
		List<T> lista = new ArrayList<T>();
		lista.addAll(nós.keySet());
		return lista;
	}

	public void addNó(T nó) {
		List<T> conjunto = new ArrayList<T>();

		for (T nó1 : nós.keySet()) {
			if (nó1.adjacente(nó)) {
				// Adiciona o nó existente à lista de adjacentes do novo nó
				conjunto.add(nó1);

				// Adiciona o novo nó à lista de adjacentes do nó existente
				nós.get(nó1).add(nó);
			}
		}

		nós.put(nó, conjunto);
	}

	public void addNós(List<T> nós) {
		for (T nó : nós) {
			addNó(nó);
		}
	}

	public void removeNó(T nó) {
		List<T> conjunto = nós.remove(nó);
		for (T adjacente : conjunto) {
			nós.get(adjacente).remove(nó);
		}
	}

	public List<T> adjacentes(T nó) {
		return nós.get(nó);
	}

	/**
	 * Construtor
	 * 
	 * @param nós
	 * @param cores
	 */
	public GrafoColorido(List<T> nós) {
		super();
		this.nós = new HashMap<T, List<T>>();
		coloracao = new HashMap<T, U>();
		for (T nó : nós) {
			addNó(nó);
		}
		colorido = false;
	}

	/**
	 * Retorna o mapa contendo as cores de cada nó
	 * 
	 * @return o mapa contendo as cores de cada nó
	 * @throws CoresInsuficientesException
	 */
	public Map<T, U> getcoloracao() throws CoresInsuficientesException {
		if (!colorido) {
			colorir();
		}
		return coloracao;
	}

	/**
	 * Obtém os nós coloridos adjacentes ao nó indicado, juntamente com suas cores.
	 * 
	 * @param nó
	 *            o nó a ser usado como referência
	 * @return um mapa contendo os nós adjacentes e suas respectivas cores
	 */
	public Map<T, U> adjacentesColoridos(T nó) {
		Map<T, U> adjacentes = new HashMap<T, U>();
		for (T aux : adjacentes(nó)) {
			if (coloracao.containsKey(aux)) {
				adjacentes.put(aux, coloracao.get(aux));
			}
		}
		return adjacentes;
	}

	/**
	 * Obtém os nós descoloridos adjacentes ao nó indicado
	 * 
	 * @param nó
	 *            o nó a ser usado como referência
	 * @return uma lista de nós
	 */
	public List<T> adjacentesDescoloridos(T nó) {
		List<T> adjacentes = new ArrayList<T>();
		for (T aux : adjacentes(nó)) {
			if (!coloracao.containsKey(aux)) {
				adjacentes.add(aux);
			}
		}
		return adjacentes;
	}

	/**
	 * Retorna a lista de cores disponiveis para um determinado nó. Retorna apenas
	 * cores que não estejam em nós adjacentes. A primeira cor sera sempre uma que
	 * não possua o mesmo código de outras cores da lista, se isto for possivel.
	 * 
	 * @param nó
	 * @return a lista de cores disponiveis
	 */
	public List<U> coresDisponiveis(T nó) {
		List<U> result = nó.getCoresDisponiveis();

		Map<T, U> adjacentes = adjacentesColoridos(nó);

		for (U c1 : adjacentes.values()) {
			result.remove(c1);
		}

		// Coloca as cores desejaveis no começo e indesejaveis no fim da lista
		ordenaCores(result, nó);

		// Coloca uma cor que não possua o mesmo código das cores da mesma disciplina no
		// começo da lista
		int conflitos[] = new int[result.size()];
		Arrays.fill(conflitos, 0);

		for (int i = 0; i < result.size(); i++) {
			U cor1 = result.get(i);
			for (Entry<T, U> entry : adjacentes.entrySet()) {

				// Se for a mesma disciplina
				T aux = entry.getKey();
				if (aux.equalsIgnoreCode(nó)) {

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

		// Coloca a melhor cor no começo
		if (melhor < result.size()) {
			U aux = result.get(0);
			result.set(0, result.get(melhor));
			result.set(melhor, aux);
		}

		return result;
	}

	/**
	 * Coloca as cores desejaveis no começo e remove as indesejaveis
	 * 
	 * @param cores
	 *            uma lista de cores
	 * @param nó
	 *            o nó
	 */
	private void ordenaCores(List<U> cores, T nó) {
		int começo = 0;
		for (int i = 0; i < cores.size(); i++) {
			U c1 = cores.get(i);
			if (nó.isDesejavel(c1)) {
				Util.troca(cores, começo, i);
				começo++;
			}
		}

		int i = 0;
		while (i < cores.size()) {
			U cor = cores.get(i);
			if (nó.isIndesejavel(cor)) {
				cores.remove(cor);
			} else {
				i++;
			}
		}
	}

	/**
	 * Colore o nó com a cor desejada
	 * 
	 * @param nó
	 * @param cor
	 */
	private void colore(T nó, U cor) {
		coloracao.put(nó, cor);

		if (Configuracoes.desejavelAoColorir) {
			for (U corAux : nó.getCoresDisponiveis()) {
				if (cor.desejavelAoColorir(corAux)) {
					boolean preferencial = nó.isPreferencial();
					nó.addDesejavel(corAux);
					nó.setPreferencial(preferencial);
				}
			}
		}
	}

	/**
	 * Retorna a cor do nó
	 * 
	 * @param nó
	 * @return a cor do nó ou null se o nó não estiver colorido
	 */
	public U getCor(T nó) {
		if (coloracao.containsKey(nó)) {
			return coloracao.get(nó);
		} else {
			return null;
		}
	}

	/**
	 * Realiza a coloracao dos nós
	 * 
	 * @param nós:
	 *            a lista de nós a serem coloridos
	 * @param cores:
	 *            a lista de cores disponiveis
	 * @return um mapa contendo os nós com suas respectivas cores.
	 * @throws CoresInsuficientesException
	 *             caso as cores indicadas não sejam suficientes para colorir o
	 *             grafo
	 */
	public Map<T, U> colorir() throws CoresInsuficientesException {
		List<T> nós = getNós();

		// Inicia com todos os nós descoloridos
		int totalDescoloridos = nós.size();

		while (totalDescoloridos > 0) {
			// Encontra o nó a colorir: o de maior grau de saturação e com mais vértices
			// adjacentes
			T nó = null;
			List<U> coresNó = null;
			int adjNó = 0;
			for (T aux : nós) {
				// Procura um nó descolorido
				if (!this.coloracao.containsKey(aux)) {
					if (nó == null) {// Inicializa com o primeiro nó descolorido que encontrar
						nó = aux;
						coresNó = this.coresDisponiveis(nó);
						adjNó = this.adjacentesDescoloridos(nó).size();
					} else if ((!nó.isPreferencial()) && aux.isPreferencial()) {// Se for preferencial
						nó = aux;
						coresNó = this.coresDisponiveis(nó);
						adjNó = this.adjacentesDescoloridos(nó).size();
					} else {
						List<U> coresAux = this.coresDisponiveis(aux);
						int disponiveisAux = coresAux.size();
						int disponiveisNó = coresNó.size();

						// Encontra o nó com maior grau de saturação, ou seja, aquele com menor número
						// de cores disponiveis
						if (disponiveisAux < disponiveisNó) {
							nó = aux;
							coresNó = coresAux;
							adjNó = this.adjacentesDescoloridos(nó).size();
						} else if (disponiveisAux == disponiveisNó) {
							int adjAux = this.adjacentesDescoloridos(aux).size();

							// Em caso de empate, seleciona o nó com maior número de adjacentes
							// não-coloridos
							if (adjAux > adjNó) {
								nó = aux;
								coresNó = coresAux;
								adjNó = adjAux;
							}
						}
					}
				}
			}

			// Se houver pelo menos um nó não-colorido
			if (nó != null) {

				if (coresNó != null && !coresNó.isEmpty()) {
					int indice = 0;

					// Colore
					U cor = coresNó.get(indice);
					this.colore(nó, cor);
				} else {
					throw new CoresInsuficientesException(nó);
				}
			}
			totalDescoloridos--;
		}
		colorido = true;
		return this.coloracao;
	}
}
