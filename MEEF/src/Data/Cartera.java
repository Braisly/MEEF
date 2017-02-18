package Data;
import java.util.ArrayList;
public class Cartera {

    private String id;
    private ArrayList<Opcion> opciones;

    public Cartera(String id) {
        this.id = id;
        opciones = new ArrayList<>();
    }

    public Cartera() {
        this.opciones = new ArrayList<>();
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void addOpcion(Opcion opt){
        if(!this.opciones.contains(opt)){
            this.opciones.add(opt);
        }
    }

    public ArrayList<Opcion> getOpciones() {
        return opciones;
    }

    public String getId() {
        return id;
    }
    
    public void removeOptionInIndex(int index){
        this.opciones.remove(index);
    }
    
}
