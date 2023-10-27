package launchers;

import models.CarGear;


public class Main {
    public static void main(String[] args)  {
        CarGear.initData();
        Menu.menuHandler();
    }
}
