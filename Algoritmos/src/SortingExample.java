import java.io.*;
import java.util.*;

import OrdenacaoCrescente.OrdenacaoCrescente;

public class SortingExample {

    static OrdenacaoCrescente ordenacaoCrescente = new OrdenacaoCrescente();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("");

            System.out.print(
                    "Escolha o algoritmo de ordenação crescente-> \n" +
                            "1: BubbleSort \n" +
                            "2: InsertionSort \n" +
                            "3: SelectionSort \n" +
                            "4: MergeSort \n" +
                            "5: QuickSort \n" +
                            "6: HeapSort \n" +
                            "ou digite 7 para fechar o programa: ");
            int choice = scanner.nextInt();

            if (choice == 7) {
                break;
            }

            System.out.print("Digite a quantidade de números a serem ordenados: ");
            int n = scanner.nextInt();

            System.out.println("Selecione o arquivo de entrada:\n" +
                    "1: aleatorio_com_repeticao.txt \n" +
                    "2: aleatorio_sem_repeticao.txt \n" +
                    "3: crescente_com_repeticao.txt \n" +
                    "4: crescente_sem_repeticao.txt \n" +
                    "5: decrescente_com_repeticao.txt \n" +
                    "6: decrescente_sem_repeticao.txt");

            int filechoice = scanner.nextInt();
            String inputFileName = "";

            // Escolhe o arquivo de entrada com base na escolha do usuário
            switch (filechoice) {
                case 1:
                    inputFileName = "aleatorio_com_repeticao.txt";
                    break;
                case 2:
                    inputFileName = "aleatorio_sem_repeticao.txt";
                    break;
                case 3:
                    inputFileName = "crescente_com_repeticao.txt";
                    break;
                case 4:
                    inputFileName = "crescente_sem_repeticao.txt";
                    break;
                case 5:
                    inputFileName = "decrescente_com_repeticao.txt";
                    break;
                case 6:
                    inputFileName = "decrescente_sem_repeticao.txt";
                    break;
                default:
                    System.out.println("Opção inválida. O programa será encerrado.");
                    return; // Encerra o programa caso a escolha seja inválida
            }

            String outputFileName = "saida.txt"; // Arquivo de saída

            System.out.println("Arquivo de entrada selecionado: " + inputFileName);
            System.out.println("Arquivo de saída definido como: " + outputFileName);

            // Ler os números do arquivo de entrada
            int[] numbers = new int[n];
            try (BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
                for (int i = 0; i < n; i++) {
                    numbers[i] = Integer.parseInt(br.readLine());
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler o arquivo de entrada: " + e.getMessage());
            }

            String algorithm = "";
            double tempoInicial = System.currentTimeMillis();

            switch (choice) {
                // Ordenação Crescente
                case 1:
                    ordenacaoCrescente.bubbleSort(numbers);
                    algorithm = "BubbleSort";
                    break;
                case 2:
                    ordenacaoCrescente.insertionSort(numbers);
                    algorithm = "Insertion";
                    break;
                case 3:
                    ordenacaoCrescente.selectionSort(numbers);
                    algorithm = "SelectionSort";
                    break;
                case 4:
                    ordenacaoCrescente.mergeSort(numbers, 0, n - 1);
                    algorithm = "MergeSort";
                    break;
                case 5:
                    ordenacaoCrescente.quickSort(numbers, 0, n - 1);
                    algorithm = "QuickSort";
                    break;
                case 6:
                    ordenacaoCrescente.heapSort(numbers);
                    algorithm = "HeapSort";
                    break;
                default:
                    System.out.println("Algoritmo de ordenação inválido.");
            }

            double tempoFinal = System.currentTimeMillis();
            double tempoExecucao = (tempoFinal - tempoInicial) / 1000;
            System.out.println("Tempo de execução em segundos: " + tempoExecucao);

            // Gravar os números ordenados no arquivo de saída
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {
                for (int number : numbers) {
                    bw.write(Integer.toString(number));
                    bw.newLine();
                }
            } catch (IOException e) {
                System.out.println("Erro ao gravar o arquivo de saída: " + e.getMessage());
            }

            System.out.println("Ordenação concluída e salva no arquivo: " + outputFileName);

            switch (filechoice) {
                case 1:
                    exportarResultados(inputFileName, algorithm, n, tempoExecucao, "resultados" + algorithm + "AleatorioComRepeticao.txt");
                    break;
                case 2:
                    exportarResultados(inputFileName, algorithm, n, tempoExecucao, "resultados" + algorithm + "AleatorioSemRepeticao.txt");
                    break;
                case 3:
                    exportarResultados(inputFileName, algorithm, n, tempoExecucao, "resultados" + algorithm + "CrescenteComRepeticao.txt");
                    break;
                case 4:
                    exportarResultados(inputFileName, algorithm, n, tempoExecucao, "resultados" + algorithm + "CrescenteSemRepeticao.txt");
                    break;
                case 5:
                    exportarResultados(inputFileName, algorithm, n, tempoExecucao, "resultados" + algorithm + "DecrescenteComRepeticao.txt");
                    break;
                case 6:
                    exportarResultados(inputFileName, algorithm, n, tempoExecucao, "resultados" + algorithm + "DecrescenteSemRepeticao.txt");
                    break;
                default:

                    break;
            }

        }
        scanner.close();
    }

    public static void exportarResultados(String inputFileName, String algorithm, int numElements, double tempoExecucao, String outputFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, true))) {
            bw.write(String.format("%-45s %-25s %-20d %.4f", inputFileName, algorithm, numElements, tempoExecucao));
            bw.newLine();
            System.out.println("Resultados exportados para: " + outputFile);
        } catch (IOException e) {
            System.out.println("Erro ao exportar os resultados: " + e.getMessage());
        }
    }
}
