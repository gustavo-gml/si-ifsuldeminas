import time
import kernel

def pedido(nome, acao):
    print(nome + "\n" + acao + "\n")
    
    time.sleep(3)
    print(kernel.orquestrar("---API---", "Kernel salve o arquivo anotacoes_futeis.txt"))

    time.sleep(1)
    return("O Kernel orquestrou o seu pedido !\n")