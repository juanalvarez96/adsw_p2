package es.upm.dit.adsw.ej2;

import java.util.Arrays;

/**
 * @author jpuente
 * @version 2016.02.23
 */
public class DiccionarioBinario 
	implements Diccionario{
	
	private final CV[] datos;
	private int nDatos;
	
	/**
    *
    * @param max numero maximo de claves que caben.
    */
   public DiccionarioBinario (int max) {
       datos = new CV[max];
       nDatos = 0;
   }

	/**
	 * busca el índice de una clave en el rango [a,z)
	 * 
	 * @return el índice donde está (o debería estar) la clave
	 */
	private int busca(String clave, int a, int z) {
		while (a < z) {
			int m = (a + z) / 2;
			int cmp = OpMeter.compareTo(clave, datos[m].getClave());
			if (cmp == 0)
				return m;
			else if (cmp < 0)
				z = m;
			else
				a = m + 1;
		}
		return a;
	}

	@Override
	public void put(String clave, String valor) {
		if (clave == null || clave.length() == 0)
			throw new IllegalArgumentException("put(null, valor)");

		int pos = busca(clave, 0, nDatos);

		if (datos[pos] != null
				&& datos[pos].getClave().equals(clave)) {
			datos[pos].setValor(valor);
			return;
		}

		if (pos < nDatos) {
			System.arraycopy(datos, pos, datos, pos + 1, nDatos - pos);
		}
		datos[pos] = new CV(clave, valor);
		nDatos++;
	}

	@Override
	public String get(String clave) {
		if (clave == null || clave.length() == 0)
			throw new IllegalArgumentException("get(null)");
		int pos = busca(clave, 0, nDatos);
		if (datos[pos] != null && datos[pos].getClave().equals(clave))
			return datos[pos].getValor();
		else
			return null;
	}

	@Override
	public String remove(String clave) {
		int pos = busca(clave, 0, nDatos);
		if (datos[pos] == null || !datos[pos].getClave().equals(clave))
			return null;
		String valor = datos[pos].getValor();
		if (nDatos > 1) {
			System.arraycopy(datos, pos + 1, datos, pos, nDatos - pos - 1);
		}
		nDatos--;
		datos[nDatos] = null;
		return valor;
	}

	@Override
	public int size() {
		return nDatos;
	}

	@Override
	public void clear() {
        Arrays.fill(datos, null);
        nDatos = 0;		
	}

}
