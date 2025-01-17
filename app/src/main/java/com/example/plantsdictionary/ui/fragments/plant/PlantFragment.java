package com.example.plantsdictionary.ui.fragments.plant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.plantsdictionary.R;
import com.example.plantsdictionary.infrastructure.ioc.IOCFactory;
import com.example.plantsdictionary.interfaces.ImageProvider;
import com.example.plantsdictionary.ui.controls.base.FragmentWithModel;
import com.example.plantsdictionary.ui.controls.base.fragmentmodels.PlantParcelableModel;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class PlantFragment extends FragmentWithModel<PlantParcelableModel> {

    private PlantViewModel plantViewModel;

    public PlantFragment() {
        super(R.string.plantparcelablemodel);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//Получение модели и построение View на её основе
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        plantViewModel = new ViewModelProvider(this).get(PlantViewModel.class);
        View root = inflater.inflate(R.layout.fragment_plant, container, false);
//Получение TextView для отображения инфо о растении
        TextView title = root.findViewById(R.id.titleTextView);
        TextView family = root.findViewById(R.id.familyTextView);
        TextView description = root.findViewById(R.id.descriptionTextView);
//Вытащить картинку для растения
        ImageView favorite = root.findViewById(R.id.favoriteImageView);
        ImageView image = root.findViewById(R.id.plantImageView);

        ImageProvider imageProvider = IOCFactory.getIContainer().resolve(ImageProvider.class);
//Выставление данных при изменении,
        plantViewModel.getPlantViewModelMutableLiveData().observe(getViewLifecycleOwner(), x -> {
            title.setText(x.getTitle());
            family.setText(x.getFamily());
            description.setText(x.getDescription());
//Назначение картинки избранного
            favorite.setImageResource(x.isFavorite() ? R.drawable.ic_favorite_active : R.drawable.ic_favorite);
            image.setImageBitmap(imageProvider.loadBitmap(x.getImageName()));
        });

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plantViewModel.setFavorite();
            }
        });
//Загрузка актуальных растений
        plantViewModel.loadPlantById(model.getId());

        return root;
    }
}