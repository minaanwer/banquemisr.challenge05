create database "banquemisr-challenge05";


CREATE TABLE public."user" (
                               id bigserial NOT NULL,
                               email varchar(255) NULL,
                               "password" varchar(255) NULL,
                               username varchar(255) NULL,
                               CONSTRAINT user_pkey PRIMARY KEY (id)
);

CREATE TABLE public.notification (
                                     id bigserial NOT NULL,
                                     message varchar(255) NULL,
                                     read_status bool NOT NULL,
                                     "timestamp" timestamp(6) NULL,
                                     user_id int8 NOT NULL,
                                     CONSTRAINT notification_pkey PRIMARY KEY (id),
                                     CONSTRAINT fkspjuqpmul833046oftkpgmr8a FOREIGN KEY (user_id) REFERENCES public."user"(id)
);

CREATE TABLE public."role" (
                               id bigserial NOT NULL,
                               "name" varchar(255) NULL,
                               user_id int8 NOT NULL,
                               CONSTRAINT role_pkey PRIMARY KEY (id),
                               CONSTRAINT fk4lgn6npfsbcnghey3a43f31uy FOREIGN KEY (user_id) REFERENCES public."user"(id)
);

CREATE TABLE public.task (
                             id bigserial NOT NULL,
                             description varchar(255) NULL,
                             due_date date NULL,
                             priority varchar(255) NULL,
                             status varchar(255) NULL,
                             title varchar(255) NULL,
                             user_id int8 NULL,
                             CONSTRAINT task_pkey PRIMARY KEY (id),
                             CONSTRAINT fkc0l5wtvbqchmjf9h4c9iwrk8s FOREIGN KEY (user_id) REFERENCES public."user"(id)
);