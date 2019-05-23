

### Questão 1 - Em sala, mostramos o seguinte trecho de código (desprotegido). Nesse código, três threads incrementam um contador compartilhado. Cada thread realiza 10.000.000 operações de incremento. Modifique o código para que nenhum incremento seja perdido, e, ao fim da execução do programa, o valor do contador seja 30.000.000. Execute experimentos para coletar o tempo de execução de cada uma das duas versões do programa. Explique a razão da diferença de desempenho. Forneça evidências que corroborem sua explicação (no Linux, você pode usar as ferrametnas eBPF e perf, por exemplo).



#### Solução para que nenhum incremento seja perdido

* Como dito no enunciado da questão, as três threads do programa incrementam um contador de forma compartilhada, ou seja, a área do código em que ocorre tal incremento é dita como região crítica, em que ocorrem condições de corrida entre as threads.
* Para que não houvessem condições de corrida, foi preciso pensar em alguma solução que garantisse exclusão mútua entre as threads, ou seja, que apenas uma thread por vez entrasse na região crítica e nenhuma outra que tentasse tal feito obtivesse sucesso. Portanto, utilizou-se um mutex para solucionar o problema, dado que os mutex são feitos para sinalizar quando regiões críticas de código precisam de acesso exclusivo, impedindo que outras threads com a mesma proteção sejam executadas simultaneamente e acessem os mesmos locais de memória.
* O código da solução encontra-se [aqui](https://github.com/dalesEwerton/PC-Lista1/blob/master/1/ThreadsSequenciais.cpp) e as linhas utilizadas para solução foram da _17_ até a _21_. 
* Já o código original encontra-se [aqui](https://github.com/dalesEwerton/PC-Lista1/blob/master/1/ThreadsConcorrentes.cpp).
Foram feitas algumas pequenas mudanças no código disponibilizado pelo professor, para que este executasse nas máquinas dos alunos. Porém, o fluxo e execução original não foram alterados.

![Tempo1](./Images/TempoThreadsConcorrentes.png)
![Tempo2](./Images/TempoThreadsSequenciais.png)


