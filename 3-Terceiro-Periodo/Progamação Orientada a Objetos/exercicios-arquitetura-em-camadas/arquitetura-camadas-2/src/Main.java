import controller.ProdutoController;
import model.Produto;

public class Main{
    static void main(String[] args){
        ProdutoController controller = new ProdutoController();

        controller.cadastrar("Notebook", 6000, 10);
        controller.cadastrar("Mouse", 80, 50);
        controller.listar();
        controller.buscar("Teclado");
        controller.buscar("Mouse");

    }
}