package com.example.courses.infrastructure.persistence.nossql;

import com.example.courses.application.exception.CourseNotFoundException;
import com.example.courses.domain.AchievementsReadModel;
import com.example.courses.domain.CourseReadModel;
import com.example.courses.domain.CourseReadRepository;
import com.example.courses.domain.PageableCourseReadModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@EnableMongoRepositories
public interface MongoCourseRepository extends MongoRepository<CourseMongoModel, UUID>, CourseReadRepository {

    Page<CourseMongoModel> findAll(Pageable pageable);

    @Override
    default PageableCourseReadModel findAllCourses(int page, int pSize) {
        Page<CourseMongoModel> courseMongoModels = findAll(PageRequest.of(page, pSize));
        long totalElements = courseMongoModels.getTotalElements();
        int totalPages = courseMongoModels.getTotalPages();
        long offset = courseMongoModels.getPageable().getOffset();
        int pageNumber = courseMongoModels.getPageable().getPageNumber();
        int pageSize = courseMongoModels.getPageable().getPageSize();
        List<CourseReadModel> courseReadModels = mapToCourseReadModel(courseMongoModels.get());
        return new PageableCourseReadModel(courseReadModels,totalElements,totalPages,offset,pageNumber,pageSize);
    }

    private static List<CourseReadModel> mapToCourseReadModel(Stream< CourseMongoModel>courseMongoModels) {
        return courseMongoModels.map(
                cmm -> new CourseReadModel(cmm.getCourseId(),
                        cmm.getCourseName(),
                        cmm.getAchievements().stream()
                                .map(amm -> new AchievementsReadModel(
                                        amm.getName(),
                                        amm.getPoints())).toList())).toList();
    }

    @Override
    default CourseReadModel getCourseById(UUID id) {
        Optional<CourseMongoModel> mongoModel = findById(id);
        if (mongoModel.isEmpty()) {
            throw new CourseNotFoundException();
        }
        return mapToCourseReadModel(mongoModel).get();
    }

    private static Optional<CourseReadModel> mapToCourseReadModel(Optional<CourseMongoModel> mongoModel) {
        return mongoModel.map(cmm -> new CourseReadModel(cmm.getCourseId(),
                cmm.getCourseName(),
                cmm.getAchievements().stream()
                        .map(amm -> new AchievementsReadModel(
                                amm.getName(),
                                amm.getPoints())).toList()));
    }
}
