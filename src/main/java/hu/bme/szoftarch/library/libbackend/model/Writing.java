package hu.bme.szoftarch.library.libbackend.model;

import hu.bme.szoftarch.library.libbackend.model.enums.Category;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.awt.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "writing")
public class Writing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Enumerated(EnumType.STRING)
    private Category category;

    @PositiveOrZero
    private int pages;

    @Column(name = "date_of_publication")
    @Temporal(TemporalType.DATE)
    private Date dateOfPublication;

    @Column(name = "concrete_books")
    @OneToMany(mappedBy = "writing")
    private List<Book> concreteBooks;

    /*  TODO
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] cover;
     */

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Date getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(Date dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public List<Book> getConcreteBooks() {
        return concreteBooks;
    }

    public void setConcreteBooks(List<Book> concreteBooks) {
        this.concreteBooks = concreteBooks;
    }

    public void addConcreteBook(Book book) {
        this.concreteBooks.add(book);
    }

    @Override
    public String toString() {
        return "Writing{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
