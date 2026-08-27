import sys
import os
from service import AuthService, SistemaService

try:
    from tabulate import tabulate
except ImportError:
    def tabulate(data, headers=None, tablefmt=None):
        return str(headers) + "\n" + str(data)


class InterfaceTerminal:
    def __init__(self):
        self.auth_service = AuthService()
        self.sistema_service = SistemaService()

    def limpar_tela(self):
        os.system('cls' if os.name == 'nt' else 'clear')

    def exibir_titulo(self, texto):
        print("\n" + "=" * 65)
        print(f"{texto.center(65)}")
        print("=" * 65 + "\n")

    def menu_principal(self):
        while True:
            self.limpar_tela()
            print("💡 DICA: Use 'Ctrl +' ou 'Ctrl -' para ajustar o zoom do terminal.")
            self.exibir_titulo("SISTEMA DE INGRESSOS - ACESSO LOCALHOST")
            print("1. Login")
            print("2. Cadastro de Cliente")
            print("3. Cadastro de Organizador")
            print("0. Sair")

            op = input("\nEscolha: ")
            if op == '1':
                self.tela_login()
            elif op == '2':
                self.tela_cadastro_usuario('cliente')
            elif op == '3':
                self.tela_cadastro_usuario('organizador')
            elif op == '0':
                sys.exit()

    def tela_login(self):
        self.limpar_tela();
        self.exibir_titulo("LOGIN")
        email = input("Email: ");
        senha = input("Senha: ")
        if self.auth_service.login(email, senha):
            self.menu_logado()
        else:
            input("\n❌ Credenciais incorretas! [Enter]")

    def tela_cadastro_usuario(self, tipo):
        self.limpar_tela();
        self.exibir_titulo(f"CADASTRO DE {tipo.upper()}")
        n = input("Nome: ");
        e = input("Email: ");
        s = input("Senha: ")
        try:
            self.sistema_service.cadastrar_usuario(n, e, s, tipo)
            input("\n✅ Cadastro realizado com sucesso! [Enter]")
        except Exception as err:
            input(f"\nErro: {err} [Enter]")

    def menu_logado(self):
        while self.auth_service.usuario_logado:
            u = self.auth_service.usuario_logado
            self.limpar_tela();
            self.exibir_titulo(f"PAINEL: {u.nome.upper()} ({u.tipo.upper()})")
            if u.tipo == 'admin':
                self.menu_admin()
            elif u.tipo == 'organizador':
                self.menu_organizador()
            else:
                self.menu_cliente()

    def menu_admin(self):
        print("--- INGRESSOS ---           --- USUÁRIOS ---")
        print("1. Listar / Novo            5. Listar Usuários")
        print("2. Atualizar Ingresso       6. Atualizar Usuário")
        print("3. Deletar Ingresso         7. Deletar Usuário")
        print("--- RELATÓRIOS ---          ----------------")
        print("4. Relatórios Gerais        0. Logout")

        op = input("\nOpção: ")
        if op == '1':
            self.tela_listar_ingressos();
            conf = input("\nDeseja cadastrar novo? (s/n): ")
            if conf.lower() == 's': self.tela_cadastro_ingresso()
        elif op == '2':
            self.tela_atualizar_ingresso()
        elif op == '3':
            self.tela_deletar_ingresso()
        elif op == '4':
            self.tela_relatorio_publicos(); self.tela_relatorio_compradores()
        elif op == '5':
            self.tela_listar_usuarios(); input("\n[Enter]")
        elif op == '6':
            self.tela_atualizar_usuario()
        elif op == '7':
            self.tela_deletar_usuario()
        elif op == '0':
            self.auth_service.logout()

    def menu_organizador(self):
        print("1. Criar Novo Evento")
        print("2. Listar Meus Eventos")
        print("3. Editar Meu Evento")
        print("4. Relatório de Vendas")
        print("5. Ranking de Compradores")
        print("0. Logout")

        op = input("\nOpção: ")
        if op == '1':
            self.tela_cadastro_ingresso()
        elif op == '2':
            self.tela_listar_ingressos(apenas_meus=True); input("\n[Enter]")
        elif op == '3':
            self.tela_atualizar_ingresso(apenas_meus=True)
        elif op == '4':
            self.tela_relatorio_publicos()
        elif op == '5':
            self.tela_relatorio_compradores()
        elif op == '0':
            self.auth_service.logout()

    def menu_cliente(self):
        print("1. Ver Eventos e Comprar")
        print("2. Meus Ingressos Comprados")
        print("0. Logout")

        op = input("\nOpção: ")
        if op == '1':
            self.tela_comprar_ingresso()
        elif op == '2':
            self.tela_meus_ingressos()
        elif op == '0':
            self.auth_service.logout()

    # --- TELAS DE INGRESSOS ---
    def tela_listar_ingressos(self, apenas_meus=False):
        self.limpar_tela()
        u = self.auth_service.usuario_logado
        ings = self.sistema_service.listar_meus_ingressos(
            u.id) if apenas_meus else self.sistema_service.listar_todos_ingressos()
        if ings:
            # RENOMEADO: Quantidade
            data = [[i.id, i.evento, f"R${float(i.preco):.2f}", i.quantidade_disponivel, i.data_evento] for i in ings]
            print(tabulate(data, headers=["ID", "Evento", "Preço", "Quantidade", "Data"], tablefmt="grid"))
            return ings
        print("Nenhum evento encontrado.");
        return []

    def tela_cadastro_ingresso(self):
        self.limpar_tela();
        self.exibir_titulo("NOVO EVENTO")
        n = input("Nome do Evento: ")
        try:
            p = float(input("Preço: "));
            q = int(input("Quantidade: "))
            d = input("Data (DD/MM/YYYY HH:MM): ")
            self.sistema_service.cadastrar_ingresso(n, p, q, d, self.auth_service.usuario_logado.id)
            input("\n✅ Evento criado! [Enter]")
        except Exception as e:
            input(f"\n❌ Erro: {e} [Enter]")

    def tela_atualizar_ingresso(self, apenas_meus=False):
        ings = self.tela_listar_ingressos(apenas_meus)
        if not ings: return input("\n[Enter]")
        try:
            id_i = int(input("\nID para editar: "))
            if apenas_meus and id_i not in [x.id for x in ings]:
                return input("\n❌ Erro: ID inválido ou sem permissão. [Enter]")

            i = self.sistema_service.buscar_ingresso_por_id(id_i)
            print(f"\nEditando: {i.evento} (Vazio para manter)")
            n = input("Novo nome: ");
            p = input("Novo preço: ")
            q = input("Nova quantidade: ");
            d = input("Nova data: ")

            self.sistema_service.atualizar_ingresso(id_i, evento=n if n else None,
                                                    preco=float(p) if p else None, quantidade=int(q) if q else None,
                                                    data_br=d if d else None)
            input("\n✅ Atualizado! [Enter]")
        except Exception as e:
            input(f"\n❌ Erro: {e} [Enter]")

    def tela_deletar_ingresso(self):
        ings = self.tela_listar_ingressos()
        if not ings: return input("\n[Enter]")
        try:
            id_i = int(input("\nID do evento para DELETAR: "))
            self.sistema_service.deletar_ingresso(id_i)
            input("\n✅ Evento removido! [Enter]")
        except Exception as e:
            input(f"\n❌ Erro: {e} [Enter]")

    # --- TELAS DE USUÁRIOS ---
    def tela_listar_usuarios(self):
        self.limpar_tela();
        self.exibir_titulo("LISTA DE USUÁRIOS")
        us = self.sistema_service.listar_usuarios()
        data = [[u.id, u.nome, u.email, u.tipo] for u in us]
        print(tabulate(data, headers=["ID", "Nome", "Email", "Tipo"], tablefmt="grid"))

    def tela_atualizar_usuario(self):
        self.tela_listar_usuarios()
        try:
            id_u = int(input("\nID do usuário para editar: "))
            u = self.sistema_service.buscar_usuario_por_id(id_u)
            if not u: return input("\n❌ Não encontrado. [Enter]")

            n = input(f"Novo nome [{u.nome}]: ")
            e = input(f"Novo email [{u.email}]: ")
            t = input(f"Novo tipo (admin/cliente/organizador) [{u.tipo}]: ")

            self.sistema_service.atualizar_usuario(id_u, nome=n if n else None, email=e if e else None,
                                                   tipo=t if t else None)
            input("\n✅ Usuário atualizado! [Enter]")
        except Exception as err:
            input(f"\n❌ Erro: {err} [Enter]")

    def tela_deletar_usuario(self):
        self.tela_listar_usuarios()
        try:
            id_u = int(input("\nID do usuário para DELETAR: "))
            if id_u == self.auth_service.usuario_logado.id: return input("\n❌ Você não pode se deletar! [Enter]")
            self.sistema_service.deletar_usuario(id_u)
            input("\n✅ Usuário removido! [Enter]")
        except Exception as err:
            input(f"\n❌ Erro: {err} [Enter]")

    # --- COMPRAS E RELATÓRIOS ---
    def tela_comprar_ingresso(self):
        ings = self.tela_listar_ingressos()
        if not ings: return input("\n[Enter]")
        try:
            id_i = int(input("\nID do Ingresso: "));
            qtd = int(input("Quantidade: "))
            self.sistema_service.realizar_compra(self.auth_service.usuario_logado.id, id_i, qtd)
            input("\n✅ Compra realizada com sucesso! [Enter]")
        except Exception as e:
            input(f"\n❌ Erro: {e} [Enter]")

    def tela_meus_ingressos(self):
        self.limpar_tela();
        self.exibir_titulo("MINHAS COMPRAS")
        c = self.sistema_service.buscar_compras_por_usuario(self.auth_service.usuario_logado.id)
        if c:
            print(tabulate(c, headers="keys", tablefmt="grid"))
        else:
            print("Nenhuma compra encontrada.")
        input("\n[Enter]")

    def tela_relatorio_publicos(self):
        self.limpar_tela();
        self.exibir_titulo("EVENTOS MAIS VENDIDOS")
        print(tabulate(self.sistema_service.obter_maiores_publicos(), headers=["Evento", "Vendas"], tablefmt="grid"))
        input("\n[Enter para continuar]")

    def tela_relatorio_compradores(self):
        self.limpar_tela();
        self.exibir_titulo("TOP 10 COMPRADORES")
        print(tabulate(self.sistema_service.obter_maiores_compradores(), headers=["Nome", "Qtd", "Total"],
                       tablefmt="grid"))
        input("\n[Enter para continuar]")


if __name__ == "__main__":
    InterfaceTerminal().menu_principal()