package com.example.courses.infrastructure.streaming;

import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/data")
public class DashboardController {

    private InteractiveQueryService queryService;

    public DashboardController(InteractiveQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/courses/count")
    public ResponseEntity<?> getCourseCount() {
        ReadOnlyKeyValueStore<String, Integer> keyValueStore = queryService
                .getQueryableStore("courses-count-store", QueryableStoreTypes.keyValueStore());

        return ResponseEntity.ok(keyValueStore.all());
    }
}