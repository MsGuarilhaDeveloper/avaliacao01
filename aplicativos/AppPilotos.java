package aplicativos;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import classes.Pessoa;
import classes.Pilotos;
import classes.expandirVetor;

public class AppPilotos {
    private static final Object[] Pessoa = null;
    private static final Pilotos[] Pilotos = null;

    public static void main(String[] args) throws InterruptedException, IOException {
        int MAX_ELEMENTOS = 2;
        final int MAX_ERROS_CPF = 3;
        int opcao, qtdCadastrados = 0;
       // int opcao, qtdCadastrados = 0;
        Pessoa[] pilotos = new Pessoa[MAX_ELEMENTOS];

        Scanner in = new Scanner(System.in);

        do {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Cadastrar piloto");
            System.out.println("2 - Listar pilotos cadastrados");
            System.out.println("3 - Procurar piloto por CPF");
         // System.out.println("4 - Modificar armazenamento");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = in.nextInt();
            in.nextLine(); 

            if (opcao == 1) {
                
                if (qtdCadastrados == MAX_ELEMENTOS) {
                    System.out.println(
                            "\nNão há espaço para cadastrar novos pilotos, a capacidade foi aumentada automaticamente.");
                    MAX_ELEMENTOS = MAX_ELEMENTOS * 2;
                    voltarMenu(in);
                    continue;
                }


                Pessoa piloto = new Pessoa();

                System.out.print("Nome: ");
                piloto.setNome(in.nextLine());

                
                int numVezes = 0;
                do {
                    try {
                        System.out.print("CPF: ");
                        piloto.setCpf(in.nextLine());
                    } catch (InputMismatchException ex) {
                        System.out.println(ex.getMessage() + " Tente novamente.");
                        numVezes += 1;
                    }
                } while (piloto.getCpf() == null && numVezes < MAX_ERROS_CPF);

                // Se CPF está nulo, é porque errou as MAX_ERROS_CPF vezes. Assim, fim do programa.
                if (piloto.getCpf() == null) {
                    System.out.printf("Você errou o CPF %d vezes. O programa será encerrado.", numVezes);
                    return;
                }

                pilotos[qtdCadastrados] = piloto;
                qtdCadastrados = qtdCadastrados + 1;

                System.out.println("\nPiloto cadastrado com sucesso.");
                voltarMenu(in);
            } else if (opcao == 2) {
                if (qtdCadastrados == 0) {
                    System.out.println("\nNão há pilotos cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                }
               
                System.out.println("\nPILOTOS CADASTRADOS:");
                System.out.println("***********************");

                for (int i = 0; i < qtdCadastrados; i++) {
                    System.out.printf("\nPiloto %d: %s\n", (i + 1), pilotos[i].getNome());
                    System.out.printf("CPF: %s\n", pilotos[i].getCpf());//pegar cpf apartir
                }
            }
                else if (opcao == 3) {
                    if (qtdCadastrados == 0) {
                        System.out.println("\nNão há pilotos cadastrados para exibir.");
                        voltarMenu(in);
                        continue;
                }
                

                 
                 System.out.println("Informe o cpf para a busca?");
                 String buscarcpf = in.nextLine();
                 System.out.println("=======================\n");


                for (int i = 0; i < qtdCadastrados; i++){
                    if(buscarcpf.equals(pilotos[i].getCpf())){
                        System.out.println("Piloto encontrado");
                        System.out.println("=======================");
                        System.out.println("Nome: " + pilotos[i].getNome());
                        System.out.println("CPF " + pilotos[i].getCpf());
                        System.out.println("=======================");
                    }                
                }
                

                
            }
            
          /*   else if (opcao == 4){
                System.out.println("1 - Aumentar armazenamento");
                System.out.println("2 - Diminuir armazenamento");
                int escolha = in.nextInt();
                
                if (escolha == 1){
                    System.out.println("Qualto deseja aumentar:");
                    int aumentarVetor = in.nextInt();
                    
                   /*  expandirVetor aumentarVetor = expandirVetor();

                    in.nextInt(); 
                    Pessoa[] expandirVetor = new Pessoa[10];
                    for(int i = 0; i < 10; i++) {
                        //Pessoa[i] = i + 10;
                    }
                    int[] Pilotos = new int[20];
                    System.arraycopy(pilotos , 0, expandirVetor, 0, 10);

                    System.out.println("Tamanho aumentado para: " + aumentarVetor);
                    
                }
            } */


            

        } while (opcao != 0);


        System.out.println("Fim do programa!");

        in.close();
    }

    private static expandirVetor expandirVetor() {
        return null;
    }

    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");
        
        System.out.flush();
    }
}
