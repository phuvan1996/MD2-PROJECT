package ra.persistion;

import ra.busines.entyti.Product;
import ra.busines.imp.ProductImp;
import ra.config.CheckValidate;

import java.util.Scanner;

public class ProductMenu {
private final static ProductImp productImp = new ProductImp();
static Product product = new Product();
public static void productManagement(Scanner scanner){
boolean exitProductMenu = true;
do {
    System.out.println("|----------------------------------------------------|");
    System.out.println("|                 QUẢN LÝ SẢN PHẨM                   |");
    System.out.println("|----------------------------------------------------|");
    System.out.println("|   1.Danh sách sản phẩm theo danh mục sản phẩm      |");
    System.out.println("|   2.Tạo mới sản phẩm                               |");
    System.out.println("|   3.Cập nhật sản phẩm                              |");
    System.out.println("|   4.Tìm kiếm sản phẩm theo tên sản phẩm            |");
    System.out.println("|   5.Xoá sản phẩm                                   |");
    System.out.println("|   6.Thoát                                          |");
    System.out.println("|____________________________________________________|");
    String choice;
    do {
        choice = scanner.nextLine();
        if (choice.trim().length()!=0){
            if (CheckValidate.checkInterger(choice)){
                break;
            }else {
                System.err.println("vui lòng chọn từ 1-6");
            }
        }
    }while (true);
    switch (Integer.parseInt(choice)){
        case 1:
            productImp.displayData();
            break;
        case 2:
            Product proNew = productImp.inputData(scanner);
            //Ghi du lieu ra file
            productImp.creat(proNew);
            break;
        case 3:
            productImp.updateProduct(scanner);
            break;
        case 4:
            productImp.searchByName();
            break;
        case 5:
            System.out.println("nhập mã sản phẩm cần xoá");
            String id;
            do {
                id = scanner.nextLine();
                if (id.trim().length()!=0) {
                break;
                }else {
                    System.err.println("Trường không đươc bỏ trống");
                }
            }while (true);
            productImp.update(id);
            break;
        case 6:
            exitProductMenu = false;
    }
}while (exitProductMenu);
   }
}
