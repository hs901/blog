INSERT INTO article(title, content) VALUES ('Attempt1', 'One');
INSERT INTO article(title, content) VALUES ('Attempt2', 'Two');
INSERT INTO article(title, content) VALUES ('Attempt3', 'Three');

INSERT INTO article(title, content) VALUES('Whats your favorite movie?', 'Reply');
INSERT INTO article(title, content) VALUES('Whats your best cuisine ever?','Reply');
INSERT INTO article(title, content) VALUES('Whats your hobby?','Reply');

INSERT INTO comment(article_id, nickname, body) VALUES(4,'Park','Good Duel Hunting');
INSERT INTO comment(article_id, nickname, body) VALUES(4,'Kim','I am Sam');
INSERT INTO comment(article_id, nickname, body) VALUES(4,'Choi','Prison Break');

INSERT INTO comment(article_id, nickname, body) VALUES(5,'Park','Chicken');
INSERT INTO comment(article_id, nickname, body) VALUES(5,'Kim','Shabushabu');
INSERT INTO comment(article_id, nickname, body) VALUES(5,'Choi','Sushi');

INSERT INTO comment(article_id, nickname, body) VALUES(6,'Park','Jogging');
INSERT INTO comment(article_id, nickname, body) VALUES(6,'Kim','Youtube');
INSERT INTO comment(article_id, nickname, body) VALUES(6,'Choi','Reading');

--INSERT INTO attendance(employee_name, attendance_time) VALUES ('Mai Hayakawa', '08:32');
--INSERT INTO attendance(employee_name, attendance_time) VALUES ('Tomohiro Yamauchi', '08:45');
--INSERT INTO attendance(employee_name, attendance_time) VALUES ('Hayato Nishimura', '08:58');