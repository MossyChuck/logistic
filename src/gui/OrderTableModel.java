package gui;

import db.Database;
import orders.Item;
import orders.Order;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;

public class OrderTableModel implements TableModel {

    private Order[] orders;

    public OrderTableModel(){
        orders = Database.getOrders();
    }
    @Override
    public int getRowCount() {
        return orders.length;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0:
                return "Customer";
            case 1:
                return "Stock";
            case 2:
                return "Destination place";
            case 3:
                return "Items";
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    private String itemsToString(ArrayList<Item> items){
        String result = "";
        for(Item item: items){
            result+=item.getName()+", ";
        }
        return result;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Order order = orders[rowIndex];
        switch (columnIndex){
            case 0:
                return order.getCustomer();
            case 1:
                return order.getStock().getName();
            case 2:
                return order.getDestinationPlace().getName();
            case 3:
                return itemsToString(order.getItems());
        }
        return "";
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
