package com.cleanup.todoc.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cleanup.todoc.repository.MainRepository;


public class ViewModelFactory implements ViewModelProvider.Factory {

    private static ViewModelFactory factory;

    public static ViewModelFactory getInstance() {
        if (factory == null) {
            synchronized (ViewModelFactory.class) {
                if (factory == null) {
                    factory = new ViewModelFactory(
                            new MainRepository()


                    );
                }
            }
        }

        return factory;
    }

    // This field inherit the singleton property from the ViewModelFactory : it is scoped to the ViewModelFactory
    // Ask your mentor about DI scopes (Singleton, ViewModelScope, ActivityScope)
    @NonNull
    private final MainRepository mainRepository;

    private ViewModelFactory(@NonNull MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(
                    mainRepository
            );
        }}
}

