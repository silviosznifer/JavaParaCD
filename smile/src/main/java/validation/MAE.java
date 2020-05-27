package validation;

import smile.validation.RegressionMeasure;

/**
 * Created by suemareverton on 29/08/17.
 */
public class MAE implements RegressionMeasure {

    public double measure(double[] truth, double[] prediction) {

        if(truth == null || truth.length == 0)
            throw new IllegalArgumentException("Parâmetro truth deverá conter 1 ou mais elementos");
        if(prediction == null || prediction.length == 0)
            throw new IllegalArgumentException("Parâmetro prediction deverá conter 1 ou mais elementos");
        if(truth.length != prediction.length)
            throw new IllegalArgumentException("Truth e prediction deverão conter o mesmo número de elementos");

        double e = 0;
        for(int i = 0; i < truth.length; i++) {
            e += Math.abs(truth[i] - prediction[i]);
        }

        return e / truth.length;
    }

}
