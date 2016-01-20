package Data;
public class Futuro {
    
    public String Hora;
    public String Volumen;
    public String Ultimo;
    public String Compra_Vol;
    public String Compra_Precio;
    public String Venta_Vol;
    public String Venta_Precio;
    public String Vencimiento;
    public String Apertura;
    public String Anterior;
    public String Maximo;
    public String Minimo;

    public Object[] toArray() {
        Object[] result = new Object[12];
        result[0] = Hora;
        result[1] = Volumen;
        result[2] = Ultimo;
        result[3] = Compra_Vol;
        result[4] = Compra_Precio;
        result[5] = Venta_Vol;
        result[6] = Venta_Precio;
        result[7] = Vencimiento;
        result[8] = Apertura;
        result[9] = Anterior;
        result[10] = Maximo;
        result[11] = Minimo;
        return result;
    }
}
