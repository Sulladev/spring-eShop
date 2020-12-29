package ru.geekbrains.controller.repr;

import ru.geekbrains.persist.model.Product;
import ru.geekbrains.persist.model.Role;
import ru.geekbrains.persist.model.User;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class RoleRepr {

    private Long id;

    @NotEmpty
    private String name;

    private List<User> users;

    public RoleRepr() {
    }

    public RoleRepr(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.users = role.getUsers();
    }


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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "RoleRepr{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}


