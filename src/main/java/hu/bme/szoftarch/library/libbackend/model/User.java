package hu.bme.szoftarch.library.libbackend.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;

    private List<Book> books;

    private List<Writing> booksRead;

    private Subscription subscription;
}
