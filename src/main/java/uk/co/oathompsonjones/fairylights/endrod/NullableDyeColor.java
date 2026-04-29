package uk.co.oathompsonjones.fairylights.endrod;

import net.minecraft.util.DyeColor;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.function.IntFunction;

public enum NullableDyeColor implements StringIdentifiable {
    WHITE(0, "white"),
    ORANGE(1, "orange"),
    MAGENTA(2, "magenta"),
    LIGHT_BLUE(3, "light_blue"),
    YELLOW(4, "yellow"),
    LIME(5, "lime"),
    PINK(6, "pink"),
    GRAY(7, "gray"),
    LIGHT_GRAY(8, "light_gray"),
    CYAN(9, "cyan"),
    PURPLE(10, "purple"),
    BLUE(11, "blue"),
    BROWN(12, "brown"),
    GREEN(13, "green"),
    RED(14, "red"),
    BLACK(15, "black"),
    NULL(16, "null");

    public static final  StringIdentifiable.Codec<NullableDyeColor> CODEC = StringIdentifiable.createCodec(
            NullableDyeColor::values);
    private static final IntFunction<NullableDyeColor>              BY_ID = ValueLists.createIdToValueFunction(NullableDyeColor::getId,
            values(),
            ValueLists.OutOfBoundsHandling.ZERO
    );
    private final        int                                        id;
    private final        String                                     name;

    NullableDyeColor(int id, String name) {
        this.id   = id;
        this.name = name;
    }

    public static NullableDyeColor byId(int id) {
        return BY_ID.apply(id);
    }

    @Nullable
    @Contract("_,!null->!null;_,null->_")
    public static NullableDyeColor byName(
            String name,
            @Nullable
            NullableDyeColor defaultColor
    ) {
        NullableDyeColor dyeColor = CODEC.byId(name);
        return dyeColor != null ? dyeColor : defaultColor;
    }

    public static NullableDyeColor byDyeColor(
            @Nullable
            DyeColor dyeColor
    ) {
        if (dyeColor == null)
            return NULL;
        return byName(dyeColor.getName(), NULL);
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }

    public String asString() {
        return this.name;
    }
}
