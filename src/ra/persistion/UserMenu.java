package ra.persistion;

import ra.busines.entyti.User;
import ra.busines.imp.UserImp;
import ra.config.CheckValidate;

import java.util.Scanner;

public class UserMenu {
    private final static UserImp userImp = new UserImp();
    static User user = new User();
    public static void UserManagement(Scanner scanner){
        boolean exitUserMenu = true;
        do {
            System.out.println("|---------------------------------------------------|");
            System.out.println("|           QUẢN LÝ TÀI KHOẢN                       |");
            System.out.println("|---------------------------------------------------|");
            System.out.println("|  1.Danh sách tài khoản theo ngày giảm dần         |");
            System.out.println("|  2.Thêm tài khoản                                 |");
            System.out.println("|  3.Cập nhật trạng thái tài khoản quản trị         |");
            System.out.println("|  4.Tìm kiếm tài khoản theo tên đăng nhập          |");
            System.out.println("|  5.Thoát                                          |");
            System.out.println("|---------------------------------------------------|");
            String choice;
            choice = scanner.nextLine();
            if (choice.trim().length()!=0){
                if (CheckValidate.checkInterger(choice)){
                    switch (Integer.parseInt(choice)){
                        case 1:
                            userImp.displayData();
                            break;
                        case 2:
                            User user1 = userImp.inputData(scanner);
                            boolean result = userImp.creat(user1);
                            break;
                        case 3:
                            System.out.println("nhập mã tài khoản cần cập nhật");
                            String id = scanner.nextLine();
                            userImp.update(Integer.parseInt(id));
                            break;
                        case 4:
                            userImp.searchUser();
                            break;
                        case 5:
                            exitUserMenu = false;
                            break;
                        default:
                            System.err.println("Vui lòng chọn từ 1-5");
                    }
                }else {
                    System.err.println("Vui lòng chọn 1 số");
                }
            }else {
                System.err.println("Vui lòng chọn");
            }
        }while (exitUserMenu);
    }
}
