package Q2;

import java.util.ArrayList;

public class Barco extends Thread {

    private ArrayList<Aluno> discentes = new ArrayList<>(4);
    private final int CAPACIDADE = 4;

    /**
     * Irá receber e validar a composição de alunos no barco,
     * Não permitindo que um barco contenha 3 alunos da UFCG ou UEPB e 1 aluno da outra universidade.
     * Todos os outros arranjos são válidos.
     * @param aluno
     */
    public boolean embarcar(Aluno aluno) {
        synchronized (discentes) {
            boolean ehUltimoDiscente = discentes.size() == 3;
            if (ehUltimoDiscente) {
                long alunosUFCG = discentes.stream().filter(d -> d.getUniversidade() == UniversidadeEnum.UFCG).count();
                long alunosUEPB = 3 - alunosUFCG;

                boolean ehAlunoDaUFCG = aluno.getUniversidade() == UniversidadeEnum.UFCG;
                boolean ehAlunoDaUEPB = !ehAlunoDaUFCG;

                if (alunosUEPB == 3 && ehAlunoDaUFCG) {
                    System.out.println("Barco com 3 alunos da UEPB, não pode receber aluno UFCG.");
                } else if (alunosUFCG == 3 && ehAlunoDaUEPB) {
                    System.out.println("Barco com 3 alunos da UFCG, não pode receber aluno UEPB.");
                } else if (ehAlunoDaUFCG && alunosUFCG == 2) {
                    System.out.println("Caso aluno UFCG entre no barco, configuração inválida, aluno terá que esperar");
                } else if (ehAlunoDaUEPB && alunosUEPB == 2) {
                    System.out.println("Caso aluno UEPB entre no barco, configuração inválida, aluno terá que esperar");
                } else {
                    System.out.println(String.format("Aluno %s entrou no barco", aluno.getName()));
                    discentes.add(aluno);
                    return true;
                }
            } else if (discentes.size() < 3) {
                System.out.println(String.format("Aluno %s entrou no barco", aluno.getName()));
                discentes.add(aluno);
                return true;
            }
            return false; // Aluno não pôde entrar no barco
        }
    }

    @Override
    public void run() {
        while (true) {
            synchronized (discentes) {
                if (discentes.size() < CAPACIDADE) {
                    System.out.println("Barco não atingiu capacidade, está esperando mais discentes");
                } else if (discentes.size() == CAPACIDADE) {
                    System.out.println("Barco atingiu a capacidade, irá atravessar o açude");
                    discentes.get(0).rema();
                    System.out.println("Barco chegou no outro lado do rio, e começara do outro lado do açude novamente");
                    for (Aluno estudante: discentes) {
                        estudante.setAtravessou(true);
                    };
                    discentes.clear();
                }
            }

            try {
                Thread.sleep(2000 + (int)(Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
