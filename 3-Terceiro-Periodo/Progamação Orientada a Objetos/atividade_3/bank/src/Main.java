import entities.ContaBancaria;
import entities.exceptions.ContaInativaException;
import entities.exceptions.SaldoInsuficienteException;
import entities.exceptions.ValorInvalidoException;

import javax.swing.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main{
    private static Scanner sc = new Scanner(System.in);

    public static int menu(){
        int op = 0;
        System.out.println("Selecione uma opção");
        System.out.println("1 - Depositar");
        System.out.println("2 - Sacar");
        System.out.println("3 - Dados da conta");
        System.out.println("0 - Sair");
        op = Integer.parseInt(sc.nextLine());
        return op;
    }
    public static void main(){

        ContaBancaria contaExemplo = new ContaBancaria(1,"Gabinho", 1000.45);
        int op;

        do{
            op = menu();
            switch (op){
                case 1:
                    try {
                        System.out.print("Valor para deposito: ");
                        double valor = Double.parseDouble(sc.nextLine());

                        contaExemplo.deposit(valor);
                    }catch (ValorInvalidoException | ContaInativaException e){
                        System.out.println(e.getMessage());
                    }catch (InputMismatchException e) {
                        System.out.println("Erro: O valor inserido não é um número inteiro válido.");
                    } catch (NumberFormatException e) {
                        System.out.println("Erro de formato numérico.");
                    }
                    break;
                case 2:
                    try {
                        System.out.print("Valor para saque: ");
                        double valor = Double.parseDouble(sc.nextLine());

                        contaExemplo.withdraw(valor);

                    }catch (ValorInvalidoException | SaldoInsuficienteException | ContaInativaException e){
                        System.out.println(e.getMessage());
                    }catch (InputMismatchException e) {
                        System.out.println("Erro: O valor inserido não é um número inteiro válido.");
                    } catch (NumberFormatException e) {
                        System.out.println("Erro de formato numérico.");
                    }
                    break;
                case 3:
                    System.out.println("\n" + contaExemplo + "\n");
                case 0:
                    break;
                default:
                    System.out.println("Operação invalida!");
                    break;

            }
        } while (op != 0);
    }
}