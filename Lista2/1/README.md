### Questão 01 - Considere um programa com duas funções principais indicadas abaixo:

int gateway(int num_replicas)

int request()

A função request deve sortear um número aleatório entre 1 e 30, dormir por uma quantidade de segundos dada pelo número aleatório sorteado, e retornar o valor do número. Por sua vez, a função gateway deve iniciar num_replicas goroutines, e cada thread goroutines a função request. 

a) Escreva uma versão do programa em que a função gateway retorna o valor da primeira goroutine que termine a execução da função request (sem que seja necessário esperar as demais goroutines terminem).

b) Escreva uma versão do programa em que a função gateway retorna a soma dos valores retornados na execução da função request por todas as réplicas criadas.
