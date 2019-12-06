package indi.cloud.oauth.center.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Message {

    private  static String  UserNotFound;
    private static String UserDisabled;
    private static String UserExpired;
    private static String InstitutionDisabled;
    private static String InstitutionExpired;


    public static String getUserNotFound() {
        return UserNotFound;
    }

    public static String getUserDisabled() {
        return UserDisabled;
    }

    public static String getUserExpired() {
        return UserExpired;
    }

    public static String getInstitutionExpired() {
        return InstitutionExpired;
    }

    public static String getInstitutionDisabled() {
        return InstitutionDisabled;
    }

    @Value("${ccx.message.user-not-found}")
    public void setUserNotFound(String userNotFound) {
        UserNotFound = userNotFound;
    }

    @Value("${ccx.message.user-disabled}")
    public void setUserNotEnabled(String userDisabled) {
        UserDisabled = userDisabled;
    }

    @Value("${ccx.message.user-expired}")
    public void setUserExpired(String userExpired) {
        UserExpired = userExpired;
    }

    @Value("${ccx.message.institution-disabled}")
    public void setInstitutionLocked(String institutionDisabled) {
        InstitutionDisabled = institutionDisabled;
    }

    @Value("${ccx.message.institution-expired}")
    public void setInstitutionExpired(String institutionExpired) {
        InstitutionExpired = institutionExpired;
    }
}
