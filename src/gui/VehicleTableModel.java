package gui;

import db.Database;
import exception.MySqlException;
import park.Vehicle;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class VehicleTableModel implements TableModel{
    private Vehicle[] data;

    public VehicleTableModel(){
        try {
            data = Database.getVehicles();
        }catch (MySqlException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0:
                return "Model";
            case 1:
                return "Max speed";
            case 2:
                return "Volume";
            case 3:
                return "Carrying capacity";
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return String.class;
            case 1:
            case 2:
            case 3:
                return Double.class;
        }
        return Object.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Vehicle v = data[rowIndex];
        switch (columnIndex){
            case 0:
                return v.getModel();
            case 1:
                return v.getMaxSpeed();
            case 2:
                return v.getVolume();
            case 3:
                return v.getMaxWeight();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String value = (String) aValue;
        switch (columnIndex){
            case 0:
                data[rowIndex].setModel(value);
            case 1:
                data[rowIndex].setMaxSpeed(Double.parseDouble(value));
            case 2:
                data[rowIndex].setVolume(Double.parseDouble(value));
            case 3:
                data[rowIndex].setMaxWeight(Double.parseDouble(value));
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
