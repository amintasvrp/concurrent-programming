### Questão 01

Considere um programa com duas funções principais indicadas abaixo:

* int gateway(int num_replicas)

* int request()

A função request deve sortear um número aleatório entre 1 e 30, dormir por uma quantidade de segundos dada pelo número aleatório sorteado, e retornar o valor do número. Por sua vez, a função gateway deve iniciar num_replicas goroutines, e cada thread goroutines a função request. 

a) Escreva uma versão do programa em que a função gateway retorna o valor da primeira goroutine que termine a execução da função request (sem que seja necessário esperar as demais goroutines terminem).

b) Escreva uma versão do programa em que a função gateway retorna a soma dos valores retornados na execução da função request por todas as réplicas criadas.

obs 1) Pequenas mudanças na API indicada podem ser realizadas, para que seja possível adaptar o código para a linguagem indicada. Contacte o professor caso queira realizar alguma mudança.

* A resposta da letra _a)_ pode ser encontrada [aqui](https://github.com/dalesEwerton/PC-Lista1/blob/master/Lista2/1/1a/questao1a.go)
* A resposta de letra _b)_ pode ser encontrada [aqui](https://github.com/dalesEwerton/PC-Lista1/blob/master/Lista2/1/2a/questao1b.go)