import org.apache.log4j.BasicConfigurator;

import smile.regression.OLS;

/**
 * Created by suemareverton on 22/08/17.
 */
public class Exercicio01 {

    public static void main(String[] args) {

        // Log
        BasicConfigurator.configure();

        // Variáveis preditoras
        // Cada elemento do array é uma observação
        // Cada observação por sua vez é um array com outras variáveis preditoras

        // Neste caso porém, estamos utilizando apenas 1 variável preditora por observação

        // Regressão Linear Simples

        // Wikipedia:
        // O Método dos Mínimos Quadrados (MMQ), ou Quadrados Mínimos Ordinários (MQO)
        // ou OLS (do inglês Ordinary Least Squares) é uma técnica de otimização
        // matemática que procura encontrar o melhor ajuste para um conjunto de dados
        // tentando minimizar a soma dos quadrados das diferenças
        // entre o valor estimado e os dados observados
        // (tais diferenças são chamadas resíduos)

        // Prevendo gastos escolares através da quantidade de filhos

        double[][] X = new double[][] { { 2 }, { 3 }, { 4 }, { 5 }, { 6 }, { 7 } };

        // Resultado das observações de X
        double[] y  = new double[] { 800, 1100, 1400, 1500, 1570, 1700};

        // Cria o modelo
        // OLS regr = new OLS(X,y);
        OLS regr = new OLS.Trainer().train(X,y);

        // Realiza a predição
        double y_pred = regr.predict(new double[] { 3 });

        System.out.println("Valor previsto: " + y_pred);
    }

}
