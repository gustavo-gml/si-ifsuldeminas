email = input("Digite seu email: ")
senha = input("Digite sua senha")

if email == "agente007":
    print("Passou na primeira fase")
    if senha == "1234":
        print("Bem vindo !")
    else:
        print("Senha incorreta !")

else:
    print("Email incorreto !")