package Resposta;

public interface IChannel {
	
	/* Um canal recebe mensagens enviadas por processos (threads) remetentes.
	 * O canal deve ter uma capacidade máxima, ou seja, ao atingir o limite, 
	 * novas mensagens não podem ser enviadas para o canal imediatamente.
	 * 
	 */
	
	public void putMessage(String message); 
	
	/*
	 * Processos recipientes lêem as mensagens enviadas no canal.
	 * Mensagens devem ser lidas na ordem que entraram no canal. 
	 * Uma vez lida, a mensagem não pode ser lida novamente.
	 */
	
	public String takeMessage();

}
