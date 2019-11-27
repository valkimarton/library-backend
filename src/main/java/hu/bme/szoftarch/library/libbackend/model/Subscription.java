package hu.bme.szoftarch.library.libbackend.model;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "max_lendable_books")
    @Positive
    private int maxLendableBooks;

    @Column(name = "price_per_month")
    @PositiveOrZero
    private double pricePerMonth;

    @Column(name = "max_lend_time")
    @PositiveOrZero
    private int maxLendTime;

    public Long getId() {
        return id;
    }

    public int getMaxLendableBooks() {
        return maxLendableBooks;
    }

    public void setMaxLendableBooks(int maxLendableBooks) {
        this.maxLendableBooks = maxLendableBooks;
    }

    public double getPricePerMonth() {
        return pricePerMonth;
    }

    public void setPricePerMonth(double pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    public int getMaxLendTime() {
        return maxLendTime;
    }

    public void setMaxLendTime(int maxLendTime) {
        this.maxLendTime = maxLendTime;
    }
}
