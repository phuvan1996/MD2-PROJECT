package ra.busines.imp;

import ra.busines.design.ICatalog;
import ra.busines.entyti.Catalog;
import ra.config.CheckValidate;
import ra.config.ShopConstant;
import ra.config.ShopMessage;
import ra.data.FileImp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CatalogImp implements ICatalog<Catalog,Integer>{

    @Override
    public List<Catalog> searchByName() {
        System.out.println("Nhập tên danh mục cần tìm:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
       List<Catalog>listFullCatalog = readFromFile();
        System.out.println("*_____________________________________________________________________________________*");
        System.out.printf("%-20s%-20s%-20s%-20s\n", "Mã danh mục","Tên danh mục","Độ ưu tiên","Trạng thái");
        System.out.println("*_____________________________________________________________________________________*");
        for (Catalog cat:listFullCatalog) {
            if (cat.getCatalogName().equals(name)) {
                String status = (cat.isStatus()) ? "Hoạt Động" : "Không hoat động";
                System.out.printf("%-20s%-20s%-20s%-20s\n",cat.getCatalogId(),cat.getCatalogName(),cat.getPriority(),status);
            }
        }
        return null;
    }

    @Override
    public Catalog updateCatalog(Scanner scanner) {
        List<Catalog>listCatalog = readFromFile();
        System.out.println("Nhập mã danh mục cần cập nhật");
        String catalogUpdate;
        do {
            catalogUpdate = scanner.nextLine();
            if (catalogUpdate.trim().length()!=0){
                if (CheckValidate.checkInterger(catalogUpdate)){
                    break;
                }else {
                    System.err.println("nhập vào 1 số nguyên");
                }
            }else {
                System.err.println("Trường không được bỏ trống");
            }
        }while (true);
        boolean exit = false;
        for (Catalog cat:listCatalog) {
            if (cat.getCatalogId()==Integer.parseInt(catalogUpdate)){
                exit = true;
                System.out.println("Cập nhật tên danh mục");
                String nameUpdate = scanner.nextLine();
                if (nameUpdate.trim().length()!=0){
                    if (CheckValidate.checkLenghString(nameUpdate,6,30)){
                        boolean checkExit = false;
                        for (Catalog catalog:listCatalog) {
                            if (catalog.getCatalogName().equals(nameUpdate)){
                                checkExit = true;
                                break;
                            }
                        }
                        if (!checkExit){
                            cat.setCatalogName(nameUpdate);
                        }else {
                            System.err.println(ShopMessage.CATALOG_NOTIFY_NAME_EXITS);
                        }
                    }else {
                        System.err.println(ShopMessage.CATALOG_NOTIFY_EXITS);
                    }
                }else {
                    continue;
                }
                System.out.println("Cập nhật độ ưu tiên danh mục");
                String priority = scanner.nextLine();
                if (priority.trim().length()!=0){
                    if (CheckValidate.checkInterger(priority)){
                        cat.setPriority(Integer.parseInt(priority));
                    }else {
                        System.err.println("Nhập vào 1 số nguyên");
                    }
                }else {
                    break;
                }
            }
        }
        boolean result = writeToFile(listCatalog);
        if (!exit){
            System.err.println("Không tìm thấy");
        }else {
            if (result){
                System.out.println("Cập nhật thành công");
            }
        }
        return null;
    }

    @Override
    public boolean creat(Catalog catalog) {
        List<Catalog>listCatalog = readFromFile();
        if (listCatalog==null){
            listCatalog = new ArrayList<>();
        }
        listCatalog.add(catalog);
        boolean result = writeToFile(listCatalog);
        return result;
    }

    @Override
    public List<Catalog> findAll() {
        return readFromFile();
    }

    @Override
    public boolean update(Integer id) {
        List<Catalog> listCatalog = readFromFile();
        boolean returnData = false;
        for (Catalog catalog1:listCatalog) {
            if (catalog1.getCatalogId()==id){
                catalog1.setStatus(!catalog1.isStatus());
                break;
            }
        }
        boolean result = writeToFile(listCatalog);
        if (result && returnData) {
            return true;
        }
        return false;
    }
    @Override
    public Catalog inputData(Scanner scanner) {
        List<Catalog>listCatalog = readFromFile();
        if (listCatalog==null){
            listCatalog = new ArrayList<>();
        }
        Catalog catalogNew = new Catalog();
       if (listCatalog.size()==0){
           catalogNew.setCatalogId(1);
       }else {
           int max = 0;
           for (Catalog catalog:listCatalog) {
               if (max<catalog.getCatalogId()){
                   max=catalog.getCatalogId();
               }
           }
           catalogNew.setCatalogId(max+1);
       }
        System.out.println("nhập vào tên danh mục");
       do {
           catalogNew.setCatalogName(scanner.nextLine());
           boolean checkExit = false;
           try {
               if (CheckValidate.checkLenghString(catalogNew.getCatalogName(),6,30)){
                   for (Catalog cat:listCatalog) {
                       if (cat.getCatalogName().equals(catalogNew.getCatalogName())){
                           checkExit = true;
                           break;
                       }
                   }
                   if (!checkExit){
                       break;
                   }else {
                       System.err.println(ShopMessage.CATALOG_NOTIFY_NAME_EXITS);
                   }
               }else {
                   System.err.println(ShopMessage.CATALOG_NOTIFY_EXITS);
               }
           }catch (NumberFormatException e){
               e.printStackTrace();
           }
       }while (true);
        System.out.println("nhập độ ưu tiên danh mục");
        do {
            String priority = scanner.nextLine();
            boolean checkPriority = CheckValidate.checkInterger(priority);
            if (checkPriority){
                catalogNew.setPriority(Integer.parseInt(priority));
                break;
            }else {
                System.err.println(ShopMessage.CATALOG_NOTIFY_PRIORITY);
            }
        }while (true);
        System.out.print("Trạng thái danh mục\n");
        System.out.println("1.Hoạt động");
        System.out.println("2.Không hoạt động");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice==1){
            catalogNew.setStatus(true);
        }
        return catalogNew;
    }
    @Override
    public boolean writeToFile(List<Catalog> list) {
        FileImp<Catalog> fileImp = new FileImp<>();
            return fileImp.writerToFile(list, ShopConstant.URL_CATALOG);
        }

    @Override
    public List<Catalog> readFromFile() {
        FileImp<Catalog> fileImp = new FileImp<>();
        return fileImp.readDataFromFile(ShopConstant.URL_CATALOG);
    }

    @Override
    public void displayData() {
        List<Catalog> listCatalog = readFromFile();
        if(listCatalog==null){
            System.out.println("Chua co danh muc nao");
        }else {
            listCatalog.sort(new Comparator<Catalog>() {
                @Override
                public int compare(Catalog o1, Catalog o2) {
                    return (o1.getPriority()- o2.getPriority());
                }
            });
            System.out.println("*_____________________________________________________________________________________*");
            System.out.printf("%-20s%-20s%-20s%-20s\n", "Mã danh mục","Tên danh mục","Độ ưu tiên","Trạng thái");
            System.out.println("*_____________________________________________________________________________________*");
            for (Catalog cat:listCatalog) {
                String status = (cat.isStatus())?"Hoạt Động":"Không hoat động";
                System.out.printf("%-20s%-20s%-20s%-20s\n",cat.getCatalogId(),cat.getCatalogName(),cat.getPriority(),status);
            }
        }
    }
}
