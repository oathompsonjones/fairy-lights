package uk.co.oathompsonjones.endrod;

import com.google.common.collect.Lists;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.DyeColor;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ColorProperty extends EnumProperty<DyeColor> {
    protected ColorProperty(String name, Collection<DyeColor> values) {
        super(name, DyeColor.class, values);
    }

    public static ColorProperty of(String name) {
        return of(name, (color) -> true);
    }

    public static ColorProperty of(String name, Predicate<DyeColor> filter) {
        return of(name, Arrays.stream(DyeColor.values()).filter(filter).collect(Collectors.toList()));
    }

    public static ColorProperty of(String name, DyeColor... values) {
        return of(name, Lists.newArrayList(values));
    }

    public static ColorProperty of(String name, Collection<DyeColor> values) {
        return new ColorProperty(name, values);
    }
}