package com.cleanup.todoc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.cleanup.todoc.model.Project;

import java.util.List;


@Dao
public interface ProjectDAO {

    @Query("SELECT * FROM Project")
    public LiveData<List<Project>> getAllProjects();

    @Query("SELECT * FROM Project WHERE id = :projectId")
    public LiveData<Project> getProject(long projectId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertProject(Project project);

    @Insert
    public void insertAll(Project... projects);
}
