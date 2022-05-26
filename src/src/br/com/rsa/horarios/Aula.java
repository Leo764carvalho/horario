package src.br.com.rsa.horarios;

import java.io.Serializable;
import java.util.List;

import src.br.com.rsa.horarios.coloracao.Nó;

/**
 * Cada nó do grafo a ser colorido corresponde a uma aula. O sistema permite
 * mais de uma aula por disciplina.
 * 
 * @author felipe
 *
 */
public class Aula extends Nó<Horario> implements Serializable{
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
	 * Verifica se esta aula é adjacente a outro Nó. Isto sera verdadadeiro se as
	 * aulas entrarem em choque.
	 */
	
	public boolean adjacente(Nó<Horario> nó) {
		if (nó instanceof Aula) {
			Aula aula = (Aula) nó;

			return this.disciplina.choque(aula.disciplina);
		} else {
			return false;
		}
	}

	public String toString() {
		return disciplina.toString();
	}

	/**
	 * Verifica se as duas aulas correspondem à mesma disciplina
	 */
	
	public boolean equalsIgnoreCode(Nó<Horario> nó) {
		if (nó instanceof Aula) {
			Aula aula = (Aula) nó;
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
