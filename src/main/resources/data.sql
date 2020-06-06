-- -- -- /** collaboraters table ********************************/
INSERT INTO `collaboraters` (`first_name`, `last_name`, `email`, `photo_url`) VALUES
('ayoub', 'zahir','ayoub@gmail.com','assets/img/user2.svg'),
('john', 'doh', 'jhon@gmail.com', 'assets/img/user2.svg'),
('aynam', 'tahir','aynam@gmail.com','assets/img/user2.svg'),
('anass', 'agrich','anass@gmail.com','assets/img/user2.svg'),
('youssef', 'zahir', 'youssef@gmail.com', 'assets/img/user2.svg');

-- -- -- /** competences table ********************************/
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

-- -- -- /** collaborateur_competence table ********************************/
INSERT INTO `collaborater_competence` (`competence_id`, `collaborater_id`) VALUES
(1, 1),
(2, 2),
(4, 1),
(5, 1),
(6, 1),
(6, 2),
(8, 1);

-- -- -- /** projects table ********************************/
INSERT INTO 
`projects` (`end_date`, `hourly_volume`, `name`, `description`, `start_date`) 
VALUES
('2020-06-07', 200, 'E-Learning', 'Is a formally written declaration of the project and its idea and context to explain, context to explain, context to explain, and context to explain here.', '2020-06-01'),
('2020-06-15', 178, 'AR Game', 'The goals and objectives to be reached, the business need and problem to be addressed.','2020-06-01'),
('2020-06-30', 130, 'VR App', 'Potentials pitfalls and challenges, approaches and execution methods, resource estimates.','2020-06-01');

-- -- -- /** tasks table ********************************/
INSERT INTO 
`tasks` (`description`, `end_date`, `hourly_volume`, `name`, `start_date`, `project_id`) 
VALUES
('The implementation of a simple many-to-many relationship was rather straightforward.', '2020-06-08', 40, 'DB modeling', '2020-06-01', 1),
(' The only problem is that we cannot add a property to a relationship that way, because we connected the entities directly.', '2020-06-18', 80, 'UI and UX', '2020-06-01', 1),
('Since we map DB attributes to class fields in JPA, we need to create a new entity class for the relationship.', '2020-06-25', 80, 'Design PS', '2020-06-01', 2),
('Since we map DB attributes to class fields in JPA, we need to create a new entity class for the relationship.', '2020-06-15', 40, 'Google VR', '2020-06-01', 3);


-- -- -- /** task_competence table ********************************/
INSERT INTO 
`task_competence` (`competence_id`, `task_id`) 
VALUES
(1, 2),
(1, 3),
(2, 4),
(5, 2),
(6, 1),
(7, 1);

-- -- -- /** collaborater_task table ********************************/
INSERT INTO `collaborater_task` (`collaborater_id`, `task_id`, `working_hours`) VALUES
(1, 2, 50),
(1, 4, 20),
(2, 1, 20),
(2, 2, 30);