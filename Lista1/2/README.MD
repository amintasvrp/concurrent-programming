

### Enunciado

Uma abstração bastante usada em programação concorrentes são os canais. Um canal recebe mensagens enviadas por processos (threads) remetentes. Processos recipientes lêem as mensagens enviadas no canal. Mensagens devem ser lidas na ordem que entraram no canal. Uma vez lida, a mensagem não pode ser lida novamente. O canal deve ter uma capacidade máxima, ou seja, ao atingir o limite, novas mensagens não podem ser enviadas para o canal imediatamente. Considere que o construtor do canal recebe um inteiro que indica sua capacidade máxima. Mensagens não podem ser descartadas. Implemente a interface abaixo para o canal, usando quaisquer mecanismos de coordenação e controle de concorrência da linguagem Java, exceto as estruturas de dados de Concurrent Collections. Considere tanto critérios de corretude quanto de eficiência (p.ex evite spin locks quando possível).


#### Interface

```
public interface Channel {
    public void putMessage(String message);
    public String takeMessage();
}
```

#### Solução da questão

O código da solução da questão encontra-se [aqui](https://github.com/dalesEwerton/PC-Lista1/tree/master/2/Questao-2/src/resposta)




