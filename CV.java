package es.upm.dit.adsw.ej2;

/**
 * Pareja clave-valor.
 *
 * @author jose a. manas
 * @version 1.2.2016
 */
class CV implements Comparable<CV> {
    private final String clave;
    private String valor;

    /**
     * constructor.
     *
     * @param clave la clave que identifica el valor.
     * @param valor el valor asociado a la clave.
     */
    public CV(String clave, String valor) {
        this.clave = clave;
        this.valor = valor;
    }

    /**
     * getter.
     *
     * @return la clave que identifica el valor.
     */
    public String getClave() {
        return clave;
    }

    /**
     * getter.
     *
     * @return el valor asociado a la clave.
     */
    public String getValor() {
        return valor;
    }

    /**
     * setter.
     *
     * @param valor el valor asociado a la clave.
     */
    public void setValor(String valor) {
        this.valor = valor;
    }
    
    /** 
     * compara dos pares (clave, valor)
     * @param
     *   el elemento con el se compara
     * @return
     *   el resultado de comparar las claves respectivas
     */
    @Override
    public int compareTo(CV dato) {
    	return this.clave.compareTo(dato.clave);
    }
    
}