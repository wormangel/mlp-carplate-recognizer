package test;

import java.io.IOException;

import org.joone.engine.Monitor;
import org.joone.engine.NeuralNetEvent;
import org.joone.engine.NeuralNetListener;

import core.PreProcessador;
import core.ReconhecedorDePlacasMLP;

public class MockMain implements NeuralNetListener {

	public static void main(String[] args) throws IOException, InterruptedException {
		ReconhecedorDePlacasMLP rec = new ReconhecedorDePlacasMLP();
		rec.inicializaRedes();
		
		//testaDownSample();
		
		String xstr = new ReconhecedorDePlacasMLP().
		System.out.println(x.);
	}
	
	public static void testaDownSample() throws IOException, InterruptedException{
		int[] x = PreProcessador.ProcessaImagem("C:\\temp\\porFuncao\\treinamento\\3\\4.jpg");
		double count = 0;
		for (int i : x){
			System.out.print(i + ";");
			count++;
			if (count % 10 == 0.0){
				System.out.println();
			}
		}
	}

	@Override
	public void cicleTerminated(NeuralNetEvent e) {
		System.out.println("Ciclo terminado." + e.getSource());
		
	}

	@Override
	public void errorChanged(NeuralNetEvent e) {
		Monitor mon = (Monitor)e.getSource();
        System.out.println("Ciclo: "+(mon.getTotCicles()-mon.getCurrentCicle())+" RMSE:"+mon.getGlobalError());
		
	}

	@Override
	public void netStarted(NeuralNetEvent e) {
		System.out.println("Treinando...");
		
	}

	@Override
	public void netStopped(NeuralNetEvent e) {
		System.out.println("Treinado!");
		
	}

	@Override
	public void netStoppedError(NeuralNetEvent e, String error) {
		System.out.println("Erro! " + error);
		
	}

}
