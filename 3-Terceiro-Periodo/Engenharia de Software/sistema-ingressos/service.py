import re
from datetime import datetime
from repository import UsuarioRepository, IngressoRepository, CompraRepository
from models import Usuario, Ingresso, CompraIngresso


class AuthService:
    def __init__(self):
        self.repo = UsuarioRepository()
        self.usuario_logado = None

    def login(self, email, senha):
        usuario = self.repo.find_by_email(email)
        if usuario and usuario.senha == senha:
            self.usuario_logado = usuario
            return True
        return False

    def logout(self):
        self.usuario_logado = None


class SistemaService:
    def __init__(self):
        self.usuario_repo = UsuarioRepository()
        self.ingresso_repo = IngressoRepository()
        self.compra_repo = CompraRepository()

    # --- VALIDAÇÕES DE SEGURANÇA ---
    def validar_email(self, email):
        padrao = r'^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$'
        if not re.match(padrao, email):
            raise Exception("❌ Formato de email inválido!")

    # --- GERENCIAMENTO DE USUÁRIOS ---
    def cadastrar_usuario(self, nome, email, senha, tipo='cliente'):
        self.validar_email(email)
        if len(senha) < 4:
            raise Exception("❌ A senha deve ter pelo menos 4 caracteres!")
        if self.usuario_repo.find_by_email(email):
            raise Exception("❌ Este email já está cadastrado!")

        novo_usuario = Usuario(nome=nome, email=email, senha=senha, tipo=tipo)
        return self.usuario_repo.create(novo_usuario)

    def listar_usuarios(self):
        return self.usuario_repo.find_all()

    def buscar_usuario_por_id(self, id_u):
        return self.usuario_repo.find_by_id(id_u)

    def atualizar_usuario(self, id_u, nome=None, email=None, senha=None, tipo=None):
        u = self.usuario_repo.find_by_id(id_u)
        if not u: raise Exception("Usuário não encontrado.")

        if email and email != u.email:
            self.validar_email(email)
            if self.usuario_repo.find_by_email(email):
                raise Exception("Email já em uso.")

        if nome: u.nome = nome
        if email: u.email = email
        if senha: u.senha = senha
        if tipo: u.tipo = tipo
        return self.usuario_repo.update(u)

    def deletar_usuario(self, id_u):
        return self.usuario_repo.delete(id_u)

    # --- GERENCIAMENTO DE INGRESSOS ---
    def cadastrar_ingresso(self, evento, preco, quantidade, data_br, organizador_id):
        try:
            data_obj = datetime.strptime(data_br, "%d/%m/%Y %H:%M")
            if data_obj < datetime.now():
                raise Exception("❌ A data não pode ser no passado!")
            data_sql = data_obj.strftime("%Y-%m-%d %H:%M:%S")
        except ValueError:
            raise Exception("❌ Formato inválido! Use DD/MM/YYYY HH:MM")

        if preco <= 0 or quantidade <= 0:
            raise Exception("❌ Valores devem ser maiores que zero.")

        novo = Ingresso(
            evento=evento, preco=preco,
            quantidade_disponivel=quantidade,
            data_evento=data_sql,
            organizador_id=organizador_id
        )
        return self.ingresso_repo.create(novo)

    def listar_todos_ingressos(self):
        return self.ingresso_repo.find_all()

    def listar_meus_ingressos(self, org_id):
        return self.ingresso_repo.find_by_organizador(org_id)

    def buscar_ingresso_por_id(self, id_i):
        return self.ingresso_repo.find_by_id(id_i)

    def atualizar_ingresso(self, id_i, evento=None, preco=None, quantidade=None, data_br=None):
        ing = self.ingresso_repo.find_by_id(id_i)
        if not ing: raise Exception("Ingresso não encontrado.")

        if data_br:
            dt_obj = datetime.strptime(data_br, "%d/%m/%Y %H:%M")
            if dt_obj < datetime.now(): raise Exception("Nova data no passado.")
            ing.data_evento = dt_obj.strftime("%Y-%m-%d %H:%M:%S")

        if evento: ing.evento = evento
        if preco is not None: ing.preco = preco
        if quantidade is not None: ing.quantidade_disponivel = quantidade
        return self.ingresso_repo.update(ing)

    def deletar_ingresso(self, id_i):
        return self.ingresso_repo.delete(id_i)

    # --- COMPRAS E RELATÓRIOS ---
    def realizar_compra(self, u_id, i_id, qtd):
        ing = self.ingresso_repo.find_by_id(i_id)
        if not ing or ing.quantidade_disponivel < qtd:
            raise Exception("❌ Estoque insuficiente ou evento inexistente.")

        valor = float(ing.preco) * int(qtd)
        compra = CompraIngresso(usuario_id=u_id, ingresso_id=i_id, quantidade=qtd, valor_total=valor)
        return self.compra_repo.create(compra)

    def buscar_compras_por_usuario(self, u_id):
        return self.compra_repo.find_by_usuario_id(u_id)

    def obter_maiores_publicos(self):
        return self.compra_repo.get_top_publicos()

    def obter_maiores_compradores(self):
        return self.compra_repo.get_top_compradores()