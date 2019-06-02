

### Realize uma comparação de desempenho entre os seguintes pares de estruturas de dados alternativas. Sua resposta deve conter uma descrição dos experimentos realizados, o código para executar os experimentos, bem como o código para analisar os dados experimentais. Alem disso, uma breve descrição dos resultados alcançados com plots que os ilustram.

 * a) ConcurrentHashMap vs Collections.synchronizedMap
 * b) CopyOnWriteArrayList vs Collections.synchronizedList

obs) É importante avaliar as diferenças de desempenho conforme o número de threads aumenta (ou seja, conforme aumenta a conteção). Ainda, é importante avaliar a proporção da carga de trabalho (entre operações que alteram a estrutura de dados e operações que não alteram). Particularmente, o tamanho das estruturar pode ser também um fator importante (é o caso claro de CopyOnWriteArrayList).

# Resolução

## Metodologia

A metodologia utitlizada tanto para o item A quanto para o item B a foi a mesma.

### Testes

Inicialmente foram criadas classes que implementam a interface Runnable do Java. Tais classes recebiam como parâmetro em seus construtores
* A instancia da coleção concorrente onde os testes seriam executados
* A refência de um Latch utilizado para garantir a execução completa de todas as threads
* Um id para designar a thread
* A quantidade total de operações de inserção, remoção e leitura a serem realizadas
* Conjuntos sob os quais são retirados os parametros para a realização de inserções, remoções e leituras

Em seguida, criamos classes de execução dos testes nas coleções, uma classe para cada item da questão, [Q05A.java](./Q05A.java) e [Q05B.java](./Q05B.java).

Essas classes recebem como parametro (via linha de comando)
* Um número que representa a coleção a ser utitlizada no teste
* O número de operações de leitura a serem realizadas
* O número de operações de escrita a serem realizadas
* O número máximo de threads simultaneas a serem utilizadas no teste

Cada teste então foi executado isoladamente, para evitar que os 'restos' das operações anteriores não intereferissem no resultado dos testes seguintes.

Sob tais condições, os seguintes testes foram executados para cada uma das 4 coleções
* 50% de operações de leitura e 50% de operações de escrita
* 90% de operações de leitura e 10% de operações de escrita
* 10% de operações de leitura e 90% de operações de escrita

Cada teste foi executado para um número máximo de threads variando de 1 à 60.

[As saídas para os testes executados podem ser encontradas aqui](./outputs).

### Gráficos

Nos gráficos escolhidos para visualizar os resultados dos testes para essa questão cada ponto indica no eixo `x` o tempo de execução do teste em nanosegundos e no eixo `y` a quantidade de threads criadas para o teste. Também plotamos uma curva de tendência dos resuldos baseados nos pontos obtidos do experimento. A cor de cada ponto de da linha de tendencia indica a coleção utilizada no teste e o número em negrito no canto superior esquerdo de cada gráfico mostra no número de operações realizadas em cada um dos testes.

## Resultados

### A) ConcurrentHashMap vs Collections.synchronizedMap

#### Gráfico para o teste que utiliza 50% de operações de leitura e 50% de operações de escrita

![5050](https://raw.githubusercontent.com/dalesEwerton/PC-Lista1/master/assets/img/ConcurrentHashMap%20vs%20SynchronizeMap%2050%25R%2050%25W.png?token=AE7ADSC476FEFD6BPXICO5S47GLMG)
[Clique para acessar gráfico com qualidade e de forma interativa no Flourish](https://public.flourish.studio/visualisation/394146/)

#### Gráfico para o teste que utiliza 10% de operações de leitura e 90% de operações de escrita

![1090](https://raw.githubusercontent.com/dalesEwerton/PC-Lista1/master/assets/img/ConcurrentHashMap%20vs%20SynchronizeMap%2010%25R%2090%25W%20.png?token=AE7ADSDIQJXIFMXFN4D3CP247GLKK)
[Clique para acessar gráfico com qualidade e de forma interativa no Flourish](https://public.flourish.studio/visualisation/394174/)

#### Gráfico para o teste que utiliza 90% de operações de leitura e 10% de operações de escrita

![9010](https://raw.githubusercontent.com/dalesEwerton/PC-Lista1/master/assets/img/ConcurrentHashMap%20vs%20SynchronizeMap%2090%25R%2010%25W%20.png?token=AE7ADSB2V6DTLSDINTVKFQ247GLOC)
[Clique para acessar gráfico com qualidade e de forma interativa no Flourish](https://public.flourish.studio/visualisation/394177/)

#### Conclusões sobre o item A


###  B) CopyOnWriteArrayList vs Collections.synchronizedList

#### Gráfico para o teste que utiliza 50% de operações de escrita e 50% de operações de leitura
![5050B](https://raw.githubusercontent.com/dalesEwerton/PC-Lista1/master/assets/img/CopyOnWriteArrayList%20vs%20SynchronizeList%2050%25R%2050%25W%20.png?token=AE7ADSBAD3EI4CIORQWISVC47RKQG)
[Clique para acessar gráfico com qualidade e de forma interativa no Flourish](https://public.flourish.studio/visualisation/397240/)

#### Gráfico para o teste que utiliza 10% de operações de leitura e 90% de operações de escrita
![1090B](https://raw.githubusercontent.com/dalesEwerton/PC-Lista1/master/assets/img/CopyOnWriteArrayList%20vs%20SynchronizeList%2010%25R%2090%25W%20.png?token=AE7ADSFYOGMZKM4MFN3YW6S47RKW6)
[Clique para acessar gráfico com qualidade e de forma interativa no Flourish](https://public.flourish.studio/visualisation/397241/)


#### Gráfico para o teste que utiliza 90% de operações de leitura e 10% de operações de escrita
![9010B](https://raw.githubusercontent.com/dalesEwerton/PC-Lista1/master/assets/img/CopyOnWriteArrayList%20vs%20SynchronizeList%2090%25R%2010%25W%20.png?token=AE7ADSCPIBZNDU6X42UH3BC47RKZQ)
[Clique para acessar gráfico com qualidade e de forma interativa no Flourish](https://public.flourish.studio/visualisation/397242/)

#### Gráfico para o teste que utiliza 99% de operações de leitura e 1% de operações de escrita
![9010B](https://raw.githubusercontent.com/dalesEwerton/PC-Lista1/master/assets/img/CopyOnWriteArrayList%20vs%20SynchronizeList%2099%25R%201%25W%20.png?token=AE7ADSCSO4HD7MFWRVKTN4K47RK4O)
[Clique para acessar gráfico com qualidade e de forma interativa no Flourish](https://public.flourish.studio/visualisation/397243/)


#### Conclusões sobre o item B


