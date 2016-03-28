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
     * Hero cons
     */
    @Column(name = "hero_cons")
    public String cons;

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
        String[] cons = new String[3];
        cons[0] = "a";
        cons[1] = "b";
        cons[2] = "c";

        Hero self = new Select()
                .from(Hero.class)
                .where("hero_name = ?", heroName)
                .executeSingle();

        if (self == null) {
            this.setCons(cons);
            this.save();
        } else {
            self.name = heroName;
            self.heroAttribute = attribute;
            self.heroRole = heroRole;
            this.setCons(cons);
            self.save();
        }
    }

    /**
     * Set the hero counters, receives a list of string and serialize to a String
     *
     * @param heroCons Counters list
     *
     */
    public void setCons(String[] heroCons) {
        ConsSerializer serializer = new ConsSerializer();
        this.cons = (String) serializer.serialize(heroCons);
    }

    /**
     * Get the hero counters list
     *
     * @return Counters list
     *
     */
    public String[] getCons() {
        ConsSerializer serializer = new ConsSerializer();
        return (String[]) serializer.deserialize(this.cons);
    }
}
