package hu.bme.szoftarch.library.libbackend.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "writing_id")
    private Writing writing;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private LibUser lendee;

    @Column(name = "lend_time")
    @PositiveOrZero
    private int lendTime;

    @Column(name = "return_date")
    @Temporal(TemporalType.DATE)
    private Date returnDate;

    public Book() {}

    public Book(Writing writing) {
        this.writing = writing;
        this.lendee = null;
        this.lendTime = 0;
        this.returnDate = null;
    }

    public Long getId() {
        return id;
    }

    public Writing getWriting() {
        return writing;
    }

    public void setWriting(Writing writing) {
        this.writing = writing;
    }

    public LibUser getLendee() {
        return lendee;
    }

    public void setLendee(LibUser lendee) {
        this.lendee = lendee;
    }

    public int getLendTime() {
        return lendTime;
    }

    public void setLendTime(int lendTime) {
        this.lendTime = lendTime;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isAvailable() { return this.lendee == null; }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", writing=" + writing +
                '}';
    }

    public Book lend(LibUser user) {
        this.lendee = user;
        this.lendTime = user.getSubscription().getMaxLendTime();

        Calendar returnCalendar = Calendar.getInstance();
        returnCalendar.add(Calendar.DATE, lendTime);
        this.returnDate = returnCalendar.getTime();

        user.addWritingToBooksRead(this.writing);

        return this;
    }

    public Book returnBook() {
        this.lendee = null;
        this.lendTime = 0;
        this.returnDate = null;

        return this;
    }
}
