

### Considere um programa com duas funções principais indicadas abaixo:

 * int gateway(int num_replicas)

 * int request()

A função request deve sortear um número aleatório entre 1 e 30, dormir por uma quantidade de segundos dada pelo número aleatório sorteado, e retornar o valor do número. Por sua vez, a função gateway deve iniciar num_replicas threads, e cada thread executará a função request. 

Escreva, em Java e em C, uma versão do programa em que a função gateway retorna o valor da primeira thread que termine a execução da função request (sem que seja necessário esperar as demais threads terminarem).
Escreva, em Java e em C, ma versão do programa em que a função gateway retorna a soma dos valores retornados na execução da função request por todas as réplicas criadas.

obs 1) Pequenas mudanças na API indicada podem ser realizadas, para que seja possível adaptar o código para as linguagens indicadas. Contacte o professor caso queira realizar alguma mudança.

obs 2) Em Java, não use nenhuma biblioteca além da SDK. Também, não considere nenhum mecanismo avançado de controle de concorrência (somente as classes/interfaces Runnable e Thread, bem como as primitivas synchronized, volatile  e wait/notify). Em C, use pthreads.

#### Comando utilizado para compilação (em C) no Linux

```
gcc -pthread -o solucao solucao.c
``` 

#### Soluções da questão

* O código da solução da letra _a_ em C encontra-se [aqui](https://github.com/dalesEwerton/PC-Lista1/blob/master/3/solucaoAC.c)
* O código da solução da letra _b_ em C encontra-se [aqui](https://github.com/dalesEwerton/PC-Lista1/blob/master/3/solucaoBC.c)
* O código da solução da letra _a_ em Java encontra-se [aqui](https://github.com/dalesEwerton/PC-Lista1/tree/master/3/Questao-3/src/respostaA)
* O código da solução da letra _b_ em Java encontra-se [aqui](https://github.com/dalesEwerton/PC-Lista1/tree/master/3/Questao-3/src/respostaB)




