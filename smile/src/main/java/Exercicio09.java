import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.apache.log4j.BasicConfigurator;

import smile.data.Attribute;
import smile.data.AttributeDataset;
import smile.data.NumericAttribute;
import smile.data.parser.DelimitedTextParser;
import smile.regression.OLS;
import smile.validation.MSE;
import smile.validation.RMSE;
import validation.MAE;
import validation.R2;

/**
 * Created by suemareverton on 23/08/17.
 */
public class Exercicio09 {

    public static void main(String[] args) {
        BasicConfigurator.configure();

        DelimitedTextParser parser = new DelimitedTextParser();
        parser.setColumnNames(true);
        parser.setDelimiter(",");
        parser.setCommentStartWith("#");
        parser.setResponseIndex(new NumericAttribute("Sales"), 3);

        Attribute[] attributes = new Attribute[3];
        attributes[0] =  new NumericAttribute("TV");
        attributes[1] =  new NumericAttribute("Radio");
        attributes[2] =  new NumericAttribute("Newspaper");

        try {
            AttributeDataset ds =
                    parser.parse(
                            attributes,
                            new File("src/main/datasets/advertising.csv")
                    );

            // Agora utilizaremos 3 variáveis preditoras => Regressão múltipla

            // Atribuindo variáveis preditoras
            double[][] X = ds.toArray(new double[ds.size()][]);

            // Atribuindo variável target
            double[] y = ds.toArray(new double[ds.size()]);

            // Divisão dos dados em treino e teste
            float trainSize = 0.8f;
            int trainSamples = (int) (ds.size() * trainSize);
            int testSamples = ds.size() - trainSamples;

            double[][] X_train = new double[trainSamples][];
            double[][] X_test  = new double[testSamples][];
            double[] y_train = new double[trainSamples];
            double[] y_test = new double[testSamples];

            System.arraycopy(X,0,X_train,0,trainSamples);
            System.arraycopy(X,trainSamples,X_test,0,testSamples);
            System.arraycopy(y,0,y_train,0,trainSamples);
            System.arraycopy(y,trainSamples,y_test,0,testSamples);

            // Qual o problema deste tipo de divisão de dados?

            // Criando modelo preditivo
            OLS regr = new OLS.Trainer().train(X_train, y_train);

            // Criando um array para valores previstos nos dados de treino
            double[] y_pred_train = new double[y_train.length];
            for(int i = 0; i < y_train.length; i++) {
                y_pred_train[i] = regr.predict(X_train[i]);
            }

            // Criando um array para valores previstos nos dados de teste
            double[] y_pred_test = new double[y_test.length];
            for(int i = 0; i < y_test.length; i++)
                y_pred_test[i] = regr.predict(X_test[i]);

            System.out.println("---");
            System.out.println("MAE nos dados de treino: " + new MAE().measure(y_train, y_pred_train));
            System.out.println("MAE nos dados de teste: "  + new MAE().measure(y_test, y_pred_test));
            System.out.println("---");
            System.out.println("MSE nos dados de treino: " + new MSE().measure(y_train, y_pred_train));
            System.out.println("MSE nos dados de teste: "  + new MSE().measure(y_test, y_pred_test));
            System.out.println("---");
            System.out.println("RMSE nos dados de treino: " + new RMSE().measure(y_train, y_pred_train));
            System.out.println("RMSE nos dados de teste: "  + new RMSE().measure(y_test, y_pred_test));
            System.out.println("---");
            System.out.println("R2: " + regr.RSquared());
            System.out.println("R2 (DSA) nos dados de treino: " + new R2().measure(y_train, y_pred_train));
            System.out.println("R2 (DSA) nos dados de teste: "  + new R2().measure(y_test, y_pred_test));

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
