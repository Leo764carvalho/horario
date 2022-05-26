package src.br.com.rsa.horarios;

import java.io.Serializable;
import java.util.List;

import src.br.com.rsa.horarios.coloracao.N�;

/**
 * Cada n� do grafo a ser colorido corresponde a uma aula. O sistema permite
 * mais de uma aula por disciplina.
 * 
 * @author felipe
 *
 */
public class Aula extends N�<Horario> implements Serializable{
	private Disciplina disciplina;

	public Aula(Disciplina disciplina, List<Horario> Horarios) {
		super(Horarios);
		this.disciplina = disciplina;

		// Registra os Horarios desejaveis e indesejaveis do professor
		//List<Horario> Desejavel = disciplina.getProfessor().getDesejavel();
		//List<Horario> Indesejavel = disciplina.getProfessor().getIndesejavel();
		//addDesejavel(Desejavel);
		//addIndesejavel(Indesejavel);
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	/**
	 * Verifica se esta aula � adjacente a outro N�. Isto sera verdadadeiro se as
	 * aulas entrarem em choque.
	 */
	
	public boolean adjacente(N�<Horario> n�) {
		if (n� instanceof Aula) {
			Aula aula = (Aula) n�;

			return this.disciplina.choque(aula.disciplina);
		} else {
			return false;
		}
	}

	public String toString() {
		return disciplina.toString();
	}

	/**
	 * Verifica se as duas aulas correspondem � mesma disciplina
	 */
	
	public boolean equalsIgnoreCode(N�<Horario> n�) {
		if (n� instanceof Aula) {
			Aula aula = (Aula) n�;
			return aula.disciplina.equals(this.disciplina);
		} else {
			return false;
		}
	}

	
	public boolean isDesejavel(Horario cor) {
		return super.isDesejavel(cor) || disciplina.getProfessor().getDesejavel().contains(cor);
	}

	public boolean isIndesejavel(Horario cor) {
		return super.isIndesejavel(cor) || disciplina.getProfessor().getIndesejavel().contains(cor);
	}
}
