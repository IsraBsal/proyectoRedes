package pruebasproyecto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class proyecto {

	// Imprimir un arrelgo 
	static void imprime(int ar[]) 
	{ 
		for (int i = 1; i < ar.length; i++) { 
			System.out.print(ar[i]); 
		} 
		System.out.println(); 
	} 

	static void imprimearr(int arr[]) {
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]);
		}
		System.out.println();
	}

	static void imprimearr2(char arr[]) {
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]);
		}
		System.out.println();
		//Nuevo
	}

	//Termina impresion de arreglos 

	// calcula el valor de los bits redundantes
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

	//Genera el codigo hamming
	static int[] generateCode(String str, int M, int r) 
	{ 
		int[] ar = new int[r + M + 1]; 
		int j = 0; 
		for (int i = 1; i < ar.length; i++) { 
			if ((Math.ceil(Math.log(i) / Math.log(2)) 
					- Math.floor(Math.log(i) / Math.log(2))) 
					== 0) { 
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
		StringBuilder binario = new StringBuilder();
		for (byte b : bytes)
		{
			int val = b;
			for (int i = 0; i < 8; i++)
			{
				binario.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
			binario.append(' ');
		}
		System.out.println("'" + s + "' a binario:" + binario);
		return binario.toString();
	}

	static int[] codigo_Linea_Manchester(int[] palabra_completa_bin,char[] codigo_linea_manchester_arr) {
		for(int i=0;i<palabra_completa_bin.length;i++) {
			if(palabra_completa_bin[i]==0) {
				System.out.println("Nivel bajo en la posicion "+i);
				palabra_completa_bin[i]=-1;
				codigo_linea_manchester_arr[i]='b';
			}
			else {
				System.out.println("Nivel alto en la posicion "+i);
				codigo_linea_manchester_arr[i]='a';
			}
		}
		return palabra_completa_bin;
	}

	static void ruido(int[] arr_manchester,int[] aleatorios) {
		int j=0;
		int i=0;
		//Lleno el arreglo de aleatorios
		for(int z=0;z<aleatorios.length;z++) {
			Random r = new Random(); 
			int resultado = (int) (r.nextFloat() * (23 - 0) + 0);
			System.out.println("Soy el random " +resultado+ " En la posicion del arreglo aleatorios "+z);
			aleatorios[z]= resultado;
		}

		//


		while(j<arr_manchester.length) {

			if(arr_manchester[j+aleatorios[i]] == (-1)) {
				System.out.println("Habia un -1, introducimos un 1 en la posicion" + (j+aleatorios[i]));
				arr_manchester[j+aleatorios[i]]=1;
			}
			else {
				System.out.println("Habia un 1, introducimos un -1 en la posicion " + ( j + aleatorios[i]) );
				arr_manchester[j+aleatorios[i]] = (-1) ;
			}


			j=j+24;
			i=i+1;
		}
		System.out.println();
	}



	// Driver code 
	public static void main(String[] args) throws FileNotFoundException, IOException 
	{ 
		int indice_arreglo=0;
		int a=0;
		int contador_while=0;

		char[] palabra = leeArchivo("C:\\Users\\is_ga\\git\\crc-practica4\\src\\ArchivoTxt\\hola.txt" );
		char[] palabra_de1=new char[1]; //Areglo usado para tomar solo 1 letras de la palabra original del txt
		int b=12*palabra.length;
		int[] palabra_completa_bin=new int[b];
		char[] codigo_linea_manchester_arr=new char[b];

		//Para checar el arreglo
		System.out.println("Descomponiendo el txt en un arreglo de char, este sera el mensaje a transmitir");
		imprimearr2(palabra);
		System.out.println();
		//Termina

		while(indice_arreglo<palabra.length) {

			for(int j=0;j<1;j++) {
				//System.out.println("valor de j= "+j);
				palabra_de1[j]=palabra[indice_arreglo];
				indice_arreglo++;
			}

			String datastream = String.valueOf(palabra_de1);
			System.out.println("Palabra a transmitir:"+datastream);
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

			System.out.println("Generando el codigo de hamming "); 
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
		imprimearr(palabra_completa_bin);
		//imprime(palabra_completa_bin);
		//		for(int i=0;i<palabra_completa_bin.length;i++) {
		//			System.out.print(palabra_completa_bin[i]);
		//		}
		System.out.println();
		System.out.println("Longitud = "+palabra_completa_bin.length);
		System.out.println();

		//Comienza codigo de linea

		System.out.println("Codigo de linea manchester");
		int[] palabra_completa_bin_manchester=codigo_Linea_Manchester(palabra_completa_bin,codigo_linea_manchester_arr);
		imprimearr(palabra_completa_bin_manchester);
		System.out.println();
		imprimearr2(codigo_linea_manchester_arr);
		//		for(int i=0;i<palabra_completa_bin_manchester.length;i++) {
		//			System.out.print(palabra_completa_bin_manchester[i]);
		//		}
		System.out.println();
		System.out.println("Introducimos ruido");
		int tam= palabra_completa_bin_manchester.length/24;
		System.out.println(tam);
		int[] aleatorios=new int[tam];
		ruido(palabra_completa_bin_manchester, aleatorios);
		System.out.println("Palabra con ruido");
		imprimearr(palabra_completa_bin_manchester);



		//Termina mi codigo




		// Valores que necesita el codigo original -----------------------------------------------------------------// 
		/*
		String str = "01011010"; 
		int M = str.length(); 
		int r = 1; */
		//Terminan valores-------------------------------------------------------------------------------------------//



	} 




}
