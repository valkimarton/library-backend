package hu.bme.szoftarch.library.libbackend.dto;

import hu.bme.szoftarch.library.libbackend.model.Book;
import hu.bme.szoftarch.library.libbackend.model.Writing;
import hu.bme.szoftarch.library.libbackend.model.enums.Category;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WritingDTO {
    private long id;
    private String title;
    private long authorId;
    private Category category;
    private int pages;
    private Date dateOfPublication;
    private List<Long> concreteBookIds;
    private String description;

    public WritingDTO() {}

    public WritingDTO(Writing writing) {
        id = writing.getId();
        title = writing.getTitle();
        authorId = writing.getAuthor().getId();
        category = writing.getCategory();
        pages = writing.getPages();
        dateOfPublication = writing.getDateOfPublication();
        concreteBookIds = new ArrayList<Long>();
        for (Book book : writing.getConcreteBooks()) {
            concreteBookIds.add(book.getId());
        }
        description = writing.getDescription();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
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

    public List<Long> getConcreteBookIds() {
        return concreteBookIds;
    }

    public void setConcreteBookIds(List<Long> concreteBookIds) {
        this.concreteBookIds = concreteBookIds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


