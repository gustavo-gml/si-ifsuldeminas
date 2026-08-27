import controller.UsuarioController;

public class Main {
    static void main(String[] args){
        UsuarioController controller = new UsuarioController();
        String tipoLogogado1 = "admin"; //simular admin logado
        String tipoLogogado2 = "comum"; //simularusuario normal
        controller.cadastrar("Zezé", "zezinhoreidelas@email.com", "35948506456", tipoLogogado1, "comum"); //Cadastro completo
        controller.cadastrar("Maria", "mariailuminada@gmail.com", "", tipoLogogado1, "comum"); //Telefone vazio
        controller.cadastrar("Zezé", "zezinhoreidelas@gmail.com", "35948506456", tipoLogogado1, "comum");//Email cadastrado
        controller.cadastrar("Gabinho", "gabinhoiluminado@gmail.com", null, tipoLogogado1, "comum"); //Telefone null
        controller.cadastrar("Saulinho", "saulinhoreidetodosgmail.com", null, tipoLogogado1, "comum"); //Sem '@'
        controller.cadastrar("Saulinho", "saulinhoreidetodos@gmailcom", null, tipoLogogado1, "comum"); //Sem '.' depois do'@'

        System.out.print("\n");
        controller.listar();
        System.out.print("\n");
        controller.buscar("gabinhoiluminado@gmail.com"); //busca por email
        System.out.print("\n\n");

        //cadastrando sem acesso de admin
        controller.cadastrar("Saulinho", "saulinhoreidetodos@gmail.com", null, tipoLogogado2, "comum");
    }
}