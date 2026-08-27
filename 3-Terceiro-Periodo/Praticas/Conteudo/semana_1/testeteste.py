login_digitado = input("Digite o login: ")
senha_digitada = input("Digite a senha: ")

# Nível 1: Verificando o usuário primeiro
if login_digitado == "agente007":
    
    # Nível 2: Só chega aqui se o usuário estiver certo!
    if senha_digitada == "secreto":
        print("Bem-vindo, Agente! Acesso liberado aos arquivos.")
    else:
        print("Acesso bloqueado: Senha incorreta.")

# Else do Nível 1 (Se o usuário estiver errado)
else:
    print("Acesso negado: Usuário desconhecido.")



    