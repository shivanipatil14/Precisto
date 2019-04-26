package in.precisto.precisto;

public class UserInfo {

    private String firstName, lastName, contact, dob, email, gender, businessName, industry, businessType, password;

    UserInfo() {
        firstName = "First Name";
        lastName = "Last Name";
        contact = "Contact No.";
        dob = "DD/MM/YY";
        email = "example@mail.com";
        gender = "Gender";
        businessName = "Business Name";
        industry = "Industry";
        businessType = "Business Type";
        password = "";
    }

    String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    String getContact() {
        return contact;
    }

    void setContact(String contact) {
        this.contact = contact;
    }

    public String getDob() {
        return dob;
    }

    void setDob(String dob) {
        this.dob = dob;
    }

    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    String getGender() {
        return gender;
    }

    void setGender(String gender) {
        this.gender = gender;
    }

    String getBusinessName() {
        return businessName;
    }

    void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    String getIndustry() {
        return industry;
    }

    void setIndustry(String industry) {
        this.industry = industry;
    }

    String getBusinessType() {
        return businessType;
    }

    void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }
}

