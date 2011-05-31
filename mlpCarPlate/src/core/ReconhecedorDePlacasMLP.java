package core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.joone.engine.FullSynapse;
import org.joone.engine.LinearLayer;
import org.joone.engine.Monitor;
import org.joone.engine.NeuralNetListener;
import org.joone.engine.SigmoidLayer;
import org.joone.engine.learning.TeachingSynapse;
import org.joone.io.FileInputSynapse;
import org.joone.io.MemoryOutputSynapse;
import org.joone.net.NeuralNet;

/**
 * Classe principal do projeto, contém as redes neurais MLP e fornece métodos para que uma aplicação efetue
 * operações usando a rede.
 * As seguintes letras não foram consideradas devido à escassez de samples: E,H,J,Q,S,T,V,W,X
 * Os seguintes números não foram considerados devido à escassez de samples: 
 * @author Lucas Medeiros, Vítor Amaral
 *
 */
public class ReconhecedorDePlacasMLP {

	private final int tamanhoDownSample = 15 * 10;
	private final int numeroDeSaidasLetra = 17; // Excluindo 9 letras
	private final int numeroDeSaidasNumero = 10;
	
	private final String diretorioArquivosTreinamento = "C:\\temp\\porFuncao\\treinamento";
	private final String diretorioArquivosValidacao = "C:\\temp\\porFuncao\\validacao";
	
	private final String arquivoEntradaTreinamentoLetras = "C:\\temp\\inputTreinamentoLetras.txt";
	private final String arquivoSaidaTreinamentoLetras = "C:\\temp\\outputTreinamentoLetras.txt";
	private final String arquivoEntradaTreinamentoNumeros = "C:\\temp\\inputTreinamentoNumeros.txt";
	private final String arquivoSaidaTreinamentoNumeros = "C:\\temp\\outputTreinamentoNumeros.txt";

	private NeuralNet redeAlfabeto = null;
	private NeuralNet redeNumeros = null;
	
    public static void main(String args[]) throws IOException, InterruptedException {
    	ReconhecedorDePlacasMLP rec = new ReconhecedorDePlacasMLP();
        
        rec.inicializaRedes();
        rec.treinaRedes();
    }
    
    public int[] preProcessa(String caminhoImagem) throws IOException, InterruptedException{
    	return PreProcessador.ProcessaImagem(caminhoImagem);
    }
    
    /**
     * Recebe uma string correspondente ao caminho no disco para a imagem a ser identificada.
     * @param path
     * @throws InterruptedException 
     * @throws IOException 
     */
    public String identificaNumero(String pathImagem) throws IOException, InterruptedException {
    	int[] entradaDaRede = preProcessa(pathImagem);
    	return identificaNumero(entradaDaRede);
    }
    
    /**
     * Recebe um array de bits correspondente à imagem downsamplezada e identifica a placa
     * @param imagem A imagem, já pre-processada
     * @throws IOException 
     */
    public String identificaNumero(int[] entradaDaRede) throws IOException{
    	// Cria arquivo temporário para servir de entrada para a rede
    	File arquivoDeEntrada = CriaArquivoParaReconhecimento(entradaDaRede);
    	
    	// Remove sinapses de entrada anteriores
    	redeNumeros.getInputLayer().removeAllInputs();
    	
    	// Adiciona uma nova camada de entrada 
		FileInputSynapse inputSynapse1 = new FileInputSynapse();

		inputSynapse1.setInputFile(arquivoDeEntrada);
		inputSynapse1.setName("input1");
		inputSynapse1.setAdvancedColumnSelector("1-150");
		inputSynapse1.setFirstRow(1);
		inputSynapse1.setLastRow(numeroDeSaidasNumero); // 10 números

		// Coloca esta sinapse como entrada da camada de entrada
		redeNumeros.getInputLayer().addInputSynapse(inputSynapse1);
		
		// Remove sinapses de saída anteriores
		redeNumeros.getOutputLayer().removeAllOutputs();
    	
    	// Adiciona uma nova camada de saída
		MemoryOutputSynapse outputSynapse1 = new MemoryOutputSynapse();

		// Coloca esta sinapse como saída da camada de entrada
		redeNumeros.getOutputLayer().addOutputSynapse(outputSynapse1);
		
		redeNumeros.getMonitor().setTotCicles(1);
		redeNumeros.getMonitor().setTrainingPatterns(1);
		redeNumeros.getMonitor().setLearning(false);
		redeNumeros.go();
		double[] resposta = outputSynapse1.getNextPattern();
		
		return Util.converteArrayDeBitsParaNumero(resposta);
	}
    
	/**
     * Recebe uma string correspondente ao caminho no disco para a imagem a ser identificada.
     * @param path
     * @throws InterruptedException 
     * @throws IOException 
     */
    public String identificaLetra(String pathImagem) throws IOException, InterruptedException {
    	int[] entradaDaRede = preProcessa(pathImagem);
    	return identificaLetra(entradaDaRede);
    }
    
	/**
     * Recebe um array de bits correspondente à imagem downsamplezada e identifica a placa
     * @param imagem A imagem, já pre-processada
     * @throws IOException 
     */
    public String identificaLetra(int[] entradaDaRede) throws IOException{
    	// Cria arquivo temporário para servir de entrada para a rede
    	File arquivoDeEntrada = CriaArquivoParaReconhecimento(entradaDaRede);
    	
    	// Remove sinapses de entrada anteriores
    	redeAlfabeto.getInputLayer().removeAllInputs();
    	
    	// Adiciona uma nova camada de entrada 
		FileInputSynapse inputSynapse1 = new FileInputSynapse();

		inputSynapse1.setInputFile(arquivoDeEntrada);
		inputSynapse1.setName("input1");
		inputSynapse1.setAdvancedColumnSelector("1-150");
		inputSynapse1.setFirstRow(1);
		inputSynapse1.setLastRow(numeroDeSaidasLetra); // 26 letras menos 9 excluídas

		// Coloca esta sinapse como entrada da camada de entrada
		redeAlfabeto.getInputLayer().addInputSynapse(inputSynapse1);
		
		// Remove sinapses de saída anteriores
    	redeAlfabeto.getOutputLayer().removeAllOutputs();
    	
    	// Adiciona uma nova camada de saída
		MemoryOutputSynapse outputSynapse1 = new MemoryOutputSynapse();

		// Coloca esta sinapse como saída da camada de entrada
		redeAlfabeto.getOutputLayer().addOutputSynapse(outputSynapse1);
		
		redeAlfabeto.getMonitor().setTotCicles(1);
		redeAlfabeto.getMonitor().setTrainingPatterns(1);
		redeAlfabeto.getMonitor().setLearning(false);
		redeAlfabeto.go();
		double[] resposta = outputSynapse1.getNextPattern();
		
		return Util.converteArrayDeBitsParaLetra(resposta);
	}

	public void treinaRedes() throws IOException, InterruptedException {		
    	treinaRedeAlfabeto();
    	treinaRedeNumeros();    	
    }
	
	/**
	 * Recebe um array de bits e escreve em um arquivo que servirá de entrada para a rede neural.
	 * @param entradaDaRede
	 * @return
	 * @throws IOException
	 */
	private File CriaArquivoParaReconhecimento(int[] entradaDaRede) throws IOException{
		File arquivoDeEntrada = new File("C:\\entradaNeural.txt");
    	FileWriter writer = new FileWriter(arquivoDeEntrada);
    	writer.flush();
    	StringBuilder str = new StringBuilder();
    	for(int i = 0; i < entradaDaRede.length; i++){
    		str.append(entradaDaRede.toString());
    		str.append(";");
    	}
    	str.deleteCharAt(str.length()-1);
    	writer.write(str.toString());
    	writer.close();
    	return arquivoDeEntrada;
	}
    
    protected void treinaRedeAlfabeto() throws IOException, InterruptedException{
    	
    	// Prepara a entrada para o treinamento da rede
    	geraArquivosDeTreinamentoParaLetras();
    	
		FileInputSynapse inputSynapse1 = new FileInputSynapse();

		inputSynapse1.setInputFile(new File(arquivoEntradaTreinamentoLetras));
		inputSynapse1.setName("input1");
		inputSynapse1.setAdvancedColumnSelector("1-150");
		inputSynapse1.setFirstRow(1);
		inputSynapse1.setLastRow(26); // 26 letras

		// Coloca esta sinapse como entrada da camada de entrada
		redeAlfabeto.getInputLayer().addInputSynapse(inputSynapse1);

		// Prepara a saída para o treinamento da rede
		FileInputSynapse desiredSynapse1 = new FileInputSynapse();
		
		desiredSynapse1.setInputFile(new File(arquivoSaidaTreinamentoLetras));
		desiredSynapse1.setName("desired1");
		desiredSynapse1.setAdvancedColumnSelector("1-150");
		desiredSynapse1.setFirstRow(1);
		desiredSynapse1.setLastRow(26);

		TeachingSynapse trainer = new TeachingSynapse();
		trainer.setDesired(desiredSynapse1);
		
		// Coloca esta sinapse como saída da camada de saída
		redeAlfabeto.setTeacher(trainer);
		redeAlfabeto.getOutputLayer().addOutputSynapse(trainer);
    	
    	// get the monitor object to train or feed forward
        Monitor monitor = redeAlfabeto.getMonitor();
        
        // set the monitor parameters
        monitor.setLearningRate(0.8);
        monitor.setMomentum(0.3);
        monitor.setTrainingPatterns(26);
        monitor.setTotCicles(2000);
        monitor.setLearning(true);
        redeAlfabeto.go(true);
        System.out.println("Network stopped. Last RMSE="+redeAlfabeto.getMonitor().getGlobalError());
    }
    
    protected void treinaRedeNumeros() throws IOException, InterruptedException{
    	
    	// Prepara a entrada para o treinamento da rede
    	geraArquivosDeTreinamentoParaNumeros();
    	
		FileInputSynapse inputSynapse1 = new FileInputSynapse();

		inputSynapse1.setInputFile(new File(arquivoEntradaTreinamentoNumeros));
		inputSynapse1.setName("input1");
		inputSynapse1.setAdvancedColumnSelector("1-150");
		inputSynapse1.setFirstRow(1);
		inputSynapse1.setLastRow(10); // 10 dígitos

		// Coloca esta sinapse como entrada da camada de entrada
		redeNumeros.getInputLayer().addInputSynapse(inputSynapse1);

		// Prepara a saída para o treinamento da rede
		FileInputSynapse desiredSynapse1 = new FileInputSynapse();
		
		desiredSynapse1.setInputFile(new File(arquivoSaidaTreinamentoNumeros));
		desiredSynapse1.setName("desired1");
		desiredSynapse1.setAdvancedColumnSelector("1-150");
		desiredSynapse1.setFirstRow(1);
		desiredSynapse1.setLastRow(26);

		TeachingSynapse trainer = new TeachingSynapse();
		trainer.setDesired(desiredSynapse1);
    	
		// Coloca esta sinapse como saída da camada de saída
		redeNumeros.setTeacher(trainer);
		redeNumeros.getOutputLayer().addOutputSynapse(trainer);
    	
    	// get the monitor object to train or feed forward
        Monitor monitor = redeNumeros.getMonitor();
        
        // set the monitor parameters
        monitor.setLearningRate(0.8);
        monitor.setMomentum(0.3);
        monitor.setTrainingPatterns(26);
        monitor.setTotCicles(2000);
        monitor.setLearning(true);
        redeNumeros.go(true);
        System.out.println("Network stopped. Last RMSE="+redeNumeros.getMonitor().getGlobalError());
    }
    
	public void inicializaRedes() {
		inicializaRedeAlfabeto();
		inicializaRedeNumeros();
	}

	private void inicializaRedeAlfabeto(){
		// Rede para letras

		// Camadas da rede
		LinearLayer input = new LinearLayer();
		SigmoidLayer hidden = new SigmoidLayer();
		SigmoidLayer output = new SigmoidLayer();

		// Tamanhos
		input.setRows(150); // 10x15 pixels cada caractere
		hidden.setRows(100);
		output.setRows(26);

		input.setLayerName("inputLayer");
		hidden.setLayerName("hiddenLayer");
		output.setLayerName("outputLayer");

		// Sinapses
		FullSynapse synapse_IH = new FullSynapse(); /* input -> hidden conn. */
		FullSynapse synapse_HO = new FullSynapse(); /* hidden -> output conn. */

		// Conecta a camada de entrada à camada escondida
		input.addOutputSynapse(synapse_IH);
		hidden.addInputSynapse(synapse_IH);

		// Conecta a camada escondida à camada de saída
		hidden.addOutputSynapse(synapse_HO);
		output.addInputSynapse(synapse_HO);

		// Adiciona as camadas criadas à rede neural
		redeAlfabeto = new NeuralNet();

		redeAlfabeto.addLayer(input, NeuralNet.INPUT_LAYER);
		redeAlfabeto.addLayer(hidden, NeuralNet.HIDDEN_LAYER);
		redeAlfabeto.addLayer(output, NeuralNet.OUTPUT_LAYER);
	}

	private void inicializaRedeNumeros(){
		// Rede para números

		// Camadas da rede
		LinearLayer input = new LinearLayer();
		SigmoidLayer hidden = new SigmoidLayer();
		SigmoidLayer output = new SigmoidLayer();

		// Tamanhos
		input.setRows(150); // 10x15 pixels cada caractere
		hidden.setRows(100);
		output.setRows(26);

		input.setLayerName("inputLayer");
		hidden.setLayerName("hiddenLayer");
		output.setLayerName("outputLayer");

		// Sinapses
		FullSynapse synapse_IH = new FullSynapse(); /* input -> hidden conn. */
		FullSynapse synapse_HO = new FullSynapse(); /* hidden -> output conn. */

		// Conecta a camada de entrada à camada escondida
		input.addOutputSynapse(synapse_IH);
		hidden.addInputSynapse(synapse_IH);

		// Conecta a camada escondida à camada de saída
		hidden.addOutputSynapse(synapse_HO);
		output.addInputSynapse(synapse_HO);
		
		// Adiciona as camadas criadas à rede neural
		redeNumeros = new NeuralNet();

		redeNumeros.addLayer(input, NeuralNet.INPUT_LAYER);
		redeNumeros.addLayer(hidden, NeuralNet.HIDDEN_LAYER);
		redeNumeros.addLayer(output, NeuralNet.OUTPUT_LAYER);

	}
	
	public void adicionaListener(NeuralNetListener listener){
		redeAlfabeto.addNeuralNetListener(listener);
		redeNumeros.addNeuralNetListener(listener);
	}
	
	public ReconhecedorDePlacasMLP(){}
	
	/**
	 * Cria uma instância do reconhecedor e se registra como observador dos eventos das redes neurais.
	 */
	public ReconhecedorDePlacasMLP(NeuralNetListener listener){
		adicionaListener(listener);
	}

	private void geraArquivosDeTreinamentoParaLetras() throws IOException, InterruptedException{
		File diretoriosTreinamento = new File(diretorioArquivosTreinamento);

		File inputTreinamento = new File(arquivoEntradaTreinamentoLetras);
		FileWriter writerInput = new FileWriter(inputTreinamento);
		writerInput.flush();
		
		File outputTreinamento = new File(arquivoSaidaTreinamentoLetras);
		FileWriter writerOutput = new FileWriter(outputTreinamento);
		writerOutput.flush();
		
		// Pra cada diretório (letra) no diretório de treinamento
		for (String dirLetra : diretoriosTreinamento.list()) {
			// Obtém a lista de imagens no diretório dessa letra
			File dirComImagensDaLetra = new File(diretorioArquivosTreinamento + "\\" + dirLetra);
			// Pra cada imagem dessa letra
			for (String arquivoImagem : dirComImagensDaLetra.list()) {
				// Faz downsample e converte para array de ints
				int[] imagemEmBits = preProcessa(dirComImagensDaLetra + "\\" + arquivoImagem);
				// Converte para string e escreve no arquivo de entrada
				writerInput.write(Util.converteArrayDeBitsParaString(imagemEmBits) + "\n");
				
				// Escreve no arquivo de saída esperada a linha correspondente ao caractere
				writerOutput.write(Util.converteDeLetraParaArrayDeBits(dirLetra) + "\n");
			}
		}
		
		writerInput.close();
		writerOutput.close();		
	}

	private void geraArquivosDeTreinamentoParaNumeros() throws IOException, InterruptedException{
		File diretoriosTreinamento = new File(diretorioArquivosTreinamento);

		File inputTreinamento = new File(arquivoEntradaTreinamentoNumeros);
		FileWriter writerInput = new FileWriter(inputTreinamento);
		writerInput.flush();
		
		File outputTreinamento = new File(arquivoSaidaTreinamentoNumeros);
		FileWriter writerOutput = new FileWriter(outputTreinamento);
		writerOutput.flush();
		
		// Pra cada diretório (número) no diretório de treinamento
		for (String dirNumero : diretoriosTreinamento.list()) {
			// Obtém a lista de imagens no diretório dessa letra
			File dirComImagensDoNumero = new File(diretorioArquivosTreinamento + "\\" + dirNumero);
			// Pra cada imagem dessa número
			for (String arquivoImagem : dirComImagensDoNumero.list()) {
				// Faz downsample e converte para array de ints
				int[] imagemEmBits = preProcessa(dirComImagensDoNumero + "\\" + arquivoImagem);
				// Converte para string e escreve no arquivo de entrada
				writerInput.write(Util.converteArrayDeBitsParaString(imagemEmBits) + "\n");
				
				// Escreve no arquivo de saída esperada a linha correspondente ao caractere
				writerOutput.write(Util.converteDeNumeroParaArrayDeBits(dirNumero) + "\n");
			}
		}
		
		writerInput.close();
		writerOutput.close();	
	}
	
	public static String SAIDA_ESPERADA_A = "1;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0";
	public static String SAIDA_ESPERADA_B = "0;1;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0";
	public static String SAIDA_ESPERADA_C = "0;0;1;0;0;0;0;0;0;0;0;0;0;0;0;0;0";
	public static String SAIDA_ESPERADA_D = "0;0;0;1;0;0;0;0;0;0;0;0;0;0;0;0;0";
	public static String SAIDA_ESPERADA_F = "0;0;0;0;1;0;0;0;0;0;0;0;0;0;0;0;0";
	public static String SAIDA_ESPERADA_G = "0;0;0;0;0;1;0;0;0;0;0;0;0;0;0;0;0";
	public static String SAIDA_ESPERADA_I = "0;0;0;0;0;0;1;0;0;0;0;0;0;0;0;0;0";
	public static String SAIDA_ESPERADA_K = "0;0;0;0;0;0;0;1;0;0;0;0;0;0;0;0;0";
	public static String SAIDA_ESPERADA_L = "0;0;0;0;0;0;0;0;1;0;0;0;0;0;0;0;0";
	public static String SAIDA_ESPERADA_M = "0;0;0;0;0;0;0;0;0;1;0;0;0;0;0;0;0";
	public static String SAIDA_ESPERADA_N = "0;0;0;0;0;0;0;0;0;0;1;0;0;0;0;0;0";
	public static String SAIDA_ESPERADA_O = "0;0;0;0;0;0;0;0;0;0;0;1;0;0;0;0;0";
	public static String SAIDA_ESPERADA_P = "0;0;0;0;0;0;0;0;0;0;0;0;1;0;0;0;0";
	public static String SAIDA_ESPERADA_R = "0;0;0;0;0;0;0;0;0;0;0;0;0;1;0;0;0";
	public static String SAIDA_ESPERADA_U = "0;0;0;0;0;0;0;0;0;0;0;0;0;0;1;0;0";
	public static String SAIDA_ESPERADA_Y = "0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;1;0";
	public static String SAIDA_ESPERADA_Z = "0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;1";
	
	public static String SAIDA_ESPERADA_0 = "1;0;0;0;0;0;0;0;0;0";
	public static String SAIDA_ESPERADA_1 = "0;1;0;0;0;0;0;0;0;0";
	public static String SAIDA_ESPERADA_2 = "0;0;1;0;0;0;0;0;0;0";
	public static String SAIDA_ESPERADA_3 = "0;0;0;1;0;0;0;0;0;0";
	public static String SAIDA_ESPERADA_4 = "0;0;0;0;1;0;0;0;0;0";
	public static String SAIDA_ESPERADA_5 = "0;0;0;0;0;1;0;0;0;0";
	public static String SAIDA_ESPERADA_6 = "0;0;0;0;0;0;1;0;0;0";
	public static String SAIDA_ESPERADA_7 = "0;0;0;0;0;0;0;1;0;0";
	public static String SAIDA_ESPERADA_8 = "0;0;0;0;0;0;0;0;1;0";
	public static String SAIDA_ESPERADA_9 = "0;0;0;0;0;0;0;0;0;1";
	
}
