CREATE TABLE domain_event
(
    event_id       VARCHAR(36) PRIMARY KEY,
    aggregate_id   VARCHAR(36)       NOT NULL,
    event_type     VARCHAR(255) NOT NULL,
    event_data     JSON         NOT NULL,
    occurred_at    TIMESTAMP    NOT NULL,
    version        INT          NOT NULL
);
