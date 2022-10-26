package ra.busines.design;

import ra.busines.entyti.User;

import java.util.List;
import java.util.Scanner;

public interface IUser <T,E>extends IShop<T,E>{
    boolean update(E e);

    List<User> searchUser();
}
