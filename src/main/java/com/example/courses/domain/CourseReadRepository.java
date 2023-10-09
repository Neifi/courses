package com.example.courses.domain;

import java.util.UUID;

public interface CourseReadRepository {
    PageableCourseReadModel findAllCourses(int page, int pSize);

    CourseReadModel getCourseById(UUID id);

}
