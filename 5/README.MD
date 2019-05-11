

### Realize uma comparação de desempenho entre os seguintes pares de estruturas de dados alternativas. Sua resposta deve conter uma descrição dos experimentos realizados, o código para executar os experimentos, bem como o código para analisar os dados experimentais. Alem disso, uma breve descrição dos resultados alcançados com plots que os ilustram.

 * a) ConcurrentHashMap vs Collections.synchronizedMap
 * b) CopyOnWriteArrayList vs Collections.synchronizedList

obs) É importante avaliar as diferenças de desempenho conforme o número de threads aumenta (ou seja, conforme aumenta a conteção). Ainda, é importante avaliar a proporção da carga de trabalho (entre operações que alteram a estrutura de dados e operações que não alteram). Particularmente, o tamanho das estruturar pode ser também um fator importante (é o caso claro de CopyOnWriteArrayList).





