package com.konpi.flower.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ModItem annotation.
 * For auto register items.
 * Use on class extends Item.
 * @author Infinity_rain
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModItem
{
    /**
     * Registry name of item
     * @return RegistryName
     */
    String name();

    /**
     * Unlocalized name of block
     * @return TranslateKey
     */
    String translate() default "";
}