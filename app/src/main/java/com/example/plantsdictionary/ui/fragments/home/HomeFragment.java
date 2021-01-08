package com.example.plantsdictionary.ui.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantsdictionary.R;
import com.example.plantsdictionary.ui.controls.recyclerview.RecyclerCardViewAdapter;
import com.example.plantsdictionary.ui.controls.ui.ActionRecyclerViewHolder;

/**
 * Фрагмент с действиями
 */
public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       //Вытащить ViewModel из фрагмента
        //Менеджеры отвечают за работу с системой, ему при работе передаётся текущий фрагмент
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        //Создание фрагмента на основе fragment_home layout
        //View создаётся на основе xml из переданного фрагмента
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//Создание RecyclerView
         //Вытащить RecyclerView
        final RecyclerView actionRecyclerView = root.findViewById(R.id.actionsRecyclerView);
        //Создание таблицы с 2 колонками и неограниченным количеством строк
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        actionRecyclerView.setLayoutManager(gridLayoutManager);
        //Задание адаптера: создание адаптера с передачей actions, ссылки на фрагмент
        //Задание layout для recyclerView, задание фабрики: возврат actionrecyclerviewholder для view
        actionRecyclerView.setAdapter(new RecyclerCardViewAdapter(homeViewModel.getActionViewModels(), this, R.layout.action_item,
                view -> new ActionRecyclerViewHolder(view)));
//Привязка наблюдателя к действию: обращаемся к recyclerView, получаем адаптер, сообщаем адаптеру, что необходимо перечитать данные
        //Триггер перерисовки карточек
        homeViewModel.getActionViewModels().observe(getViewLifecycleOwner(), actionViewModels -> actionRecyclerView.getAdapter().notifyDataSetChanged());

        return root;
    }
}