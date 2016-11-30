package com.github.brnmb.amounty.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.Date;
import java.util.List;

@Table(name = "amounty")
public class Amounty extends Model{

    @Column(name = "name")
    private String mName;

    @Column(name = "creation")
    private Date mCreation;

    public Amounty() {

    }

    public Amounty(String name, Date creation) {
        mName = name;
        mCreation = creation;
    }

    public static List<Amounty> getAllAmounty() {
        return new Select()
                .from(Amounty.class)
                .execute();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Date getCreationDate() {
        return mCreation;
    }

    public void setCreationDAte(Date creationDate) {
        mCreation = creationDate;
    }

}
