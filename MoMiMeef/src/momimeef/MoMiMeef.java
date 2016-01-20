package momimeef;
import DB.ConnectSqlite;
import View.MainFrame;
public class MoMiMeef {
    public static void main(String[] args) {
        ConnectSqlite db = new  ConnectSqlite ();
        //Se insertan algunos datos
        //db.insert("persona", " nombre, apellido ", " 'Charly', 'Manson' ");
        //db.deleteWallet("'valor'");
        //db.insert("persona", " nombre, apellido ", " 'Marilyn' , 'Garcia' ");
        //db.insert("persona", " nombre, apellido ", " 'Marcelo', 'Chamboneti' ");
        //Se imprimen los datos de la tabla
        System.out.println( db.select());
        db.desconectar();
        new MainFrame().setVisible(true);
    }
}
