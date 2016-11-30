package com.github.brnmb.amounty.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

@Table(name = "item")
public class Item {

    @Column(name = "name")
    private String name;

    @Column(name = "acquisition")
    private Date acquisition;
}
