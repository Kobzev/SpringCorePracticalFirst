package ua.kobzev.theatre.domain;

import javax.persistence.*;

/**
 * Created by Kostiantyn_Kobziev on 3/29/2016.
 */
@Entity
@Table(name = "accounts")
public class UserAccount {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    @JoinColumn(name = "userid")
    private User user;

    @Column(name = "money")
    private Double prepaidMoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getPrepaidMoney() {
        return prepaidMoney;
    }

    public void setPrepaidMoney(Double prepaidMoney) {
        this.prepaidMoney = prepaidMoney;
    }

    public UserAccount(User user, Double prepaidMoney) {
        this.user = user;
        this.prepaidMoney = prepaidMoney;
    }

    public UserAccount() {
    }

    public UserAccount(Integer id, User user, Double prepaidMoney) {
        this.id = id;
        this.user = user;
        this.prepaidMoney = prepaidMoney;
    }
}
