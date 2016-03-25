package org.github.brnmb.android.active.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/** Hero role model
 *
 * @author Bruno Miranda Brandão
 */
@Table(name = "hero_roles")
public class HeroRole extends Model {

    /**
     * Role name
     */
    @Column(name = "hero_role_name")
    public String name;

    /**
     * Role constructor, IF the role already exists then update ELSE create
     *
     * @param roleName Role name
     */
    public HeroRole(String roleName) {
        super();
        this.name = roleName;
        HeroRole self = new Select()
                .from(HeroRole.class)
                .where("hero_role_name = ?", roleName)
                .executeSingle();

        if (self == null) {
            this.save();
        } else {
            self.name = roleName;
            self.save();
        }
    }

    public HeroRole() {
        super();
    }

    /**
     * Get all roles on database
     *
     * @return List of all roles
     */
    public static List<HeroRole> getAllHeroRoles() {
        return new Select().from(HeroRole.class).execute();
    }

    /**
     * Get all hero with the specified role on database
     *
     * @return List of all heros with the specified role
     */
    public List<Hero> getHerosByAttribute() {
        return getMany(Hero.class, "hero_role");
    }
}
