package main.java;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App2 {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);

        List<String> transicoes = new ArrayList<>();

        /* 
        transicoes.add(">q0[q0, q1]");
        transicoes.add("q1[q2, q0]");
        transicoes.add("*q2[q1, q2]");
        */
         
        transicoes.add(">q0[q0, q1]");
        transicoes.add("q1[q2, q0]");
        transicoes.add("q2[q3, q1]");
        transicoes.add("*q3[q2, q3]");
        
        System.out.println(transicoes);

        System.out.println("Digite a cadeia: ");
        String cadeia = s.nextLine();

        String estadoFinal = "";
        for (String fin : transicoes){
            if (fin.contains("*")){
                estadoFinal = fin.substring(1, 3);
            }
        }

        if (Leitura(transicoes, cadeia, estadoFinal) == 1){
            System.out.println("A cadeia foi aceita.");
        }
        else{
            System.out.println("A cadeia não foi aceita.");
        }

        s.close();
    }
    public static int Leitura(List<String> transicoes, String cadeia, String estadoFinal){
        String estadoAtual = "";

        char w0 = cadeia.charAt(0);
        for (String j : transicoes){
            if (j.contains(">")){ // leitura inicial da cadeia
                if (w0 == '0'){
                    estadoAtual = j.substring(4, 5);
                    break;
                }
                if (w0 == '1'){
                    estadoAtual = j.substring(8, 10);
                    break;
                }
            }
        }

        for (int i = 1; i < cadeia.length(); i++){ // leitura do resto da cadeia
            char w = cadeia.charAt(i);
            
            for (String k : transicoes){
                if (!k.contains(">")){
                    if ((k.substring(0, 2).equals(estadoAtual))){
                        if (w == '0'){
                            estadoAtual = k.substring(3, 5);
                            break;
                        }
                        if (w == '1'){
                            estadoAtual = k.substring(7, 9);
                            break;
                        }
                    }
                }
                if (k.contains("*")){
                    if (w == '0'){
                        estadoAtual = k.substring(4, 6);
                        break;
                    }
                    if (w == '1'){
                        estadoAtual = k.substring(8, 10);
                        break;
                    }
                }
            }
        }
        if (estadoAtual.equals(estadoFinal)){
            return 1;
        }
        return 0;
    }

}
