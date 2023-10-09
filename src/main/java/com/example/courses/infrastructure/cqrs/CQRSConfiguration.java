package com.example.courses.infrastructure.cqrs;

import com.example.courses.application.create.CreateCourseCommand;
import com.example.courses.application.create.CreateCourseCommandHandler;
import com.example.courses.application.findAll.FindCourseQuery;
import com.example.courses.application.findAll.FindCourseQueryHandler;
import com.example.courses.application.findById.FindCourseByIdQuery;
import com.example.courses.application.findById.FindCourseByIdQueryHandler;
import com.example.courses.application.remove.RemoveCourseCommand;
import com.example.courses.application.remove.RemoveCourseCommandHandler;
import com.example.courses.application.update.UpdateCourseCommand;
import com.example.courses.application.update.UpdateCourseCommandHandler;
import com.example.courses.domain.CourseReadModel;
import com.example.courses.domain.PageableCourseReadModel;
import com.example.courses.domain.service.*;
import com.example.shared.infrastructure.cqrs.command.CommandBus;
import com.example.shared.infrastructure.cqrs.command.CommandHandler;
import com.example.shared.infrastructure.cqrs.command.InMemoryCommandBus;
import com.example.shared.infrastructure.cqrs.query.InMemoryQueryBus;
import com.example.shared.infrastructure.cqrs.query.QueryBus;
import com.example.shared.infrastructure.cqrs.query.QueryHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CompletableFuture;

@Configuration
public class CQRSConfiguration {

    @Bean
    QueryHandler<FindCourseByIdQuery, CourseReadModel> courseByIdFinderQueryHandler(GetCourseByIdService courseByIdFinder) {
        return new FindCourseByIdQueryHandler(courseByIdFinder);
    }

    @Bean
    QueryHandler<FindCourseQuery, PageableCourseReadModel> courseFinderQueryHandler(FindAllCoursesService courseFinder) {
        return new FindCourseQueryHandler(courseFinder);
    }

    @Bean
    CommandHandler<CompletableFuture<Void>, UpdateCourseCommand> courseUpdaterCommandHandler(UpdateCourseService courseUpdater) {
        return new UpdateCourseCommandHandler(courseUpdater);
    }

    @Bean
    CommandHandler<CompletableFuture<Void>, CreateCourseCommand> courseCreateCommandHandler(CreateCourseService createCourseService) {
        return new CreateCourseCommandHandler(createCourseService);
    }

    @Bean
    CommandHandler<CompletableFuture<Void>, RemoveCourseCommand> removeCourseCommandHandler(DeleteCourseService deleteCourseService) {
        return new RemoveCourseCommandHandler(deleteCourseService);
    }

    @Bean
    public CommandBus commandBus(CommandHandler courseCreateCommandHandler,
                                 CommandHandler courseUpdaterCommandHandler,
                                 CommandHandler removeCourseCommandHandler
    ) {
        InMemoryCommandBus commandBus = new InMemoryCommandBus();
        commandBus.registerCommandHandler(CreateCourseCommand.class, courseCreateCommandHandler);
        commandBus.registerCommandHandler(UpdateCourseCommand.class, courseUpdaterCommandHandler);
        commandBus.registerCommandHandler(RemoveCourseCommand.class, removeCourseCommandHandler);
        return commandBus;
    }

    @Bean
    public QueryBus queryBus(QueryHandler courseByIdFinderQueryHandler, QueryHandler courseFinderQueryHandler) {
        InMemoryQueryBus queryBus = new InMemoryQueryBus();
        queryBus.registerQueryHandler(FindCourseByIdQuery.class, courseByIdFinderQueryHandler);
        queryBus.registerQueryHandler(FindCourseQuery.class, courseFinderQueryHandler);
        return queryBus;
    }


}
