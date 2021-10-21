package Q1;

public class Main {

    public static void main(String[] args) {
        // Uma Thread Carro com capacidade para quatro pessoas
        Carro car = new Carro(4);
        car.start();

        // 100 passageiros buscando um lugar nesse caso
        for (int i = 0; i < 100; i++) {
            Passageiro p = new Passageiro(car);
            p.start();
        }
    }

}
