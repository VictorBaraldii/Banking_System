package modelo;

import java.io.Serializable;

public abstract class Financiamento implements Serializable {
    private static final long serialVersionUID = 1L;
    protected double valorImovel;
    protected int prazoFinanciamento;
    protected double taxaJurosAnual;

    // Construtor
    public Financiamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual) {
        this.valorImovel = valorImovel;
        this.prazoFinanciamento = prazoFinanciamento;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    // Método abstrato para calcular o pagamento mensal
    public abstract double calcularPagamentoMensal();

    // Método para calcular o total do pagamento
    public double calcularTotalPagamento() {
        double pagamentoMensal = calcularPagamentoMensal();
        double totalPagamento = pagamentoMensal * prazoFinanciamento * 12;
        return totalPagamento;
    }

    // Getters
    public double getValorImovel() {
        return valorImovel;
    }

    public int getPrazoFinanciamento() {
        return prazoFinanciamento;
    }

    public double getTaxaJurosAnual() {
        return taxaJurosAnual;
    }

    // Método para mostrar os dados do financiamento
    public void mostrarDadosFinanciamento() {
        System.out.println("Valor do imóvel: R$ " + valorImovel);
        System.out.println("Prazo do financiamento: " + prazoFinanciamento + " anos");
        System.out.println("Taxa de juros anual: " + taxaJurosAnual + " %");
    }
}
