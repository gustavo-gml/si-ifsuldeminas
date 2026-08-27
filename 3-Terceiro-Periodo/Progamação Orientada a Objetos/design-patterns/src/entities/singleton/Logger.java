package entities.singleton;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    private static Logger instancia;
    private PrintWriter writer;

    private Logger() {
        try {
            writer = new PrintWriter(new FileWriter("sistema_log.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Logger getInstancia() {
        if (instancia == null) {
            instancia = new Logger();
        }
        return instancia;
    }

    public void registrar(String mensagem) {
        if (writer != null) {
            writer.println(mensagem);
            writer.flush();
        }
    }
}