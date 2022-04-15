package ma.enset.MyContacts;

public class Contact {

    private String id;
    private String name;
    private String job;
    private String phone;

    public Contact(String id, String name, String job, String phone) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.phone = phone;
    }

    public Contact() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
