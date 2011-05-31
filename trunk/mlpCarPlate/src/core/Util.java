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
			return SAIDA_ESPERADA_A;
		if (letra == "B")
			return SAIDA_ESPERADA_B;
		if (letra == "C")
			return SAIDA_ESPERADA_C;
		if (letra == "D")
			return SAIDA_ESPERADA_D;
		if (letra == "F")
			return SAIDA_ESPERADA_F;
		if (letra == "G")
			return SAIDA_ESPERADA_G;
		if (letra == "I")
			return SAIDA_ESPERADA_I;
		if (letra == "K")
			return SAIDA_ESPERADA_K;
		if (letra == "L")
			return SAIDA_ESPERADA_L;
		if (letra == "M")
			return SAIDA_ESPERADA_M;
		if (letra == "N")
			return SAIDA_ESPERADA_N;
		if (letra == "O")
			return SAIDA_ESPERADA_O;
		if (letra == "P")
			return SAIDA_ESPERADA_P;
		if (letra == "R")
			return SAIDA_ESPERADA_R;
		if (letra == "U")
			return SAIDA_ESPERADA_U;
		if (letra == "Y")
			return SAIDA_ESPERADA_Y;
		if (letra == "Z")
			return SAIDA_ESPERADA_Z;
		
		return null;
	}

    public static String converteDeNumeroParaArrayDeBits(String numero) {
		if (numero == "0")
			return SAIDA_ESPERADA_0;
		if (numero == "1")
			return SAIDA_ESPERADA_1;
		if (numero == "2")
			return SAIDA_ESPERADA_2;
		if (numero == "3")
			return SAIDA_ESPERADA_3;
		if (numero == "4")
			return SAIDA_ESPERADA_4;
		if (numero == "5")
			return SAIDA_ESPERADA_5;
		if (numero == "6")
			return SAIDA_ESPERADA_6;
		if (numero == "7")
			return SAIDA_ESPERADA_7;
		if (numero == "8")
			return SAIDA_ESPERADA_8;
		if (numero == "9")
			return SAIDA_ESPERADA_9;
		
		return null;
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
