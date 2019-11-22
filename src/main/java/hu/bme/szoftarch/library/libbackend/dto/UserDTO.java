package hu.bme.szoftarch.library.libbackend.dto;

import hu.bme.szoftarch.library.libbackend.model.enums.RoleType;

import java.util.List;

public class UserDTO {
    private Long id;
    private String username;
    private List<Long> bookIds;
    private List<Long> booksReadIds;
    private Long subscriptionId;
    private boolean enabled;
    private List<RoleType> roleNames;

    public UserDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Long> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<Long> bookIds) {
        this.bookIds = bookIds;
    }

    public List<Long> getBooksReadIds() {
        return booksReadIds;
    }

    public void setBooksReadIds(List<Long> booksReadIds) {
        this.booksReadIds = booksReadIds;
    }

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<RoleType> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<RoleType> roleNames) {
        this.roleNames = roleNames;
    }
}
