package src.br.com.rsa.horarios.main;

import br.com.rsa.horarios.principal.tela_Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import src.br.com.rsa.horarios.Aula;
import src.br.com.rsa.horarios.Curso;
import src.br.com.rsa.horarios.DiasDaSemana;
import src.br.com.rsa.horarios.Disciplina;
import src.br.com.rsa.horarios.Horario;
import src.br.com.rsa.horarios.Professor;
import src.br.com.rsa.horarios.Turma;
import src.br.com.rsa.horarios.coloracao.CoresInsuficientesException;
import src.br.com.rsa.horarios.coloracao.GrafoColorido;

public class Main {
    
    public static void main(String[] args){
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tela_Principal().setVisible(true);
            }
        });
    }
    
	public static void mano(String[] args) {
		System.out.println(
				"Digite o número dos cursos para os quais você deseja gerar o Horario. Digite 0 para gerar o Horario");
		System.out.println(
				"1 - Computação 2017\n2 - Serviço Social\n3 - Computação 2018\n4 - Direito (Tarde)\n5 - Direito (Noite)");

		Scanner in = new Scanner(System.in);

		List<Curso> cursos = new ArrayList<Curso>();
		int i;
		do {
			i = in.nextInt();
			switch (i) {
			case 1:
				cursos.add(cursoComputacao2017(HorarioNoite()));
				break;
			case 2:
				cursos.add(cursoServicoSocial());
				break;
			case 3:
				cursos.add(cursoComputacao2018());
				break;
			case 4:
				cursos.add(cursoDireito(HorarioTarde()));
				break;
			case 5:
				cursos.add(cursoDireito(HorarioNoite()));
				break;
			case 6:
				cursos.add(cursoComputacao2017(HorarioTarde()));
				break;
			default:
				i = 0;
			}
		} while (i != 0);

		in.close();

		long inicio = System.currentTimeMillis();

		teste(cursos);

		long fim = System.currentTimeMillis();

		System.out.println("Concluído em " + (fim - inicio) + "ms");
	}

	private static void teste(List<Curso> cursos) {

		if (cursos != null && cursos.size() > 0) {

			Map<Aula, Horario> coloracao;
			try {
				GrafoColorido<Aula, Horario> grafo = new GrafoColorido<Aula, Horario>(cursos.get(0).getAulas());
				for (int i = 1; i < cursos.size(); i++) {
					grafo.addNós(cursos.get(i).getAulas());
				}
				grafo.colorir();
				coloracao = grafo.getcoloracao();

				for (Curso c : cursos) {
					c.imprimeHorario(coloracao);
				}
			} catch (CoresInsuficientesException e) {
				System.err.println("Ocorreu um erro: " + e.getMessage());
			}
		}
	}

	private static Curso cursoServicoSocial() {

		Curso curso = new Curso("Servi?o Social", HorarioNoite());

		// Horarios especiais
		List<Horario> HorarioEstagio = new ArrayList<Horario>();
		HorarioEstagio.add(new Horario("Qua.0", 3, null));
		HorarioEstagio.add(new Horario("Qui.0", 3, null));

		// Professores
		Professor marcos_antônio = new Professor("Marcos Antônio");
		Professor mayara = new Professor("Mayara");
		Professor maria_mercês = new Professor("Maria Mercês");
		Professor evilasio = new Professor("Evilasio");
		Professor maria_josé = new Professor("Maria José");
		Professor ortiz = new Professor("Ortiz");
		Professor marciana = new Professor("Marciana");
		Professor leda = new Professor("Leda");
		Professor ângela = new Professor("Ângela");
		Professor remédios = new Professor("Remédios");
		Professor jackeline = new Professor("Jackeline");
		Professor mardila = new Professor("Mardila");
		Professor socorro = new Professor("Socorro");

		// Turmas
		List<Turma> turma1 = new ArrayList<Turma>();
		List<Turma> turma3 = new ArrayList<Turma>();
		List<Turma> turma5 = new ArrayList<Turma>();
		List<Turma> turma7 = new ArrayList<Turma>();
		List<Turma> turma8 = new ArrayList<Turma>();
		List<Turma> turma1_3 = new ArrayList<Turma>();
		turma1.add(new Turma("Serviço Social 1ºP"));
		turma3.add(new Turma("Serviço Social 3ºP"));
		turma5.add(new Turma("Serviço Social 5ºP"));
		turma7.add(new Turma("Serviço Social 7ºP"));
		turma8.add(new Turma("Serviço Social 8ºP"));
		turma1_3.add(turma1.get(0));
		turma1_3.add(turma3.get(0));

		// Disciplinas
		Disciplina tSocI = new Disciplina("Teorias Sociológicas I", maria_mercês, turma1);
		Disciplina tFilI = new Disciplina("Teorias Filosóficas I", marcos_antônio, turma1);
		Disciplina metodologia = new Disciplina("Metodologia", mayara, turma1);
		Disciplina psicologiaGeral = new Disciplina("Psicologia Geral", evilasio, turma1);
		curso.addAulas(tSocI, 2);
		curso.addAulas(tFilI, 2);
		curso.addAulas(metodologia, 2);
		curso.addAulas(psicologiaGeral, 2);

		Disciplina questão = new Disciplina("Questão Social", mardila, turma1_3);
		Disciplina libras = new Disciplina("LIBRAS", socorro, turma1_3);
		curso.addAulas(questão, 1);
		curso.addAulas(libras, 1);

		Disciplina acumulação = new Disciplina("Acumulação Capitalista", leda, turma3);
		Disciplina direito = new Disciplina("Direito e Legislação", ortiz, turma3);
		Disciplina psicologiaSocial = new Disciplina("Psicologia Social", evilasio, turma3);
		Disciplina ciênciaPolítica = new Disciplina("Ciência Política", maria_josé, turma3);
		curso.addAulas(acumulação, 2);
		curso.addAulas(direito, 2);
		curso.addAulas(psicologiaSocial, 2);
		curso.addAulas(ciênciaPolítica, 2);

		Disciplina processoI = new Disciplina("Processo de Trabalho I", ângela, turma5);
		Disciplina administração = new Disciplina("Administração", leda, turma5);
		Disciplina funtamentosII = new Disciplina("Fundamentos II", remédios, turma5);
		Disciplina planejamento = new Disciplina("Planejamento Social", marciana, turma5);
		Disciplina políticaII = new Disciplina("Política Social II", jackeline, turma5);
		curso.addAulas(processoI, 2);
		curso.addAulas(administração, 2);
		curso.addAulas(funtamentosII, 2);
		curso.addAulas(planejamento, 2);
		curso.addAulas(políticaII, 2);

		Disciplina ética = new Disciplina("Ética", marciana, turma7);
		Disciplina processoIII = new Disciplina("Processo de Trabalho III", ângela, turma7);
		Disciplina pesquisaII = new Disciplina("Pesquisa Social II", remédios, turma7);
		Disciplina estagioI = new Disciplina("Estagio I", mardila, turma7);
		curso.addAulas(ética, 2);
		curso.addAulas(processoIII, 2);
		curso.addAulas(pesquisaII, 2);
		curso.addAulas(estagioI, 1, HorarioEstagio);

		Disciplina seminario = new Disciplina("Seminario de Praticas", jackeline, turma8);
		Disciplina estagioII = new Disciplina("Estagio II", mardila, turma8);
		Disciplina tcc = new Disciplina("TCC", jackeline, turma8);
		curso.addAulas(seminario, 1);
		curso.addAulas(estagioII, 1, HorarioEstagio);
		curso.addAulas(tcc, 1);

		return curso;
	}

	private static Curso cursoComputacao2017(List<Horario> Horarios) {

		Curso curso = new Curso("Computação", Horarios);

		List<Horario> sexta = curso.getHorarios(DiasDaSemana.SEXTA);

		for (Horario h : sexta)
			giovane.addIndesejavel(h);

		// Turmas
		List<Turma> turma1 = new ArrayList<Turma>();
		List<Turma> turma2 = new ArrayList<Turma>();
		turma1.add(new Turma("Computa??o 3ºP"));
		turma2.add(new Turma("Computa??o 6ºP"));

		// Disciplinas (3º Período)
		Disciplina lpi = new Disciplina("LPI", felipe, turma1);
		Disciplina estruturas = new Disciplina("Estruturas de Dados", anatiel, turma1);
		Disciplina calculo = new Disciplina("Calculo", antônio, turma1);
		Disciplina geometria = new Disciplina("Geometria", antônio, turma1);
		Disciplina engenharia = new Disciplina("Engenharia de Software", éder, turma1);

		// Aulas (3º Período)
		curso.addAulas(lpi, 2);
		curso.addAulas(estruturas, 2);
		curso.addAulas(calculo, 2);
		curso.addAulas(geometria, 2);
		curso.addAulas(engenharia, 2);

		// Disciplinas (6º Período)
		Disciplina lpiv = new Disciplina("LPIV", giovane, turma2);
		Disciplina ihc = new Disciplina("IHC", giovane, turma2);
		Disciplina redes = new Disciplina("Redes", anatiel, turma2);
		Disciplina circuitos = new Disciplina("Circuitos", éder, turma2);

		// Aulas (6º Período)
		curso.addAulas(lpiv, 2);
		curso.addAulas(ihc, 2);
		curso.addAulas(redes, 3);
		curso.addAulas(circuitos, 2);

		return curso;
	}

	private static Curso cursoComputacao2018() {

		Curso curso = new Curso("Computação", HorarioNoite());

		// Professores
		Professor felipe = new Professor("Felipe");
		Professor éder = new Professor("Éder");
		Professor anatiel = new Professor("Anatiel");
		Professor antônio = new Professor("Antônio");
		Professor giovane = new Professor("Giovane");

		// Turmas
		List<Turma> turma1 = new ArrayList<Turma>();
		List<Turma> turma2 = new ArrayList<Turma>();
		turma1.add(new Turma("Computação 4ºP"));
		turma2.add(new Turma("Computação 7ºP"));

		// Horarios
		List<Horario> sexta = curso.getHorarios(DiasDaSemana.SEXTA);

		// Disciplinas (4º Período)
		Disciplina lpii = new Disciplina("Linguagens de Programação II", giovane, turma1);
		Disciplina estatística = new Disciplina("Probabilidade e Estatística", antônio, turma1);
		Disciplina bd = new Disciplina("Banco de Dados", giovane, turma1);
		Disciplina grafos = new Disciplina("Teoria dos Grafos", felipe, turma1);
		Disciplina calculo = new Disciplina("Calculo Numérico", antônio, turma1);

		// Aulas (4º Período)
		curso.addAulas(lpii, 2);
		curso.addAulas(estatística, 2);
		curso.addAulas(bd, 2);
		curso.addAulas(grafos, 2);
		curso.addAulas(calculo, 2);

		// Disciplinas (7º Período)
		Disciplina ia = new Disciplina("Inteligência Artificial", anatiel, turma2);
		Disciplina analise = new Disciplina("analise e Projeto de Sistemas", anatiel, turma2);
		Disciplina tcci = new Disciplina("TCC I", éder, turma2);
		Disciplina algoritmos = new Disciplina("analise e Projeto de Algoritmos", felipe, turma2);

		// Aulas (6º Período)
		curso.addAulas(ia, 2);
		curso.addAulas(analise, 2);
		curso.addAulas(tcci, 2);
		curso.addAulas(algoritmos, 2);

		// Horarios indesejaveis
		giovane.addIndesejavel(sexta.get(0));
		giovane.addIndesejavel(sexta.get(1));

		return curso;
	}

	// Professores Computação
	private static Professor felipe = new Professor("Felipe");
	private static Professor éder = new Professor("Éder");
	private static Professor anatiel = new Professor("Anatiel");
	private static Professor antônio = new Professor("Antônio");
	private static Professor giovane = new Professor("Giovane");

	// Professores Direito
	private static Professor aline = new Professor("Aline Hipólito");
	private static Professor merces = new Professor("Merces");
	private static Professor mariaJosé = new Professor("Maria José");
	private static Professor marinalva = new Professor("Marinalva");
	private static Professor hidelmar = new Professor("Hidelmar Fontes");
	private static Professor marco = new Professor("Marco Antônio");
	private static Professor mafra = new Professor("Mafra");
	private static Professor mariaCarmo = new Professor("Maria do Carmo");
	private static Professor fatima = new Professor("Fatima Miranda");
	private static Professor fabrício = new Professor("Fabrício Bezerra");
	private static Professor xavier = new Professor("Xavier");
	private static Professor érica = new Professor("Érica");
	private static Professor herval = new Professor("Herval Ribeiro");
	private static Professor eloise = new Professor("Eloise");
	private static Professor aleksandro = new Professor("Aleksandro");
	private static Professor anaKarla = new Professor("Ana Karla");
	private static Professor adriano = new Professor("Adriano");
	private static Professor andreya = new Professor("Andreya");
	private static Professor elves = new Professor("Elves");
	private static Professor rodrigo = new Professor("Rodrigo");
	private static Professor francisca = new Professor("Francisca");
	private static Professor cristiane = new Professor("Cristiane");

	private static Curso cursoDireito(List<Horario> Horarios) {

		Curso curso = new Curso("Direito", Horarios);

		// Turmas
		List<Turma> turma1 = new ArrayList<Turma>();
		List<Turma> turma2 = new ArrayList<Turma>();
		List<Turma> turma3 = new ArrayList<Turma>();
		List<Turma> turma4 = new ArrayList<Turma>();
		List<Turma> turma5 = new ArrayList<Turma>();
		List<Turma> turma6 = new ArrayList<Turma>();
		List<Turma> turma7 = new ArrayList<Turma>();
		List<Turma> turma8 = new ArrayList<Turma>();
		List<Turma> turma9 = new ArrayList<Turma>();
		List<Turma> turma10 = new ArrayList<Turma>();

		turma1.add(new Turma("Direito 1ºNM"));
		turma2.add(new Turma("Direito 2ºNM"));
		turma3.add(new Turma("Direito 3ºNM"));
		turma4.add(new Turma("Direito 4ºNM"));
		turma5.add(new Turma("Direito 5ºNM"));
		turma6.add(new Turma("Direito 6ºNM"));
		turma7.add(new Turma("Direito 7ºNM"));
		turma8.add(new Turma("Direito 8ºNM"));
		turma9.add(new Turma("Direito 9ºNM"));
		turma10.add(new Turma("Direito 10ºNM"));

		// Disciplinas (1º Período)
		Disciplina introdução = new Disciplina("Introdução ao Direito", aline, turma1);
		Disciplina sociologia = new Disciplina("Sociologia Geral e Jurídica", merces, turma1);
		Disciplina metodologia = new Disciplina("Metodologia de Pesquisa", mariaJosé, turma1);
		Disciplina português = new Disciplina("Português Jurídico", marinalva, turma1);
		Disciplina economia = new Disciplina("Economia Política", hidelmar, turma1);
		Disciplina filosofia = new Disciplina("Filosofia Geral e Jurídica", marco, turma1);
		Disciplina antropologia = new Disciplina("Antropologia", mafra, turma1);

		// Aulas (1º Período)
		curso.addAulas(introdução, 2);
		curso.addAulas(sociologia, 2);
		curso.addAulas(metodologia, 1);
		curso.addAulas(português, 1);
		curso.addAulas(economia, 1);
		curso.addAulas(filosofia, 2);
		curso.addAulas(antropologia, 1);

		// Disciplinas (2º Período)
		Disciplina hermenutica = new Disciplina("Hermenutica Jurídica", mariaCarmo, turma2);
		Disciplina direitoConsI = new Disciplina("Direito Constitucional I", fatima, turma2);
		Disciplina tgp = new Disciplina("T.G.P.", fabrício, turma2);
		Disciplina tge = new Disciplina("T.G.E.", xavier, turma2);
		Disciplina psicologiaJurídica = new Disciplina("Psicologia Jurídica", érica, turma2);
		Disciplina cidadania = new Disciplina("Cidadania e Direitos Humanos", mariaJosé, turma2);

		// Aulas (2º Período)
		curso.addAulas(hermenutica, 2);
		curso.addAulas(direitoConsI, 2);
		curso.addAulas(tgp, 2);
		curso.addAulas(tge, 2);
		curso.addAulas(psicologiaJurídica, 1);
		curso.addAulas(cidadania, 1);

		// Disciplinas (3º Período)
		Disciplina direitoPenalI = new Disciplina("Direito Penal I", herval, turma3);
		Disciplina direitoConsII = new Disciplina("Direito Constitucional II", eloise, turma3);
		Disciplina direitoTrabI = new Disciplina("Direito do Trabalho I", anaKarla, turma3);
		Disciplina direitoCivilI = new Disciplina("Direito Civil I", aleksandro, turma3);
		Disciplina resolução = new Disciplina("Resolução de Conflitos", mariaCarmo, turma3);
		Disciplina criminologia = new Disciplina("Criminologia", adriano, turma3);

		// Aulas (3º Período)
		curso.addAulas(direitoPenalI, 2);
		curso.addAulas(direitoConsII, 2);
		curso.addAulas(direitoTrabI, 2);
		curso.addAulas(direitoCivilI, 2);
		curso.addAulas(resolução, 1);
		curso.addAulas(criminologia, 1);

		// Disciplinas (4º Período)
		Disciplina direitoAdmI = new Disciplina("Direito Administrativo I", andreya, turma4);
		Disciplina direitoPenalII = new Disciplina("Direito Penal II", elves, turma4);
		Disciplina procCivilI = new Disciplina("Proc. Civil I", rodrigo, turma4);
		Disciplina direitoCivilII = new Disciplina("Direito Civil II", francisca, turma4);
		Disciplina direitoAmbiental = new Disciplina("Direito Ambiental", xavier, turma4);
		Disciplina direitoTrabII = new Disciplina("Direito do Trabalho II", cristiane, turma4);

		// Aulas (4º Período)
		curso.addAulas(direitoAdmI, 2);
		curso.addAulas(direitoPenalII, 2);
		curso.addAulas(procCivilI, 2);
		curso.addAulas(direitoCivilII, 2);
		curso.addAulas(direitoAmbiental, 1);
		curso.addAulas(direitoTrabII, 1);

		return curso;
	}

	public static List<Horario> HorarioTarde() {
		List<Horario> cores = new ArrayList<Horario>();
		cores.add(new Horario("Segunda, 14:00", 0, DiasDaSemana.SEGUNDA));
		cores.add(new Horario("Segunda, 16:00", 1, DiasDaSemana.SEGUNDA));
		cores.add(new Horario("Terça, 14:00", 0, DiasDaSemana.TERCA));
		cores.add(new Horario("Terça, 16:00", 1, DiasDaSemana.TERCA));
		cores.add(new Horario("Quarta, 14:00", 0, DiasDaSemana.QUARTA));
		cores.add(new Horario("Quarta, 16:00", 1, DiasDaSemana.QUARTA));
		cores.add(new Horario("Quinta, 14:00", 0, DiasDaSemana.QUINTA));
		cores.add(new Horario("Quinta, 16:00", 1, DiasDaSemana.QUINTA));
		cores.add(new Horario("Sexta, 14:00", 0, DiasDaSemana.SEXTA));
		cores.add(new Horario("Sexta, 16:00", 1, DiasDaSemana.SEXTA));

		return cores;
	}

	public static List<Horario> HorarioNoite() {
		List<Horario> cores = new ArrayList<Horario>();
		cores.add(new Horario("Segunda, 18:00", 2, DiasDaSemana.SEGUNDA));
		cores.add(new Horario("Segunda, 20:00", 3, DiasDaSemana.SEGUNDA));
		cores.add(new Horario("Terça, 18:00", 2, DiasDaSemana.TERCA));
		cores.add(new Horario("Terça, 20:00", 3, DiasDaSemana.TERCA));
		cores.add(new Horario("Quarta, 18:00", 2, DiasDaSemana.QUARTA));
		cores.add(new Horario("Quarta, 20:00", 3, DiasDaSemana.QUARTA));
		cores.add(new Horario("Quinta, 18:00", 2, DiasDaSemana.QUINTA));
		cores.add(new Horario("Quinta, 20:00", 3, DiasDaSemana.QUINTA));
		cores.add(new Horario("Sexta, 18:00", 2, DiasDaSemana.SEXTA));
		cores.add(new Horario("Sexta, 20:00", 3, DiasDaSemana.SEXTA));

		return cores;
	}
}