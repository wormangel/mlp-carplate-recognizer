package test;

import java.io.IOException;

import core.PreProcessador;
import core.ReconhecedorDePlacasMLP;

public class MockMain {

	public static void main(String[] args) throws IOException, InterruptedException {
		ReconhecedorDePlacasMLP rec = new ReconhecedorDePlacasMLP();
		//rec.inicializaRedes();
		
		int[] x = PreProcessador.ProcessaImagem("C:\\temp\\plc\\001\\1.jpg");
		double count = 0;
		for (int i : x){
			System.out.print(i + ";");
			count++;
			if (count % 10 == 0.0){
				System.out.println();
			}
		}
		//String xstr = new ReconhecedorDePlacasMLP().
		//System.out.println(x.);
	}

}
