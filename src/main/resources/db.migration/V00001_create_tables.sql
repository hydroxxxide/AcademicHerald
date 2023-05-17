
CREATE TABLE IF NOT EXISTS public.categories
(
    id bigint NOT NULL DEFAULT nextval('categories_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT categories_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.categories
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS public.categories_publications
(
    category_id bigint NOT NULL,
    publications_id bigint NOT NULL,
    CONSTRAINT uk_bqsems9q7v588cv20tn1h1jqn UNIQUE (publications_id),
    CONSTRAINT fk218ycvo8ostia1tsbcxmrp1ej FOREIGN KEY (category_id)
        REFERENCES public.categories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk40yfeoygosmfa950ol8mdtk6j FOREIGN KEY (publications_id)
        REFERENCES public.publications (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.categories_publications
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS public.publications
(
    id bigint NOT NULL DEFAULT nextval('publications_id_seq'::regclass),
    date_of_creation timestamp(6) without time zone,
    subtitle character varying(255) COLLATE pg_catalog."default",
    text character varying(255) COLLATE pg_catalog."default",
    title character varying(255) COLLATE pg_catalog."default",
    type character varying(255) COLLATE pg_catalog."default",
    author bigint,
    category bigint,
    CONSTRAINT publications_pkey PRIMARY KEY (id),
    CONSTRAINT fkh071pb0m2wklhviq9fr89cynq FOREIGN KEY (author)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fklcm9ixanhef0w3d6981rjfhca FOREIGN KEY (category)
        REFERENCES public.categories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.publications
    OWNER to postgres;


CREATE TABLE IF NOT EXISTS public.users
(
    id bigint NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    email character varying(255) COLLATE pg_catalog."default",
    role character varying(255) COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.users_publications
(
    user_id bigint NOT NULL,
    publications_id bigint NOT NULL,
    CONSTRAINT uk_7muuy67nsa8skc82ier83muem UNIQUE (publications_id),
    CONSTRAINT fk5rtwd6jcctlakqbhm0uf1q5dj FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkgrwp4o74730710q48b3nkxd18 FOREIGN KEY (publications_id)
        REFERENCES public.publications (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users_publications
    OWNER to postgres;