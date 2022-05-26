package src.br.com.rsa.horarios.coloracao;

/**
 * Classe contendo configurações da coloração
 * 
 * @author felipe
 *
 */
public class Configuracoes{

	/**
	 * Indica que, ao colorir um nó, a função {@link Cor#desejavelAoColorir(Cor)}
	 * deve ser chamada para alterar as prioridades das cores.
	 */
	static boolean desejavelAoColorir = false;

	/**
	 * Indica que, ao colorir um nó, a função {@link Cor#desejavelAoColorir(Cor)}
	 * deve ser chamada para alterar as prioridades das cores.
	 * 
	 * Valor padrão: <code>false</code>
	 */
	public static void setDesejavelAoColorir(boolean valor) {
		desejavelAoColorir = valor;
	}
}
