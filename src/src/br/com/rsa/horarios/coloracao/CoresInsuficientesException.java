package src.br.com.rsa.horarios.coloracao;

/**
 * Exce��o que indica que n�o foi poss�vel realizar a colora��o do grafo com as
 * cores selecionadas
 * 
 * @author felipe
 *
 */
public class CoresInsuficientesException extends Exception {

	private N�<?> n� = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 3639645680070299480L;

	public CoresInsuficientesException(N�<?> n�) {
		super();
		this.n� = n�;
	}

	@Override
	public String getMessage() {
		return "N�o foi poss�vel realizar a colora��o com as cores indicadas. N�o foi poss�vel colorir " + n�;
	}
}
