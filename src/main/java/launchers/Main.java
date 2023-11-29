package launchers;

import exceptions.*;
import models.CarGear;

import javax.mail.MessagingException;


public class Main {
    public static void main(String[] args) throws MyAppExceptions, MessagingException {
        CarGear.initData();
        Menu.menuHandler();
    }
}
