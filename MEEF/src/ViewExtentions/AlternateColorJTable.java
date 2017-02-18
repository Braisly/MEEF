/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewExtentions;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author lala
 */
public class AlternateColorJTable extends JTable {

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int Index_row, int Index_col) {
        Component comp = super.prepareRenderer(renderer, Index_row, Index_col);
//even index, selected or not selected
        if (Index_row % 2 == 0 ) {
            comp.setBackground(new Color(191,222,92));
            comp.setForeground(Color.WHITE);
        } else {
            comp.setBackground(Color.white);
            comp.setForeground(Color.BLACK);
        }
        
        if(isCellSelected(Index_row, Index_col)){
            comp.setBackground(Color.ORANGE);
            comp.setForeground(Color.BLACK);
        }
        return comp;
    }

}
