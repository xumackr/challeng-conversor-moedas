package ConversorDeMoedas;

import com.google.gson.JsonObject;

public class OperacoesDeMoeda {
    private final JsonObject taxasDeConversao;

    public OperacoesDeMoeda(JsonObject taxasDeConversao) {
        this.taxasDeConversao = taxasDeConversao;
    }

    public double obterTaxa(String siglaMoeda) {
        return taxasDeConversao.getAsJsonObject("conversion_rates").get(siglaMoeda).getAsDouble();
    }

    public double converter(double valor, String siglaOrigem, String siglaDestino) {
        double taxaOrigem = obterTaxa(siglaOrigem);
        double taxaDestino = obterTaxa(siglaDestino);
        return valor * (taxaDestino / taxaOrigem);
    }
}
