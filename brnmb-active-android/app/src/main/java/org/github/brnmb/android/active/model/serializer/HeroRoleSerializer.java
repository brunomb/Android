package org.github.brnmb.android.active.model.serializer;

import com.activeandroid.serializer.TypeSerializer;
import org.github.brnmb.android.active.enums.HeroRoleEnum;

@SuppressWarnings("unused")
public class HeroRoleSerializer extends TypeSerializer{
    @Override
    public Class<?> getDeserializedType() {
        return HeroRoleEnum.class;
    }

    @Override
    public Class<?> getSerializedType() {
        return String.class;
    }

    @Override
    public String serialize(Object data) {
        return ((HeroRoleEnum) data).getHeroRole();
    }

    @Override
    public HeroRoleEnum deserialize(Object data) {
        return HeroRoleEnum.valueOf((String) data);
    }
}
