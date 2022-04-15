package ma.enset.procontacts;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM Contact")
    List<Contact> getAll();

    @Insert
    void insert(Contact contact);

    @Query("SELECT * FROM Contact WHERE name LIKE :name")
    List<Contact> findByName(String name);

    @Query("SELECT * FROM Contact WHERE ID= :ID")
    Contact findByID(int ID);


    @Query("DELETE FROM Contact WHERE ID= :ID")
    void delete(String ID);

    @Update
    void update(Contact contact);

    @Query("DELETE FROM Contact")
    void clear();


}
