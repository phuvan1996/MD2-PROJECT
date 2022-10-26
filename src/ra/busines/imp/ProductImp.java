package ra.busines.imp;

import ra.busines.design.IProduct;
import ra.busines.entyti.Catalog;
import ra.busines.entyti.Product;
import ra.config.CheckValidate;
import ra.config.ShopConstant;
import ra.config.ShopMessage;
import ra.data.FileImp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ProductImp implements IProduct<Product,String> {

    @Override
    public List<Product> searchByName() {
        System.out.println("nhập vào tên sản phẩm cần tìm");
        Scanner scanner = new Scanner(System.in);
        String Productname = scanner.nextLine();
        List<Product> listProductName = readFromFile();
        System.out.println("*_____________________________________________________________________________________*");
        System.out.printf("%-20s%-20s%-20s\n", "Mã Sản Phẩm","Tên Sản Phẩm","Trạng thái");
        System.out.println("*_____________________________________________________________________________________*");
        for (Product product:listProductName) {
            if (product.getProductName().equals(Productname)) {
                String status = (product.isStatus()) ? "Hoạt động" : "Không hoạt động";
                System.out.printf("%-20s%-20s%-20s\n",product.getProductId(),product.getProductName(),status);
            }
        }
        return null;
    }

    @Override
    public Product updateProduct(Scanner scanner) {
        List<Product> listProduct = readFromFile();
        CatalogImp catalogImp = new CatalogImp();
        List<Catalog> listCatalog = catalogImp.readFromFile();
        System.out.println("Nhập mã sản phẩm cần cập nhật");
        String name = scanner.nextLine();
        boolean exit = false;
        for (Product productUpdate : listProduct) {
            if (productUpdate.getProductId().equals(name)) {
                exit = true;
                System.out.println("Cập nhật tên sản phẩm");
                String nameUpdate = scanner.nextLine();
                if (nameUpdate.trim() != "" && nameUpdate.length() != 0) {
                    if (CheckValidate.checkLenghString(nameUpdate, 6, 30)) {
                        productUpdate.setProductName(nameUpdate);
                    }
                }
                System.out.println("Cập nhật giá sản phẩm");
                String priceUpdate = scanner.nextLine();
                if (priceUpdate.trim() != "" && priceUpdate.length() != 0) {
                    if (CheckValidate.checkFloat(priceUpdate)) {
                        if (Float.parseFloat(priceUpdate) > 0) {
                            productUpdate.setPriceProduct(Float.parseFloat(priceUpdate));
                        }
                    }
                }
                System.out.println("Cập nhật danh mục cho sản phẩm");
                for (Catalog catalog : listCatalog) {
                    System.out.printf("%d.%s\n", catalog.getCatalogId(), catalog.getCatalogName());
                }
                String choice = scanner.nextLine();
                if (choice.trim() != "" && choice.length() != 0) {
                    if (CheckValidate.checkInterger(choice)) {
                        if (Integer.parseInt(choice) >= 1 && Integer.parseInt(choice) <= 5) {
                            break;
                        } else {
                            System.err.println("Nhập vào 1 số nguyên");
                        }
                    }
                }
            }
        }
        if (!exit) {
            System.out.println("không tìm thấy");
        } else {
            boolean result = writeToFile(listProduct);
            if (result) {
                System.out.println("Cập nhật thành công");
            }
        }
        return null;
    }
    //Thêm mới sản phẩm.
    @Override
    public boolean creat(Product product) {
        List<Product> listProduct = readFromFile();
        if (product == null) {
            listProduct = new ArrayList<>();
        }
        listProduct.add(product);
        boolean result = writeToFile(listProduct);
        return result;
    }

    @Override
    public List<Product> findAll() {
        return readFromFile();
    }

    //cập nhật sản phẩm.
    @Override
    public boolean update(String product) {
        List<Product> listProduct = readFromFile();
        boolean returnData = false;
        for (Product product1:listProduct) {
            if (product1.getProductId().equals(product)){
                product1.setStatus(!product1.isStatus());
                returnData = true;
                break;
            }
        }
        boolean result = writeToFile(listProduct);
        if (result&&returnData){
            return true;
        }
        return false;
    }
//nhập dữ liệu
    @Override
    public Product inputData(Scanner scanner) {
        List<Product> listProduct = readFromFile();
        if (listProduct == null) {
            listProduct = new ArrayList<>();
        }
        Product productNew = new Product();
        System.out.println("Nhập vào mã sản phẩm");
        do {
            String productId = scanner.nextLine();
            if (CheckValidate.checkProductLength(productId)){
                if (productId.charAt(0) == 'P') {
                    boolean checkExit = false;
                    for (Product product : listProduct) {
                        if (product.getProductId().equals(productId)) {
                            checkExit = true;
                            break;
                        }
                    }
                    if (!checkExit) {
                        productNew.setProductId(productId);
                        break;
                    } else {
                        System.err.println(ShopMessage.PRODUCTID_NOTIFY_EXITS);
                    }
                } else {
                    System.err.println(ShopMessage.PRODUCTID_NOTIFY_LENGTH);
                }
            } else {
                System.err.println(ShopMessage.PRODUCTID_NOTIFY_CHARACTERS);
            }
        } while (true);
        System.out.println("Nhập vào tên sản phẩm");
        do {
            String productName = scanner.nextLine();
            if (CheckValidate.checkLenghString(productName,6,30)) {
                boolean checkExit = false;
                for (Product product : listProduct) {
                    if (product.getProductName().equals(productName)) {
                        checkExit = true;
                        break;
                    }
                }
                if (!checkExit) {
                    productNew.setProductName(productName);
                    break;
                } else {
                    System.err.println(ShopMessage.PRODUCTNAME_NOTIFY_EXITS);
                }
            } else {
                System.err.println(ShopMessage.PRODUCTNAME_NOTIFY_NAME);
            }
        } while (true);
        System.out.println("Nhập vào giá sản phẩm");
        do {
            String productPrice = scanner.nextLine();
            if (CheckValidate.checkFloat(productPrice)) {
                if (Float.parseFloat(productPrice) > 0) {
                    productNew.setPriceProduct(Float.parseFloat(productPrice));
                    break;
                } else {
                    System.err.println(ShopMessage.PRODUCTPRICE_NOTIFY);
                }
            }

        } while (true);
        System.out.println("Nhập vào trạng thái sản phẩm\n");
        System.out.println("1.Hoạt động");
        System.out.println("2.Không hoạt động");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1) {
                productNew.setStatus(false);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return productNew;
    }
    @Override
    public boolean writeToFile(List<Product> list) {
        FileImp<Product> fileImp = new FileImp<>();
            return fileImp.writerToFile(list,ShopConstant.URL_PRODUCT);

    }

    @Override
    public List<Product> readFromFile() {
        FileImp<Product> fileImp = new FileImp<>();
        return fileImp.readDataFromFile(ShopConstant.URL_PRODUCT);
    }
    @Override
    public void displayData() {
        List<Product> listProduct = readFromFile();
      if (listProduct==null){
          System.out.println("chưa có sản phẩm nào");
      }
        System.out.println("*_____________________________________________________________________________________*");
        System.out.printf("%-20s%-20s%-20s\n", "Mã Sản Phẩm","Tên Sản Phẩm","Trạng thái");
        System.out.println("*_____________________________________________________________________________________*");
        for (Product product:listProduct) {
            String status = (product.isStatus())?"Hoạt động":"Không hoạt động";
            System.out.printf("%-20s%-20s%-20s\n",product.getProductId(),product.getProductName(),status);
        }
    }
}
