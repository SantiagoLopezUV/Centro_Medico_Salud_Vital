package utils;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class ResizeColumnsTable implements ComponentListener {

    final JTable table;
    final int totalPanelWidth;

    public ResizeColumnsTable(JTable table, int totalPanelWidth) {
        this.table = table;
        this.totalPanelWidth = totalPanelWidth;

    }

    @Override
    public void componentResized(ComponentEvent e) {
        int colCount = table.getColumnCount();
        int colWidth = totalPanelWidth / colCount;
        for (int i = 0; i < colCount; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(colWidth);
            column.setMinWidth(colWidth);
        }
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
