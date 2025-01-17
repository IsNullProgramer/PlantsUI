package com.example.plantsdictionary.ui.fragments.byfamily;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantsdictionary.R;
import com.example.plantsdictionary.ui.controls.bindings.TextBinder;
import com.example.plantsdictionary.ui.controls.recyclerview.RecyclerCardViewAdapter;
import com.example.plantsdictionary.ui.controls.ui.FamilyRecyclerViewHolder;

/**
 * Фрагмент семейства
 */
public class ByFamilyFragment extends Fragment {

    private ByFamilyViewModel byFamilyViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        byFamilyViewModel = new ViewModelProvider(this).get(ByFamilyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_by_family, container, false);

        /**
         * Отображения списка семейств
         * Аналогично с цветами: передача модели, ссылки на фрагмент, layout
         * и фабрика карточек семейств
         */
        RecyclerView recyclerView = root.findViewById(R.id.familiesRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new RecyclerCardViewAdapter(byFamilyViewModel.getFamilyViewModel(), this, R.layout.family_item,
                x -> new FamilyRecyclerViewHolder((x))));
//Поиск, назначение слушателя для изменения текста
        TextView searchTextView = root.findViewById(R.id.searchEditText);

        searchTextView.addTextChangedListener(new TextBinder(byFamilyViewModel.getSearchValue()));
//Оповещение об изменении
        //Оповещение recyclerView об обновлении
        byFamilyViewModel.getFamilyViewModel().observe(getViewLifecycleOwner(), x -> recyclerView.getAdapter().notifyDataSetChanged());
        //Релоад данных (живой поиск)
        byFamilyViewModel.getSearchValue().observe(getViewLifecycleOwner(), x -> byFamilyViewModel.reloadData());

        return root;
    }
}