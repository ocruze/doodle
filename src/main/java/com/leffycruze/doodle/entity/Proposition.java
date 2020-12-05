package com.leffycruze.doodle.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Proposition {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private LocalDate date;

    @Column
    private LocalTime start;

    @Column
    private LocalTime finish;

    @Column(nullable = false)
    @OneToMany(targetEntity = User.class)
    @CollectionTable
    private Set<User> going = new HashSet<>();

    @Column(nullable = false)
    @OneToMany(targetEntity = User.class)
    @CollectionTable
    private Set<User> notGoing = new HashSet<>();

    @Column(nullable = false)
    @OneToMany(targetEntity = User.class)
    @CollectionTable
    private Set<User> maybe = new HashSet<>();

    public Proposition(LocalDate date, LocalTime start, LocalTime finish) {
        this.date = date;
        this.start = start;
        this.finish = finish;
    }

    public Proposition(String date, String start, String finish) {
        String dateArr[] = date.split("-"); // YYYY-MM-DD
        String startArr[] = start.split(":"); // HH:MN
        String finishArr[] = finish.split(":"); // HH:MN

        this.date = LocalDate.of(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]),
                Integer.parseInt(dateArr[2]));
        this.start = LocalTime.of(Integer.parseInt(startArr[0]), Integer.parseInt(startArr[1]));
        this.finish = LocalTime.of(Integer.parseInt(finishArr[0]), Integer.parseInt(finishArr[1]));
    }

    public Proposition() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getFinish() {
        return finish;
    }

    public void setFinish(LocalTime finish) {
        this.finish = finish;
    }

    public Set<User> getGoing() {
        return going;
    }

    public void setGoing(Set<User> going) {
        this.going = going;
    }

    public Set<User> getNotGoing() {
        return notGoing;
    }

    public void setNotGoing(Set<User> notGoing) {
        this.notGoing = notGoing;
    }

    public Set<User> getMaybe() {
        return maybe;
    }

    public void setMaybe(Set<User> maybe) {
        this.maybe = maybe;
    }
}
