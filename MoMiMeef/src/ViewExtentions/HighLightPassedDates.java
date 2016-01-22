/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewExtentions;

import java.awt.Color;
import java.awt.Component;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author lala
 */
public class HighLightPassedDates extends AlternateColorJTable {

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int Index_row, int Index_col) {
        Component c = super.prepareRenderer(renderer, Index_row, Index_col); //To change body of generated methods, choose Tools | Templates.
        
        if(thisDateIsPassed(Index_row)){
            c.setForeground(Color.RED);
        }
        
        return c;
    }
    private boolean thisDateIsPassed(int Index_row) {
        DefaultTableModel model =(DefaultTableModel) this.getModel();
        String date = model.getValueAt(Index_row, 3).toString();//
        Date today = Calendar.getInstance().getTime();
        DateFormat format = new SimpleDateFormat("d MMM yyyy");
        try {
            //format.format(today);
            return today.after(format.parse(date));
        } catch (ParseException ex) {
            System.out.println("Entro");
        }
        return false;
    }
    
    
}
