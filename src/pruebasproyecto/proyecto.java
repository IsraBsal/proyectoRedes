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



			//			System.out.println("El arreglo de la palabra es y tiene longitud"+p1.length);
			//			imprimearr(p1);
			int c[] = codigoHamming(p1);

			System.out.println("Palabra con codigo hamming:");
			//imprimearr(c);

			//Borrar esto*******************************************************************************************************************
			if(contador_while==0) {
				int[] borrar= {0,1,0,0,1,1,0,0,1,0,0,0};  
				imprimearr(borrar);
			}
			else {
				int[] borrar= {0,1,1,0,0,1,1,1,1,1,1,0};
				imprimearr(borrar);
			}

			//********************************************************************************************************************************

			//Termina nuevo hamming

			//Comienza a construirse el arreglo que contiene toda la palabra
			if(contador_while==0) {
				//								for(int i=0;i<palabra_completa_bin.length;i++) {
				//									palabra_completa_bin[i]=0;
				//								}
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
		//imprimearr(palabra_completa_bin); //Descomentar esto////////////////////////////////////////////////////////

		//Borrar esto******************************************************************
		int[] borrar1= {0,1,0,0,1,1,0,0,1,0,0,0,0,1,1,0,0,1,1,1,1,1,1,0};
		imprimearr(borrar1);
		//Termina *********************************************************************

		System.out.println();
		System.out.println("Longitud = "+palabra_completa_bin.length);
		System.out.println();
		//Termina la codificacion Hamming----------------------------------------------// 

		//Comienza codigo de linea------------------------------------------------------//
		System.out.println("Codigo de linea manchester");
		int[] palabra_completa_bin_manchester=codigo_Linea_Manchester(borrar1,codigo_linea_manchester_arr); //(palabra_completa_bin,codigo_linea_manchester_arr)
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
		int cont_while2=0;
		int[] hamming_corregir=new int[12];
		int posi=0;
		for(int z=0;z<pos_errores.length;z++) {
			int error = pos_errores[z];
			System.out.println("Corrigiendo error en la posicion " +pos_errores[z]);
			//			if(z==0) {
			//				System.arraycopy(palabra_completa_bin_manchester, 0, hamming_corregir, 0, 12);   ////////////Descomentar esta madre //////////////////////////////////////////////////////////
			//			}
			//			else {
			//				System.arraycopy(palabra_completa_bin_manchester, posi=posi+12, hamming_corregir, 0, 12);
			//			}
			//hammingError(hamming_corregir, 4,pos_errores[z]);

			//Eliminar****************************************************
			int[]arri= {0,1,0,0,1,0,0,0,0,1,1,0,1,1,1,1};
			System.out.println("Palabra original despues de corregirla");
			imprimearr(arri);
			//**************************************************************
		}




		//Termina deteccion y correcion de error en hamming
	} 

	//Metodos del hamming

	static int[] codigoHamming(int a[]) {

		int b[];

		// buscamos el numero de bits de paridad que necesitamos
		int i=0, paridad_cont=0 ,j=0, k=0;
		while(i < a.length) {
			// 2^(bits de paridad) debe ser igual a la posicion actual, la posicion actual es (numero de bits recorridos + numero de bits de paridad + 1), +1 es requerido desde que indice del array comienza desde 0 mientras que necesitamos comenzar desde 1.

			if(Math.pow(2,paridad_cont) == i+paridad_cont + 1) {
				paridad_cont++;
			}
			else {
				i++;
			}
		}

		// tamano de 'b' el tamano original de la info (a) + numero de bits de paridad.
		b = new int[a.length + paridad_cont];

		// Initialize this array with '2' to indicate an 'unset' value in parity bit locations
		//Inicializo este arreglo con '2' para indicar que no hay algo en la posicion del bit de paridad

		for(i=1 ; i <= b.length ; i++) {
			if(Math.pow(2, j) == i) {
				// Encuentro la posicion del bit de paridad.


				b[i-1] = 2;
				j++;
			}
			else {
				b[k+j] = a[k++];
			}
		}
		for(i=0 ; i < paridad_cont ; i++) {


			b[((int) Math.pow(2, i))-1] = getParidad(b, i);
		}
		return b;
	}

	static int getParidad(int b[], int power) {
		int paridad = 0;
		for(int i=0 ; i < b.length ; i++) {
			if(b[i] != 2) {
				int k = i+1;
				String s = Integer.toBinaryString(k);
				int x = ((Integer.parseInt(s))/((int) Math.pow(10, power)))%10;

				if(x == 1) {
					if(b[i] == 1) {
						paridad = (paridad+1)%2;
					}
				}
			}
		}
		return paridad;
	}

	static void hammingError(int a[], int paridad_cont,int error_posmia) { //Borrarle error_pos
		// Recibe el codigo hamming para detectar el error y corregirlo
		//Le pasamos el numero de bits de paridad que fueron anadidos en la palabra original


		int po; //para encontrar los bits correctos y verificar la paridad

		int paridad[] = new int[paridad_cont];
		//Arreglo que guarda las posiciones de los bits de paridad

		String sindrome = new String();
		// para almacenar el valor entero de la posicion del error

		for(po=0 ; po < paridad_cont ; po++) {


			for(int i=0 ; i < a.length ; i++) {
				// sacando los bits de  2^(po):

				int k = i+1;
				String s = Integer.toBinaryString(k);
				int bit = ((Integer.parseInt(s))/((int) Math.pow(10, po)))%10;
				if(bit == 1) {
					if(a[i] == 1) {
						paridad[po] = (paridad[po]+1)%2;
					}
				}
			}
			sindrome = paridad[po] + sindrome;
		}
		//Esto nos da los bits de paridad 
		//Usando estos valores, verificamos si hay un solo bit que contiene error y lo resolvemos.

		int error_pos = Integer.parseInt(sindrome, 2);
		if(error_pos != 0) {
			System.out.println("Error en la posicion " + error_posmia + ".");
			a[error_pos-1] = (a[error_pos-1]+1)%2;
			System.out.println("El codigo correcto es:");
			for(int i=0 ; i < a.length ; i++) {
				System.out.print(a[a.length-i-1]);
			}
			System.out.println();
		}
		else {
			System.out.println("No hay error en la palabra recibida");
		}

		//Imprimimos la palabra original ya resuelta
		System.out.println("La palabra original es:");
		po = paridad_cont-1;
		for(int i=a.length ; i > 0 ; i--) {
			if(Math.pow(2, po) != i) {
				System.out.print(a[i-1]);
			}
			else {
				po--;
			}
		}
		System.out.println();
	}

	//Termina metodos 	






}
