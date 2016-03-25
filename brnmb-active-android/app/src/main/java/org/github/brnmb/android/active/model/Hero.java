package org.github.brnmb.android.active.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/** Hero model
 *
 * @author Bruno Miranda Brandão
 */
@Table(name = "heros")
public class Hero extends Model {

    /**
     * Hero name
     */
    @Column(name = "hero_name")
    public String name;

    /**
     * Primary attribute of the hero
     */
    @Column(name = "hero_attribute")
    public HeroAttribute heroAttribute;

    /**
     * Role of the hero
     */
    @Column(name = "hero_role")
    public HeroRole heroRole;

    /**
     * Get all heros on database
     *
     * @return List of all heros
     */
    public static List<Hero> getAllHeros() {
        return new Select().from(Hero.class).execute();
    }

    public Hero() {
        super();
    }

    /**
     * Hero constructor, IF the hero already exists then update ELSE create
     *
     * @param heroName Hero name
     * @param attribute Hero primary attribute
     * @param role Hero role
     *
     */
    public Hero(String heroName, HeroAttribute attribute, HeroRole role){
        super();
        this.name = heroName;
        this.heroAttribute = attribute;
        this.heroRole = role;

        Hero self = new Select()
                .from(Hero.class)
                .where("hero_name = ?", heroName)
                .executeSingle();

        if (self == null) {
            this.save();
        } else {
            self.name = heroName;
            self.heroAttribute = attribute;
            self.heroRole = heroRole;
            self.save();
        }
    }
}
