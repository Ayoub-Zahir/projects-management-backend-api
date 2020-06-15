/** collaboraters table ********************************/
-- INSERT INTO `collaboraters` (`first_name`, `last_name`, `email`, `photo_url`) VALUES
-- ('ayoub', 'zahir','ayoub@gmail.com','assets/img/user2.svg'),
-- ('john', 'doh', 'jhon@gmail.com', 'assets/img/user2.svg'),
-- ('aynam', 'tahir','aynam@gmail.com','assets/img/user2.svg'),
-- ('anass', 'agrich','anass@gmail.com','assets/img/user2.svg'),
-- ('youssef', 'zahir', 'youssef@gmail.com', 'assets/img/user2.svg');

/** users table ********************************/
INSERT INTO `users` (`first_name`, `last_name`, `password`, `role`, `email`, `photo_url`) VALUES
('ayoub', 'zahir', '$2y$12$arOZS4qR4BFhjFSAaxddcuw3pohrU75w5xQHn7/7hOODv1N8ody7W','ROLE_ADMIN','admin@gmail.com','assets/img/admin.svg'),
('youssef', 'zahir', '$2y$12$arOZS4qR4BFhjFSAaxddcuw3pohrU75w5xQHn7/7hOODv1N8ody7W','ROLE_MANAGER','manager@gmail.com', 'assets/img/manager.svg'),
('anass', 'agrich','$2y$12$arOZS4qR4BFhjFSAaxddcuw3pohrU75w5xQHn7/7hOODv1N8ody7W','ROLE_COLLABORATER','anass@gmail.com','assets/img/collaborater.svg'),
('khalid', 'motai','$2y$12$arOZS4qR4BFhjFSAaxddcuw3pohrU75w5xQHn7/7hOODv1N8ody7W','ROLE_COLLABORATER','khalid@gmail.com','assets/img/collaborater.svg'),
('ayman', 'ghafoli','$2y$12$arOZS4qR4BFhjFSAaxddcuw3pohrU75w5xQHn7/7hOODv1N8ody7W','ROLE_COLLABORATER','ayman@gmail.com','assets/img/collaborater.svg'),
('hatim', 'hossam','$2y$12$arOZS4qR4BFhjFSAaxddcuw3pohrU75w5xQHn7/7hOODv1N8ody7W','ROLE_COLLABORATER','hatim@gmail.com','assets/img/collaborater.svg');

/** competences table ********************************/
INSERT INTO `competences` (`name`) VALUES
('Angular'),
('Spring Boot'),
('Jira Software'),
('Git'),
('Javascript'),
('MySql'),
('UML'),
('NodeJs'),
('Php Laravel');

/** collaborateur_competence table ********************************/
INSERT INTO `collaborater_competence` (`competence_id`, `collaborater_id`) VALUES
(1, 3),
(2, 3),
(3, 3),
(1, 4),
(2, 4),
(3, 5),
(2, 6),
(3, 6),
(4, 3),
(5, 4),
(6, 6),
(7, 3),
(8, 4),
(9, 5);

/** projects table ********************************/
INSERT INTO 
`projects` (`end_date`, `hourly_volume`, `name`, `description`, `start_date`) 
VALUES
('2020-06-18', 200, 'E-Learning', 'Is a formally written declaration of the project and its idea and context to explain, context to explain, context to explain, and context to explain here.', '2020-06-10'),
('2020-06-20', 178, 'AR Game', 'The goals and objectives to be reached, the business need and problem to be addressed.','2020-06-10'),
('2020-07-01', 130, 'VR App', 'Potentials pitfalls and challenges, approaches and execution methods, resource estimates.','2020-06-10');

/** tasks table ********************************/
INSERT INTO 
`tasks` (`description`, `end_date`, `hourly_volume`, `name`, `start_date`, `project_id`) 
VALUES
('The implementation of a simple many-to-many relationship was rather straightforward.', '2020-06-05', 20, 'DB modeling', '2020-06-01', 1),
(' The only problem is that we cannot add a property to a relationship that way, because we connected the entities directly.', '2020-06-13', 80, 'UI and UX', '2020-06-08', 1),
('Since we map DB attributes to class fields in JPA, we need to create a new entity class for the relationship.', '2020-07-04', 80, 'Task 3', '2020-06-29', 1),
('Since we map DB attributes to class fields in JPA, we need to create a new entity class for the relationship.', '2020-07-04', 80, 'Task 4', '2020-06-29', 1),
('Since we map DB attributes to class fields in JPA, we need to create a new entity class for the relationship.', '2020-07-04', 80, 'Design PS', '2020-06-29', 2),
('Since we map DB attributes to class fields in JPA, we need to create a new entity class for the relationship.', '2020-07-04', 80, 'Task 2', '2020-06-29', 2),
('Since we map DB attributes to class fields in JPA, we need to create a new entity class for the relationship.', '2020-07-04', 80, 'Task 3', '2020-06-29', 2),
('Since we map DB attributes to class fields in JPA, we need to create a new entity class for the relationship.', '2020-06-23', 40, 'Google VR', '2020-06-15', 3);


/** task_competence table ********************************/
INSERT INTO 
`task_competence` (`competence_id`, `task_id`) 
VALUES
(1, 2),
(1, 3),
(2, 4),
(5, 2),
(6, 1),
(7, 1);

/** collaborater_task table ********************************/
INSERT INTO `collaborater_task` (`collaborater_id`, `task_id`, `working_hours`) VALUES
(3, 1, 50),
(4, 2, 20),
(5, 3, 20),
(6, 4, 30);