package com.example.courses.domain.service;

import com.example.courses.domain.PageableCourseReadModel;

public interface FindAllCoursesService {
    PageableCourseReadModel findAllCourses(int page, int pSize);
}
