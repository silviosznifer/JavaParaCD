import org.apache.log4j.BasicConfigurator;
import smile.data.Attribute;
import smile.data.AttributeDataset;
import smile.data.NumericAttribute;
import smile.data.parser.DelimitedTextParser;
import smile.regression.OLS;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created by suemareverton on 23/08/17.
 */
public class Exercicio08 {

    public static void main(String[] args) {
        BasicConfigurator.configure();

        DelimitedTextParser parser = new DelimitedTextParser();
        parser.setColumnNames(true);
        parser.setDelimiter(";");
        parser.setCommentStartWith("#");
        parser.setResponseIndex(new NumericAttribute("length"), 2);

        Attribute[] attributes = new Attribute[2];
        attributes[0] =  new NumericAttribute("age");
        attributes[1] =  new NumericAttribute("water_temperature");

        try {
            AttributeDataset ds =
                    parser.parse(
                            attributes,
                            new File("src/main/datasets/peixes.csv")
                    );

            // Agora utilizaremos 2 variáveis preditoras => Regressão múltipla

            // Atribuindo variáveis preditoras
            double[][] X = ds.toArray(new double[ds.size()][]);

            // Atribuindo variável target
            double[] y = ds.toArray(new double[ds.size()]);

            // Criando modelo preditivo
            OLS regr = new OLS.Trainer().train(X, y);

            System.out.println("R2: " + regr.RSquared());

            // Qual o tamanho de um peixe com 41 dias em uma água com 25 graus?
            double[] vars = new double[] { 41, 25 };
            double resultado = regr.predict(vars);
            System.out.println("Resultado da previsão: " + resultado);

            // Agora observe a terceira linha do nosso dataset: 41;25;2120
            // Ele previu 2319 e uma observação do nosso dataset com os mesmos valores foi de 2120
            // A diferença é o erro ou resíduo

            // Mas, como ele chegou a este resultado?
            System.out.println("Intercept: " + regr.intercept());
            System.out.println("Coeficientes: " + regr.coefficients()[0] + " e " + regr.coefficients()[1]);

            // y = a + b1x1 + b2x2
            // onde, a = intercept
            // y = 3904.26 + (41 * 26.24) + (25 * -106.41)
            // y = 3904.26 + 1075.84 + (-2660.25)
            // y = 2319.85 (diferença no arredondamento das casas decimais)

            // Observe a relação das variáveis preditoras com os coeficientes
            // Para o peixe crescer mais rápido, é melhor uma água mais quente ou mais fria?
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
