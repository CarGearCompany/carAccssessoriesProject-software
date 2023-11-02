package controllers;

import models.CarGear;

public class LogoutController {

    private LogoutController() {
    }

   public static void logout(){
        CarGear.setCurrentUser(null);
   }
}
