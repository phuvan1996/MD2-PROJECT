package ra.busines.imp;

import ra.busines.design.ITable;
import ra.busines.entyti.Table;
import ra.config.CheckValidate;
import ra.config.ShopConstant;
import ra.config.ShopMessage;
import ra.data.FileImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TableImp implements ITable<Table, Integer> {

    @Override
    public boolean creat(Table table) {
        List<Table> listTable = readFromFile();
        if (listTable == null) {
            listTable = new ArrayList<>();
        }
        listTable.add(table);
        boolean result = writeToFile(listTable);
        return result;
    }

    @Override
    public List<Table> findAll() {
        return readFromFile();
    }
//Cập nhật trạng thái bàn
    @Override
    public boolean update(Integer table) {
        return false;
    }

    @Override
    public Table inputData(Scanner scanner) {
        List<Table> listTable = readFromFile();
        if (listTable == null) {
            listTable = new ArrayList<>();
        }
        Table tableNew = new Table();
        System.out.println("Nhập vào mã bàn: ");
        do {
            String tableId = scanner.nextLine();
            boolean checkExit = false;
            if (CheckValidate.checkTableNameLength(tableId)) {
                if (CheckValidate.checkCharAtTable(tableId)) {
                    for (Table table : listTable) {
                        if (table.getTableId().equals(tableId)) {
                            checkExit = true;
                            break;
                        }
                    }
                    if (!checkExit) {
                        tableNew.setTableId(tableId);
                        break;
                    } else {
                        System.err.println(ShopMessage.TABLENAME_LENGTH_NOTIFY);
                    }
                } else {
                    System.err.println(ShopMessage.TABLECHARAT_NOTIFY);
                }
            } else {
                System.err.println("Vui lòng nhập mã gồm 5 kí tự");
            }
        } while (true);
        System.out.println("Nhập vào tên bàn");
        tableNew.setTableName(scanner.nextLine());
        System.out.println("Nhập vào số chỗ ngồi");
        do {
            String tableSeat = scanner.nextLine();
            if (CheckValidate.checkInterger(tableSeat)) {
                if (Integer.parseInt(tableSeat) > 0) {
                    tableNew.setSeatNumber(Integer.parseInt(tableSeat));
                    break;
                } else {
                    System.err.println("Vui lòng nhập số chỗ ngồi lớn hơn 0");
                }
            }
        } while (true);
        System.out.println("Nhập vào trạng thái bàn");
        System.out.println("1.Trống");
        System.out.println("2.Đang sử dụng");
        System.out.println("3.Đang ghép");
        System.out.println("4.Hỏng");
        do {
            String choice = scanner.nextLine();
            if (choice.trim().length() != 0) {
                if (CheckValidate.checkInterger(choice)) {
                    tableNew.setTableStatus(Integer.parseInt(choice));
                    break;
                } else {
                    System.err.println("Trường không được để trống");
                    break;
                }
            }
        } while (true);
        return tableNew;
    }

    @Override
    public boolean writeToFile(List<Table> list) {
        FileImp<Table> fileImp = new FileImp<>();
        return fileImp.writerToFile(list, ShopConstant.URL_TABLE);
    }

    @Override
    public List<Table> readFromFile() {
        FileImp<Table> fileImp = new FileImp<>();
        return fileImp.readDataFromFile(ShopConstant.URL_TABLE);
    }

    @Override
    public void displayData() {
        List<Table> listTable = readFromFile();
        for (Table table : listTable) {
            String status1 = "";
            switch (table.isTableStatus()) {
                case 1:
                    status1 = "Trống";
                    break;
                case 2:
                    status1 = "Đang sử dụng";
                    break;
                case 3:
                    status1 = "Đang ghép";
                    break;
                case 4:
                    status1 = "Hỏng";
                    break;
            }
            System.out.println("*_____________________________________________________________________________________*");
            System.out.printf("%-20s%-20s%-20s%-20s\n", "Mã Bàn","Tên Bàn","Số Chỗ Ngồi","Trạng thái");
            System.out.println("*_____________________________________________________________________________________*");
            System.out.printf("%-20s%-20s%-20s%-20s\n",table.getTableId(),table.getTableName(),table.getSeatNumber(),status1);
        }
    }
    @Override
    public boolean updateTableStatus(String str) {
        Scanner scanner = new Scanner(System.in);
        List<Table> listTable = readFromFile();
        boolean returnData = false;
        for (Table tableUpdate : listTable) {
            if (tableUpdate.getTableId().contains(str)) {
                System.out.println("1.Trống");
                System.out.println("2.Đang ghép");
                System.out.println("3.Đang sử dụng");
                System.out.println("4.Hỏng");
                do {
                    String choice = scanner.nextLine();
                    if (CheckValidate.checkInterger(choice)) {
                        if (choice.length() >= 1 && choice.length() <= 4) {
                            tableUpdate.setTableStatus(Integer.parseInt(choice));
                            returnData = true;
                            break;
                        } else {
                            System.err.println("Vui lòng chọn từ 1-4 ");
                        }
                    } else {
                        System.err.println("vui lòng nhập 1 số");
                    }
                } while (true);
            }
            boolean result = writeToFile(listTable);
            if (result && returnData) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Table updateTable(Scanner scanner) {
        List<Table>listTale = readFromFile();
        System.out.println("Nhập mã bàn cần cập nhật");
        String tabUpdate = scanner.nextLine();
        boolean exit = false;
        for (Table table1:listTale) {
            if (table1.getTableId().equals(tabUpdate)){
                exit = true;
                System.out.println("Cập nhật tên bàn");
                String tableName;
                do {
                    tableName = scanner.nextLine();

                    if (tableName.trim()!=""&&tableName.length()!=0){
                        if (CheckValidate.checkTableNameLength(tableName)){
                                boolean checkExit = false;
                                for (Table tab:listTale) {
                                    if (tab.getTableName().equals(tableName)){
                                        checkExit = true;
                                        break;
                                    }
                                }
                                if (!checkExit){
                                    table1.setTableName(tableName);
                                    break;
                                }else {
                                    System.err.println("Tên bàn đã tồn tại");
                                }
                        }else {
                            System.err.println("Vui lòng nhập tên bàn gồm 5 kí tự");
                        }
                    }else {
                        break;
                    }
                }while (true);
                System.out.println("Nhập vào số chỗ ngồi cần cập nhật");
                String seatTable = scanner.nextLine();
                if (seatTable.trim().length()!=0){
                    if (CheckValidate.checkInterger(seatTable)){
                        table1.setSeatNumber(Integer.parseInt(seatTable));
                        break;
                    }else {
                        System.err.println("Nhập vào 1 số nguyên");
                    }
                }else {
                    break;
                }
            }
        }
        boolean result = writeToFile(listTale);
        if (!exit){
            System.err.println("Không tìm thấy");
        }else {
            if (result){
                System.out.println("Cập nhật trạng thái thành công");
            }
        }
        return null;
    }
}
