package ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.joone.engine.Monitor;
import org.joone.engine.NeuralNetEvent;
import org.joone.engine.NeuralNetListener;

import core.ReconhecedorDePlacasMLP;

public class ReconhecedorSwing extends JFrame implements NeuralNetListener {
	
	private static ReconhecedorDePlacasMLP reconhecedor = null;

	private JButton btnTreinar, btnValidar;
	
	private JTextField txtInputTreinamento, txtInputValidacao, txtInputVerificacao,
		txtOutputTreinamento, txtOutputValidacao, txtOutputVerificacao;
	
	// Botão para escolher a placa (diretório com as fotos dos caracteres individuais da placa)
	private JButton btnProcurarPlaca;
	
	public static void main(String[] args) {
		ReconhecedorSwing rec = new ReconhecedorSwing();
		
		reconhecedor = new ReconhecedorDePlacasMLP(rec);
		reconhecedor.inicializaRedes();

	}
	
	public ReconhecedorSwing() {
		btnTreinar = new JButton("Treinar as redes");
		getContentPane().add(btnTreinar);
		btnValidar = new JButton("Validar as redes");
		getContentPane().add(btnValidar);
		// btnVerificar = new JButton("Verificar placa de carro");
		//getContentPane().add(btnVerificar);
	}
	

	@Override
	public void cicleTerminated(NeuralNetEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void errorChanged(NeuralNetEvent e) {
		Monitor mon = (Monitor)e.getSource();
        System.out.println("Cycle: "+(mon.getTotCicles()-mon.getCurrentCicle())+" RMSE:"+mon.getGlobalError());
	}

	@Override
	public void netStarted(NeuralNetEvent e) {
		System.out.println("Training...");
	}

	@Override
	public void netStopped(NeuralNetEvent e) {
		System.out.println("Stopped");
	}

	@Override
	public void netStoppedError(NeuralNetEvent e, String error) {
		// TODO Auto-generated method stub

	}

}
