package org.github.brnmb.android.active.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "heros")
public class Hero extends Model {
    @Column(name = "hero_name")
    public String name;

    @Column(name = "hero_attribute")
    public HeroAttribute heroAttribute;

    @Column(name = "hero_role")
    public HeroRole heroRole;

    public Hero(){
        super();
    }

    public static List<Hero> getAllHeros() {
        return new Select().from(Hero.class).execute();
    }

    // Make sure to have a default constructor for every ActiveAndroid model
    public Hero(String heroName, HeroAttribute attribute, HeroRole role){
        super();
        this.name = heroName;
        this.heroAttribute = attribute;
        this.heroRole = role;
    }
}
