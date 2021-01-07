package com.example.plantsdictionary.ui.controls.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantsdictionary.ui.controls.recyclerview.viewholder.CardViewConstructor;
import com.example.plantsdictionary.ui.controls.recyclerview.viewholder.CardViewHolder;

import java.util.List;

/**
 * Адаптер для Recycler, реализует отрисовку карточек для растений
 * Плодит CardViewHolder
 *
 * @param <T>
 * @param <Card>
 */
public class RecyclerCardViewAdapter<T, Card extends CardViewHolder<T>> extends RecyclerView.Adapter<Card> {

    /**
     * Список данных
     */
    private LiveData<List<T>> list;
    /**
     * Передаваемый рабочий фрагмент
     */
    private final Fragment fragment;
    /**
     * Какой layout использовать для эелемента
     */
    private final int itemLayout;
    /**
     * Конструктор элемента
     */
    private final CardViewConstructor<Card> cardViewConstructor;
//Передача через конструктор
    public RecyclerCardViewAdapter(LiveData<List<T>> list, Fragment fragment, int itemLayout, CardViewConstructor<Card> cardViewConstructor) {
        this.list = list;
        this.fragment = fragment;
        this.itemLayout = itemLayout;
        this.cardViewConstructor = cardViewConstructor;
    }

    /**
     * Создание нового элемента
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public Card onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Отрисовка recyclerView
        //Получение layout и создание view
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        //Создание карточки растения на основе переданного view
        Card pvh = cardViewConstructor.getCard(v);
        //Возврат карточки
        return pvh;
    }

    /**
     * Заполнение нового элемента
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull Card holder, int position) {
        holder.setObj(list.getValue().get(position));
    }

    /**
     * Получить количество
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return list.getValue() != null ? list.getValue().size() : 0;
    }
}
