package hu.bme.szoftarch.library.libbackend.model;

import hu.bme.szoftarch.library.libbackend.model.enums.Category;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.List;

public class Writing {
    private Long id;

    private String title;

    private Author author;

    @Enumerated(EnumType.STRING)
    private Category category;

    private int pages;

    private Date dateOfPublication;

    private List<Book> concreteBooks;


}
