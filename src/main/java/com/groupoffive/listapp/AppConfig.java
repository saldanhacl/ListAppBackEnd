package com.groupoffive.listapp;

import com.groupoffive.listapp.controllers.CategoriesController;
import com.groupoffive.listapp.controllers.GroupsController;
import com.groupoffive.listapp.controllers.ListsController;
import com.groupoffive.listapp.controllers.UsersController;
import com.groupoffive.listapp.routers.CategoriesRouter;
import com.groupoffive.listapp.routers.GroupsRouter;
import com.groupoffive.listapp.routers.ListsRouter;
import com.groupoffive.listapp.routers.UsersRouter;
import com.groupoffive.listapp.util.Crypt;
import com.groupoffive.listapp.util.CryptSha256;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@Configuration
public class AppConfig {

    public static AnnotationConfigApplicationContext getContext() {
        return new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @Bean
    public EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("mydb").createEntityManager();
    }

    @Bean
    public Crypt cryptSha256() {
        return new CryptSha256();
    }

    @Bean
    public ListsRouter listsRouter() {
        return new ListsRouter(listsController());
    }

    @Bean
    public GroupsRouter groupsRouter() {
        return new GroupsRouter(groupsController());
    }

    @Bean
    public UsersRouter usersRouter() {
        return new UsersRouter(usersController());
    }

    @Bean
    public CategoriesRouter categoriesRouter() {
        return new CategoriesRouter(categoriesController());
    }

    @Bean
    public ListsController listsController() {
        return new ListsController(this.getEntityManager());
    }

    @Bean
    GroupsController groupsController() {
        return new GroupsController(this.getEntityManager());
    }

    @Bean
    UsersController usersController() {
        return new UsersController(this.getEntityManager(), this.cryptSha256());
    }

    @Bean
    CategoriesController categoriesController() {
        return new CategoriesController(this.getEntityManager());
    }

}
