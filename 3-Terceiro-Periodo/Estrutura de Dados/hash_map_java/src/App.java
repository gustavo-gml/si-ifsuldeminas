import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        
        HashMap<Integer,String> pessoaHash = new HashMap<>();

        int qtdElementos=1000000;

        long tempoInicialHash = System.currentTimeMillis();

        for(int i=0;i<qtdElementos;i++)
            pessoaHash.put(i, "Pessoa "+i);
        for(int i=0;i<qtdElementos;i++)
            pessoaHash.containsKey(i);

        long tempoFinalHash = System.currentTimeMillis();

        double tempoTotalSegHash = (tempoFinalHash-tempoInicialHash)/1000.00;

        System.out.printf("Hash Time (s): %.3f\n", tempoTotalSegHash);

        //Array List
        List<Integer> pessoaArray = new ArrayList<>();

        long tempoInicialArray = System.currentTimeMillis();

        for(int i=0;i<qtdElementos;i++)
            pessoaArray.add(i);
        for(int i=0;i<qtdElementos;i++)
            pessoaArray.contains(i);

        long tempoFinalArray = System.currentTimeMillis();

        double tempoTotalSegArray = (tempoFinalArray-tempoInicialArray)/1000.00;

        System.out.printf("Array List Time (s): %.3f\n", tempoTotalSegArray);


          // List
        List<Integer> pessoaList = new LinkedList<>();

        long tempoInicialList = System.currentTimeMillis();

        for(int i=0;i<qtdElementos;i++)
            pessoaList.add(i);
        for(int i=0;i<qtdElementos;i++)
            pessoaList.contains(i);

        long tempoFinalList = System.currentTimeMillis();

        double tempoTotalSegList = (tempoFinalList-tempoInicialList)/1000.00;

        System.out.printf("List Time (s): %.3f\n", tempoTotalSegList);

    }
}
