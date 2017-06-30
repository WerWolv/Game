package com.werwolv.api.modloader;

import com.werwolv.gui.IGuiHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Mod{

    String modId();

    String modName() default "";

    String modVersion() default "";

    Class<? extends IGuiHandler> guiHandler() default IGuiHandler.class;


}
