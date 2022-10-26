package ra.busines.design;

import ra.busines.entyti.Product;

import java.util.List;
import java.util.Scanner;

public interface IProduct<T,E> extends IShop<T,E> {
    List<Product> searchByName();
    T updateProduct(Scanner scanner);
}