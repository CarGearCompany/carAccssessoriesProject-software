package launchers;

import exceptions.*;
import models.CarGear;


public class Main {
    public static void main(String[] args) throws MyAppExceptions {
        CarGear.initData();
        Menu.menuHandler();
    }
}
