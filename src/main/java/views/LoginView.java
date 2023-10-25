package views;

import controllers.Login;
import models.CarGear;
import scanners.CustomScanner;

import java.util.Scanner;
import java.util.logging.Logger;

public class LoginView {
    private static final Logger logger = Logger.getLogger(LoginView.class.getName());
    private LoginView() {
    }

    public static void login(){
        String email = CustomScanner.scanNonEmptyString("Email" , new Scanner(System.in));
        String password = CustomScanner.scanNonEmptyString("PassWord",new Scanner(System.in));
        if (Login.login(email,password)){
            String msg = "Hi, " + CarGear.getCurrentUser().getName().getFirstName();
            logger.info(msg);
        }else
            logger.warning("Invalid Login");



    }

}
