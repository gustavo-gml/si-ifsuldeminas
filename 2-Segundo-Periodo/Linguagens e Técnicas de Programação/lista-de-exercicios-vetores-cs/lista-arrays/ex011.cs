using System;
using MinhaBiblioteca;

class ex011
{
    static string DecodificarP(string mensagemCod)
    {

        char[] decodificado = new char[mensagemCod.Length]; /*Vai armazenar a mensagem decodificada*/

        int j = 0; /*Ponteiro de escrita para o vetor de decodificação*/

        for (int i = 1; i < mensagemCod.Length - 1; i++) /*A primeira letra sempre será um p, para todos os casos , o que faz com que não seja necessário verificar se esse caractere precisa ser testado. Além disso, todo final de palavra tem um caractere da palavra, portanto o ultimo caractere da string sempre será um caractere que precisa ser incluído, não necessitando de testes. Devido a isso, int i = 1; i < mensagemCod.Length - 1*/
        {
            if (mensagemCod[i - 1] == 'p' && (mensagemCod[i + 1] == 'p' || mensagemCod[i + 1] == ' '))  /*Satizfaz a decodificação para qualquer caracter não Vazio*/
            {
                decodificado[j] = mensagemCod[i];
                j++;
            }
            else if (mensagemCod[i] == ' ') /*Trata o caso do caracter vazio*/
            {
                decodificado[j] = ' ';
                j++;
            }
            
        }
        decodificado[j] = mensagemCod[mensagemCod.Length - 1]; /*A ultima posição da string sempre será uma letra que precisa ser incluida*/
        
        decodificado = decodificado[..(j+1)]; /*Modifica o vetor para que fique sem espaços vazios, j carrega o tamanho "real" da string decodificada (j+1 ocorre devido aos parametros do slicing)*/
    
        string mensagemDec = new string(decodificado); /*Transforma o vetor de decodificação em uma nova string*/
        return mensagemDec;
    }
    static void Main()
    {
        string mensagemCodificada, mensagemDecodificada;

        mensagemCodificada = Console.ReadLine();

        mensagemDecodificada = DecodificarP(mensagemCodificada);

        Console.WriteLine(mensagemDecodificada);
    }
}