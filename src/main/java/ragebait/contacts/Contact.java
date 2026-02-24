package ragebait.contacts;

public class Contact {
    private String name;
    private String phone;
    private String email;

    public Contact(String name, String phone, String email) {
        this.name = name.trim();
        this.phone = phone.trim();
        this.email = email.trim();
    }

    // Getters
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }

    // Setters
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return name + " | Phone: " + phone + " | Email: " + email;
    }

    public String toFileFormat() {
        return name + " | " + phone + " | " + email;
    }
}
