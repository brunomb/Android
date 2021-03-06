package org.github.brnmb.android.active.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import org.github.brnmb.android.active.enums.HeroRoleEnum;
import org.github.brnmb.android.active.exceptions.NoDataException;

import java.util.List;

/** Hero role model
 *
 * @author Bruno Miranda Brandão
 */
@Table(name = "hero_roles")
public class HeroRole extends Model implements Parcelable {

    /**
     * Role name
     */
    @Column(name = "name")
    public HeroRoleEnum role;

    /**
     * Hero
     */
    @Column(name = "hero")
    public Hero hero;

    /**
     * Role constructor, IF the role already exists then update ELSE create a new one
     *
     * @param role Role name
     */
    public HeroRole(HeroRoleEnum role, Hero hero) {
        super();
        this.role = role;
        this.hero = hero;
        HeroRole self = new Select()
                .from(HeroRole.class)
                .where("name = ?", role)
                .where("hero = ?", hero.getId())
                .executeSingle();

        if (self == null) {
            this.save();
        } else {
            self.role = role;
            self.save();
        }
    }

    public HeroRole() {
        super();
    }

    public HeroRoleEnum getName() {
        return role;
    }

    public void setName(HeroRoleEnum name) {
        this.role = name;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    /**
     * Get all roles on database
     *
     * @return List of all roles
     */
    public static List<HeroRole> getAllHeroRoles() throws NoDataException {
        List<HeroRole> allHeroRoles = new Select().from(HeroRole.class).execute();

        if (allHeroRoles != null) {
            if (allHeroRoles.size() > 0) {
                return allHeroRoles;
            }
        }

        throw new NoDataException("getAllHeroRoles. No data found.");
    }

    public static List<Hero> getHerosByRoles(HeroRole role) {
        return new Select()
                .from(HeroRole.class)
                .where("name = ?", role.getName())
                .execute();
    }

    /**
     * Get all hero with the specified role on database
     *
     * @return List of all heros with the specified role
     */
    public static List<Hero> getHerosByAttribute(HeroRole role) {
        return new Select()
                .from(Hero.class)
                .where("hero_role = ?", role.getId())
                .execute();
    }

    public static final Parcelable.Creator<HeroRole> CREATOR = new Creator<HeroRole>() {
        @Override
        public HeroRole createFromParcel(Parcel in) {
            return new HeroRole(in);
        }

        @Override
        public HeroRole[] newArray(int size) {
            return new HeroRole[size];
        }
    };

    public HeroRole(Parcel in) {
        in.writeString(role.getHeroRole());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(role.getHeroRole());
    }
}
