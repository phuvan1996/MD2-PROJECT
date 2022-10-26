package ra.busines.design;

import java.util.List;
import java.util.Scanner;

public interface IShop<T,E> {
    boolean creat(T t);
    List<T>findAll();
    boolean update(E e);
    T inputData(Scanner scanner);
    boolean writeToFile(List<T> list);
    List<T>readFromFile();
    void displayData();
}
