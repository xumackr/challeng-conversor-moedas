# Conversor de Moedas

## Visão Geral

O **Conversor de Moedas** é uma aplicação Java que permite converter valores entre diferentes moedas com base nas taxas de câmbio atuais obtidas de uma API de terceiros. A aplicação solicita ao usuário as moedas de origem e destino e o valor a ser convertido, então calcula e exibe o valor convertido. Além disso, mantém um histórico das conversões realizadas, que é salvo em um arquivo para futuras consultas.

## Funcionalidades

- Conversão de valores entre diferentes moedas usando taxas de câmbio atualizadas.
- Suporte a múltiplas moedas, incluindo USD, EUR, BRL, ARS, e GBP.
- Histórico das conversões realizadas, com detalhes sobre cada operação.
- Interface de linha de comando interativa para facilitar a entrada de dados pelo usuário.

## Tecnologias Utilizadas

- **Java 11 ou superior**: Linguagem de programação principal do projeto.
- **Gson**: Biblioteca para manipulação de JSON.
- **HttpClient**: Para realizar requisições HTTP à API de taxas de câmbio.
- **API Exchange Rate**: Para obter as taxas de câmbio atuais.

## Requisitos

- Java 11 ou superior instalado em seu sistema.
- Acesso à internet para buscar as taxas de câmbio da API.

## Instalação e Execução

1. **Clone o repositório**:

   ```bash
   git clone https://github.com/seu-usuario/conversor-de-moedas.git
   cd conversor-de-moedas

2. **Compile o projeto**:
      
   - Se estiver usando uma IDE (como IntelliJ IDEA ou Eclipse); 
   - basta importar o projeto como um projeto Java e compilar. 
   - Certifique-se de ter a biblioteca gson-2.8.6.jar na pasta lib.
   - Se preferir usar a linha de comando:

   ```bash
   javac -d bin -sourcepath src -cp lib/gson-2.8.6.jar src/ConversorDeMoedas/*.java

3. **Execute a aplicação**:

   ```bash
   java -cp "bin;lib/gson-2.8.6.jar" ConversorDeMoedas.ConversorDeMoedasApp


### Uso

- Ao iniciar a aplicação, você verá uma mensagem de boas-vindas e instruções básicas.

- Informe a sigla da moeda de origem e a sigla da moeda de destino conforme solicitado. Certifique-se de digitar as siglas em letras maiúsculas.

- Digite o valor que deseja converter.

- O aplicativo exibirá o valor convertido e adicionará a operação ao histórico.

- Para encerrar a aplicação a qualquer momento, digite "sair".

- Após encerrar, o histórico de conversões será salvo no arquivo historico_conversoes.txt localizado na pasta historico.

### Possíveis Melhorias

- **Suporte a mais moedas:** Adicionar suporte para mais siglas de moedas.

- **Interface gráfica:** Criar uma interface gráfica para tornar o uso mais intuitivo.

- **Configurações personalizadas:** Permitir ao usuário definir a precisão das conversões e outros parâmetros.

- **Cache de taxas de câmbio:** Implementar um sistema de cache para evitar múltiplas chamadas à API em um curto período.

### Contribuição

Contribuições são bem-vindas! Se você encontrar bugs ou tiver ideias para melhorias, sinta-se à vontade para abrir uma issue ou enviar um pull request.

### Contato

Para dúvidas ou sugestões, você pode me encontrar em:

- [LinkedIn](https://www.linkedin.com/in/lucas-campos-248043274/)
- WhatsApp: +55 21 92007-6580
- Email: lucas.1532@hotmail.com
