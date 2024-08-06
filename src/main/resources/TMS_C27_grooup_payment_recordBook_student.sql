CREATE TABLE grooup
(
    id    serial      NOT NULL,
    title varchar(20) NOT NULL,
    room  integer     NOT NULL,
    CONSTRAINT grooup_pkey PRIMARY KEY (id)
);

CREATE TABLE record_book
(
    id     serial  NOT NULL,
    rating integer NOT NULL,
    CONSTRAINT record_book_pkey PRIMARY KEY (id)
);

CREATE TABLE student
(
    id             serial      NOT NULL,
    name           varchar(50) NOT NULL,
    age            integer     NOT NULL,
    group_id       integer,
    record_book_id integer,
    CONSTRAINT student_pkey PRIMARY KEY (id),
    CONSTRAINT fk_group_id FOREIGN KEY (group_id)
        REFERENCES public.grooup (id),
    CONSTRAINT fk_record_book_id FOREIGN KEY (record_book_id)
        REFERENCES public.record_book (id)
);

ALTER TABLE student
    ADD COLUMN surname VARCHAR(50);

ALTER TABLE student
    ALTER COLUMN surname SET NOT NULL;

CREATE TABLE payment
(
    id           SERIAL         NOT NULL PRIMARY KEY,
    student_id   INTEGER        NOT NULL REFERENCES student (id),
    amount       DECIMAL(10, 2) NOT NULL,
    payment_date DATE           NOT NULL,
    status       VARCHAR(6)     NOT NULL
);