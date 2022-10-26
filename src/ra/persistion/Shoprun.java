package ra.persistion;

import ra.config.CheckValidate;

import java.util.Scanner;

public class Shoprun {
  static Scanner scanner = new Scanner(System.in);
    public static void coffeeManagement() {
        do {
            System.out.println("|---------------------------------------------------|");
            System.out.println("|                 AHA COFFEE                        |");
            System.out.println("|               1.Quản trị danh mục                 |");
            System.out.println("|               2.Quản trị sản phẩm                  |");
            System.out.println("|               3.Quản trị bàn                       |");
            System.out.println("|               4.Quản trị người dùng                |");
            System.out.println("|               5.Thoát                             |");
            System.out.println("|___________________________________________________|");
            String choice;
            do {
                choice = scanner.nextLine();
                if (choice.trim().length()!=0){
                    if (CheckValidate.checkInterger(choice)){
                        break;
                    }else {
                        System.err.println("Vui lòng nhập 1 số nguyên!");
                    }
                }else {
                    System.out.println("Trường không được bỏ trống!");
                }
            }while (true);
            switch (Integer.parseInt(choice)){
                case 1:
                    CatalogMenu.catalogManagement(scanner);
                    break;
                case 2:
                    ProductMenu.productManagement(scanner);
                    break;
                case 3:
                    TableMenu.tableManagement(scanner);
                    break;
                case 4:
                    UserMenu.UserManagement(scanner);
                    break;
                case 5:
                    System.exit(0);
            }
        }while (true);
    }
}