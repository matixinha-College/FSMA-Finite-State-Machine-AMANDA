package main.java;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);

        final Map<String, String> estados = new HashMap<>();

        System.out.println("Quantos estados o autômato tem?");
        int qt  = s.nextInt();
        s.nextLine();

        for (int i = 0; i < qt; i++){ // guardando os estados e sua classificação (inicial, normal ou final)
            System.out.println("Esse estado " + "(q" + i + ") é inicial, normal ou final?");
            String est = s.nextLine();
    
            if (est.equals("inicial") || est.equals("i")){
                estados.put("q" + i, "inicial");
            }
            else if (est.equals("normal") || est.equals("n")){
                estados.put("q" + i, "normal");
            }
            else if (est.equals("final") || est.equals("f")){
                estados.put("q" + i, "final");
            }
        }

        if (!estados.containsValue("final") || (!estados.containsValue("inicial"))){
            System.out.println("Autômato incompleto! Construção interrompida.");
            s.close();
            return;
        }

        System.out.println("Qual o alfabeto do autômato? ");
        final List<String> alfabeto = new ArrayList<>(); // armazenando o alfabeto do autômato

        String s1 = s.nextLine();
        alfabeto.add(s1);
        String s2 = s.nextLine();
        alfabeto.add(s2);

        System.out.println("Funções de transição: ");

        final Map<String, List<String>> transicoes = new HashMap(); // tabela da função de transição

        for (int j = 0; j < estados.size(); j++){
            System.out.println("estado q" + j + " lendo " + alfabeto.get(0) + ":"); // estados qX lendo a primeira palavra do alfabeto
            String l1 = s.nextLine();
            System.out.println("estado q" + j + " lendo " + alfabeto.get(1) + ":"); // estados qX lendo a segunda palavra do alfabeto
            String l2 = s.nextLine();

            List<String> temp = new ArrayList<>(); 
            temp.add(l1);
            temp.add(l2);
            
            transicoes.put("q" + j, temp); // preenchendo uma linha da tabela de transição
        }         

        System.out.println(transicoes);

        System.out.println("Autômato pronto. Deseja ler uma cadeia?");
        
        String estadoInicial = "null";
            List<String> estadosFinais = new ArrayList<>();

            for (String key : estados.keySet()){ // procurando o estado inicial e os estados finais para ler a cadeia
                if (estados.get(key) == "inicial"){
                    estadoInicial = key;
                }
                else if (estados.get(key) == "final"){
                    estadosFinais.add(key);
                }

        String op = s.nextLine();
        while (op.equals("sim") || op.equals("s")){ // testes de aceitação
            System.out.print("Digite a cadeia a ser testada: ");
            String cadeia = s.nextLine();

            if(Leitura(cadeia, transicoes, estadoInicial, estadosFinais) == 1){
                System.out.println("A cadeia foi aceita.\n Ler outra?");
                op = s.nextLine();
            }
            else{
                System.out.println("A cadeia não foi aceita. \n Ler outra?");
                op = s.nextLine();
            }
        }
         
        s.close();
        }

    }
     

    // método quebrado
    public static int Leitura(String cadeia, Map<String, List<String>> transicoes, String estadoInicial, List<String> estadosFinais){
        for (int i = 0; i < cadeia.length(); i++){
            char w = cadeia.charAt(i);

            String estadoAtual = "null";
            return 1;

        }

        return 0;
        
    }
}
