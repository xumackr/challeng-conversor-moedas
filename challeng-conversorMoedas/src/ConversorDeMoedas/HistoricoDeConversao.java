package ConversorDeMoedas;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HistoricoDeConversao {
    private final List<Map<String, Object>> historico;
    private final Gson gson;
    private final String caminhoDiretorio;
    private final String nomeArquivo;

    public HistoricoDeConversao(Gson gson, String caminhoDiretorio, String nomeArquivo) {
        this.gson = gson;
        this.historico = new ArrayList<>();
        this.caminhoDiretorio = caminhoDiretorio;
        this.nomeArquivo = nomeArquivo;
    }

    public void adicionarRegistro(String siglaOrigem, double valorOrigem, String siglaDestino, double valorConvertido) {
        LocalDateTime agora = LocalDateTime.now();
        String dataHora = agora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Map<String, Object> registro = new HashMap<>();
        registro.put("data_hora", dataHora);
        registro.put("moeda_origem", siglaOrigem);
        registro.put("valor_origem", valorOrigem);
        registro.put("moeda_destino", siglaDestino);
        registro.put("valor_convertido", valorConvertido);

        historico.add(registro);
    }

    public void salvarParaArquivo() throws IOException {
        File diretorio = new File(caminhoDiretorio);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        File arquivo = new File(diretorio, nomeArquivo);
        try (FileWriter escritor = new FileWriter(arquivo, true)) {
            escritor.write("\n");
            escritor.write(gson.toJson(historico));
        }
    }
}
