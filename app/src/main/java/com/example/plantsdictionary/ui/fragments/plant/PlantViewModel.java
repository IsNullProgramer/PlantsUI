package com.example.plantsdictionary.ui.fragments.plant;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantsdictionary.data.models.Favorite;
import com.example.plantsdictionary.infrastructure.ioc.IOCFactory;
import com.example.plantsdictionary.infrastructure.ioc.IOContainer;
import com.example.plantsdictionary.interfaces.DataProvider;

public class PlantViewModel extends ViewModel {

    private final DataProvider dataProvider;
    MutableLiveData<com.example.plantsdictionary.ui.controls.ui.models.PlantViewModel> plantViewModelMutableLiveData;

    public PlantViewModel() {
        plantViewModelMutableLiveData = new MutableLiveData<>();
        dataProvider = IOCFactory.getIContainer().resolve(DataProvider.class);
    }

    public void loadPlantById(int id) {
        plantViewModelMutableLiveData.postValue(new com.example.plantsdictionary.ui.controls.ui.models.PlantViewModel(null, dataProvider.getPlantById(id), dataProvider.isFavoritesExists(id)));
    }

    public MutableLiveData<com.example.plantsdictionary.ui.controls.ui.models.PlantViewModel> getPlantViewModelMutableLiveData() {
        return plantViewModelMutableLiveData;
    }
//Получение акутальных данных
    public void setFavorite() {
        com.example.plantsdictionary.ui.controls.ui.models.PlantViewModel plantViewModel = plantViewModelMutableLiveData.getValue();
        //Если мы нажали на уже избранное, происходит удаление
        if (plantViewModel.isFavorite())
            dataProvider.deleteFavorite(plantViewModel.getId());
        else
            //Если такого растения в избранных нет, добавляем
            dataProvider.insertFavorite(new Favorite(plantViewModel.getId()));
        //Перезагрузка избранных
        loadPlantById(plantViewModel.getId());
    }
}
