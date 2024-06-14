package ConversorDeMoedas;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConversorDeMoedasApp {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        // Configuração do Gson
        Gson configuracaoGson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        // Requisição para obter taxas de câmbio
        String urlApi = "https://v6.exchangerate-api.com/v6/d1c7a549392b7c46ab9cfd92/latest/USD";
        HttpClient clienteHttp = HttpClient.newHttpClient();
        HttpRequest requisicaoHttp = HttpRequest.newBuilder()
                .uri(URI.create(urlApi))
                .build();
        HttpResponse<String> respostaHttp = clienteHttp.send(requisicaoHttp, HttpResponse.BodyHandlers.ofString());

        if (respostaHttp.statusCode() != 200) {
            System.out.println("Erro ao acessar a API de taxas de câmbio. Verifique sua conexão e tente novamente.");
            return;
        }

        JsonObject jsonTaxas = configuracaoGson.fromJson(respostaHttp.body(), JsonObject.class);
        OperacoesDeMoeda operacoesMoeda = new OperacoesDeMoeda(jsonTaxas);
        HistoricoDeConversao historicoConversao = new HistoricoDeConversao(configuracaoGson, "/home/lucas/Documents/challeng-conversorMoedas/historico", "historico_conversoes.txt");

        System.out.println("""
                Olá! Bem-vindo ao nosso Conversor de Moedas.
                Para começar, siga as instruções e vamos converter algumas moedas.
                Para encerrar a qualquer momento, digite "sair".
                """);

        while (true) {
            try {
                System.out.println("""
                    ========================================================================
                    Por favor, informe a sigla da moeda de origem :
                    Exemplos:
                    
                    Dólar Americano (USD);
                    Euro (EUR);
                    Real (BRL);
                    peso argentino (ARS);
                    Libra Esterlina (GBP).
                    
                    """);

                String siglaOrigem = scanner.nextLine().toUpperCase();
                if (siglaOrigem.equalsIgnoreCase("sair")) {
                    break;
                }

                if (!jsonTaxas.getAsJsonObject("conversion_rates").has(siglaOrigem)) {
                    System.out.println("Moeda de origem não reconhecida. Por favor, tente novamente com uma sigla válida.");
                    continue;
                }

                System.out.println("""
                    Agora, informe a sigla da moeda para a qual deseja converter :
                   Exemplos:
                
                   Dólar Americano (USD);
                   Euro (EUR);
                   Real (BRL);
                   peso argentino (ARS);
                   Libra Esterlina (GBP).
                   
                   """);

                String siglaDestino = scanner.nextLine().toUpperCase();
                if (siglaDestino.equalsIgnoreCase("sair")) {
                    break;
                }

                if (!jsonTaxas.getAsJsonObject("conversion_rates").has(siglaDestino)) {
                    System.out.println("Moeda de destino não reconhecida. Por favor, tente novamente com uma sigla válida.");
                    continue;
                }

                System.out.println("Informe o valor que você deseja converter:");
                double valor = scanner.nextDouble();
                scanner.nextLine();  // Limpa o buffer do scanner

                double valorConvertido = operacoesMoeda.converter(valor, siglaOrigem, siglaDestino);
                System.out.printf("O valor de %.2f %s é equivalente a %.2f %s\n", valor, siglaOrigem, valorConvertido, siglaDestino);

                historicoConversao.adicionarRegistro(siglaOrigem, valor, siglaDestino, valorConvertido);

            } catch (NullPointerException e) {
                System.out.println("""
                    Ocorreu um erro: você digitou uma informação inválida.
                    Verifique se está usando as siglas corretas das moedas e se o valor está no formato apropriado.
                    Exemplos de siglas: USD, EUR, BRL
                    Exemplos de valores: 5,50 ou 1000,50
                    """);
            } catch (InputMismatchException e) {
                System.out.println("Erro: valor digitado é inválido. Por favor, insira um número válido.");
                scanner.nextLine();  // Limpa o buffer do scanner
            }
        }

        // Salva o histórico de conversões
        historicoConversao.salvarParaArquivo();
        scanner.close();
        System.out.println("Conversor encerrado. Seu histórico de conversões foi salvo.");
    }
}
