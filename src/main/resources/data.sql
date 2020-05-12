/** projects table ********************************/
INSERT INTO 
    `projects` (`end_date`, `hourly_volume`, `name`, `start_date`) 
VALUES
    ('2020-05-12', 20, 'MANAGEMENT SYSTEM', '2020-06-12'),
    ('2020-08-31', 78, 'AR Game', '2020-05-20'),
    ('2020-06-19', 10, 'VR App', '2020-05-18');

/** tasks table ********************************/
INSERT INTO 
    `tasks` (`end_date`, `name`, `start_date`, `project_id`) 
VALUES
    ('2020-05-31', 'Data base modeling', '2020-05-11', 1),
    ('2020-05-31', 'UI design', '2020-05-11', 1),
    ('2020-05-31', 'Unity Setup', '2020-05-11', 2),
    ('2020-05-31', 'UX Design', '2020-05-11', 1);