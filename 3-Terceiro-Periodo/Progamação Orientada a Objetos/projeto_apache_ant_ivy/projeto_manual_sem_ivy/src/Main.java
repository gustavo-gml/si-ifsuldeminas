import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Sistema de Cadastro (Versão Manual) ===\n");

        try {
            // 1. Criando os dados (talvez trocar para uma classe usuario depois ?)
            Map<String, Object> usuario = new HashMap<>();
            usuario.put("nome", "Maria Silva");
            usuario.put("idade", 28);
            usuario.put("cargo", "Engenheira de Software");

            // 2. Usando o Jackson para criar um arquivo físico
            ObjectMapper mapper = new ObjectMapper();
            File arquivoDeSaida = new File("dados_usuario.json");

            // formata bonito (PrettyPrinter) e salva no arquivo
            mapper.writerWithDefaultPrettyPrinter().writeValue(arquivoDeSaida, usuario);

            System.out.println("Sucesso! Olhe a sua pasta, o arquivo 'dados_usuario.json' foi gerado.");

        } catch (Exception e) {
            System.out.println("Erro: Faltou algum arquivo .jar na pasta lib!");
            e.printStackTrace();
        }
    }
}