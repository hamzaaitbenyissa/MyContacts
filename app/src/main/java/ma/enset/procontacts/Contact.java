package ma.enset.procontacts;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Contact implements Serializable {

    @PrimaryKey
    @NonNull
    String id;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "job")
    String job;
    @ColumnInfo(name = "phone")
    String phone;

    public Contact(String id, String name, String job, String phone) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.phone = phone;
    }

    @Ignore
    public Contact(String name, String job, String phone) {
        this.name = name;
        this.job = job;
        this.phone = phone;
    }

    @Ignore
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
