package core;

public class Util {
	
	public static String converteArrayDeBitsParaNumero(double[] resposta) {
    	for (int i = 0; i<= resposta.length; i++){
    		if (resposta[i] != 0) {
    			int numero = i + 1;
    			return String.valueOf(numero);
    		}
    	}
		return null;
	}
    
    public static String converteArrayDeBitsParaLetra(double[] resposta) {
    	for (int i = 0; i<= resposta.length; i++){
    		if (resposta[i] != 0) {
    			int posicaoAscii = i + 65;
    			return String.valueOf( (char) posicaoAscii );
    		}
    	}
		return null;
	}
    
    public static String converteArrayDeBitsParaString(int[] entrada){
		StringBuilder str = new StringBuilder();
    	for(int i = 0; i < entrada.length; i++){
    		str.append(entrada.toString());
    		str.append(";");
    	}
    	str.deleteCharAt(str.length()-1);
    	return str.toString();
	}
    
    public static String converteDeLetraParaArrayDeBits(String letra) {
		if (letra == "A")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_A;
		if (letra == "B")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_B;
		if (letra == "C")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_C;
		if (letra == "D")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_D;
		if (letra == "F")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_F;
		if (letra == "G")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_G;
		if (letra == "I")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_I;
		if (letra == "K")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_K;
		if (letra == "L")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_L;
		if (letra == "M")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_M;
		if (letra == "N")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_N;
		if (letra == "O")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_O;
		if (letra == "P")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_P;
		if (letra == "R")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_R;
		if (letra == "U")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_U;
		if (letra == "Y")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_Y;
		if (letra == "Z")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_Z;
		
		return null;
	}

    public static String converteDeNumeroParaArrayDeBits(String numero) {
		if (numero == "0")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_0;
		if (numero == "1")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_1;
		if (numero == "2")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_2;
		if (numero == "3")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_3;
		if (numero == "4")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_4;
		if (numero == "5")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_5;
		if (numero == "6")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_6;
		if (numero == "7")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_7;
		if (numero == "8")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_8;
		if (numero == "9")
			return ReconhecedorDePlacasMLP.SAIDA_ESPERADA_9;
		
		return null;
	}

}
