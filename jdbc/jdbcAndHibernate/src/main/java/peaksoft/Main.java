package peaksoft;

import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;
import peaksoft.util.Util;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        boolean isTrue=true;
        while(isTrue) {

            System.out.println("""
                    
                    1 to create table 
                    2 to drop table 
                    3 to save new user to table
                    4 to clean table 
                    5 remove by id 
                    6 get all users
                    0 to exit 
                    enter: 
                    """);
            int num = new Scanner(System.in).nextInt();
            switch (num) {
                case 1 -> userService.createUsersTable();
                case 2 -> userService.dropUsersTable();
                case 3 -> userService.saveUser("Aizada", "Abdyrazakova", (byte) 20);
                case 30 -> userService.saveUser("Misora","Akary",(byte) 20);
                case 4 -> userService.cleanUsersTable();
                case 5 -> userService.removeUserById(6L);
                case 6 -> System.out.println(userService.getAllUsers());
                case 0 -> isTrue=false;
            }
        }
    }
}
