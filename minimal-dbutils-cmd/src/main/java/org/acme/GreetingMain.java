package org.acme;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@QuarkusMain
public class GreetingMain implements QuarkusApplication {

    public List<Todo> todos = List.of(
            new Todo(1, "Todo 1", "low"),
            new Todo(2, "Todo 2", "medium"),
            new Todo(3, "Todo 3", "high")
    );

    @Inject DataSource dataSource;

    @Override
    public int run(String... args) throws Exception {
        QueryRunner runner = new QueryRunner(dataSource);

        //init db
        runner.execute("CREATE TABLE TODO (id INTEGER, title TEXT, priority TEXT)");
        for(Todo todo :  todos) {
            runner.update("INSERT into TODO (id, title, priority) values(?, ?, ?)",
                    todo.id, todo.priority, todo.title);
        }

        //select
        List<Todo> theTodos = runner.query("SELECT * FROM TODO", new BeanListHandler<>(Todo.class));
        theTodos.forEach(todo -> System.out.println(todo));

        //clean db
        runner.execute("DROP TABLE TODO");

        return 0;
    }
}