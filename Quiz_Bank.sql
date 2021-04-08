Create database QuizBank

CREATE TABLE tblUser(
	userId varchar(150) PRIMARY KEY,
	username nvarchar(50),
	password varchar(200),
	status varchar(20),
	role varchar(20)
)

CREATE TABLE tblSubject(
	subId varchar(20) PRIMARY KEY,
	subName nvarchar(50),
)

alter table tblSubject
add time_take int

CREATE TABLE tblQuestion(
	questionId varchar(20) PRIMARY KEY,
	content nvarchar(250),
	optionA nvarchar(250),
	optionB nvarchar(250),
	optionC nvarchar(250),
	optionD nvarchar(250),
	correct_answer nvarchar(250),
	create_date date,
	status varchar(20),
	subId varchar(20) references tblSubject(subId)
)

CREATE TABLE tblQuiz(
	quizId varchar(50) PRIMARY KEY,
	subId varchar(20) references tblSubject(subId),
	userId varchar(150) references tblUser(userId),
	num_of_question int,
	create_date date,
	time_take int,
	score float,
	num_of_correct int,
)

CREATE TABLE tblQuestionInQuiz(
	questionId varchar(20) references tblQuestion(questionId),
	quizId varchar(50) references tblQuiz(quizId),
	answer_choose nvarchar(250)
)

SELECT *
FROM tblUser

SELECT userId FROM tblUser WHERE userId = 'huyvd'

SELECT subId, subName from tblSubject

SELECT questionId, content, optionA, optionB, optionC, optionD, correct_answer, create_date, status, subName
FROM tblQuestion q join tblSubject s on q.subId = s.subId
GROUP BY questionId, content, optionA, optionB, optionC, optionD, correct_answer, create_date, status, subName
ORDER By create_date desc
OFFSET 10 ROWS 
FETCH NEXT 10 ROWS ONLY

SELECT COUNT(questionId) as total
FROM tblQuestion

INSERT tblUser(userId, username, password, role, status)
VALUES('minhtn', 'Nhat Minh', '123', 'student', 'new')

SELECT questionId, content, optionA, optionB, optionC, optionD, correct_answer, create_date, status, s.subName
FROM tblQuestion q, tblSubject s
WHERE q.subId = s.subId
	AND content like '%%'
	AND s.subId = 1
	AND status = 'active'
GROUP BY questionId, content, optionA, optionB, optionC, optionD, correct_answer, create_date, status, s.subName
ORDER BY create_date DESC
OFFSET 0 ROWS
FETCH NEXT 10 ROWS ONLY 

SELECT COUNT(questionId) as total
FROM tblQuestion q, tblSubject s
WHERE q.subId = s.subId
	AND content like '%%'
	AND s.subId = 1
	AND status = 'active'

SELECT q.questionId
FROM tblQuestion q join tblQuestionInQuiz qi on q.questionId = qi.questionId
WHERE q.questionId = 'Q001'

UPDATE tblQuestion
SET content = 'asdf', optionA = 'a', optionB = 'b', optionC ='c', optionD = 'd', correct_answer = 'A', subId = 1
WHERE questionId = 'asdf'

UPDATE tblSubject
SET num_of_question = 20, time_take = 30
WHERE subId = 1

SELECT num_of_question, time_take
FROM tblSubject 
WHERE subId = '1'

SELECT TOP(5) questionId, content, optionA, optionB, optionC, optionD, correct_answer, create_date, status, s.subName
FROM tblQuestion q join tblSubject s on q.subId = s.subId
WHERE status = 'active' AND s.subId = '1'
ORDER BY NEWID()

INSERT tblQuiz(quizId, userId, subId, num_of_question, num_of_correct, score, create_date, time_take)
VALUES()

INSERT tblQuestionInQuiz(quizId, questionId, answer_choose)
VALUES()

Select q.questionId, correct_answer, answer_choose
from tblQuestion q join tblQuestionInQuiz qq on q.questionId = qq.questionId
where qq.questionId IN (SELECT questionId 
					FROM tblQuestionInQuiz
					WHERE quizId = '070321-015637-518482') 


Select * from tblQuestion where questionId = 
Select * from tblQuestion where questionId = 

INSERT tblQuestion(questionId, content, optionA, optionB, optionC, optionD, correct_answer, create_date, status, subId) VALUES
('Q004', 'Which sentence is correct?', 'An interface is name collection of semantically related abstract member.', 'All interface members are implicitly private.', 'Interfaces can provide an implementation of the methods', 'Interfaces can be used to define state data.', 'A', '2021-03-01', 'active', '1'),
('Q005', 'what is boxing?', 'Boxing is conversion form an object to a value type.', 'Boxing is conversion form a string to an object', 'Boxing is conversion form a string to an integer', 'Boxing is conversion form an integer to a string.', 'A', '2021-03-01', 'active', '1'),
('Q006', 'Which sentence about strong name is correct?', 'Strong name in only friend name of the assembly', 'Strong name includes no more than 128-bit numbers', 'You must create a strong name when building a private assembly', 'Strong name is used to uniquely identify the publisher of a.NET library.', 'D', '2021-03-01', 'active', '1'),
('Q007', 'which sentence about default context is correct?', 'default context is context1.', 'default context is the last context created within an application domain', 'default context is used to group together.NET objects that have no specific or unique contextual needs.', 'Only default application domain has default context', 'C', '2021-03-01', 'active', '1'),
('Q008', 'which sentence about the following code is correct?<br>StreamWriter sw = new StreamWriter("Text.txt")', 'if file "Text.txt" does not exist, this code will raise an exception', 'if file "Text.txt" exist, this code will append to this file', 'if file "Text.txt" exist, this code will raise an exception', 'if file "Text.txt" does not exist, this code will create a new file', 'D', '2021-03-01', 'active', '1'),
('Q009', 'What is the first event is raised when the form has been allocated on the managed heap?', 'Load', 'Deactivate', 'Click', 'Activate', 'A', '2021-03-01', 'active', '1'),
('Q010', 'Which event of form handles pressing a key?', 'KeyUp', 'Activated', 'Deactivate', 'KeyDown', 'D', '2021-03-01', 'active', '1'),
('Q011', 'How do we create a control to enter password?', 'Checked only', 'Checked, Indeterminate and Unchecked', 'Unchecked only', 'Checked and Unchecked', 'A', '2021-03-01', 'active', '1'),
('Q012', 'Which sentence is correct?', 'Panel controls cannot support scroll bars', 'Panel controls are used to group related controls in a logical unit', 'Panel controls cannot contain other controls', 'Panel controls are not visible at runtime', 'B', '2021-03-01', 'active', '1'),
('Q013', 'What is SoapFormatter?', 'SoapFormatter is not a serialization formatter', 'SoapFormatter is a serialization formatter that represents graph as a SOAP message', 'SoapFormatter serialization object graph to a stream using a compact binary format', 'SoapFormatter persists an object graph as an XML document', 'B', '2021-03-01', 'active', '1'),
('Q014', 'What are building blocks of an XML Web service?', 'A discovery service and a transport protocol.', 'A discovery service, a description service and a transport protocol.', 'A description service and a transport protocol.', 'A discovery service and a description service.', 'B', '2021-03-01', 'active', '1'),
('Q015', 'Which access modifier makes the code only accessible within the same class?', 'Protected', 'Abstract', 'Public', 'Private', 'D', '2021-03-01', 'active', '1'),
('Q016', 'Boxing in.NET allows the user to convert', 'A reference type to a value type', 'A value type to a reference type', 'Int to double', 'Double to int', 'A', '2021-03-01', 'active', '1'),
('Q017', 'Which of the following is the CORRECT definition of the term Nonfunctional Requirement?', 'One or more logically related system capabilities that provide value to a user and are described by a set of functional requirements', 'A description of a behavior that a system will exhibit under specific conditions', 'A kind of nonfunctional requirement that describes a service or performance characteristic of a product.', 'A description of a property or characteristic that a system must exhibit or a constraint that it must respect.', 'D', '2021-03-01', 'active', '2'),
('Q018', 'Which of the following is the CORRECT definition of the term Feature?', 'A description of a behavior that a system will exhibit under specific conditions.', 'A kind of nonfunctional requirement that describes a service or performance characteristic of a product.', 'A description of a property or characteristic that a system must exhibit or a constraint that it must respect.', 'One or more logically related system capabilities that provide value to a user and are described by a set of functional requirements', 'D', '2021-03-01', 'active', '2'),
('Q019', 'The following skills are important for business analyst, EXCEPT:', 'Project framework knowledge', 'Modeling skills', 'Interpersonal skills', 'Creativity and Observational skills', 'A', '2021-03-01', 'active', '2'),
('Q020', 'Which is NOT a common way to represent software requirement?', 'The language you "invented" and write what you like', 'Formal specifications that define requirements by using mathematically precise specification languages', 'Visual models that illustrate transformational processes, system states and changes between them, data, relationships, logic flows, and the like.', 'Well-structured and carefully written natural language', 'A', '2021-03-01', 'active', '2'),
('Q021', 'What is not included in Requirement Effort?', 'Holding workshops and interviews, analyzing documents, and performing other elicitation activities', 'Writing requirements specifications, creating analysis models, and prioritizing requirements', 'Planning requirements-related activities for the project', 'Writing the software sources', 'E', '2021-03-01', 'active', '2'),

('Q022', '', '', '', '', '', '', '2021-03-01', 'active', '2'),
('Q023', '', '', '', '', '', '', '2021-03-01', 'active', '2'),
('Q024', '', '', '', '', '', '', '2021-03-01', 'active', '2'),
('Q025', '', '', '', '', '', '', '2021-03-01', 'active', '2'),
('Q026', '', '', '', '', '', '', '2021-03-01', 'active', '2'),
('Q027', '', '', '', '', '', '', '2021-03-01', 'active', '2'),
('Q028', '', '', '', '', '', '', '2021-03-01', 'active', '2'),
('Q029', '', '', '', '', '', '', '2021-03-01', 'active', '2'),
('Q030', '', '', '', '', '', '', '2021-03-01', 'active', '2'),
('Q031', '', '', '', '', '', '', '2021-03-01', 'active', '2'),
