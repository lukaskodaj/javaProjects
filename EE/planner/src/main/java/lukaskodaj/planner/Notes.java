package lukaskodaj.planner;


import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "userId")
    private int userId;

    @Column(name = "title")
    private String title;

    @Column(name = "note")
    @Type(type = "text")
    private String note;

    @Column(name = "cleanNote")
    @Type(type = "text")
    private String cleanNote;

    @Column(name = "type")
    private String type;

    @Column(name = "created")
    @Type(type = "date")
    private Date created;

    @Column(name = "edited")
    @Type(type = "timestamp")
    private Timestamp edited;

    public Notes()
    {

    }

    public Notes(int userId, String title, String note, String cleanNote, String type, Date created, Timestamp edited) {
        this.userId = userId;
        this.title = title;
        this.note = note;
        this.cleanNote = cleanNote;
        this.type = type;
        this.created = created;
        this.edited = edited;
    }

    public String getCleanNote() {
        return cleanNote;
    }

    public void setCleanNote(String cleanNote) {
        this.cleanNote = cleanNote;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNote() {
        return note;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getEdited() {
        return edited;
    }

    public void setEdited(Timestamp edited) {
        this.edited = edited;
    }



//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(long userId) {
//        this.userId = userId;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
}
