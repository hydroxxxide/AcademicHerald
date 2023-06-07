insert into users (username, email, role, password)
values ('Argen', 'argen@mail.com', 'ROLE_REVIEWER', '$2a$12$62b7EaTtXxFdd.hfahblo.Nt7/yimuGz1euxhDIu4dqe0tsbWIJNW'),
       ('Felix', 'felix@mail.com', 'ROLE_MENTOR', '$2a$12$62b7EaTtXxFdd.hfahblo.Nt7/yimuGz1euxhDIu4dqe0tsbWIJNW'),
       ('Arsen', 'arsen@mail.com', 'ROLE_STUDENT', '$2a$12$62b7EaTtXxFdd.hfahblo.Nt7/yimuGz1euxhDIu4dqe0tsbWIJNW'),
       ('Azamat', 'azamat@mail.com', 'ROLE_STUDENT', '$2a$12$62b7EaTtXxFdd.hfahblo.Nt7/yimuGz1euxhDIu4dqe0tsbWIJNW'),
       ('Dasha', 'dasha@mail.com', 'ROLE_STUDENT', '$2a$12$62b7EaTtXxFdd.hfahblo.Nt7/yimuGz1euxhDIu4dqe0tsbWIJNW'),
       ('Khashem', 'khashem@mail.com', 'ROLE_STUDENT', '$2a$12$62b7EaTtXxFdd.hfahblo.Nt7/yimuGz1euxhDIu4dqe0tsbWIJNW'),
       ('Adelina', 'adelina@mail.com', 'ROLE_STUDENT', '$2a$12$62b7EaTtXxFdd.hfahblo.Nt7/yimuGz1euxhDIu4dqe0tsbWIJNW'),
       ('Askat', 'askat@mail.com', 'ROLE_STUDENT', '$2a$12$62b7EaTtXxFdd.hfahblo.Nt7/yimuGz1euxhDIu4dqe0tsbWIJNW'),
       ('Ruslan', 'ruslan@mail.com', 'ROLE_STUDENT', '$2a$12$62b7EaTtXxFdd.hfahblo.Nt7/yimuGz1euxhDIu4dqe0tsbWIJNW'),
       ('Atai', 'atai@mail.com', 'ROLE_STUDENT', '$2a$12$62b7EaTtXxFdd.hfahblo.Nt7/yimuGz1euxhDIu4dqe0tsbWIJNW'),
       ('Aidai', 'aidai@mail.com', 'ROLE_STUDENT', '$2a$12$62b7EaTtXxFdd.hfahblo.Nt7/yimuGz1euxhDIu4dqe0tsbWIJNW'),
       ('Gulkaiyr', 'gulkaiyr@mail.com', 'ROLE_STUDENT',
        '$2a$12$62b7EaTtXxFdd.hfahblo.Nt7/yimuGz1euxhDIu4dqe0tsbWIJNW'),
       ('Admin', 'admin@mail.com', 'ROLE_ADMIN', '$2a$12$62b7EaTtXxFdd.hfahblo.Nt7/yimuGz1euxhDIu4dqe0tsbWIJNW');

insert into categories (name)
values ('Научпоп'),
       ('Искуственный интеллект'),
       ('Новости'),
       ('IT-Компании'),
       ('Программирование'),
       ('Мобильная разработка');
insert into tag (name)
values ('#Java'),
       ('#Python'),
       ('#C++'),
       ('#JavaScript'),
       ('#Frontend'),
       ('#Backend');
-- insert into course(type, mentor_id)values
--                             ('JAVA',2 )
--                             ('PYTHON'),
--                             ('JAVASCRIPT'),
--                             ('C'),
--                             ('PROJECT_MANAGEMENT'),
--                             ('TESTING'),
--                             ('UX_DESIGN')
