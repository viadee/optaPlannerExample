DROP TABLE IF EXISTS REQUEST;
CREATE TABLE REQUEST (
    id     NUMBER(10)   NOT NULL,
    set_id     NUMBER(10)   NOT NULL,
    basket_value   NUMBER(6, 4) NOT NULL,
    cus_lat NUMBER(4, 4)   NOT NULL,
    cus_long NUMBER(4, 4)   NOT NULL,
    CONSTRAINT pk_request PRIMARY KEY (id)
);

DROP TABLE IF EXISTS TIMEWINDOW;
CREATE TABLE TIMEWINDOW (
    id     NUMBER(10)   NOT NULL,
    tw_set     NUMBER(10)   NOT NULL,
    tw_start_time  VARCHAR2(5) NOT NULL,
    tw_end_time  VARCHAR2(5) NOT NULL,
    CONSTRAINT pk_timewindow PRIMARY KEY (id),
    CONSTRAINT c_schicht_zeit CHECK ((tw_start_time IS NULL AND tw_end_time IS NULL) OR
                                     (tw_start_time IS NOT NULL AND tw_end_time IS NOT NULL AND tw_start_time < tw_end_time))
);

DROP TABLE IF EXISTS REQUEST_TIMEWINDOW;
CREATE TABLE REQUEST_TIMEWINDOW (
    request_id     NUMBER(10)   NOT NULL,
    tw_id     NUMBER(10)   NOT NULL,
    CONSTRAINT pk_request_timewindow PRIMARY KEY (request_id, tw_id),
    CONSTRAINT fk_requ_time_request FOREIGN KEY (request_id) REFERENCES REQUEST(id),
    CONSTRAINT fk_requ_time_tw FOREIGN KEY (tw_id) REFERENCES TIMEWINDOW(id)
);
