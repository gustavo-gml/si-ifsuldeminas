import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Sistema de Cadastro (Versão com Apache Ivy) ===\n");

        try {
            Map<String, Object> usuario = new HashMap<>();
            usuario.put("nome", "João Ivy");
            usuario.put("idade", 30);
            usuario.put("cargo", "Arquiteto de Software");

            ObjectMapper mapper = new ObjectMapper();
            File arquivoDeSaida = new File("dados_usuario_ivy.json");

            mapper.writerWithDefaultPrettyPrinter().writeValue(arquivoDeSaida, usuario);

            System.out.println("Sucesso Mágico! O arquivo 'dados_usuario_ivy.json' foi gerado.");

        } catch (Exception e) {
            System.out.println("Erro dramático: As bibliotecas não estão aqui!");
            e.printStackTrace();
        }
    }
}