package org.github.brnmb.android.active.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;
import java.util.jar.Attributes;

/** Hero Attribute model
 *
 * @author Bruno Miranda Brandão
 */
@Table(name = "hero_attributes")
public class HeroAttribute extends Model {

    /**
     * Attribute name
     */
    @Column(name = "hero_attribute_name")
    public String name;

    /**
     * Attribute constructor, IF the attribute already exists then update ELSE create
     *
     * @param attributeName Attribute name
     */
    public HeroAttribute(String attributeName) {
        super();
        this.name = attributeName;

        HeroAttribute self = new Select()
                .from(HeroAttribute.class)
                .where("hero_attribute_name = ?", attributeName)
                .executeSingle();

        if (self == null) {
            this.save();
        } else {
            self.name = attributeName;
            self.save();
        }
    }

    public HeroAttribute() {
        super();
    }

    /**
     * Get all attributes on database
     *
     * @return List of all heros
     */
    public static List<HeroAttribute> getAllAttributes() {
        return new Select().from(HeroAttribute.class).execute();
    }

    /**
     * Get all hero with the specified attribute on database
     *
     * @return List of all heros with the specified attribute
     */
    public List<Hero> getHerosByAttribute() {
        return getMany(Hero.class, "hero_attribute");
    }
}
