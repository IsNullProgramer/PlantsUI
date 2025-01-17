package com.example.plantsdictionary;

import androidx.room.Room;

import com.example.plantsdictionary.data.logic.ComplexDataProvider;
import com.example.plantsdictionary.data.logic.JsonProvider;
import com.example.plantsdictionary.data.logic.LocalDatabase;
import com.example.plantsdictionary.data.logic.LocalImageProvider;
import com.example.plantsdictionary.infrastructure.ioc.IContainer;
import com.example.plantsdictionary.infrastructure.ioc.IOCFactory;
import com.example.plantsdictionary.interfaces.DataProvider;
import com.example.plantsdictionary.interfaces.ImageProvider;

/**
 * Класс приложения
 */
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * Создадим и заполним контейнер
         * Регистрация провайдеров json и картинок + БД
         * В дальнейшем через этот контейнер можно будет образаться к сервисам и данным
         */
        IContainer ioContainer = IOCFactory.getIContainer();
        ioContainer.register(DataProvider.class, new ComplexDataProvider(new JsonProvider(this),
                Room.databaseBuilder(getApplicationContext(), LocalDatabase.class, "PlantsDB").fallbackToDestructiveMigration().build()));
        ioContainer.register(ImageProvider.class, new LocalImageProvider(this));
    }
}
