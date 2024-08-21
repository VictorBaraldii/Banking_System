package main;

import modelo.*;
import util.InterfaceUsuario;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
        ArrayList<Financiamento> financiamentos = new ArrayList<>();

        // Pedir dados para um financiamento do usuário
        System.out.println("Digite os dados para um financiamento:");
        double valorImovel = interfaceUsuario.pedirValorImovel();
        int prazoFinanciamento = interfaceUsuario.pedirPrazoFinanciamento();
        double taxaJurosAnual = interfaceUsuario.pedirTaxaJurosAnual();
        double tamanhoAreaConstruida = interfaceUsuario.pedirTamanhoAreaConstruida();
        double tamanhoTerreno = interfaceUsuario.pedirTamanhoTerreno();

        // Criar e adicionar um financiamento de Casa com os dados do usuário
        Casa financiamentoCasa = new Casa(valorImovel, prazoFinanciamento, taxaJurosAnual, tamanhoAreaConstruida, tamanhoTerreno);
        financiamentos.add(financiamentoCasa);

        // Adicionar financiamentos predefinidos
        financiamentos.add(new Casa(200000, 10, 8.0, 120.0, 300.0));
        financiamentos.add(new Apartamento(300000, 15, 9.0, 2, 5));
        financiamentos.add(new Apartamento(250000, 20, 7.5, 1, 10));
        financiamentos.add(new Terreno(150000, 12, 10.0, "Residencial"));

        // Calcular e mostrar totais
        double totalValorImoveis = 0;
        double totalValorFinanciamentos = 0;
        for (Financiamento financiamento : financiamentos) {
            totalValorImoveis += financiamento.getValorImovel();
            totalValorFinanciamentos += financiamento.calcularTotalPagamento();
            financiamento.mostrarDadosFinanciamento();
        }

        System.out.println("Total de todos os imóveis: R$ " + totalValorImoveis);
        System.out.println("Total de todos os financiamentos: R$ " + totalValorFinanciamentos);

        // Testando a exceção na classe Casa
        try {
            double desconto = 200;
            double novoPagamentoMensal = financiamentoCasa.calcularPagamentoMensalComDesconto(desconto);
            System.out.println("Novo pagamento mensal com desconto de R$ " + desconto + ": R$ " + novoPagamentoMensal);
        } catch (DescontoMaiorDoQueJurosException e) {
            System.out.println(e.getMessage());
        }

        // Salvar financiamentos em arquivo de texto
        salvarFinanciamentosEmArquivoTexto(financiamentos, "financiamentos.txt");

        // Ler financiamentos do arquivo de texto
        ArrayList<Financiamento> financiamentosLidos = lerFinanciamentosDeArquivoTexto("financiamentos.txt");
        System.out.println("Financiamentos lidos do arquivo de texto:");
        for (Financiamento financiamento : financiamentosLidos) {
            financiamento.mostrarDadosFinanciamento();
        }

        // Serializar financiamentos
        serializarFinanciamentos(financiamentos, "financiamentos.ser");

        // Desserializar financiamentos
        ArrayList<Financiamento> financiamentosDesserializados = desserializarFinanciamentos("financiamentos.ser");
        System.out.println("Financiamentos desserializados:");
        for (Financiamento financiamento : financiamentosDesserializados) {
            financiamento.mostrarDadosFinanciamento();
        }
    }

    private static void salvarFinanciamentosEmArquivoTexto(ArrayList<Financiamento> financiamentos, String arquivoNome) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoNome))) {
            for (Financiamento financiamento : financiamentos) {
                if (financiamento instanceof Casa) {
                    Casa casa = (Casa) financiamento;
                    writer.write("Casa," + casa.getValorImovel() + "," + casa.getPrazoFinanciamento() + "," + casa.getTaxaJurosAnual() + "," +
                            casa.getTamanhoAreaConstruida() + "," + casa.getTamanhoTerreno());
                } else if (financiamento instanceof Apartamento) {
                    Apartamento apto = (Apartamento) financiamento;
                    writer.write("Apartamento," + apto.getValorImovel() + "," + apto.getPrazoFinanciamento() + "," + apto.getTaxaJurosAnual() + "," +
                            apto.getNumeroVagasGaragem() + "," + apto.getNumeroAndar());
                } else if (financiamento instanceof Terreno) {
                    Terreno terreno = (Terreno) financiamento;
                    writer.write("Terreno," + terreno.getValorImovel() + "," + terreno.getPrazoFinanciamento() + "," + terreno.getTaxaJurosAnual() + "," +
                            terreno.getTipoZona());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar financiamentos em arquivo de texto: " + e.getMessage());
        }
    }

    private static ArrayList<Financiamento> lerFinanciamentosDeArquivoTexto(String arquivoNome) {
        ArrayList<Financiamento> financiamentos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoNome))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                switch (partes[0]) {
                    case "Casa":
                        financiamentos.add(new Casa(Double.parseDouble(partes[1]), Integer.parseInt(partes[2]), Double.parseDouble(partes[3]),
                                Double.parseDouble(partes[4]), Double.parseDouble(partes[5])));
                        break;
                    case "Apartamento":
                        financiamentos.add(new Apartamento(Double.parseDouble(partes[1]), Integer.parseInt(partes[2]), Double.parseDouble(partes[3]),
                                Integer.parseInt(partes[4]), Integer.parseInt(partes[5])));
                        break;
                    case "Terreno":
                        financiamentos.add(new Terreno(Double.parseDouble(partes[1]), Integer.parseInt(partes[2]), Double.parseDouble(partes[3]),
                                partes[4]));
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler financiamentos de arquivo de texto: " + e.getMessage());
        }
        return financiamentos;
    }

    private static void serializarFinanciamentos(ArrayList<Financiamento> financiamentos, String arquivoNome) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivoNome))) {
            oos.writeObject(financiamentos);
        } catch (IOException e) {
            System.err.println("Erro ao serializar financiamentos: " + e.getMessage());
        }
    }

    private static ArrayList<Financiamento> desserializarFinanciamentos(String arquivoNome) {
        ArrayList<Financiamento> financiamentos = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivoNome))) {
            financiamentos = (ArrayList<Financiamento>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao desserializar financiamentos: " + e.getMessage());
        }
        return financiamentos;
    }
}
