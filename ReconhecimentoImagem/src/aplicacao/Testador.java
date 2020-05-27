package aplicacao;

import java.io.File;

import javax.swing.JFileChooser;

import opencv.ExtratorCaracteristicas;

public class Testador {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setDialogTitle("Abrir");
		fileChooser.setApproveButtonText("Abrir");
		fileChooser.setApproveButtonToolTipText("Abrir");
		fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
		fileChooser.setCurrentDirectory(new File("/media/silvio/Dispositivo/eclipse-workspace/ReconhecimentoImagem/imagens"));
		
		int option = fileChooser.showSaveDialog(null);
		
		if (option == JFileChooser.APPROVE_OPTION) {
		
			File arquivo = fileChooser.getSelectedFile();
			System.out.println(arquivo.getParent());
			System.out.println(arquivo.getPath());
			System.out.println(arquivo.getName());
			
			ExtratorCaracteristicas ec = new ExtratorCaracteristicas();
			
			String dadosImagem = ec.extrator(arquivo.getParent()+"/", arquivo.getName());
		
			System.out.println(dadosImagem);
			
			ModeloHomerBart m = new ModeloHomerBart();
			
			System.out.println(m.classificarNaiveBayes(dadosImagem));
			System.out.println(m.classificarJ48(dadosImagem));
			
		}
	}

}
