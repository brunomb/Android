package org.github.brnmb.android.active.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/** Hero Attribute model
 *
 * @author Bruno Miranda Brandão
 */
@Table(name = "hero_attributes")
public class HeroAttribute extends Model implements Parcelable{

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
    public static List<Hero> getHerosByAttribute(HeroAttribute attribute) {
        return new Select()
                .from(Hero.class)
                .where("hero_attribute = ?", attribute.getId())
                .execute();
    }

    public static final Parcelable.Creator<HeroAttribute> CREATOR = new Creator<HeroAttribute>() {
        @Override
        public HeroAttribute createFromParcel(Parcel in) {
            return new HeroAttribute(in);
        }

        @Override
        public HeroAttribute[] newArray(int size) {
            return new HeroAttribute[size];
        }
    };

    public HeroAttribute(Parcel in) {
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
