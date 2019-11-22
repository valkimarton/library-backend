package hu.bme.szoftarch.library.libbackend.dto;

import java.util.Date;

public class BookDTO {
    private Long id;
    private Long writingId;
    private Long lendeeId;
    private int lendTime;
    private Date returnDate;

    public BookDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWritingId() {
        return writingId;
    }

    public void setWritingId(Long writingId) {
        this.writingId = writingId;
    }

    public Long getLendeeId() {
        return lendeeId;
    }

    public void setLendeeId(Long lendeeId) {
        this.lendeeId = lendeeId;
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
}
