package pruebasproyecto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class proyecto {

	// Imprimir un arrelgo 
	static void imprime(int ar[]) 
	{ 
		for (int i = 1; i < ar.length; i++) { 
			System.out.print(ar[i]); 
		} 
		System.out.println(); 
	} 

	// calculating value of redundant bits 
	static int[] calculation(int[] ar, int r) 
	{ 
		for (int i = 0; i < r; i++) { 
			int x = (int)Math.pow(2, i); 
			for (int j = 1; j < ar.length; j++) { 
				if (((j >> i) & 1) == 1) { 
					if (x != j) 
						ar[x] = ar[x] ^ ar[j]; 
				} 
			} 
			System.out.println("r" + x + " = "
					+ ar[x]); 
		} 

		return ar; 
	} 

	static int[] generateCode(String str, int M, int r) 
	{ 
		int[] ar = new int[r + M + 1]; 
		int j = 0; 
		for (int i = 1; i < ar.length; i++) { 
			if ((Math.ceil(Math.log(i) / Math.log(2)) 
					- Math.floor(Math.log(i) / Math.log(2))) 
					== 0) { 

				// if i == 2^n for n in (0, 1, 2, .....) 
				// then ar[i]=0 
				// codeword[i] = 0 ---- 
				// redundant bits are initialized 
				// with value 0 
				ar[i] = 0; 
			} 
			else { 

				// codeword[i] = dataword[j] 
				ar[i] = (int)(str.charAt(j) - '0'); 
				j++; 
			} 
		} 
		return ar; 
	} 

	static char[] leeArchivo(String archivo) throws FileNotFoundException, IOException {
		String cadena;
		char palabraarr[];
		FileReader f = new FileReader(archivo);
		BufferedReader b = new BufferedReader(f);
		while((cadena = b.readLine())!=null) {
			System.out.println("La palabra del txt es: "+cadena);
			return palabraarr=cadena.toCharArray();
		}
		b.close();
		return palabraarr=cadena.toCharArray();
	}

	static String convertirBinario(String s) {

		byte[] bytes = s.getBytes();
		StringBuilder binary = new StringBuilder();
		for (byte b : bytes)
		{
			int val = b;
			for (int i = 0; i < 8; i++)
			{
				binary.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
			binary.append(' ');
		}
		System.out.println("'" + s + "' to binary:" + binary);
		return binary.toString();
	}

	// Driver code 
	public static void main(String[] args) throws FileNotFoundException, IOException 
	{ 
		int indice_arreglo=0;
		int a=0;
		int contador_while=0;
		//Empieza mi codigo
		char[] palabra = leeArchivo("C:\\Users\\is_ga\\git\\crc-practica4\\src\\ArchivoTxt\\hola.txt" );
		char[] palabra_de1=new char[1]; //Areglo usado para tomar solo 1 letras de la palabra original del txt
		int b=12*palabra.length;
		int[] palabra_completa_bin=new int[b];

		//Para checar el arreglo
		System.out.println("Descomponiendo el txt en un arreglo de char");
		for  (int x=0; x<palabra.length; x++) {
			System.out.print(palabra[x]);
			System.out.println();
		}
		//Termina

		while(indice_arreglo<palabra.length) {

			for(int j=0;j<1;j++) {
				//System.out.println("valor de j= "+j);
				palabra_de1[j]=palabra[indice_arreglo];
				indice_arreglo++;
			}

			String datastream = String.valueOf(palabra_de1);
			System.out.println("Palabra a transmitir:"+datastream);





			//Variables del codigo original

			//			String str= datastream;
			//			System.out.println("to string= "+str);
			String str=convertirBinario(datastream);
			str=str.substring(0, 8);
			System.out.println("Longitud del binario "+str.length());
			int M = str.length(); 
			int r = 1;
			//

			while (Math.pow(2, r) < (M + r + 1)) { 
				// r is number of redundant bits 
				r++; 
			} 
			int[] ar = generateCode(str, M, r); //ar es el arreglo que contiene la palabra

			System.out.println("Generated hamming code "); 
			ar = calculation(ar, r); 
			imprime(ar);

			//Comienza a construirse el arreglo que contiene tod
			if(contador_while==0) {
				for(int i=0;i<palabra_completa_bin.length;i++) {
					palabra_completa_bin[i]=0;
				}
				System.arraycopy(ar, 0, palabra_completa_bin, 0, 12);
				contador_while++;
			}
			else {
				System.arraycopy(ar, 0, palabra_completa_bin, a=a+12, 12);
				contador_while++;
			}
			//Termina la construccion del arreglo de hamming

			contador_while++;

		}
		System.out.println("Palabra completa despues de hamming");
		imprime(palabra_completa_bin);
		System.out.println("Longitud = "+palabra_completa_bin.length);





		//Termina mi codigo




		// Valores que necesita el codigo original -----------------------------------------------------------------// 
		/*
		String str = "01011010"; 
		int M = str.length(); 
		int r = 1; */
		//Terminan valores-------------------------------------------------------------------------------------------//



	} 




}
