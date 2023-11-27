package models;

import enums.Gender;
import enums.UserType;
import exceptions.ItemNotFoundException;
import exceptions.WeakPasswordException;

import java.util.ArrayList;
import java.util.List;

public class Installer extends User{
    private List<Schedule> scheduleList;
    private List<Request> installerRequestList ;
    public Installer() {

    }

    public Installer(Name name, int age, Gender gender, String password, ContactInfo contactInfo, UserType userType) throws WeakPasswordException {
        super(name, age, gender, password, contactInfo, userType);
        this.setUserType(UserType.INSTALLER);
        scheduleList = new ArrayList<>();
        installerRequestList = new ArrayList<>();
    }

    public List<Request> getRequests() {
        return installerRequestList;
    }

    public void addRequest(Request request){
        installerRequestList.add(request);
    }
    public void removeRequest(Request request){
        installerRequestList.remove(request);
    }

    public List<Schedule> getSchedules() {
        return scheduleList;
    }

    public Schedule getScheduleByDate(String date) throws ItemNotFoundException {
        for (Schedule s:
             scheduleList) {
            if(s.getDate().equals(date)){
                return s;
            }
        }

        throw new ItemNotFoundException();
    }

    public void addSchedule(Schedule schedule){
        scheduleList.add(schedule);
    }
    public void removeSchedule(Schedule schedule){
        scheduleList.remove(schedule);
    }

}
