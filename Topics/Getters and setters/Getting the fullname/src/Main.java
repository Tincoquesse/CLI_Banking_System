
class User {
    
    private String firstName;
    private String lastName;

    public User() {
        this.firstName = "";
        this.lastName = "";
    }

    public void setFirstName(String firstName) {
        if (firstName != null && firstName != "") {
            this.firstName = firstName;
        }
    }

    public void setLastName(String lastName) {
        if (lastName != null && lastName != "") {
            this.lastName = lastName;
        }
    }

    public String getFullName() {
        if (firstName != "" && lastName != "") {
            return firstName.concat(" " + lastName);
        }
        else if (firstName != "" && lastName.equals("")) {
            return firstName;
        }
        else if (firstName.equals("") && lastName != "") {
            return lastName;
        }
        else {
            return "Unknown";
        }
    }

}
