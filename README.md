# healthcare

# Goal of the assignment:  Demonstrate hands-on experience with the technology stack and ability to design, develop and test services and applications from first principles. 

•	Use the latest release version of these tools and libraries:
.	Java 7, Jersey, Spring Boot
.	Maven 3
.	JUnit 4

•	Backend:

.	Consider a typical domain hierarchy in Healthcare: Hospital, Patients, Examinations. They have a one-many, parent child relationship. Consider the following attributes for the domain objects:
	•	Hospital: ID, Name, Description
	•	Patient: ID, Name, DateOfBirth, Gender
	•	Examination: ID, ExamDate, Name, Description; Weight(kg) and Height(cms) of the patient at the time of examination.

.	Choose an RDBMS that you have prior experience in (PostgreSQL, MySQL, HSQLDB, H2)
.	Choose an app-server that you have prior experience in (Jetty / Tomcat / JBoss)
.	Design and implement a REST service that does the following:
	•	CRUD operations on the objects in the domain hierarchy (Hospital, Patient and Examination).
	•	Method to compute Age of the Patient
	
#	Frontend (optional for full stack developers):
o	Choose a Javascript library that you have prior experience in (AngularJS, ReactJS, Bootstrap, jQuery)
o	Build a simple responsive single page application that displays a list of Hospitals and their patients