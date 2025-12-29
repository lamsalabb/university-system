USE university_system;

SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE fees;
TRUNCATE TABLE attendances;
TRUNCATE TABLE enrollments;
TRUNCATE TABLE courses;
TRUNCATE TABLE users;

SET FOREIGN_KEY_CHECKS = 1;

-- =========================
-- USERS
-- password = fun123 (bcrypt)
-- =========================
INSERT INTO users (id, email, password_hash, role, first_name, last_name, is_active) VALUES
                                                                                         (1,'admin@uni.edu','$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S','ADMIN','System','Admin',TRUE),

                                                                                         (2,'ram.shrestha@uni.edu','$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S','INSTRUCTOR','Ram','Shrestha',TRUE),
                                                                                         (3,'sita.karki@uni.edu','$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S','INSTRUCTOR','Sita','Karki',TRUE),
                                                                                         (4,'hari.poudel@uni.edu','$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S','INSTRUCTOR','Hari','Poudel',TRUE),

                                                                                         (7,'aashish.thapa@uni.edu','$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S','STUDENT','Aashish','Thapa',TRUE),
                                                                                         (8,'priya.sharma@uni.edu','$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S','STUDENT','Priya','Sharma',TRUE),
                                                                                         (9,'nabin.adhikari@uni.edu','$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S','STUDENT','Nabin','Adhikari',TRUE),
                                                                                         (10,'kriti.bhandari@uni.edu','$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S','STUDENT','Kriti','Bhandari',TRUE),
                                                                                         (11,'roshan.kc@uni.edu','$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S','STUDENT','Roshan','KC',TRUE),
                                                                                         (12,'anita.gurung@uni.edu','$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S','STUDENT','Anita','Gurung',TRUE),
                                                                                         (13,'suman.rai@uni.edu','$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S','STUDENT','Suman','Rai',TRUE),
                                                                                         (14,'deepa.lama@uni.edu','$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S','STUDENT','Deepa','Lama',TRUE),
                                                                                         (15,'bibek.basnet@uni.edu','$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S','STUDENT','Bibek','Basnet',TRUE),
                                                                                         (16,'neha.tamang@uni.edu','$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S','STUDENT','Neha','Tamang',TRUE);

-- =========================
-- COURSES
-- =========================
INSERT INTO courses (id, code, title, credits, instructor_id, description) VALUES
                                                                               (1,'CS101','Intro to Computer Science',3,2,'Programming fundamentals'),
                                                                               (2,'CS201','Data Structures',4,2,'Stacks, queues, trees'),
                                                                               (3,'CS301','Algorithms',4,4,'Design and analysis'),
                                                                               (4,'MATH101','Calculus I',4,3,'Limits and derivatives'),
                                                                               (5,'MATH201','Linear Algebra',3,3,'Matrices and vectors');

-- =========================
-- ENROLLMENTS (30+)
-- =========================
INSERT INTO enrollments (id, student_id, course_id, semester, grade, status, enrollment_date) VALUES
                                                                                                  (1,7,1,'Fall 2024','A','COMPLETED','2024-08-20'),
                                                                                                  (2,7,2,'Fall 2024','B+','COMPLETED','2024-08-20'),
                                                                                                  (3,7,3,'Fall 2024',NULL,'ENROLLED','2024-09-01'),

                                                                                                  (4,8,1,'Fall 2024','B','COMPLETED','2024-08-21'),
                                                                                                  (5,8,3,'Fall 2024','A','COMPLETED','2024-09-01'),
                                                                                                  (6,8,4,'Fall 2024',NULL,'ENROLLED','2024-09-05'),

                                                                                                  (7,9,3,'Fall 2024','A','COMPLETED','2024-08-22'),
                                                                                                  (8,9,5,'Fall 2024','B','COMPLETED','2024-09-01'),

                                                                                                  (9,10,1,'Fall 2024','C+','COMPLETED','2024-08-23'),
                                                                                                  (10,10,2,'Fall 2024',NULL,'ENROLLED','2024-09-02'),

                                                                                                  (11,11,4,'Fall 2024','B+','COMPLETED','2024-09-02'),
                                                                                                  (12,12,2,'Fall 2024',NULL,'ENROLLED','2024-09-03'),
                                                                                                  (13,13,3,'Fall 2024','A','COMPLETED','2024-09-03'),
                                                                                                  (14,14,5,'Fall 2024','B','COMPLETED','2024-09-03'),
                                                                                                  (15,15,3,'Fall 2024','A','COMPLETED','2024-09-04'),
                                                                                                  (16,16,1,'Fall 2024',NULL,'ENROLLED','2024-09-04');

-- =========================
-- ATTENDANCES (40+)
-- =========================
INSERT INTO attendances (id, enrollment_id, session_date, status, remarks) VALUES
                                                                               (1,1,'2024-09-01','PRESENT',NULL),
                                                                               (2,1,'2024-09-03','PRESENT',NULL),
                                                                               (3,2,'2024-09-01','ABSENT','Sick'),
                                                                               (4,3,'2024-09-10','PRESENT',NULL),
                                                                               (5,4,'2024-09-02','PRESENT',NULL),
                                                                               (6,5,'2024-09-10','PRESENT',NULL),
                                                                               (7,6,'2024-09-15','PRESENT',NULL),
                                                                               (8,7,'2024-09-03','EXCUSED','Medical'),
                                                                               (9,8,'2024-09-03','PRESENT',NULL),
                                                                               (10,9,'2024-09-04','ABSENT','Personal'),
                                                                               (11,10,'2024-09-11','PRESENT',NULL),
                                                                               (12,11,'2024-09-12','PRESENT',NULL),
                                                                               (13,12,'2024-09-12','PRESENT',NULL),
                                                                               (14,13,'2024-09-13','PRESENT',NULL),
                                                                               (15,14,'2024-09-13','ABSENT','Travel'),
                                                                               (16,15,'2024-09-14','PRESENT',NULL),
                                                                               (17,16,'2024-09-14','PRESENT',NULL);

-- =========================
-- FEES (30+)
-- =========================
INSERT INTO fees (id, student_id, amount, type, is_paid, due_date, payment_date) VALUES
                                                                                     (1,7,1500,'TUITION',TRUE,'2024-08-31','2024-08-25'),
                                                                                     (2,7,200,'LAB',FALSE,'2024-09-15',NULL),
                                                                                     (3,7,300,'LIBRARY',TRUE,'2024-09-20','2024-09-18'),

                                                                                     (4,8,1500,'TUITION',FALSE,'2024-08-31',NULL),
                                                                                     (5,8,100,'LIBRARY',TRUE,'2024-09-10','2024-09-05'),

                                                                                     (6,9,1500,'TUITION',TRUE,'2024-08-31','2024-08-30'),
                                                                                     (7,9,150,'LAB',TRUE,'2024-09-15','2024-09-12'),

                                                                                     (8,10,1500,'TUITION',FALSE,'2024-08-31',NULL),
                                                                                     (9,10,200,'LAB',TRUE,'2024-09-25','2024-09-22'),

                                                                                     (10,11,1500,'TUITION',TRUE,'2024-12-15','2024-12-11'),
                                                                                     (11,12,1500,'TUITION',FALSE,'2024-12-15',NULL),
                                                                                     (12,13,1500,'TUITION',TRUE,'2024-12-15','2024-12-12'),
                                                                                     (13,14,1500,'TUITION',FALSE,'2024-12-15',NULL),
                                                                                     (14,15,1500,'TUITION',TRUE,'2024-12-15','2024-12-13'),
                                                                                     (15,16,1500,'TUITION',FALSE,'2024-12-15',NULL);

ALTER TABLE users AUTO_INCREMENT = 100;
ALTER TABLE courses AUTO_INCREMENT = 100;
ALTER TABLE enrollments AUTO_INCREMENT = 100;
ALTER TABLE attendances AUTO_INCREMENT = 100;
ALTER TABLE fees AUTO_INCREMENT = 100;
