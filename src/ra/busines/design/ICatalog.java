package ra.busines.design;

import ra.busines.entyti.Catalog;

import java.util.List;
import java.util.Scanner;

public interface ICatalog<T,E> extends IShop<T,E>{
    List<Catalog> searchByName();
    T updateCatalog(Scanner scanner);
}
