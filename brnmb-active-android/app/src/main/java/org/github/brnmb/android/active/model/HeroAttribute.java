package org.github.brnmb.android.active.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "hero_attributes")
public class HeroAttribute extends Model {
    @Column(name = "hero_attribute_name")
    public String name;

    public HeroAttribute() {
        super();
    }

    public HeroAttribute(String attributeName) {
        super();
        this.name = attributeName;
    }

    public static List<HeroAttribute> getAllAtrributes() {
        return new Select().from(HeroAttribute.class).execute();
    }

    public List<Hero> getHerosByAttribute() {
        return getMany(Hero.class, "hero_attribute");
    }

    public List<Hero> heros() {
        return getMany(Hero.class, "HeroAttribute");
    }
}
