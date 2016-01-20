package momimeef;
import DB.ConnectSqlite;
import View.MainFrame;
public class MoMiMeef {
    public static void main(String[] args) {
        ConnectSqlite db = new  ConnectSqlite ();
        System.out.println( db.select());
        db.desconectar();
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        frame.startWorker();
        System.out.println("jajaja");
    }
}
