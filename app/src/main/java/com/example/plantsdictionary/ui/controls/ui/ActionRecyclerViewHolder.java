package com.example.plantsdictionary.ui.controls.ui;

import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.plantsdictionary.R;
import com.example.plantsdictionary.interfaces.models.IModelActionSerialize;
import com.example.plantsdictionary.ui.controls.recyclerview.viewholder.CardViewHolder;
import com.example.plantsdictionary.ui.controls.ui.models.ActionViewModel;
import com.example.plantsdictionary.ui.interfaces.ActivityNavigator;

/**
 * ViewHolder для карточки действия
 */
public class ActionRecyclerViewHolder extends CardViewHolder<ActionViewModel> {

    /**
     * Компоненты
     */
    TextView actionTitle;
    ImageView image;

    /**
     * @param itemView
     */
    public ActionRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        actionTitle = itemView.findViewById(R.id.action_title);
        image = itemView.findViewById(R.id.action_view);
    }

    /**
     * Привязать объект
     * @param obj
     */
    @Override
    public void setObj(ActionViewModel obj) {
        super.setObj(obj);
        actionTitle.setText(obj.getCaption());
        image.setImageResource(getDrawableByName(obj.getImage()));
    }

    /**
     * Обработка нажатия
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Override
    public void click() throws InstantiationException, IllegalAccessException {
        super.click();

        ActivityNavigator activityNavigator = (ActivityNavigator) getActivity();
//Bundle состоит из примитивов. Parcelable нужен чтобы засунуть объект в bundle
        Parcelable parcelable = null;
        int parcelableId = 0;

        /**
         * Если есть аргументы, то надо собрать объект модели,
         * Здесь описывается модель карточек для главного экрана
         */
        if (obj.getParcelableClass() != null) {
            IModelActionSerialize iModelActionSerialize = obj.getParcelableClass().newInstance();
            //Запись аргументов из json
            iModelActionSerialize.load(obj.getActionArguments());
            //Сохранение как parcelable
            parcelable = (Parcelable) iModelActionSerialize;
            parcelableId = getStringIdByName(obj.getParcelableClassId());
        }
        //Использование parcelable как аргумента для нового фрагмента при навигации
        activityNavigator.navigateTo(getIdByName(obj.getNavigateTo()), parcelableId, parcelable);
    }
}
