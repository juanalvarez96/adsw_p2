package es.upm.dit.adsw.ej2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.Random;

/**
 * Pruebas de diccionario
 * 
 * @author jpuente
 * @version 20160217
 */
public class DiccionarioTest2 {
	
	private Diccionario diccionarioBinario;
	private Diccionario diccionarioBST;
	
	private static int size = 10;
	
	// sin semilla, realmente aleatorio; cada ejecución, datos nuevos
	// monkey testing
	private final Random random = new Random();	
	
	@Before
	public void setUp() {
		diccionarioBinario = new DiccionarioBinario(size);
		diccionarioBST = new BST();
	}
	
	/**
	 * Diccionario vacío
	 */
	@Test
	public void testEmpty() {
		testEmpty(diccionarioBinario);
		testEmpty(diccionarioBST);
	}
	
	private void testEmpty(Diccionario diccionario) {
		assertEquals(0,diccionario.size());
		assertNull(diccionario.get("clave"));
		assertNull(diccionario.remove("clave)"));
	}
	
	/**
	 * Introducir clave nula
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testPut00() {
		testPut00(diccionarioBinario);
		testPut00(diccionarioBST);
	}
	
	private void testPut00 (Diccionario diccionario) {
        diccionario.put(null, "valor");
	}
	
	/**
	 * Introducir clave vacía
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testPut01() {
		testPut01(diccionarioBinario);
		testPut01(diccionarioBST);
	}
	
	private void testPut01(Diccionario diccionario) {
        diccionario.put("", "valor");
	}
	
	/**
	 * Introducir clave nueva
	 */
	@Test
	public void testPut02(){
		testPut02(diccionarioBinario);
		testPut02(diccionarioBST);
	}
	
	private void testPut02 (Diccionario diccionario) {
		diccionario.put("clave", "valor");
		assertEquals(1,diccionario.size());
		assertEquals("valor",diccionario.get("clave"));	
	}

	/** 
	 * Introducir clave repetida
	 */
	@Test
	public void testPut03 () {
		testPut03(diccionarioBinario);		
		testPut03(diccionarioBST);		
	}
	
	private void testPut03 (Diccionario diccionario) {
		diccionario.put("clave", "valor1");
		assertEquals(1,diccionario.size());
		diccionario.put("clave", "valor2");
		assertEquals(1,diccionario.size());
		assertEquals("valor2",diccionario.get("clave"));	
	}
	
	/**
	 * Introducir valor nuevo en diccionario lleno
	 */
	@Test (expected = RuntimeException.class)
	public void testPut04() {
		testPut04(diccionarioBinario);
	}
	
	private void testPut04 (Diccionario diccionario) {
		for (int i =0; i < size; i++) {
			diccionario.put(Integer.toString(i), "valor");
		}
		diccionario.put("otro","lleno");
	}
	
	/**
	 * Recuperar clave nula 
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testGet00(){
		testGet00(diccionarioBinario);
		testGet00(diccionarioBST);
	}
	
	private void testGet00 (Diccionario diccionario) {
		diccionario.get(null);
	}
 	
	/**
	 * Recuperar clave vacía
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testGet01() {
		testGet01(diccionarioBinario);
		testGet01(diccionarioBST);
	}
	
	private void testGet01 (Diccionario diccionario) {
		diccionario.get("");
	}
	
	/**
	 * Recuperar clave inexistente (diccionario vacío)
	 */
	@Test
	public void testGet02(){
		testGet02(diccionarioBinario);
		testGet02(diccionarioBST);
	}
	
	private void testGet02 (Diccionario diccionario) {
		assertNull(diccionario.get("clave"));			
	}

	/**
	 * Recuperar clave inexistente
	 */
	@Test
	public void testGet03(){
		testGet03(diccionarioBinario);
		testGet03(diccionarioBST);
	}
	
	private void testGet03 (Diccionario diccionario) {
		diccionario.put("clave", "valor");
		assertNull(diccionario.get("clave1"));		
	}

	/**
	 * Eliminar clave nula
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testRemove00(){
		testRemove00(diccionarioBinario);
		testRemove00(diccionarioBST);
	}
	
	private void testRemove00 (Diccionario diccionario) {
		diccionario.remove(null);
	}
	
	/**
	 * Eliminar clave vacía
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testRemove01(){
		testRemove01(diccionarioBinario);
		testRemove01(diccionarioBST);
	}
	
	private void testRemove01 (Diccionario diccionario) {
		diccionario.remove("");
	}
	
	
	/**
	 * Eliminar clave inexistente (diccionario vacío)
	 */
	@Test 
	public void testRemove02() {
		testRemove02(diccionarioBinario);
		testRemove02(diccionarioBST);
	}
	
	private void testRemove02 (Diccionario diccionario) {
		assertNull(diccionario.remove("clave"));
		assertEquals(0,diccionario.size());		
	}
	
	/**
	 * Eliminar clave inexistente
	 */
	@Test
	public void testRemove03() {
		testRemove03(diccionarioBinario);
		testRemove03(diccionarioBST);
	}
	
	private void testRemove03 (Diccionario diccionario) {
		diccionario.put("clave", "valor");
		assertNull(diccionario.remove("clave1"));
		assertEquals(1,diccionario.size());
	}
	
	/**
	 * Eliminar clave existente
	 */
	@Test
	public void testRemove04(){
		testRemove04(diccionarioBinario);
		testRemove04(diccionarioBST);
	}
	
	private void testRemove04 (Diccionario diccionario) {		
		diccionario.put("clave", "valor");
		assertEquals("valor", diccionario.remove("clave"));
		assertEquals(0,diccionario.size());
	}
	
	/**
	 * Eliminar clave existente, comprobar que el resto sigue igual
	 */
	@Test
	public void testRemove05(){
		testRemove05(diccionarioBinario);
		testRemove05(diccionarioBST);
	}
	
	private void testRemove05 (Diccionario diccionario) {			
		diccionario.put("clave1", "valor1");
		diccionario.put("clave2", "valor2");
		diccionario.put("clave3", "valor3");
		assertEquals(3,diccionario.size());
		assertEquals("valor2", diccionario.remove("clave2"));
		assertEquals("valor1", diccionario.get("clave1"));
		assertNull(diccionario.get("clave2"));
		assertEquals("valor3", diccionario.get("clave3"));
		assertEquals(2,diccionario.size());
	}
	
	/**
	 * Borrar todo el diccionario
	 */
	@Test
	public void testClear00(){
		testClear00(diccionarioBinario);
		testClear00(diccionarioBST);
	}
	
	private void testClear00 (Diccionario diccionario) {	
		diccionario.put("clave", "valor");
		diccionario.clear();
		assertNull(diccionario.get("clave"));
		assertEquals(0,diccionario.size());
	}
	
	/**
	 * Test aleatorios
	 */
	@Test
	public void testN() {
		testN(diccionarioBinario);
		testN(diccionarioBST);
	}
	   
	private void testN(Diccionario diccionario) {
		int N = 10;
		
		for (int vez = 0; vez < 10; vez++) {
			int[] datos = mkData(N);

			// carga el diccionario con claves y valores aleatorios
			for (int dato : datos) {
				String clave = String.format("[%d]", dato);
				String valor = String.valueOf(dato);
				diccionario.put(clave, valor);
			}
			assertEquals(N, diccionario.size());

			// recarga
			for (int dato : datos) {
				String clave = String.format("[%d]", dato);
				String valor = String.valueOf(dato);
				diccionario.put(clave, valor);
			}
			assertEquals(N, diccionario.size());

			// acceso
			for (int dato : datos) {
				String clave = String.format("[%d]", dato);
				String valor = String.valueOf(dato);
				assertEquals(valor, diccionario.get(clave));
			}

			// borrado
			for (int dato : datos) {
				String clave = String.format("[%d]", dato);
				String valor = String.valueOf(dato);
				assertEquals(valor, diccionario.remove(clave));
			}
			assertEquals(0, diccionario.size());

			diccionario.clear();
		}
	}

	/**
	 * genera un vector de n enteros /* entre 0 y n, revueltos aleatoriamente 
	 * sin duplicados
	 **/
	private int[] mkData(int n) {
		int[] datos = new int[n];
		for (int i = 0; i < n; i++)
			datos[i] = i;
		for (int i = 0; i < n; i++) {
			int p = random.nextInt(n);
			int q = random.nextInt(n);
			int dp = datos[p];
			int dq = datos[q];
			datos[p] = dq;
			datos[q] = dp;
		}
		return datos;
	}
		
}
