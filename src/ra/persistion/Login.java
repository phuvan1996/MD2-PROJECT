package ra.persistion;

import ra.busines.entyti.User;
import ra.busines.imp.UserImp;
import ra.config.CheckValidate;
import ra.data.FileImp;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Login {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        User user = new User(1,"phuvan","1234",true,"phuvan2604@gmail.com","(84)-(0395888259)","1234","pham phu van","date");
        UserImp userImp = new UserImp();
//        userImp.creat(user);
        do {
            System.out.println("|--------------------------------|");
            System.out.println("|        ĐĂNG NHẬP TÀI KHOẢN     |");
            System.out.println("|        1.Đăng nhập             |");
            System.out.println("|        2.Đăng kí               |");
            System.out.println("|________________________________|");
           String choice;
           do {
               choice = scanner.nextLine();
               if (choice.trim().length()!=0){
                   if (CheckValidate.checkInterger(choice)){
                       break;
                   }else {
                       System.err.println("Vui lòng nhập 1 số nguyên");
                   }
               }
           }while (true);
           switch (Integer.parseInt(choice)){
               case 1:
                   List<User>listUser = userImp.readFromFile();
                   String User,passWord;
                   System.out.println("Nhập tên đăng nhập");
                   User = scanner.nextLine();
                   System.out.println("Nhập mật khẩu");
                   passWord = scanner.nextLine();
                   for (User user1:listUser) {
                       if (user1.getUserName().equals(User)&&user1.getPassWord().equals(passWord)){
                           Shoprun.coffeeManagement();
                           break;
                       }
                   }
                   break;
               case 2:
                   System.exit(0);
           }
        }while (true);
    }
}
