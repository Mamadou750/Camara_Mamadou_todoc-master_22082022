package com.cleanup.todoc;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.cleanup.todoc.database.TodocDataBase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class TaskDaoTest {
    private TodocDataBase mDataBase;

    //set data for test
    private static long PROJECT_ID = 1L;
    private static Project PROJECT_DEMO = new Project(PROJECT_ID,"Project test",0xFFEADAD1);
    private static Task TASK_DEMO = new Task(1L ,PROJECT_ID,"task test", new Date().getTime());

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.mDataBase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getTargetContext(),
                        TodocDataBase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void tearDown() throws Exception {
        this.mDataBase.close();
    }

    @Test
    public void insertAndGetProject() throws InterruptedException {
        this.mDataBase.projectDAO().insertProject(PROJECT_DEMO);
        Project project = LiveDataTestUtils.LiveDataTestUtil.getValue(this.mDataBase.projectDAO().getProject(PROJECT_ID));
        assertTrue(project.getName().equals(PROJECT_DEMO.getName()) && project.getId() == PROJECT_ID);
    }

    @Test
    public void insertAndGetTasks() throws InterruptedException {
        this.mDataBase.projectDAO().insertProject(PROJECT_DEMO);
        this.mDataBase.taskDAO().insertTask(TASK_DEMO);
        List<Task> tasks = LiveDataTestUtils.LiveDataTestUtil.getValue(this.mDataBase.taskDAO().getTasks(PROJECT_ID));
        assertTrue(tasks.size() == 1);
    }

    @Test
    public void insertAndUpdateTask() throws InterruptedException {
        this.mDataBase.projectDAO().insertProject(PROJECT_DEMO);
        this.mDataBase.taskDAO().insertTask(TASK_DEMO);
        Task task = LiveDataTestUtils.LiveDataTestUtil.getValue(this.mDataBase.taskDAO().getTasks(PROJECT_ID)).get(0);
        task.setName("task updated");
        this.mDataBase.taskDAO().updateTask(task);

        List<Task> taskList = LiveDataTestUtils.LiveDataTestUtil.getValue(this.mDataBase.taskDAO().getTasks(PROJECT_ID));
        assertTrue(taskList.size() == 1);
        assertEquals("task updated", taskList.get(0).getName());
    }

    @Test
    public void InsertAndDeleteTask() throws InterruptedException {
        this.mDataBase.projectDAO().insertProject(PROJECT_DEMO);
        this.mDataBase.taskDAO().insertTask(TASK_DEMO);

        List<Task> taskList = LiveDataTestUtils.LiveDataTestUtil.getValue(this.mDataBase.taskDAO().getTasks(PROJECT_ID));
        assertTrue(taskList.size() == 1);

        this.mDataBase.taskDAO().deleteTask(taskList.get(0).getId());
        List<Task> taskList1 = LiveDataTestUtils.LiveDataTestUtil.getValue(this.mDataBase.taskDAO().getTasks(PROJECT_ID));
        assertTrue(taskList1.isEmpty());
    }
}
