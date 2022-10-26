package ra.busines.design;

import ra.busines.entyti.Table;

import java.util.List;
import java.util.Scanner;

public interface ITable<T,E>extends IShop<T,E>{
    boolean updateTableStatus(String str);
    T updateTable(Scanner scanner);
}
