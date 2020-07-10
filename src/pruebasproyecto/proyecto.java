package pruebasproyecto;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import org.xml.sax.helpers.ParserFactory;

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

	//Convertir la cadena a array
	static int[] parse(String num){
		int[] arr = new int[num.length()];
		for(int i=0; i<num.length(); i++){
			arr[i] = Character.getNumericValue(num.charAt(i));
		}
		return arr;
	}


	//	// calcula el valor de los bits redundantes
	//	static int[] calculation(int[] ar, int r) 
	//	{ 
	//		for (int i = 0; i < r; i++) { 
	//			int x = (int)Math.pow(2, i); 
	//			for (int j = 1; j < ar.length; j++) { 
	//				if (((j >> i) & 1) == 1) { 
	//					if (x != j) 
	//						ar[x] = ar[x] ^ ar[j]; 
	//				} 
	//			} 
	//			System.out.println("r" + x + " = "
	//					+ ar[x]); 
	//		} 
	//
	//		return ar; 
	//	} 

	//Genera el codigo hamming
	//	static int[] generateCode(String str, int M, int r) 
	//	{ 
	//		int[] ar = new int[r + M + 1]; 
	//		int j = 0; 
	//		for (int i = 1; i < ar.length; i++) { 
	//			if ((Math.ceil(Math.log(i) / Math.log(2)) 
	//					- Math.floor(Math.log(i) / Math.log(2))) 
	//					== 0) { 
	//				ar[i] = 0; 
	//			} 
	//			else { 
	//
	//				// codeword[i] = dataword[j] 
	//				ar[i] = (int)(str.charAt(j) - '0'); 
	//				j++; 
	//			} 
	//		} 
	//		return ar; 
	//	} 

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
				System.out.println("Nivel bajo en la posicion " +i);
				palabra_completa_bin[i]=-1;
				codigo_linea_manchester_arr[i]='b';
			}
			else {
				System.out.println("Nivel alto en la posicion " +i);
				codigo_linea_manchester_arr[i]='a';
			}
		}
		return palabra_completa_bin;
	}

	static void ruido(int[] arr_manchester,int[] aleatorios) { //Funcion que introduce ruido cada 24 bits 
		int j=0;
		int i=0;
		//Lleno el arreglo de aleatorios
		for(int z=0;z<aleatorios.length;z++) {
			Random r = new Random(); 
			int resultado = (int) (r.nextFloat() * (23 - 0) + 0);
			System.out.println("Soy el random " +resultado+ " En la posicion del arreglo aleatorios "+z);
			aleatorios[z]= resultado;
		}

		while(j<arr_manchester.length) { //Intercambiar por i
			System.out.println("Valor de J=" +j);
			if(i==aleatorios.length) {
				return;
			}
			if(arr_manchester[j+aleatorios[i]] == (-1)) {
				System.out.println("Habia un -1, introducimos un 1 en la posicion " + (j+aleatorios[i]));
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

	static void decodifica_Manchester(int[] arr_manchester,char[] cod_lin_manchester,int errores[]) {
		int pos_error=0;
		//decodificacion 
		for(int i=0;i<arr_manchester.length;i++) {
			if(arr_manchester[i]==(-1)) {
				if(cod_lin_manchester[i]!='b') {
					System.out.println("Error en la posicion " +i+ " Es un nivel alto y hay un nivel bajo");
					errores[pos_error]=i;
					pos_error++;
					arr_manchester[i]=0;
				}
				else {
					arr_manchester[i]=0;
				}

			}
			else {
				if(cod_lin_manchester[i]!='a') {
					System.out.println("Error en la posicion " + i +  " Es un nivel bajo y hay un nivel alto");
					errores[pos_error]=i;
					pos_error++;
					arr_manchester[i]=1;
				}
				else {
					arr_manchester[i]=1;
				}
			}
		}
		System.out.println();
	}



	// Driver code 
	public static void main(String[] args) throws FileNotFoundException, IOException 
	{ 
		int indice_arreglo=0;
		int a=0;
		int contador_while=0;

		char[] palabra = leeArchivo("C:\\Users\\is_ga\\Documents\\hola.txt" );
		char[] palabra_de1=new char[1]; //Areglo usado para tomar solo 1 letras de la palabra original del txt
		int b=12*palabra.length;
		int[] palabra_completa_bin=new int[b];
		char[] codigo_linea_manchester_arr=new char[b];

		//Para checar el arreglo
		System.out.println("Descomponiendo el txt en un arreglo de char, este sera el mensaje a transmitir");
		imprimearr2(palabra);
		System.out.println();
		//Termina

		//Comienza la codificacion Haming-----------------------------------------------------------// 
		while(indice_arreglo<palabra.length) {

			for(int j=0;j<1;j++) {
				//System.out.println("valor de j= "+j);
				palabra_de1[j]=palabra[indice_arreglo];
				indice_arreglo++;
			}


			String datastream = String.valueOf(palabra_de1);
			System.out.println();
			System.out.println("Palabra a transmitir:"+datastream);
			String str=convertirBinario(datastream);
			str=str.substring(0, 8);
			System.out.println("Longitud del binario "+str.length());

			//Nuevo hamming
			int [] p1=parse(str);
			//System.out.println("El arreglo de la palabra es y tiene longitud"+p1.length);
			//imprimearr(p1);
			int c[] = generateCode1(p1);
			System.out.println("Palabra con codigo hamming:");
			imprimearr(c);
			//Termina nuevo hamming

			//Comienza a construirse el arreglo que contiene toda la palabra
			if(contador_while==0) {
				//				for(int i=0;i<palabra_completa_bin.length;i++) {
				//					palabra_completa_bin[i]=0;
				//				}
				System.arraycopy(c, 0, palabra_completa_bin, 0, 12);
				contador_while++;
			}
			else {
				System.arraycopy(c, 0, palabra_completa_bin, a=a+12, 12);
				contador_while++;
			}
			//Termina la construccion del arreglo de hamming

			contador_while++;

		}


		System.out.println("Palabra completa despues de hamming");
		imprimearr(palabra_completa_bin);
		System.out.println();
		System.out.println("Longitud = "+palabra_completa_bin.length);
		System.out.println();
		//Termina la codificacion Hamming----------------------------------------------// 

		//Comienza codigo de linea------------------------------------------------------//
		System.out.println("Codigo de linea manchester");
		int[] palabra_completa_bin_manchester=codigo_Linea_Manchester(palabra_completa_bin,codigo_linea_manchester_arr);
		imprimearr(palabra_completa_bin_manchester);
		System.out.println();
		imprimearr2(codigo_linea_manchester_arr);
		System.out.println();
		//Termina codigo de linea manchester------------------------------------//////////

		//Introducimos ruido-------------------------------------------/
		System.out.println("Introducimos ruido");
		int tam= palabra_completa_bin_manchester.length/24;
		System.out.println(tam);
		int[] aleatorios=new int[tam];
		ruido(palabra_completa_bin_manchester, aleatorios);
		System.out.println("Palabra con ruido");
		imprimearr(palabra_completa_bin_manchester);
		//Termina ruido----------------------------------------------------/

		//Decodificamos manchester---------------------------------------------/
		System.out.println("Decodificamos manchester con ruido");
		int[] pos_errores=new int[aleatorios.length];
		decodifica_Manchester(palabra_completa_bin_manchester, codigo_linea_manchester_arr, pos_errores);
		imprimearr(palabra_completa_bin_manchester);
		//Termina codificacion manchester--------------------------------------------------------//

		//Comienza deteccion y correccion de error en hamming  

		for(int z=0;z<pos_errores.length;z++) {
			int error = pos_errores[z];
			System.out.println("Corrigiendo error en la posicion"+pos_errores[z]);
			receive(palabra_completa_bin_manchester, palabra_completa_bin_manchester.length - palabra.length);

		}




		//Termina deteccion y correcion de error en hamming

		//Termina mi codigo




		// Valores que necesita el codigo original -----------------------------------------------------------------// 
		/*
		String str = "01011010"; 
		int M = str.length(); 
		int r = 1; */
		//Terminan valores-------------------------------------------------------------------------------------------//



	} 

	//Metodos del hamming

	static int[] generateCode1(int a[]) {
		// We will return the array 'b'.
		int b[];

		// We find the number of parity bits required:
		int i=0, parity_count=0 ,j=0, k=0;
		while(i < a.length) {
			// 2^(parity bits) must equal the current position
			// Current position is (number of bits traversed + number of parity bits + 1).
			// +1 is needed since array indices start from 0 whereas we need to start from 1.

			if(Math.pow(2,parity_count) == i+parity_count + 1) {
				parity_count++;
			}
			else {
				i++;
			}
		}

		// Length of 'b' is length of original data (a) + number of parity bits.
		b = new int[a.length + parity_count];

		// Initialize this array with '2' to indicate an 'unset' value in parity bit locations:

		for(i=1 ; i <= b.length ; i++) {
			if(Math.pow(2, j) == i) {
				// Found a parity bit location.
				// Adjusting with (-1) to account for array indices starting from 0 instead of 1.

				b[i-1] = 2;
				j++;
			}
			else {
				b[k+j] = a[k++];
			}
		}
		for(i=0 ; i < parity_count ; i++) {
			// Setting even parity bits at parity bit locations:

			b[((int) Math.pow(2, i))-1] = getParity(b, i);
		}
		return b;
	}

	static int getParity(int b[], int power) {
		int parity = 0;
		for(int i=0 ; i < b.length ; i++) {
			if(b[i] != 2) {
				// If 'i' doesn't contain an unset value,
				// We will save that index value in k, increase it by 1,
				// Then we convert it into binary:

				int k = i+1;
				String s = Integer.toBinaryString(k);

				//Nw if the bit at the 2^(power) location of the binary value of index is 1
				//Then we need to check the value stored at that location.
				//Checking if that value is 1 or 0, we will calculate the parity value.

				int x = ((Integer.parseInt(s))/((int) Math.pow(10, power)))%10;
				if(x == 1) {
					if(b[i] == 1) {
						parity = (parity+1)%2;
					}
				}
			}
		}
		return parity;
	}

	static void receive(int a[], int parity_count) {
		// This is the receiver code. It receives a Hamming code in array 'a'.
		// We also require the number of parity bits added to the original data.
		// Now it must detect the error and correct it, if any.

		int power;
		// We shall use the value stored in 'power' to find the correct bits to check for parity.

		int parity[] = new int[parity_count];
		// 'parity' array will store the values of the parity checks.

		String syndrome = new String();
		// 'syndrome' string will be used to store the integer value of error location.

		for(power=0 ; power < parity_count ; power++) {
			// We need to check the parities, the same no of times as the no of parity bits added.

			for(int i=0 ; i < a.length ; i++) {
				// Extracting the bit from 2^(power):

				int k = i+1;
				String s = Integer.toBinaryString(k);
				int bit = ((Integer.parseInt(s))/((int) Math.pow(10, power)))%10;
				if(bit == 1) {
					if(a[i] == 1) {
						parity[power] = (parity[power]+1)%2;
					}
				}
			}
			syndrome = parity[power] + syndrome;
		}
		// This gives us the parity check equation values.
		// Using these values, we will now check if there is a single bit error and then correct it.

		int error_location = Integer.parseInt(syndrome, 2);
		if(error_location != 0) {
			System.out.println("Error is at location " + error_location + ".");
			a[error_location-1] = (a[error_location-1]+1)%2;
			System.out.println("Corrected code is:");
			for(int i=0 ; i < a.length ; i++) {
				System.out.print(a[a.length-i-1]);
			}
			System.out.println();
		}
		else {
			System.out.println("There is no error in the received data.");
		}

		// Finally, we shall extract the original data from the received (and corrected) code:
		System.out.println("Original data sent was:");
		power = parity_count-1;
		for(int i=a.length ; i > 0 ; i--) {
			if(Math.pow(2, power) != i) {
				System.out.print(a[i-1]);
			}
			else {
				power--;
			}
		}
		System.out.println();
	}

	//Termina metodos 	






}
