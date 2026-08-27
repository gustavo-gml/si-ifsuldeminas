import time
import hardware
def gerenciar(nome, acao):
    print(nome + "\n" + acao + "\n")
    
    time.sleep(3)
    print("Ger. Processos: processo na fila (nº12)!")
    time.sleep(1)
    print("Ger. Memória: memória para a ação alocada !")
    time.sleep(1)
    print("Ger. arquivos: endereço de salvamento anotado (xy124)!\n")
    time.sleep(1)

    print(hardware.salvar("processo nº12","salvar","xy124","anotacoes_futeis.txt"))

    time.sleep(1)

    return("Hardware confirmou a gravação do arquivo!\n")