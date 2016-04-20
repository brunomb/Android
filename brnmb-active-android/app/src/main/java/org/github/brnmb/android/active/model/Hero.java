package org.github.brnmb.android.active.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Hero model
 *
 * @author Bruno Miranda Brandão
 */
@Table(name = "heros")
public class Hero extends Model implements Parcelable {

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
     * Hero cons
     */
    @Column(name = "hero_cons")
    public String[] cons;

    /**
     * Hero image
     */
    @Column(name = "hero_image")
    public byte[] heroImage;

    public static final Parcelable.Creator<Hero> CREATOR = new Creator<Hero>() {
        @Override
        public Hero createFromParcel(Parcel in) {
            return new Hero(in);
        }

        @Override
        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };

    public Hero(Parcel in) {
        this.name = in.readString();
        this.heroAttribute = in.readParcelable(HeroAttribute.class.getClassLoader());
//        this.cons = in.readString();
        this.heroImage = new byte[in.readInt()];
//        in.readByteArray(this.heroImage);
    }

    /**
     * Get all heros on database
     *
     * @return List of all heros
     */
    public static List<Hero> getAllHeros() {
        return new Select().from(Hero.class).execute();
    }

    /**
     * Instantiates a new Hero.
     */
    public Hero() {
        super();
    }

    /**
     * Hero constructor, IF the hero already exists then update ELSE create
     *
     * @param heroName  Hero name
     * @param attribute Hero primary attribute
     * @param image     the image
     */
    public Hero(String heroName, HeroAttribute attribute, byte[] image){
        super();
        this.name = heroName;
        this.heroAttribute = attribute;
        this.heroImage = image;

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
            self.setCons(cons);
            self.heroImage = image;
            self.save();
        }
    }

    /**
     * Set the hero counters, receives a list of string and serialize to a String
     *
     * @param heroCons Counters list
     */
    public void setCons(String[] heroCons) {
        this.cons = heroCons;
    }

    /**
     * Get the hero counters list
     *
     * @return Counters list
     */
    public String[] getCons() {
        return this.cons;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeParcelable(heroAttribute, flags);
//        dest.writeString(cons);
        dest.writeInt(heroImage.length);
        dest.writeByteArray(heroImage);
    }
}
