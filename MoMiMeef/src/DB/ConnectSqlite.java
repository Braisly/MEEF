package DB;
import Data.Opcion;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ConnectSqlite {

    private Connection connection = null;
    private ResultSet resultSet = null;
    private Statement statement = null;
    //private String db = "\\Wallets.sqlite";
    //private String db = "/Users/lala/MoMiMeef/ProyectoMEFF/Wallets.sqlite";
    //private String db= "C:\\Users\\adriano\\Documents\\NetBeansProjects\\MoMiMeef\\ProyectoMEFF\\Wallets.sqlite";    
    //private String db= "C:\\Users\\adriano\\Documents\\NetBeansProjects\\MoMiMeef\\ProyectoMEFF\\dbNuevo.sqlite";    
    //Constructor de clase que se conecta a la base de datos SQLite 
    public ConnectSqlite(){
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DirectorioActual());
            //System.out.println("Conectado a la base de datos SQLite [ " + this.db + "]");
            //System.out.println(DirectorioActual());
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public boolean insert(String table, String fields, String values){
       boolean res=false;
       //Se arma la consulta
       //String q=" INSERT INTO " + table + " ( " + fields + " ) VALUES ( " + values + " ) ";
       //String q=" INSERT INTO SetWallets (Name) VALUES ('prueba') ";
       String q=" INSERT INTO SetOptionsWallets (Hour,Volume,Last,Volume_Buy,Price_Buy,Volume_Sale,Price_Sale,Expiration,Exercise,Type,Name) VALUES ('22:10','2','','10','50','6','10','50','100','PUT','cartera') ";
       //se ejecuta la consulta
       try {
           PreparedStatement pstm = connection.prepareStatement(q);
           pstm.execute();
           pstm.close();
           res=true;
       }catch(Exception e){
           System.out.println(e);
           System.out.println("Se produjo una excepcion");
       }
       return res;
    }
    
    public String select(){
        String res="Nombres de las carteras" +"\n";
        try {
            this.statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM setWallets ; ");
            while (resultSet.next()){
                //res+=resultSet.getString("id") + " | " + resultSet.getString("nombre") + " | " +  resultSet.getString("apellido") + " \n ";
                res+=resultSet.getString("Name") + "\n";
            }
        }catch (SQLException ex) {
            System.out.println(ex);
        }
        res += "Opciones que están en las carteras" +"\n";
        try {
            this.statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM setOptionsWallets ; ");
            while (resultSet.next()){
                //res+=resultSet.getString("id") + " | " + resultSet.getString("nombre") + " | " +  resultSet.getString("apellido") + " \n ";
                res+=resultSet.getString("Hour") + " | " + resultSet.getString("Volume") + " | " +  resultSet.getString("Last") + " | " + resultSet.getString("Volume_Buy") + " | " +  resultSet.getString("Price_Buy") + " | " + resultSet.getString("Volume_Sale") + " | " + resultSet.getString("Price_Sale") + " | " +  resultSet.getString("Expiration") + " | " + resultSet.getString("Exercise") + " | " +  resultSet.getString("Type")+ " | " + resultSet.getString("Name") + "\n";
            }
        }catch (SQLException ex) {
            System.out.println(ex);
        }
        return res;
    }
    
    public List showAllWallets(){
        List <String> wallets = new ArrayList<>();
        try {
            this.statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM setWallets ;");
            while (resultSet.next()){
                //res+=resultSet.getString("id") + " | " + resultSet.getString("nombre") + " | " +  resultSet.getString("apellido") + " \n ";
                wallets.add(resultSet.getString("Name"));
            }
        }catch (SQLException ex) {
            System.out.println(ex);
        }
        return wallets;
    }
    
    public void insertWallet(String name){
        String insertWallet ="INSERT INTO SetWallets (Name) VALUES " + "(" + name + ")";
        try {
           PreparedStatement pstm = connection.prepareStatement(insertWallet);
           pstm.execute();
           pstm.close();
       }catch(Exception e){
           System.out.println(e);
       }
    }
    
    public void deleteWallet(String name){
        String deleteWallet = "DELETE FROM SetWallets WHERE Name="+name ;
        String deleteOptionsWallet = "DELETE FROM SetOptionsWallets WHERE Name="+name ;
        try {
           PreparedStatement pstm = connection.prepareStatement(deleteWallet);
           pstm.execute();
           pstm = connection.prepareStatement(deleteOptionsWallet);
           pstm.execute();
           pstm.close();
       }catch(Exception e){
           System.out.println(e);
       }
    }
    public void desconectar(){
        try {
            if(resultSet != null){
                resultSet.close();
            }
            if(statement != null){
                statement.close();   
            }
            connection.close();
            //System.out.println("Desconectado de la base de datos [ " + this.db + "]");
        }catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public ArrayList<Opcion> selectOptionsFromWallet(String walletName){
        ArrayList<Opcion> result = new ArrayList<>();
        try {
            this.statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM setOptionsWallets WHERE Name LIKE " +"'%"+walletName +"%';");
            while (resultSet.next()){
                Opcion partialResult = new Opcion();
                //TODO obtener cada campo y asignarselo a la opcion
                partialResult.Venta_Precio = resultSet.getString("Price_Sale");
                partialResult.Vencimiento = resultSet.getString("Expiration");
                partialResult.Ejercicio = resultSet.getString("Exercise");
                partialResult.Tipo = resultSet.getString("Type");
                partialResult.FechaCompra = resultSet.getString("DateBuy");
                partialResult.Cantidad  = resultSet.getString("Amount");
                //TODO añadir la opción al array list.
                result.add(partialResult);
            }
        }catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;
    }
    
    public void insertarOpcionEnCartera(String cartera, Opcion opt,String amount){
        //String insertWallet ="INSERT INTO setOptionsWallets (Name) VALUES " + "(" + name + ")";
        int endAmount = lookOptionWallet(cartera,opt,amount);
        if(endAmount != 0){
            removeOptionSpecificFromWallet(cartera,opt);
            
        }else{
            endAmount = Integer.parseInt(amount);
        }
        String insertAmount = Integer.toString(endAmount);
        String q=" INSERT INTO SetOptionsWallets (Amount,Type,Expiration,Exercise,DateBuy,Price_Sale,Name) VALUES ("+optionToInsertSQLString(opt,cartera,insertAmount)+") ";
        try {
           PreparedStatement pstm = connection.prepareStatement(q);
           pstm.execute();
           pstm.close();
       }catch(Exception e){
           System.out.println(e);
       }
    }

    private String optionToInsertSQLString(Opcion opt, String cart,String amount) {
        String str ="";
        str +="'";
        str += amount;
        str +="',";
        str +="'";
        str += opt.Tipo;
        str +="',";
        str +="'";
        str += opt.Vencimiento;
        str +="',";
        str +="'";
        str += opt.Ejercicio;
        str +="',";
        str +="'";
        str += CurrentDate();
        str +="',";
        str +="'";
        str += opt.Venta_Precio;
        str +="',";
        str +="'";
        str += cart;
        str +="'";
        return str;
    }
    
    
    public void removeOptionFromWallet(String wallet, Opcion opt){
        System.out.println(opt.toString());
        String q = "DELETE FROM SetOptionsWallets WHERE " + optionToDeleteSQLString(opt,wallet) +";";
        try {
           PreparedStatement pstm = connection.prepareStatement(q);
           pstm.execute();
           pstm.close();
       }catch(Exception e){
           System.out.println(e);
       }
    }
    private String optionToDeleteSQLString(Opcion opt,String name) {
        String str = "";
        str+= "Amount LIKE '";
        str += opt.Cantidad;
        str +="' AND ";
        str +="Type LIKE '";
        str += opt.Tipo;
        str += "' AND ";
        str += "Expiration LIKE '";
        str += opt.Vencimiento;
        str += "' AND ";
        str += "Exercise LIKE '";
        str += opt.Ejercicio;
        str += "' AND ";
        str += "DateBuy LIKE '";
        str += opt.FechaCompra;
        str += "' AND ";
        str += " Price_Sale LIKE '";
        str += opt.Venta_Precio;
        str += "' AND ";
        str += "Name LIKE '";
        str += name;
        str += "'";
        return str;
    }
    private String optionSpecificToDeleteSQLString(Opcion opt,String name) {
        String str = "";
        str +="Type LIKE '";
        str += opt.Tipo;
        str += "' AND ";
        str += "Expiration LIKE '";
        str += opt.Vencimiento;
        str += "' AND ";
        str += "Exercise LIKE '";
        str += opt.Ejercicio;
        str += "' AND ";
        str += "DateBuy LIKE '";
        str += CurrentDate();
        str += "' AND ";
        str += " Price_Sale LIKE '";
        str += opt.Venta_Precio;
        str += "' AND ";
        str += "Name LIKE '";
        str += name;
        str += "'";
        return str;
    }
   public void removeOptionSpecificFromWallet(String wallet, Opcion opt){
        System.out.println(opt.toString());
        String q = "DELETE FROM SetOptionsWallets WHERE " + optionSpecificToDeleteSQLString(opt,wallet) +";";
        try {
           PreparedStatement pstm = connection.prepareStatement(q);
           pstm.execute();
           pstm.close();
       }catch(Exception e){
           System.out.println(e);
       }
    }
    public String DirectorioActual(){
        //String directorio = System.getProperty("java.class.path");
        File dir = new File("dbWallets.sqlite");
        return dir.getAbsolutePath();
    }
    public String CurrentDate() {
       Calendar calendario = GregorianCalendar.getInstance();
       SimpleDateFormat formato = new SimpleDateFormat("d MMM yyyy");
       return formato.format(calendario.getTime());
    }
    public int lookOptionWallet(String cartera, Opcion opt,String amount){
        String option = "" + "Type LIKE '" + opt.Tipo + "' AND " + "Expiration LIKE '" + opt.Vencimiento + "' AND " + "Exercise LIKE '" + opt.Ejercicio + "' AND " + "Price_Sale LIKE '" + opt.Venta_Precio + "'AND " + "DateBuy LIKE '" + CurrentDate() + "'AND " + "Name LIKE '" + cartera + "'" ;
        try {
            this.statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SetOptionsWallets WHERE " + option +  ";");
            if(resultSet.next()){
                String Amount = resultSet.getString("Amount");
                int endAmount = Integer.parseInt(Amount) + Integer.parseInt(amount);
                return endAmount;
            }
        }catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }
}