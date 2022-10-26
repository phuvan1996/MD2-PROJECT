package ra.busines.entyti;

import java.io.Serializable;

public class Table implements Serializable {
    private String tableId;
   private String tableName;
   private int seatNumber;
   private int tableStatus;

    public Table() {
    }

    public Table(String tableId, String tableName, int seatNumber, int tableStatus) {
        this.tableId = tableId;
        this.tableName = tableName;
        this.seatNumber = seatNumber;
        this.tableStatus = tableStatus;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int isTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(int tableStatus) {
        this.tableStatus = tableStatus;
    }
}
