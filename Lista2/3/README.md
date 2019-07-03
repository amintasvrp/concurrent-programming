### Questão 03

Avalie experimentalmente quão leve goroutines em Go são em relação à threads em Java. Em particular, você deve avaliar o consumo de memória de programas equivalentes nas respectivas linguagens.

# Resolução

## Metodologia

### Implementação da Tarefa

Para desenvolver o experimento, idealizamos uma tarefa que deveria ser implementada nas duas linguagens, go e java, de forma a medir o consumo de memória das Threads e Goroutines. A tarefa escolhida foi a execução de `n` threads/goroutines que iteravam em um comando `for` por 100 vezes e a cada iteração dormiam por 0,1 segundo. Dessa forma, garantimos que as tarefas realizadas não são grande fonte de consumo de memória, o que nos auxilia a medir o consumo de memória por parte das threads/goroutines com maior precisão.

[Clique para ver a implementação em GO da tarefa](https://github.com/dalesEwerton/PC-Lista1/blob/master/Lista2/3/MultiCounter.go)

[Clique para ver a classe Java que implementa a tarefa](https://github.com/dalesEwerton/PC-Lista1/blob/master/Lista2/3/MultiCounter.java)

[Clique para ver a classe Java que executa a tarefa](https://github.com/dalesEwerton/PC-Lista1/blob/master/Lista2/3/RunCounter.java)

Tanto a implementação em `go` quanto a implementação em `java` recebem como argumento o número de threads/goroutines que devem ser criadas para executar a tarefa.

### Execução dos Testes

A execução dos testes foi realizada da seguinte forma:

* Foi criado um Shell Script para iterar na sequência `{1..5000..5}`
* O indice da sequência foi passado como parâmetro de execução da tarefa em cada iteração

[Clique para ver o Shell Script que executa os testes para Go](https://github.com/dalesEwerton/PC-Lista1/blob/master/Lista2/3/rungo.sh)

[Clique para ver o Shell Script que executa os testes para Java](https://github.com/dalesEwerton/PC-Lista1/blob/master/Lista2/3/runjava.sh)

## Resultados

### Tendência de crescimento do consumo de memória

O gráfico abaixo mostra o consumo de memória máximo **(em Kilobytes)** de cada execução para threads e goroutines
![maximo](https://raw.githubusercontent.com/dalesEwerton/PC-Lista1/master/Lista2/3/outputs/Threads%20vs%20Goroutines%20-%20Consumo%20de%20memoria%20maximo.png?token=AE7ADSDQLOCR2H2CR5HR2VC5EUUNG)

[Também é possível ver o gráfico com mais qualidade no Flourish](https://public.flourish.studio/visualisation/460244/)

Nele podemos observar que, como esperado, o consumo de memória das Goroutines é sempre inferior ao das Threads do Java.
Algo que nos chamou atenção e que pode ser observado no gráfico é o fato do consumo máximo de memoria das Threads em Java permanecer constance para um determinado intervalo de Threads, diferente das Goroutines, onde o consumo máximo cresce de forma linear na medida em que seus números aumentam.

O gráfico abaixo exibe o consumo de memória médio **(em Kilobytes)** para cada execução. A média foi calculada a partir da coleta da quantidade de memória consumida a cada segundo de execução.
![medio](https://raw.githubusercontent.com/dalesEwerton/PC-Lista1/master/Lista2/3/outputs/Threads%20vs%20Goroutines%20-%20Consumo%20de%20memoria%20medio%402x.png?token=AE7ADSHQTGHZBZRCBMRWOSC5EUVMU)

[Também é possível ver o gráfico com mais qualidade no Flourish](https://public.flourish.studio/visualisation/460238/)

Ainda que de forma mais assimetrica, é possível observar nesse gráfico os mesmos padrões observados no gŕafico anterior

### Dispersão observada no custo de memória por Thread/Goroutine

O gráfico abaixo exibe a dispersão do consumo de memória **(em Kilobytes)** por Thread/Goroutine durante a execução do experimento
![disp](https://raw.githubusercontent.com/dalesEwerton/PC-Lista1/master/Lista2/3/outputs/Gr%C3%A1fico%20de%20dispers%C3%A3o%20do%20custo%20de%20mem%C3%B3ria%20por%20thread_goroutine%402x.png?token=AE7ADSABUJULWXRKZMJ63GS5EUWOO)

[Também é possível ver o gráfico com mais qualidade no Flourish](https://public.flourish.studio/visualisation/460267/)

**OBS: O eixo y está em escala logarítmica para facilitar a visualização**

Os dados para esse gráfico foram gerados a partir dos [resultados dos experimetos](https://github.com/dalesEwerton/PC-Lista1/blob/master/Lista2/3/outputs/results.csv), calculando para cada execução o valor consumido de memoria dividido pelo número de Threads/Goroutines da execução, desconsiderando o valor da execução inicial e podem ser encontrados [nesse arquivo](https://github.com/dalesEwerton/PC-Lista1/blob/master/Lista2/3/outputs/cost.csv).

Podemos notar que a dispersão de consumo de memória das Goroutines, coluna da esquerda no gŕafico, foi bem menos acentuado do que a dispersão das Threads de Java.
Com exeção dos `outliers`, o custo de memória por Goroutine nos experimentos variou entre **0.6Kb** e **0.7Kb**, e entre **0.8Kb** e **2Kb** para Threads.
