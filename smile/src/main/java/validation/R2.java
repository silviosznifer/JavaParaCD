package validation;

import smile.validation.RegressionMeasure;

/**
 * Created by suemareverton on 25/08/17.
 */
public class R2 implements RegressionMeasure {

    public double measure(double[] truth, double[] prediction) {

        if (truth == null || truth.length == 0)
            throw new IllegalArgumentException("Parâmetro truth deverá conter 1 ou mais elementos");
        if (prediction == null || prediction.length == 0)
            throw new IllegalArgumentException("Parâmetro prediction deverá conter 1 ou mais elementos");
        if (truth.length != prediction.length)
            throw new IllegalArgumentException("Truth e prediction deverão conter o mesmo número de elementos");

        // R2 = 1 - (SSE / SST)

        // Calculando valor médio de Y
        double y_total = 0;
        for (int i = 0; i < truth.length; i++)
            y_total += truth[i];

        double y_mean = y_total / truth.length;

        double sse = 0, sst = 0;
        for(int i = 0; i < truth.length; i++) {
            sse += Math.pow(truth[i]- prediction[i], 2);
            sst += Math.pow(truth[i] - y_mean, 2);
        }

        return 1 - sse / sst;
    }

}
