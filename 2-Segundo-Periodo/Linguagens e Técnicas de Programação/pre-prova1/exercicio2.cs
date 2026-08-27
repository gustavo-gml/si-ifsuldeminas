using System;
using MinhaBiblioteca;
/*prova t1*/
class exercicio2
{
	static bool Atg(string sequen)
	{
		bool status = false;

        sequen = sequen.ToUpper();

        for (int i = 0; i < sequen.Length - 2; i++)
        {
            if (sequen[i] == 'A' && sequen[i + 1] == 'T' && sequen[i + 2] == 'G')
            {
                status = true;
                return status;
            }
        }
			
		return status;
		
	}
	
	static void Main()
	{
		Console.Write("Digite a sequencia: ");	
		string sequen = Console.ReadLine();
		
		bool status = Atg(sequen);
		
		if(status)
		{
			Console.WriteLine("A sequência contém o start codon 'ATG'");
		}
		else{
			Console.WriteLine("A sequência NÃO contém o start codon 'ATG'");
		}
			
	}
}