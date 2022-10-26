package ra.persistion;

import ra.busines.entyti.Table;
import ra.busines.imp.TableImp;
import ra.config.CheckValidate;

import java.util.Scanner;

public class TableMenu {
    private final static TableImp tableImp = new TableImp();
    static Table table = new Table();

    public static void tableManagement(Scanner scanner) {
        boolean exitTableMenu = true;
        do {
            System.out.println("|---------------------------------------------------|");
            System.out.println("|                     QUẢN LÝ BÀN                   |");
            System.out.println("|---------------------------------------------------|");
            System.out.println("|                    1.Danh sách bàn                |");
            System.out.println("|                    2.Thêm mới bàn                 |");
            System.out.println("|                    3.Cập nhật thông tin bàn       |");
            System.out.println("|                    4.Cập nhật trạng thái bàn      |");
            System.out.println("|                    5.Thoát                        |");
            System.out.println("|---------------------------------------------------|");
            String choice;

            choice = scanner.nextLine();
            if (choice.trim().length() != 0) {
                if (CheckValidate.checkInterger(choice)) {
                    switch (Integer.parseInt(choice)) {
                        case 1:
                            tableImp.displayData();
                            break;
                        case 2:
                            Table table1 = tableImp.inputData(scanner);
                            boolean result = tableImp.creat(table1);
                            break;
                        case 3:
                           tableImp.updateTable(scanner);
                            break;
                        case 4:
                            System.out.println("Nhập vào mã bàn cần cập nhật trạng thái");
                            String tableId = scanner.nextLine();
                            tableImp.updateTableStatus(tableId);
                            System.out.println("Cập nhật trạng thái thành công");
                            break;
                        case 5:
                            exitTableMenu = false;
                            break;
                        default:
                            System.err.println("Vui lòng chọn từ 1-5");
                    }
                } else {
                    System.err.println("Vui lòng chọn 1 số");
                }
            } else {
                System.err.println("Vui lòng chọn");
            }

        } while (exitTableMenu);
    }
}