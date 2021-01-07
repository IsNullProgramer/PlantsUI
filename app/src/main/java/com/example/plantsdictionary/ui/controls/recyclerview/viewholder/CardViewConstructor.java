package com.example.plantsdictionary.ui.controls.recyclerview.viewholder;

import android.view.View;

/**
 * Конструктор для карточек для RecylerView
 * Для адаптера нужно передать фабрику, которая будет создавать карточки
 * Передаётся определённая фабрика которая создаёт определённые карточки
 * Например: создание карточек для Plants, Families и т.д.
 * @param <T>
 */
public interface CardViewConstructor<T> {
    T getCard(View view);
}
