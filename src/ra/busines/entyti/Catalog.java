package ra.busines.entyti;

import java.io.Serializable;

public class Catalog implements Serializable {
   private int catalogId;
   private int priority;
   private boolean status;
   private String catalogName;
    public Catalog() {
    }

    public Catalog(int catalogId, int priority, boolean status, String catalogName) {
        this.catalogId = catalogId;
        this.priority = priority;
        this.status = status;
        this.catalogName = catalogName;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }
}
