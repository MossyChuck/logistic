package gui;

import db.Database;
import exception.MySqlException;
import place.DestinationPlace;
import place.Road;
import place.Stock;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class RoadTableModel implements TableModel {
    private Road[] roads;

    public RoadTableModel() throws MySqlException{
        try{
            roads = Database.getRoads();
        }catch (MySqlException e){
            throw e;
        }
    }

    @Override
    public int getRowCount() {
        return roads.length;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0:
                return "Stock";
            case 1:
                return "Destination place";
            case 2:
                return "Length";
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return int.class;
        }
        return Object.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Road road = roads[rowIndex];
        switch (columnIndex){
            case 0:
                return road.getStock().getName();
            case 1:
                return road.getDestinationPlace().getName();
            case 2:
                return road.getLength();
        }
        return "";
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Road road = roads[rowIndex];
        switch (columnIndex){
            case 0:
                road.setStock(new Stock((String) aValue));
                return;
            case 1:
                road.setDestinationPlace(new DestinationPlace((String) aValue));
                return;
            case 2:
                road.setLength(Integer.parseInt((String) aValue));
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
