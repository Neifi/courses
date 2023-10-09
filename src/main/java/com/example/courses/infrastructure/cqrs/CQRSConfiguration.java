package com.example.courses.infrastructure.cqrs;

import com.example.courses.application.create.CreateCourseCommand;
import com.example.courses.application.create.CreateCourseCommandHandler;
import com.example.courses.application.remove.RemoveCourseCommand;
import com.example.courses.application.remove.RemoveCourseCommandHandler;
import com.example.courses.application.update.UpdateCourseCommand;
import com.example.courses.application.update.UpdateCourseCommandHandler;
import com.example.courses.domain.service.CreateCourseService;
import com.example.courses.domain.service.DeleteCourseService;
import com.example.courses.domain.service.UpdateCourseService;
import com.example.shared.infrastructure.cqrs.command.CommandBus;
import com.example.shared.infrastructure.cqrs.command.CommandHandler;
import com.example.shared.infrastructure.cqrs.command.InMemoryCommandBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CompletableFuture;

@Configuration
public class CQRSConfiguration {

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


}
