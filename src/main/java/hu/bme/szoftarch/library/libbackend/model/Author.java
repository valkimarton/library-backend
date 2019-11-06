package hu.bme.szoftarch.library.libbackend.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {
    private Long id;

    private String name;

    private List<Writing> books;

}
