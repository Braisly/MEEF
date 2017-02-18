package Data;
public class Opcion {
    public String Tipo;
    public String Hora;
    public String Volumen;
    public String Ultimo;
    public String Compra_Vol;
    public String Compra_Precio;
    public String Venta_Vol;
    public String Venta_Precio;
    public String Vencimiento;
    public String Ejercicio;
    public String FechaCompra;
    public String Cantidad;
    public String Ganancia;
    public String Precio_Vender;
    
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
        result[8] = Ejercicio;
        result[9] = Tipo;
        return result;
    }
     public Object[] showWallet(){
        Object [] result = new Object[8];
        result[0] = Cantidad;
        result[1] = Tipo;
        result[2] = Vencimiento;
        result[3] = Ejercicio;
        result[4] = FechaCompra;
        result[5] = Venta_Precio;
        result[6] = Precio_Vender;
        result[7] = Ganancia;
        return result;
    }
    @Override
    public String toString(){
        String resultado = this.Cantidad + " " + this.Tipo + " " + this.Vencimiento + " " + this.Ejercicio + " " + this.FechaCompra + " " + this.Venta_Precio;
        return resultado;
    }
}



