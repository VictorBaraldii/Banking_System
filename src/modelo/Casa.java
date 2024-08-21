package modelo;

public class Casa extends Financiamento {
    private static final long serialVersionUID = 1L;
    private static final double SEGURO_OBRIGATORIO = 80.0;
    private double tamanhoAreaConstruida;
    private double tamanhoTerreno;

    public Casa(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, double tamanhoAreaConstruida, double tamanhoTerreno) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.tamanhoAreaConstruida = tamanhoAreaConstruida;
        this.tamanhoTerreno = tamanhoTerreno;
    }

    @Override
    public double calcularPagamentoMensal() {
        double pagamentoMensal = (valorImovel / (prazoFinanciamento * 12)) * (1 + (taxaJurosAnual / 12));
        pagamentoMensal += SEGURO_OBRIGATORIO;
        return pagamentoMensal;
    }

    public double calcularPagamentoMensalComDesconto(double desconto) throws DescontoMaiorDoQueJurosException {
        double pagamentoMensal = calcularPagamentoMensal();
        double juros = pagamentoMensal - (valorImovel / (prazoFinanciamento * 12));
        if (desconto > juros) {
            throw new DescontoMaiorDoQueJurosException("Desconto maior do que os juros.");
        }
        return pagamentoMensal - desconto;
    }

    @Override
    public void mostrarDadosFinanciamento() {
        super.mostrarDadosFinanciamento();
        System.out.println("Tamanho da área construída: " + tamanhoAreaConstruida + " m²");
        System.out.println("Tamanho do terreno: " + tamanhoTerreno + " m²");
    }

    public double getTamanhoAreaConstruida() {
        return tamanhoAreaConstruida;
    }

    public double getTamanhoTerreno() {
        return tamanhoTerreno;
    }
}
