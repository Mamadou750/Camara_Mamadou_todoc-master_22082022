package com.cleanup.todoc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import  com.cleanup.todoc.model.Task;

import java.util.List;


@Dao
public interface TaskDAO {

    @Query("SELECT * FROM Task WHERE projectId = :projectId")
    public LiveData<List<Task>> getTasks(long projectId);

    @Query("SELECT * FROM Task")
    public LiveData<List<Task>> getAllTasks();

    @Insert
    public long insertTask(Task task);

    @Update
    public int updateTask(Task task);

    @Query("DELETE FROM Task WHERE id = :taskId")
    public int deleteTask(long taskId);
}
