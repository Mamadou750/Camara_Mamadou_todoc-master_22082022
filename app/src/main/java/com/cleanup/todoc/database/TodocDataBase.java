package com.cleanup.todoc.database;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.database.dao.ProjectDAO;
import com.cleanup.todoc.database.dao.TaskDAO;
import com.cleanup.todoc.model.Project;
import  com.cleanup.todoc.model.Task;

import java.util.Arrays;
import java.util.List;


@Database(entities = {Task.class, Project.class}, version = 1, exportSchema = false)
public abstract class TodocDataBase extends RoomDatabase {

    public static final Project[] PROJECTS = new Project[]{
            new Project( 1L, "Projet Tartampion", 0xFFEADAD1),
            new Project(2L ,"Projet Lucidia", 0xFFB4CDBA),
            new Project(3L ,"Projet Circus", 0xFFA3CED2),
    };


    public static Project getProjectById(long id) {
        for (Project project : PROJECTS) {
            if (project.getId() == id)
                return project;
        }
        return null;
    }

    //SINGLETON
    private static volatile TodocDataBase INSTANCE;

    //DAO
    public abstract TaskDAO taskDAO();
    public abstract ProjectDAO projectDAO();

    //INSTANCE
    public static TodocDataBase getInstance(Context context){
        if (INSTANCE == null){
            synchronized (TodocDataBase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    TodocDataBase.class, "TodocDatabase.db")
                            .addCallback(populateDatabase())
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback populateDatabase() {
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                for (Project project : PROJECTS) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name", project.getName());
                    contentValues.put("color", project.getColor());
                    db.insert("project", OnConflictStrategy.IGNORE, contentValues);
                }
            }
        };
    }
}

