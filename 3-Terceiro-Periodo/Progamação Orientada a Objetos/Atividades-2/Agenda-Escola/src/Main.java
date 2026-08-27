import entities.*;

import java.time.LocalTime;

public class Main{
    static void main(String[] args){
        Agenda agenda = new Agenda("Professores");
        agenda.addContato(new Contato("Matheus Guedes", "(35) 4002-8922"));
        agenda.addContato(new Contato("Ronaldinho (O bruxo)", "(35) 3298-1750"));
        System.out.println(agenda);

        Instrumento instrumentoTeste = new Bateria("Latil", "Latão");
        Aluno aluno = new Aluno("Matheus",11,instrumentoTeste);
        Turma turma1= new Turma("Mock and Rols", LocalTime.now());
        Turma turma2= new Turma("Mons and Gooses ", LocalTime.now());
        Escola escola1 = new Escola("e e trates");

        escola1.addTurma(turma1);
        escola1.addTurma(turma2);

        System.out.println(escola1);


    }


}