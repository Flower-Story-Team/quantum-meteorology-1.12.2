package com.konpi.flowerofhua.flower.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ModBlock annotation.
 * For auto register blocks.
 * Use on class extends Block.
 * @author Infinity_rain
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModBlock {
    /**
     * Registry name of block
     * @return RegistryName
     */
    String name();

    /**
     * Unlocalized name of block
     * @return TranslateKey
     */
    String translate() default "";
}