package ra.persistion;

import ra.busines.entyti.Catalog;
import ra.busines.imp.CatalogImp;
import ra.config.CheckValidate;

import java.util.Scanner;

public class CatalogMenu {
    private final static CatalogImp catalogImp = new CatalogImp();
    static Catalog catalog = new Catalog();
    public static void catalogManagement(Scanner scanner){
        boolean exitCatalogMenu = true;
        do {
            System.out.println("|---------------------------------------------------|");
            System.out.println("|             QUẢN LÝ DANH MỤC                      |");
            System.out.println("|---------------------------------------------------|");
            System.out.println("|            1.Danh sách danh mục                   |");
            System.out.println("|            2.Thêm mới danh mục                    |");
            System.out.println("|            3.Cập nhật danh mục                    |");
            System.out.println("|            4.Xoá danh mục                         |");
            System.out.println("|            5.Tìm kiếm danh mục                    |");
            System.out.println("|            6.Thoát                                |");
            System.out.println("|---------------------------------------------------|");
            String choice;
            do {
                choice = scanner.nextLine();
                if (choice.trim().length()!=0){
                    if (CheckValidate.checkInterger(choice)){
                        break;
                    }else {
                        System.out.println("vui lòng nhập từ 1-6");
                    }
                }else {
                    System.err.println("Trường không được để trống");
                }
            }while (true);
            switch (Integer.parseInt(choice)){
                case 1:
                   catalogImp.displayData();
                    break;
                case 2:
                    Catalog catalog1 = catalogImp.inputData(scanner);
                    boolean result = catalogImp.creat(catalog1);
                    break;
                case 3:
                    catalogImp.updateCatalog(scanner);
                    break;
                case 4:
                    System.out.println("nhập mã danh mục cần xoá");
                    String id;
                    do {
                        id = scanner.nextLine();
                        if (id.trim().length()!=0){
                           break;
                        }else {
                            System.err.println("Trường không được để trống");
                        }
                    }while (true);
                    catalogImp.update(Integer.parseInt(id));
                    break;
                case 5:
                    catalogImp.searchByName();
                    break;
                case 6:
                    exitCatalogMenu = false;
            }
        }while (exitCatalogMenu);
    }
}
