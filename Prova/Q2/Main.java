package Q2;

public class Main {
    public static void main(String[] args) {
        Barco barco = new Barco();
        barco.start();

        for (int i = 0; i < 8; i++) {
            boolean ehUFCG = i % 2 == 0;
            Thread discente = new Aluno(ehUFCG, barco);
            discente.start();
        }
    }
}
