package src.br.com.rsa.horarios.coloracao;

/**
 * Classe contendo configura��es da colora��o
 * 
 * @author felipe
 *
 */
public class Configuracoes{

	/**
	 * Indica que, ao colorir um n�, a fun��o {@link Cor#desejavelAoColorir(Cor)}
	 * deve ser chamada para alterar as prioridades das cores.
	 */
	static boolean desejavelAoColorir = false;

	/**
	 * Indica que, ao colorir um n�, a fun��o {@link Cor#desejavelAoColorir(Cor)}
	 * deve ser chamada para alterar as prioridades das cores.
	 * 
	 * Valor padr�o: <code>false</code>
	 */
	public static void setDesejavelAoColorir(boolean valor) {
		desejavelAoColorir = valor;
	}
}
