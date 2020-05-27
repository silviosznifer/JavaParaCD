import org.apache.log4j.BasicConfigurator;
import smile.data.AttributeDataset;
import smile.data.NumericAttribute;
import smile.data.parser.DelimitedTextParser;
import smile.regression.OLS;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by suemareverton on 22/08/17.
 */
public class Exercicio02 {

    public static void main(String[] args) {

        BasicConfigurator.configure();

        // Agora vamos novamente criar um modelo de regressão

        // Estamos utilizando DelimitedTextParser mas há outros parsers (ArffParser, GCTParser, etc).
        DelimitedTextParser parser = new DelimitedTextParser();

        // Marcador de comentários no arquivo de dataset, o parser ignorará estas linhas
        parser.setCommentStartWith("#");

        // Dataset possui cabeçalho com nome das colunas
        parser.setColumnNames(true);

        // Delimitador (no caso ponto e vírgula,)
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

            // Realizando a predição
            double y_pred = regr.predict(new double[] { 18 });

            System.out.println("Valor previsto para 18 solicitações: " + y_pred);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
