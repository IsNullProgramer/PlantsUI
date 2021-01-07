package com.example.plantsdictionary.ui.fragments.byfamily;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantsdictionary.infrastructure.ioc.IOCFactory;
import com.example.plantsdictionary.infrastructure.ioc.IOContainer;
import com.example.plantsdictionary.interfaces.DataProvider;
import com.example.plantsdictionary.ui.controls.ui.models.FamilyPlantViewModel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Модель для семейств
 */
public class ByFamilyViewModel extends ViewModel {

    private MutableLiveData<List<FamilyPlantViewModel>> familyViewModel;
    private MutableLiveData<String> searchValue;

    public ByFamilyViewModel() {
        familyViewModel = new MutableLiveData<>();
        searchValue = new MutableLiveData<>();
        reloadData();
    }

    /**
     * Перезагрузка данных
     */
    public void reloadData() {
        //Получение всех растений
        Stream<FamilyPlantViewModel> stream = IOCFactory.getIContainer().resolve(DataProvider.class).getFamilyPlants().stream().map(x -> new FamilyPlantViewModel(x));
        //Получение поисковой строки и фильтрация через StreamAPI
        String searchText = searchValue.getValue();
        if (searchText != null && searchText.trim().length() != 0)
            stream = stream.filter(x -> x.getTitle().toLowerCase().contains(searchText.toLowerCase()));
        //Преобразование в List
        familyViewModel.postValue(stream.collect(Collectors.toList()));
    }

    public MutableLiveData<String> getSearchValue() {
        return searchValue;
    }

    public MutableLiveData<List<FamilyPlantViewModel>> getFamilyViewModel() {
        return familyViewModel;
    }
}