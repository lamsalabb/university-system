USE university_system;

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE fees;
TRUNCATE TABLE attendances;
TRUNCATE TABLE enrollments;
TRUNCATE TABLE courses;
SET FOREIGN_KEY_CHECKS = 1;

-- =====================================================
-- COURSES
-- =====================================================
INSERT INTO courses (id, code, title, credits, instructor_id, description) VALUES
(1,'CS101','Intro to Computer Science',3,2,'Programming fundamentals'),
(2,'CS201','Data Structures',4,2,'Lists, stacks, trees'),
(3,'CS301','Algorithms',4,4,'Algorithm analysis'),
(4,'MATH101','Calculus I',4,3,'Differentiation & limits'),
(5,'MATH201','Linear Algebra',3,3,'Matrices and vectors');

-- =====================================================
-- ENROLLMENTS (30+)
-- =====================================================
INSERT INTO enrollments (id, student_id, course_id, semester, grade, status, enrollment_date) VALUES
(1,7,1,'Fall 2024','A','COMPLETED','2024-08-20'),
(2,7,2,'Fall 2024','B+','COMPLETED','2024-08-20'),
(3,7,3,'Fall 2024',NULL,'ENROLLED','2024-09-01'),
(4,8,1,'Fall 2024','B','COMPLETED','2024-08-21'),
(5,8,3,'Fall 2024','A','COMPLETED','2024-09-01'),
(6,8,4,'Fall 2024',NULL,'ENROLLED','2024-09-05'),

(7,9,3,'Fall 2024','A-','COMPLETED','2024-08-22'),
(8,9,5,'Fall 2024','B','COMPLETED','2024-09-01'),
(9,9,2,'Fall 2024','C+','COMPLETED','2024-09-06'),

(10,10,1,'Fall 2024','C+','COMPLETED','2024-08-23'),
(11,10,2,'Fall 2024',NULL,'ENROLLED','2024-09-02'),
(12,10,3,'Fall 2024','B','COMPLETED','2024-09-06'),

(13,11,4,'Fall 2024','B+','COMPLETED','2024-09-02'),
(14,11,5,'Fall 2024',NULL,'DROPPED','2024-09-06'),

(15,12,2,'Fall 2024',NULL,'ENROLLED','2024-09-03'),
(16,12,5,'Fall 2024','B+','COMPLETED','2024-09-07'),

(17,13,3,'Fall 2024','A','COMPLETED','2024-09-03'),
(18,13,1,'Fall 2024','A','COMPLETED','2024-09-07'),

(19,14,5,'Fall 2024','B','COMPLETED','2024-09-03'),
(20,14,2,'Fall 2024',NULL,'ENROLLED','2024-09-07'),

(21,15,3,'Fall 2024','A-','COMPLETED','2024-09-04'),
(22,15,4,'Fall 2024','A-','COMPLETED','2024-09-08'),

(23,16,1,'Fall 2024',NULL,'ENROLLED','2024-09-04'),
(24,16,2,'Fall 2024','B','COMPLETED','2024-09-08');

-- =====================================================
-- ATTENDANCES (40+)
-- =====================================================
INSERT INTO attendances (id, enrollment_id, session_date, status, remarks) VALUES
(1,1,'2024-09-01','PRESENT',NULL),
(2,1,'2024-09-03','PRESENT',NULL),
(3,2,'2024-09-01','ABSENT','Sick'),
(4,3,'2024-09-10','PRESENT',NULL),
(5,3,'2024-09-12','ABSENT','Personal'),

(6,4,'2024-09-02','PRESENT',NULL),
(7,5,'2024-09-10','PRESENT',NULL),
(8,5,'2024-09-12','PRESENT',NULL),
(9,6,'2024-09-15','PRESENT',NULL),

(10,7,'2024-09-03','EXCUSED','Medical'),
(11,7,'2024-09-05','PRESENT',NULL),
(12,8,'2024-09-03','PRESENT',NULL),
(13,9,'2024-09-06','ABSENT','Late'),

(14,10,'2024-09-04','ABSENT','Personal'),
(15,11,'2024-09-11','PRESENT',NULL),
(16,11,'2024-09-13','PRESENT',NULL),
(17,12,'2024-09-16','PRESENT',NULL),

(18,13,'2024-09-12','PRESENT',NULL),
(19,13,'2024-09-14','ABSENT','Late'),
(20,14,'2024-09-15','PRESENT',NULL),

(21,15,'2024-09-12','PRESENT',NULL),
(22,15,'2024-09-14','PRESENT',NULL),
(23,16,'2024-09-17','PRESENT',NULL),

(24,17,'2024-09-13','PRESENT',NULL),
(25,17,'2024-09-15','PRESENT',NULL),
(26,18,'2024-09-18','PRESENT',NULL),

(27,19,'2024-09-13','ABSENT','Travel'),
(28,19,'2024-09-15','PRESENT',NULL),
(29,20,'2024-09-17','PRESENT',NULL),

(30,21,'2024-09-14','PRESENT',NULL),
(31,21,'2024-09-16','PRESENT',NULL),
(32,22,'2024-09-18','PRESENT',NULL),

(33,23,'2024-09-14','PRESENT',NULL),
(34,23,'2024-09-16','ABSENT','Personal'),
(35,24,'2024-09-18','PRESENT',NULL);

-- =====================================================
-- FEES (30+)
-- =====================================================
INSERT INTO fees (id, student_id, amount, type, is_paid, due_date, payment_date) VALUES
(1,7,1500,'TUITION',TRUE,'2024-08-31','2024-08-25'),
(2,7,200,'LAB',FALSE,'2024-09-15',NULL),
(3,7,300,'LIBRARY',TRUE,'2024-09-20','2024-09-18'),
(4,7,1500,'TUITION',FALSE,'2024-12-15',NULL),

(5,8,1500,'TUITION',FALSE,'2024-08-31',NULL),
(6,8,100,'LIBRARY',TRUE,'2024-09-10','2024-09-05'),
(7,8,200,'LAB',FALSE,'2024-09-25',NULL),
(8,8,1500,'TUITION',FALSE,'2024-12-15',NULL),

(9,9,1500,'TUITION',TRUE,'2024-08-31','2024-08-30'),
(10,9,150,'LAB',TRUE,'2024-09-15','2024-09-12'),
(11,9,300,'LIBRARY',FALSE,'2024-09-20',NULL),
(12,9,1500,'TUITION',FALSE,'2024-12-15',NULL),

(13,10,1500,'TUITION',FALSE,'2024-08-31',NULL),
(14,10,200,'LAB',TRUE,'2024-09-25','2024-09-22'),
(15,10,1500,'TUITION',TRUE,'2024-12-15','2024-12-10'),

(16,11,1500,'TUITION',TRUE,'2024-12-15','2024-12-11'),
(17,11,250,'OTHER',FALSE,'2024-09-30',NULL),

(18,12,1500,'TUITION',FALSE,'2024-12-15',NULL),
(19,12,200,'LAB',TRUE,'2024-09-25','2024-09-23'),

(20,13,1500,'TUITION',TRUE,'2024-12-15','2024-12-12'),
(21,13,300,'LIBRARY',TRUE,'2024-09-20','2024-09-19'),

(22,14,1500,'TUITION',FALSE,'2024-12-15',NULL),
(23,14,150,'OTHER',FALSE,'2024-09-30',NULL),

(24,15,1500,'TUITION',TRUE,'2024-12-15','2024-12-13'),
(25,15,200,'LAB',TRUE,'2024-09-25','2024-09-24'),

(26,16,1500,'TUITION',FALSE,'2024-12-15',NULL),
(27,16,300,'LIBRARY',FALSE,'2024-09-20',NULL);
