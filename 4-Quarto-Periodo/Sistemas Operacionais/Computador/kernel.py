import time
import gerenciadores

def orquestrar(nome, acao):
    print(nome + "\n" + acao + "\n")
    
    time.sleep(3)
    print(gerenciadores.gerenciar("---Kernel---","Gerenciadores, salvem esse arquivo"))

    time.sleep(1)

    return("Gerenciadores Ja encaminharam o seu chamado!\n")