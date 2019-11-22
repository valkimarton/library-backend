package hu.bme.szoftarch.library.libbackend.dto;

import java.util.List;

public class AuthorDTO {
    private Long id;
    private String name;
    private List<Long> bookIds;

    public AuthorDTO() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<Long> bookIds) {
        this.bookIds = bookIds;
    }
}
