package lesson.two.task4;

public class AddressFormPhonesData {
    private final String homePhone;
    private final String mobilePhone;
    private final String workPhone;

    public AddressFormPhonesData(String homePhone, String mobilePhone, String workPhone) {
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }
}
