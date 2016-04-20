package org.github.brnmb.android.active.model.serializer;

import com.activeandroid.serializer.TypeSerializer;
import com.google.gson.Gson;

/**
 * ConsSerializer
 *
 * Serialize list of hero counters as Strings
 *
 * @author Bruno Miranda Brandão
 */
@SuppressWarnings("unused")
public class ConsSerializer extends TypeSerializer {

    /**
     * Gson object
     *
     * Gson object used to conversion
     */
    private static final Gson gson = new Gson();

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getDeserializedType() {
        return String[].class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getSerializedType() {
        return String.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object serialize(final Object object) {
        if (null == object) {
            return null;
        }

        return gson.toJson(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object deserialize(final Object object) {
        if (null == object) {
            return null;
        }

        return gson.fromJson(object.toString(), String[].class);
    }
}