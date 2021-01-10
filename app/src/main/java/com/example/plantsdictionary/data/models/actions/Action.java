package com.example.plantsdictionary.data.models.actions;

import com.example.plantsdictionary.interfaces.models.IModelActionSerialize;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * Действие
 */
public class Action {

    /**
     * parcelableClass в Runtime класс через Class.for
     * Вычисляется 1 раз, кэш
     */
    @JsonIgnore
    private Class parcelableRuntimeClass;

    private String name;
    private String caption;
    /**
     * Куда переходить при onClick
     */
    private String navigateTo;
    private String image;
    /**
     * Аргументы перехода
     */
    private List<ActionArguments> actionArguments;
    /**
     * Класс модели при переходе
     */
    private String parcelableClass;
    /**
     * Id модели при переходе. Id - R.string.{name}
     */
    private String parcelableClassId;

    public String getParcelableClassId() {
        return parcelableClassId;
    }



   public void setParcelableClass(String parcelableClass) {
        this.parcelableClass = parcelableClass;
    }

    public String getImage() {
        return image;
    }


    public String getName() {
        return name;
    }

    public String getCaption() {
        return caption;
    }

    public String getNavigateTo() {
        return navigateTo;
    }


    public List<ActionArguments> getActionArguments() {
        return actionArguments;
    }


//ОПределить класс модели при переходе
    public Class<IModelActionSerialize> getParcelableRuntimeClass() throws ClassNotFoundException {
        if (parcelableClass == null)
            return null;
        if (parcelableRuntimeClass != null)
            return parcelableRuntimeClass;
        return parcelableRuntimeClass = Class.forName(parcelableClass);
    }
}
