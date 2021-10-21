package Q2;

public class Aluno extends Thread {
    public UniversidadeEnum universidade;
    public boolean atravessou = false;
    public Barco barco;

    public Aluno(boolean ehUfcg, Barco barco) {
        this.universidade = ehUfcg ? UniversidadeEnum.UFCG : UniversidadeEnum.UEPB;
        this.barco = barco;
    }

    public synchronized void rema() {
        System.out.println(String.format("O Aluno %s está remando o barco", getName()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public UniversidadeEnum getUniversidade() {
        return universidade;
    }

    public synchronized void setAtravessou(boolean atravessou) {
        this.atravessou = atravessou;
    }

    @Override
    public void run() {
        boolean conseguiuEmbarcar = false;
        while (!atravessou) {
            synchronized (barco) {
                while (!conseguiuEmbarcar) {
                    conseguiuEmbarcar = barco.embarcar(this);
                }
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Aluno " + getName() + " finalmente atravessou");

    }
}
