package uk.co.oathompsonjones.endrod;

import com.google.common.collect.Lists;
import net.minecraft.state.property.EnumProperty;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ColorProperty extends EnumProperty<NullableDyeColor> {
    protected ColorProperty(String name, Collection<NullableDyeColor> values) {
        super(name, NullableDyeColor.class, values);
    }

    public static ColorProperty of(String name) {
        return of(name, (color) -> true);
    }

    public static ColorProperty of(String name, Predicate<NullableDyeColor> filter) {
        return of(name, Arrays.stream(NullableDyeColor.values()).filter(filter).collect(Collectors.toList()));
    }

    public static ColorProperty of(String name, NullableDyeColor... values) {
        return of(name, Lists.newArrayList(values));
    }

    public static ColorProperty of(String name, Collection<NullableDyeColor> values) {
        return new ColorProperty(name, values);
    }
}