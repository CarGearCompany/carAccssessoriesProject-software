package views;

import controllers.LogoutController;

public class LogoutView {

    private LogoutView() {
    }
    public static void logout(){
        LogoutController.logout();
    }

}
