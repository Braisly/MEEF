package momimeef;
import DB.ConnectSqlite;
import View.MainFrame;
public class MoMiMeef {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        frame.startWorker();
        System.out.println("jajaja");
    }
}
