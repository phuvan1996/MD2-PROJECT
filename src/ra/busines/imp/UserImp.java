package ra.busines.imp;

import ra.busines.design.IUser;
import ra.busines.entyti.User;
import ra.config.CheckValidate;
import ra.config.ShopConstant;
import ra.data.FileImp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public  class  UserImp implements IUser<User,Integer> {

    @Override
    public boolean creat(User user) {
        List<User> listUser = readFromFile();
        if (listUser == null) {
            listUser = new ArrayList<>();
        }
        listUser.add(user);
        boolean check = writeToFile(listUser);
        return check;
    }

    @Override
    public List<User> findAll() {
        return readFromFile();
    }

    @Override
    public boolean update(Integer id) {
        List<User> listUser = readFromFile();
        boolean returnData = false;
        for (User user1:listUser) {
            if (user1.getUserId()==(id)){
                user1.setUserStatus(!user1.isUserStatus());
                returnData =true;
            }
        }
        boolean result = writeToFile(listUser);
        if (result && returnData) {
            System.out.println("Thay đổi trạng thái thành công");
            return true;
        }
        return false;
    }
    @Override
    public User inputData(Scanner scanner) {
        List<User> listUser = readFromFile();
        if (listUser == null) {
            listUser = new ArrayList<>();
        }
        User userNew = new User();
        if (listUser.size() == 0) {
            userNew.setUserId(1);
        } else {
            int max = 0;
            for (User user : listUser) {
                if (max < user.getUserId()) {
                    max = user.getUserId();
                }
            }
            userNew.setUserId(max + 1);
        }
        System.out.println("nhập vào tên đăng nhập");
        do {
            userNew.setUserName(scanner.nextLine());
            boolean checkExit = false;
            if (CheckValidate.checkUserNameLength(userNew.getUserName())) {
                if (CheckValidate.checkUserName(userNew.getUserName())) {
                    for (User user : listUser) {
                        if (user.getUserName().equals(userNew.getUserName())) {
                            checkExit = true;
                            break;
                        }
                    }
                    if (!checkExit) {
                        break;
                    } else {
                        System.err.println("Tên đăng nhập đã tồn tại");
                    }
                } else {
                    System.err.println("vui lòng nhập tên gồm chữ thường chữ hoa và số");
                }
            } else {
                System.err.println("Vui lòng nhập tên từ 6 kí tự trở lên!");
            }
        } while (true);
        System.out.println("Nhập mật khẩu");
        userNew.setPassWord(scanner.nextLine());
        String checkPassWord;
        System.out.println("Nhập lại mật khẩu");
        do {
            checkPassWord = scanner.nextLine();
            if (userNew.getPassWord().equals(checkPassWord)) {
                break;
            } else {
                System.err.println("Mật khẩu không trùng khớp,vui lòng nhập lại");
            }
        } while (true);
        System.out.println("Nhập tên chủ tài khoản");
        userNew.setUserFullName(scanner.nextLine());
        System.out.println("Nhập vào trạng thái tài khoản");
        System.out.println("1.Hoạt động");
        System.out.println("2.Khoá");
        do {
            String str = scanner.nextLine();
            if (CheckValidate.checkInterger(str)) {
                if (Integer.parseInt(str) == 1) {
                    userNew.setUserStatus(true);
                    break;
                } else if (Integer.parseInt(str) == 2) {
                    userNew.setUserStatus(false);
                    break;
                } else {
                    System.out.println("vui lòng chon 1 hoặc 2");
                }
            }
        } while (true);
        System.out.println("Nhập email");
        do {
            String email = scanner.nextLine();
            if (CheckValidate.checkEmail(email)) {
                userNew.setEmail(email);
                break;
            } else {
                System.err.println("Email Sai Định Dạng!Vui Lòng Nhập Lại!");
            }
        } while (true);
        System.out.println("Nhập số điện thoại");
        do {
            String phoneNumBer = scanner.nextLine();
            if (CheckValidate.checkPhoneNumber(phoneNumBer)) {
                userNew.setPhone(phoneNumBer);
                break;
            } else {
                System.err.println("Số điện thoại sai định dạng!Vui lòng nhập lại");
            }
        } while (true);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        userNew.setDate(dateTimeFormatter.format(now));
        return userNew;
    }

    @Override
    public boolean writeToFile(List<User> list) {
        FileImp fileImp = new FileImp<>();
        return fileImp.writerToFile(list, ShopConstant.URL_USER);
    }

    @Override
    public List<User> readFromFile() {
        FileImp<User> fileImp = new FileImp<>();
        return fileImp.readDataFromFile(ShopConstant.URL_USER);
    }

    @Override
    public void displayData() {
        List<User> listUser = readFromFile();
        listUser.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        if (listUser == null) ;
        System.out.println("*_____________________________________________________________________________________________________________________________________________________*");
        System.out.printf("%-5s%-20s%-15s%-25s%-25s%-25s%-20s%-15s\n", "ID", "Tên tài khoản", "Mật khẩu", "Tên chủ tài khoản", "Email", "Phone", "Ngày tạo", "Trạng thái");
        System.out.println("*_____________________________________________________________________________________________________________________________________________________*");
        for (User user : listUser) {
            String status = (user.isUserStatus()) ? "Hoạt động" : "Khoá";
            System.out.printf("%-5d%-20s%-15s%-25s%-25s%-25s%-20s%-15s\n",user.getUserId(),user.getUserName(),user.getPassWord(),user.getUserFullName(),user.getEmail(),user.getPhone(),user.getDate(),status);
        }
    }

    @Override
    public List<User> searchUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên đăng nhập hoặc tên chủ tài khoản cần tìm:");
        String name = scanner.nextLine();
        List<User> listUserFull = readFromFile();
        System.out.println("*_____________________________________________________________________________________________________________________________________________________*");
        System.out.printf("%-5s%-20s%-15s%-25s%-25s%-25s%-20s%-15s\n", "ID", "Tên tài khoản", "Mật khẩu", "Tên chủ tài khoản", "Email", "Phone", "Ngày tạo", "Trạng thái");
        System.out.println("*_____________________________________________________________________________________________________________________________________________________*");
        for (User user : listUserFull) {
                if (user.getUserName().equals(name)||user.getUserFullName().equals(name)) {
                    String status = (user.isUserStatus()) ? "Hoạt động" : "Khoá";
                    System.out.printf("%-5d%-20s%-15s%-25s%-25s%-25s%-20s%-15s\n",user.getUserId(),user.getUserName(),user.getPassWord(),user.getUserFullName(),user.getEmail(),user.getPhone(),user.getDate(),status);
                }
           }
            return null;
    }

}