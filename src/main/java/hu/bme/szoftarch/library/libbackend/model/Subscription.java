package hu.bme.szoftarch.library.libbackend.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "subscription")
public class Subscription {
    private Long id;

    private int maxLendableBooks;

    private double pricePerMonth;
}
