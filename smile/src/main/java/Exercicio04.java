import org.apache.log4j.BasicConfigurator;
import smile.data.AttributeDataset;
import smile.data.NumericAttribute;
import smile.data.parser.DelimitedTextParser;
import smile.regression.OLS;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by suemareverton on 23/08/17.
 */
public class Exercicio04 {

    public static void main(String[] args) {
        BasicConfigurator.configure();

        // Agora vamos novamente criar um modelo de regressão

        // Estamos utilizando DelimitedTextParser mas há outros parsers (ArffParser, GCTParser, etc).
        DelimitedTextParser parser = new DelimitedTextParser();

        // Marcador de comentários no arquivo de dataset, o parser ignorará estas linhas
        parser.setCommentStartWith("#");

        // Dataset possui cabeçalho com nome das colunas
        parser.setColumnNames(true);

        // Delimitador (no caso espaço, mas poderia ser vírgula, ponto e vírgula, etc.)
        parser.setDelimiter(";");

        // Importante! Ler a documentação
        // https://github.com/haifengl/smile

        parser.setResponseIndex(new NumericAttribute("valor"), 1);

        try {
            AttributeDataset ds = parser.parse("src/main/datasets/AutoInsurSweden.txt");

            // Atribuindo variáveis preditoras
            double[][] X = ds.toArray(new double[ds.size()][]);

            // Atribuindo variável target
            double[] y = ds.toArray(new double[ds.size()]);

            // Conferindo valores
            System.out.println(X[0][0]);
            System.out.println(y[0]);

            // Criando modelo preditivo
            OLS regr = new OLS.Trainer().train(X,y);

            System.out.println("Intercept: " + regr.intercept());
            System.out.println("Quantos coeficientes? " + regr.coefficients().length);
            System.out.println("Slope: " + regr.coefficients()[0]);
            System.out.println("R2: " + regr.RSquared());

            // Resíduos
            // Calcular manualmente o resíduo do primeiro ponto para comparar com o valor obtido aqui
            double[] residuos = regr.residuals();
            for(double residuo : residuos) {
                System.out.println("Resíduo: " + residuo);
            }

            // Criando um array para valores previstos
            double[] y_pred = new double[X.length];
            for(int i = 0; i < X.length; i++)
                y_pred[i] = regr.predict(X[i]);

            System.out.println("108 reinvindicações: " + regr.predict(new double[] { 108 }));
            System.out.println("Resíduo da primeira observação: " + (Math.abs(y[0] - y_pred[0])));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
