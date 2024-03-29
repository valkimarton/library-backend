package hu.bme.szoftarch.library.libbackend.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
    private User lendee;

    private boolean available;

    @Column(name = "lend_time")
    @Positive
    private int lendTime;

    @Column(name = "return_date")
    @Temporal(TemporalType.DATE)
    private Date returnDate;

    public Long getId() {
        return id;
    }

    public Writing getWriting() {
        return writing;
    }

    public void setWriting(Writing writing) {
        this.writing = writing;
    }

    public User getLendee() {
        return lendee;
    }

    public void setLendee(User lendee) {
        this.lendee = lendee;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", writing=" + writing +
                ", available=" + available +
                '}';
    }
}
