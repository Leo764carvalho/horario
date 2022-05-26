package src.br.com.rsa.horarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Um professor possui um nome e uma lista de horarios desejaveis e
 * indesejaveis. O programa ira dar preferência a horarios desejaveis e evitara
 * os indesejaveis.
 * 
 * @author felipe
 *
 */
public class Professor implements Serializable{
	private String nome;

	private List<Horario> desejavel;

	private List<Horario> indesejavel;

	public Professor(String nome) {
		this.nome = nome;
		this.desejavel = new ArrayList<Horario>();
		this.indesejavel = new ArrayList<Horario>();
	}

	public void addDesejavel(Horario horario) {
		this.desejavel.add(horario);
	}

	public void addIndesejavel(Horario horario) {
		this.indesejavel.add(horario);
	}

	public List<Horario> getDesejavel() {
		return desejavel;
	}

	public List<Horario> getIndesejavel() {
		return indesejavel;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return nome;
	}

    public void setNome(String nome) {
        this.nome = nome;
    }
}
