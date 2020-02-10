INSERT INTO skills(id, skill_name)
VALUES (1, 'Java');
INSERT INTO skills(id, skill_name)
VALUES (2, 'SQL');
INSERT INTO skills(id, skill_name)
VALUES (3, 'js');
INSERT INTO skills(id, skill_name)
VALUES (4, 'php');
INSERT INTO skills(id, skill_name)
VALUES (5, 'Oak');

INSERT INTO accounts(id, account_name, account_status)
VALUES (1, 'sergty@gmail.com', 'ACTIVE');
INSERT INTO accounts(id, account_name, account_status)
VALUES (2, 'joe20@gmail.com', 'DELETED');
INSERT INTO accounts(id, account_name, account_status)
VALUES (3, 'franklin345@gmail.com', 'BANNED');

INSERT INTO developers(id, developer_name, account_id)
VALUES (1, 'Joe', '2');
INSERT INTO developers(id, developer_name, account_id)
VALUES (2, 'Serg', '1');
INSERT INTO developers(id, developer_name, account_id)
VALUES (3, 'Frank', '3');

INSERT INTO developers_skills(developer_id, skill_id)
VALUES ('1', '3');
INSERT INTO developers_skills(developer_id, skill_id)
VALUES ('1', '2');
INSERT INTO developers_skills(developer_id, skill_id)
VALUES ('2', '4');
INSERT INTO developers_skills(developer_id, skill_id)
VALUES ('3', '5');
INSERT INTO developers_skills(developer_id, skill_id)
VALUES ('3', '1');
