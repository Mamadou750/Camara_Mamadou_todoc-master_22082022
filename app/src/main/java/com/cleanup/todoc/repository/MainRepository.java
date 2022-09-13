package com.cleanup.todoc.repository;

import com.cleanup.todoc.App;
import com.cleanup.todoc.database.TodocDataBase;
import com.cleanup.todoc.database.dao.ProjectDAO;
import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDAO;
import com.cleanup.todoc.database.dao.TaskDAO;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.Collections;
import java.util.List;



public class MainRepository {

    public LiveData<List<Project>> getAllProjects(){
        return TodocDataBase.getInstance(App.getAppContext()).projectDAO().getAllProjects();
    }
    public LiveData<List<Task>> getAllTasks(){
        return TodocDataBase.getInstance(App.getAppContext()).taskDAO().getAllTasks();
    }

    public void insertTask(Task task){
        TodocDataBase.getInstance(App.getAppContext()).taskDAO().insertTask(task);
    }

    public void insertProject(Project project){
        TodocDataBase.getInstance(App.getAppContext()).projectDAO().insertProject(project);
    }
    public void deleteTask(long taskId){
        TodocDataBase.getInstance(App.getAppContext()).taskDAO().deleteTask(taskId);
    }

}
