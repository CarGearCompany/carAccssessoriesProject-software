package controllers;

import models.CarGear;

public class Logout {

    private Logout() {
    }

   public static void logout(){
        CarGear.setCurrentUser(null);
   }
}
