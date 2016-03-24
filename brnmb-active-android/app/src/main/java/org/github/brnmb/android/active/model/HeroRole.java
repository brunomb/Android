package org.github.brnmb.android.active.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "hero_roles")
public class HeroRole extends Model {
    @Column(name = "hero_role_name")
    public String name;

    public HeroRole() {
        super();
    }

    public HeroRole(String roleName) {
        this.name = roleName;
    }

    public static List<HeroRole> getAllHeroRoles() {
        return new Select().from(HeroRole.class).execute();
    }

    public List<Hero> heros() {
        return getMany(Hero.class, "HeroRole");
    }
}
