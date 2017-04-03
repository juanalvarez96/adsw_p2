package es.upm.dit.adsw.ej2;

/**
 * @author jpuente
 * @version 2016.02.29
 */
public class BST implements Diccionario {
	
	
	private class Nodo {
		String clave;
		String valor;
		Nodo izq;
		Nodo der;

		Nodo(String clave, String valor) {
			this.clave = clave;
			this.valor = valor;
		}
		
	}
	
	private Nodo raiz;
	private int nDatos;

	@Override
	public void put(String clave, String valor) {
		if ((clave == null) || (clave.length() == 0))
			throw new IllegalArgumentException("put : clave inválida");
		raiz = put(raiz, clave, valor);
	}
	
	/**
	 * Inserta en un subárbl a partir de un nodo raiz
	 */
	private Nodo put(Nodo nodo, String clave, String valor) {
		
		// el nodo no existe, crearlo nuevo
		if (nodo == null) {
			nodo = new Nodo(clave,valor);
			nDatos ++;
			return nodo;
		}
		
		String claveNodo = nodo.clave;
		int cmp = OpMeter.compareTo(clave, claveNodo);
		if (cmp == 0)              // clave encontrada
			nodo.valor = valor;
		else if (cmp < 0)
			nodo.izq = put (nodo.izq, clave, valor);
		else
			nodo.der = put (nodo.der, clave, valor);	
		return nodo;
	}

	@Override
	public String get(String clave) {
		if ((clave == null) || (clave.length() == 0))
			throw new IllegalArgumentException("get : clave inválida");
		return get(raiz, clave);
	}
	
	/**
	 * Busca en un subárbol, a partir de un nodo
	 */
	private String get(Nodo nodo, String clave) {
		if (nodo == null)
			return null;
		int cmp = OpMeter.compareTo(clave,nodo.clave);
		if (cmp == 0)
			return nodo.valor;              // encontrado
		else if (cmp < 0)
			return get(nodo.izq, clave);   // busca en el subárbol izquierdo
		else
			return get(nodo.der, clave);   // busca en el subárbol derecho
	}

	@Override
	public String remove(String clave) {
		if ((clave == null) || (clave.length() == 0))
			throw new IllegalArgumentException("remove : clave inválida");
		String valor = get(clave);
		if (valor == null)
			return null;
		raiz = remove(raiz, clave);
		nDatos--;
		return valor;
	}
	
	private Nodo remove(Nodo nodo, String clave) {
		if (nodo == null)
			return null;
		int cmp = OpMeter.compareTo(clave, nodo.clave);
		if (cmp == 0) { // clave encontrada, quitar este nodo
			if (nodo.izq == null) {
				nodo = nodo.der;
			} else if (nodo.der == null) {
				nodo = nodo.izq;
			} else {
                Nodo max = getMax(nodo.izq); // busca el mayor de la parte izquierda
                // copiar el maximo al hueco del nodo que se quita
                nodo.clave = max.clave;
                nodo.valor = max.valor;
                // y ahora quitar el que se ha movido
                nodo.izq = remove(nodo.izq, max.clave);
			}
		} else if (cmp < 0)
			nodo.izq = remove(nodo.izq, clave); // busca en el subárbol izquierdo
		else
			nodo.der = remove(nodo.der, clave); // busca en el subárbol derecho
		return nodo;
	}	
	
	private Nodo getMax(Nodo nodo) {
		if (nodo.der == null)
			return nodo;
		else
			return getMax(nodo.der);
	}

	@Override
	public int size() {
		return nDatos;
	}

	@Override
	public void clear() {
		nDatos = 0;
		raiz = null;
	}

}
