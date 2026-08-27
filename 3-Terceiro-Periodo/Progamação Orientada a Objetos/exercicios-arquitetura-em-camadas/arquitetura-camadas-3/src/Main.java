import controller.AlunoController;

public class Main{
    static void main(String[] args){
        AlunoController controller = new AlunoController();
        controller.cadastrar("Ana" , 8.5, "101");
        controller.cadastrar("Bruno" , 11.0, "102");
        controller.cadastrar("Carlos" , 7.0, "101");
        controller.cadastrar("Daniela" , 9.0, "103");
        controller.listar();
        controller.media();
    }
}