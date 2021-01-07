package com.example.plantsdictionary.interfaces;

import com.example.plantsdictionary.data.models.actions.Action;
import com.example.plantsdictionary.data.models.FamilyPlant;
import com.example.plantsdictionary.data.models.Favorite;
import com.example.plantsdictionary.data.models.Plants;

import java.util.List;

/**
 * Провайдер данных, предоставляет все данные
 */
public interface DataProvider {
    //Список растений
    List<Plants> getAllPlants();
//По семействам
    List<FamilyPlant> getFamilyPlants();
//Список действий
    List<Action> getAllActions();
//Есть ли избранные
    boolean isFavoritesExists(int plantId);
//Добавить избранныый
    void insertFavorite(Favorite favorite);
//Удалить избранный
    void deleteFavorite(int plantId);
//Вытащить растение по id
    Plants getPlantById(int id);
}
