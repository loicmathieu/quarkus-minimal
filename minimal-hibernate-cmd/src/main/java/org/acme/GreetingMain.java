package org.acme;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@QuarkusMain
public class GreetingMain implements QuarkusApplication {

    public List<Todo> todos = List.of(
            new Todo(1, "Todo 1", "low"),
            new Todo(2, "Todo 2", "medium"),
            new Todo(3, "Todo 3", "high")
    );

    @Inject EntityManager entityManager;

    @ActivateRequestContext
    @Transactional
    @Override
    public int run(String... args) throws Exception {

        //init db
        entityManager.createNativeQuery("CREATE TABLE TODO (id INTEGER, title TEXT, priority TEXT)").executeUpdate();
        for(Todo todo :  todos) {
            entityManager.persist(todo);
        }

        //select
        List<Todo> theTodos = entityManager.createQuery("SELECT t FROM Todo t", Todo.class).getResultList();
        theTodos.forEach(todo -> System.out.println(todo));

        //clean db
        entityManager.createNativeQuery("DROP TABLE TODO").executeUpdate();

        return 0;
    }
}