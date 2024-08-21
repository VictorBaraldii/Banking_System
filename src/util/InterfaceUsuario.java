package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InterfaceUsuario {
    private Scanner scanner;

    public InterfaceUsuario() {
        scanner = new Scanner(System.in);
    }

    // Método para pedir o valor do imóvel
    public double pedirValorImovel() {
        double valorImovel = 0;
        boolean entradaValida = false;
        do {
            try {
                System.out.print("Digite o valor do imóvel (positivo): ");
                valorImovel = scanner.nextDouble();
                if (valorImovel <= 0) {
                    throw new IllegalArgumentException("O valor do imóvel deve ser positivo.");
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número.");
                scanner.next(); // Limpa a entrada inválida
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (!entradaValida);
        return valorImovel;
    }

    // Método para pedir o prazo do financiamento
    public int pedirPrazoFinanciamento() {
        int prazoFinanciamento = 0;
        boolean entradaValida = false;
        do {
            try {
                System.out.print("Digite o prazo do financiamento em anos (positivo): ");
                prazoFinanciamento = scanner.nextInt();
                if (prazoFinanciamento <= 0) {
                    throw new IllegalArgumentException("O prazo do financiamento deve ser positivo.");
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número.");
                scanner.next(); // Limpa a entrada inválida
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (!entradaValida);
        return prazoFinanciamento;
    }

    // Método para pedir a taxa de juros anual
    public double pedirTaxaJurosAnual() {
        double taxaJurosAnual = 0;
        boolean entradaValida = false;
        do {
            try {
                System.out.print("Digite a taxa de juros anual (positiva): ");
                taxaJurosAnual = scanner.nextDouble();
                if (taxaJurosAnual <= 0) {
                    throw new IllegalArgumentException("A taxa de juros deve ser positiva.");
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número.");
                scanner.next(); // Limpa a entrada inválida
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (!entradaValida);
        return taxaJurosAnual;
    }

    // Método para pedir o tamanho da área construída
    public double pedirTamanhoAreaConstruida() {
        double tamanhoAreaConstruida = 0;
        boolean entradaValida = false;
        do {
            try {
                System.out.print("Digite o tamanho da área construída (positivo): ");
                tamanhoAreaConstruida = scanner.nextDouble();
                if (tamanhoAreaConstruida <= 0) {
                    throw new IllegalArgumentException("O tamanho da área construída deve ser positivo.");
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número.");
                scanner.next(); // Limpa a entrada inválida
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (!entradaValida);
        return tamanhoAreaConstruida;
    }

    // Método para pedir o tamanho do terreno
    public double pedirTamanhoTerreno() {
        double tamanhoTerreno = 0;
        boolean entradaValida = false;
        do {
            try {
                System.out.print("Digite o tamanho do terreno (positivo): ");
                tamanhoTerreno = scanner.nextDouble();
                if (tamanhoTerreno <= 0) {
                    throw new IllegalArgumentException("O tamanho do terreno deve ser positivo.");
                }
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número.");
                scanner.next(); // Limpa a entrada inválida
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (!entradaValida);
        return tamanhoTerreno;
    }
}
