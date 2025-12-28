USE university_system;

-- =====================================================
-- RESET DATABASE (FK SAFE)
-- =====================================================
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE fees;
TRUNCATE TABLE attendances;
TRUNCATE TABLE enrollments;
TRUNCATE TABLE courses;
TRUNCATE TABLE users;
SET FOREIGN_KEY_CHECKS = 1;

-- =====================================================
-- USERS
-- password = fun123 (bcrypt)
-- =====================================================
INSERT INTO users (id, email, password_hash, role, first_name, last_name, is_active) VALUES
-- ADMIN
(1, 'admin@uni.edu',
 '$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S',
 'ADMIN', 'System', 'Admin', TRUE),

-- INSTRUCTORS
(2, 'john.doe@uni.edu',   '$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S', 'INSTRUCTOR', 'John', 'Doe', TRUE),
(3, 'mary.smith@uni.edu', '$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S', 'INSTRUCTOR', 'Mary', 'Smith', TRUE),
(4, 'alan.turing@uni.edu','$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S', 'INSTRUCTOR', 'Alan', 'Turing', TRUE),
(5, 'grace.hopper@uni.edu','$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S', 'INSTRUCTOR', 'Grace', 'Hopper', TRUE),
(6, 'ada.lovelace@uni.edu','$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S', 'INSTRUCTOR', 'Ada', 'Lovelace', TRUE),

-- STUDENTS (20)
(7,  'alice.johnson@uni.edu',  '$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S', 'STUDENT', 'Alice', 'Johnson', TRUE),
(8,  'bob.williams@uni.edu',   '$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S', 'STUDENT', 'Bob', 'Williams', TRUE),
(9,  'charlie.brown@uni.edu',  '$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S', 'STUDENT', 'Charlie', 'Brown', TRUE),
(10, 'david.miller@uni.edu',   '$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S', 'STUDENT', 'David', 'Miller', TRUE),
(11, 'emma.davis@uni.edu',     '$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S', 'STUDENT', 'Emma', 'Davis', TRUE),
(12, 'frank.moore@uni.edu',    '$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S', 'STUDENT', 'Frank', 'Moore', TRUE),
(13, 'grace.taylor@uni.edu',   '$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S', 'STUDENT', 'Grace', 'Taylor', TRUE),
(14, 'henry.anderson@uni.edu', '$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S', 'STUDENT', 'Henry', 'Anderson', TRUE),
(15, 'isla.thomas@uni.edu',    '$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S', 'STUDENT', 'Isla', 'Thomas', TRUE),
(16, 'jack.white@uni.edu',     '$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S', 'STUDENT', 'Jack', 'White', TRUE),
(17, 'kate.harris@uni.edu',    '$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S', 'STUDENT', 'Kate', 'Harris', TRUE),
(18, 'leo.martin@uni.edu',     '$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S', 'STUDENT', 'Leo', 'Martin', TRUE),
(19, 'mia.clark@uni.edu',      '$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S', 'STUDENT', 'Mia', 'Clark', TRUE),
(20, 'noah.lewis@uni.edu',     '$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S', 'STUDENT', 'Noah', 'Lewis', TRUE),
(21, 'olivia.walker@uni.edu',  '$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S', 'STUDENT', 'Olivia', 'Walker', TRUE),
(22, 'paul.hall@uni.edu',      '$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S', 'STUDENT', 'Paul', 'Hall', TRUE),
(23, 'quinn.young@uni.edu',    '$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S', 'STUDENT', 'Quinn', 'Young', TRUE),
(24, 'rose.king@uni.edu',      '$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S', 'STUDENT', 'Rose', 'King', TRUE),
(25, 'sam.lopez@uni.edu',      '$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S', 'STUDENT', 'Sam', 'Lopez', TRUE),
(26, 'tina.scott@uni.edu',     '$2a$10$DOWSDc2PzefDsY4X2zQm6uGkN4lWQ1aC1qKjWxPz.p2lQkMZl7u8S', 'STUDENT', 'Tina', 'Scott', TRUE);

-- =====================================================
-- COURSES
-- =====================================================
INSERT INTO courses (id, code, title, credits, instructor_id, description) VALUES
(1, 'CS101',   'Intro to Computer Science', 3, 2, 'Basics of computing'),
(2, 'CS201',   'Data Structures',           4, 2, 'Lists, trees, graphs'),
(3, 'CS301',   'Algorithms',                4, 4, 'Algorithm design'),
(4, 'MATH101', 'Calculus I',                4, 3, 'Differential calculus'),
(5, 'MATH201', 'Linear Algebra',             3, 3, 'Matrices and vectors'),
(6, 'ENG101',  'English Composition',        3, 5, 'Academic writing'),
(7, 'HIST101', 'World History',              3, 6, 'Global civilizations');

-- =====================================================
-- ENROLLMENTS
-- =====================================================
INSERT INTO enrollments (id, student_id, course_id, semester, grade, status, enrollment_date) VALUES
(1, 7,  1, 'Fall 2024', 'A',  'COMPLETED', '2024-08-20'),
(2, 7,  2, 'Fall 2024', 'B+', 'COMPLETED', '2024-08-20'),
(3, 8,  1, 'Fall 2024', 'B',  'COMPLETED', '2024-08-21'),
(4, 8,  4, 'Fall 2024', NULL, 'ENROLLED', '2024-08-21'),
(5, 9,  3, 'Fall 2024', 'A-', 'COMPLETED', '2024-08-22'),
(6, 9,  5, 'Fall 2024', NULL, 'ENROLLED', '2024-08-22'),
(7, 10, 1, 'Fall 2024', 'C+', 'COMPLETED', '2024-08-23'),
(8, 10, 6, 'Fall 2024', NULL, 'DROPPED',  '2024-08-23');

-- =====================================================
-- ATTENDANCES
-- =====================================================
INSERT INTO attendances (id, enrollment_id, session_date, status, remarks) VALUES
(1, 1, '2024-09-01', 'PRESENT', NULL),
(2, 1, '2024-09-03', 'PRESENT', NULL),
(3, 2, '2024-09-01', 'ABSENT',  'Sick'),
(4, 3, '2024-09-02', 'PRESENT', NULL),
(5, 4, '2024-09-02', 'PRESENT', NULL),
(6, 5, '2024-09-03', 'EXCUSED', 'Medical'),
(7, 6, '2024-09-03', 'PRESENT', NULL),
(8, 7, '2024-09-04', 'ABSENT',  'Personal');

-- =====================================================
-- FEES
-- =====================================================
INSERT INTO fees (id, student_id, amount, type, is_paid, due_date, payment_date) VALUES
(1, 7, 1500, 'TUITION', TRUE,  '2024-08-31', '2024-08-25'),
(2, 7,  200, 'LAB',     FALSE, '2024-09-15', NULL),
(3, 8, 1500, 'TUITION', FALSE, '2024-08-31', NULL),
(4, 8,  100, 'LIBRARY', TRUE,  '2024-09-10', '2024-09-05'),
(5, 9, 1500, 'TUITION', TRUE,  '2024-08-31', '2024-08-30'),
(6, 9,  150, 'LAB',     TRUE,  '2024-09-15', '2024-09-12'),
(7,10, 1500, 'TUITION', FALSE, '2024-08-31', NULL);
