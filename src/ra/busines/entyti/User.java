package ra.busines.entyti;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
  private   int userId;
   private String userName;
   private String confirmPassword;
   private boolean userStatus;
   private String email;
   private String phone;
   private String passWord;
   private String userFullName;
   private String date;

 public User() {
    }

 public User(int userId, String userName, String confirmPassword, boolean userStatus, String email, String phone, String passWord, String userFullName, String date) {
  this.userId = userId;
  this.userName = userName;
  this.confirmPassword = confirmPassword;
  this.userStatus = userStatus;
  this.email = email;
  this.phone = phone;
  this.passWord = passWord;
  this.userFullName = userFullName;
  this.date = date;
 }

 public int getUserId() {
  return userId;
 }

 public void setUserId(int userId) {
  this.userId = userId;
 }

 public String getUserName() {
  return userName;
 }

 public void setUserName(String userName) {
  this.userName = userName;
 }

 public String getConfirmPassword() {
  return confirmPassword;
 }

 public void setConfirmPassword(String confirmPassword) {
  this.confirmPassword = confirmPassword;
 }

 public boolean isUserStatus() {
  return userStatus;
 }

 public void setUserStatus(boolean userStatus) {
  this.userStatus = userStatus;
 }

 public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }

 public String getPhone() {
  return phone;
 }

 public void setPhone(String phone) {
  this.phone = phone;
 }

 public String getPassWord() {
  return passWord;
 }

 public void setPassWord(String passWord) {
  this.passWord = passWord;
 }

 public String getUserFullName() {
  return userFullName;
 }

 public void setUserFullName(String userFullName) {
  this.userFullName = userFullName;
 }

 public String getDate() {
  return date;
 }

 public void setDate(String date) {
  this.date = date;
 }
}
