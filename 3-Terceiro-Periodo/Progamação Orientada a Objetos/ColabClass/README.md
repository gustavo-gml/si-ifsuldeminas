# 🎓 ColabClass

O **ColabClass** é uma plataforma colaborativa para organização acadêmica de turmas, permitindo centralizar disciplinas, eventos, avisos e links úteis em um único mural.

## ✨ Funcionalidades

### 👥 Usuários

* Login com Spring Security
* Perfis de acesso:

  * Administrador
  * Representante
  * Aluno
* Perfil do usuário
* Controle de permissões

### 🏛 Turmas

* Cadastro de turmas
* Edição e exclusão
* Acesso ao mural por código
* Mural específico para cada turma

### 📚 Disciplinas

* Cadastro, edição e exclusão
* Professor responsável
* Email do professor
* Horário
* Código do Google Classroom

### 📅 Eventos

* Cadastro de provas, trabalhos e outros eventos
* Edição e exclusão
* Ordenação por data

### 📢 Avisos

* Cadastro, edição e exclusão
* Avisos específicos por turma

### 🔗 Links Rápidos

* Cadastro de links úteis
* Edição e exclusão

### 🔔 Interface

* Dashboard do administrador
* Navbar com perfil e logout
* Toasts de confirmação
* Confirmação antes de excluir registros
* Interface responsiva com Bootstrap

---

# 🛠 Tecnologias Utilizadas

* Java 21
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* Thymeleaf
* Bootstrap 5
* MySQL
* Maven

---

# 📂 Estrutura do Projeto

```
src
 ├── main
 │    ├── java
 │    │     ├── config
 │    │     ├── controller
 │    │     ├── dto
 │    │     ├── model
 │    │     ├── repository
 │    │     └── service
 │    └── resources
 │          ├── static
 │          └── templates
```

---

# ⚙ Configuração do Banco de Dados

Crie um banco MySQL:

```sql
CREATE DATABASE colabclass;
```

Configure o arquivo:

```
src/main/resources/application.properties
```

Exemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/colabclass
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

# ▶ Executando o Projeto

Clone o repositório:

```bash
git clone https://github.com/seu-usuario/ColabClass.git
```

Entre na pasta:

```bash
cd ColabClass
```

Execute:

```bash
mvn spring-boot:run
```

Acesse:

```
http://localhost:8080
```

---

# 🔒 Perfis de Usuário

## Administrador

* Gerenciar usuários
* Gerenciar turmas
* Visualizar dashboard
* Acesso total ao sistema

## Representante

* Gerenciar eventos
* Gerenciar avisos
* Gerenciar disciplinas
* Gerenciar links

## Aluno

* Visualizar mural da turma

---

# 📌 Próximas Melhorias

* Alteração de senha
* Dashboard mais completo
* Modal Bootstrap para exclusão
* Calendário acadêmico
* Upload de arquivos
* Notificações avançadas
* Integração com Google Classroom

---

# 👨‍💻 Autor

Ágabo M.

Projeto desenvolvido para fins acadêmicos utilizando Spring Boot e MySQL.
