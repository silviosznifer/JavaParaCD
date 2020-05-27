package br.com.bb.datascienceacademy.formacaoJava;

import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.map.LinkedMap;

public class Exercicio2_3_Hashed_Ordered_Linked_Map {
	
	public static void main (String [] args) {
		//HashedMap<Integer, String> hm = new HashedMap<Integer, String>();
		OrderedMap<Integer, String> hm = new LinkedMap<Integer, String>();
		
		hm.put(1, "Silvio Sznifer");
		hm.put(1, "Silvio Sznifer 2");
		hm.put(7, "Alice Carolina");
		hm.put(5, "Davi Volpini");
		hm.put(3, "Lucas");
		hm.put(2, "Xavier");
		
		System.out.println(hm.get(hm.nextKey(hm.firstKey())));
		
		MapIterator<Integer, String> i = hm.mapIterator();
		Integer chave;
		
		while(i.hasNext()) {
			chave = i.next();
			
			System.out.println(chave + " - " + i.getValue());
			
			if (chave == 1) {
				System.out.println("Removendo o 1");
				i.remove();
			}
			
		}
		
		System.out.println(hm.size());
		
	
	}

}
