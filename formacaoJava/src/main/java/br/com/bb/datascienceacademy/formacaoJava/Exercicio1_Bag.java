package br.com.bb.datascienceacademy.formacaoJava;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

public class Exercicio1_Bag {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String [] args) {
		
		
		
		Bag b = new HashBag();
		
		b.add("Machine Learning", 8);
		
		b.add("Big Data", 3);
		
		System.out.println(b.size());
		
		
	}

}
