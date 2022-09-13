package com.cleanup.todoc.ui;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;

import com.cleanup.todoc.repository.MainRepository;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;
import java.util.concurrent.Executor;



public class MainViewModel extends ViewModel {
    //REPOSITORIES
    private final MainRepository mMainRepository;

    //CONSTRUCTOR
    public MainViewModel(MainRepository mMainRepository) {
        this.mMainRepository = mMainRepository;

    }

    public LiveData<List<Task>> getAllTasks(){
        return this.mMainRepository.getAllTasks();
    }

    public void insertTask(Task task){
        this.mMainRepository.insertTask(task);
    }

    public void deleteTask(long taskId){
        this.mMainRepository.deleteTask(taskId);
    }

    public LiveData<List<Project>> getAllProjects(){
        return this.mMainRepository.getAllProjects();
    }


}
