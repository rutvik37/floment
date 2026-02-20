package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.TodoPage;

public class TodoTest extends BaseTest {

    @Test
    public void createTodoTest() {

        TodoPage todo = new TodoPage(page);
        page.navigate("https://stag-webapp.incenti.ai/");
        todo.createTodo("Automation Task");
    }
}