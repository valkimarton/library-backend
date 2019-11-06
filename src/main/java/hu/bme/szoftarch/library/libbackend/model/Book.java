package hu.bme.szoftarch.library.libbackend.model;

import java.util.Date;

public class Book {
    private Long id;

    private Writing writing;

    private boolean available;

    private int lendTime;

    private Date returnDate;
}
