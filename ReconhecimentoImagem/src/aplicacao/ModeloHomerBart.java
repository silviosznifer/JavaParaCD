package aplicacao;

import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ModeloHomerBart {
	private Instances instancias;
	private DataSource ds = new DataSource("caracteristicas.arff");
	
	public ModeloHomerBart() throws Exception {
		this.instancias = ds.getDataSet();
		this.instancias.setClassIndex(6);
	}
	
	public Instances getInstancias() {
		return this.instancias;
	}
	
	public int classificarNaiveBayes(String novaInstancia) throws Exception {
		
		Instance novo = new DenseInstance(7); // número de carcteristicas
		novo.setDataset(this.instancias);
		NaiveBayes nb = new NaiveBayes();
		nb.buildClassifier(this.instancias);
		
		float [] carcteristicas = this.separaNovaInstancia(novaInstancia);
		
		novo.setValue(0, carcteristicas[0]);
		novo.setValue(1, carcteristicas[1]);
		novo.setValue(2, carcteristicas[2]);
		novo.setValue(3, carcteristicas[3]);
		novo.setValue(4, carcteristicas[4]);
		novo.setValue(5, carcteristicas[5]);
		
		double resultado[] = nb.distributionForInstance(novo);
		
		System.out.println(resultado[0] + " - " + resultado[1]);		
		if (resultado[1] > resultado[0]) return 1;
		else return 0;
	}
	
	public int classificarJ48(String novaInstancia) throws Exception {
		
		Instance novo = new DenseInstance(7); // número de carcteristicas
		novo.setDataset(this.instancias);
		
		J48 m = new J48();
		m.buildClassifier(this.instancias);
		
		float [] carcteristicas = this.separaNovaInstancia(novaInstancia);
		
		novo.setValue(0, carcteristicas[0]);
		novo.setValue(1, carcteristicas[1]);
		novo.setValue(2, carcteristicas[2]);
		novo.setValue(3, carcteristicas[3]);
		novo.setValue(4, carcteristicas[4]);
		novo.setValue(5, carcteristicas[5]);
		
		double resultado[] = m.distributionForInstance(novo);
		System.out.println(resultado[0] + " - " + resultado[1]);
		
		if (resultado[1] > resultado[0]) return 1;
		else return 0;
	
	}
	
	private float[] separaNovaInstancia(String novaInstancia) {
		
		String [] c = novaInstancia.split(",");
		float [] retorno = new float[c.length];
		
		for (int i = 0; i < c.length-1; i++) {
			retorno[i] = Float.parseFloat(c[i]);
		}
		
		return retorno;
		
	}
	
	

}
