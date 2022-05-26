package src.br.com.rsa.horarios.coloracao;

/**
 * Exceção que indica que não foi possível realizar a coloração do grafo com as
 * cores selecionadas
 * 
 * @author felipe
 *
 */
public class CoresInsuficientesException extends Exception {

	private Nó<?> nó = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 3639645680070299480L;

	public CoresInsuficientesException(Nó<?> nó) {
		super();
		this.nó = nó;
	}

	@Override
	public String getMessage() {
		return "Não foi possível realizar a coloração com as cores indicadas. Não foi possível colorir " + nó;
	}
}
