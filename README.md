# emap-petProject [![Build Status](https://travis-ci.com/detlassnake/emap-petProject.svg?branch=master)](https://travis-ci.com/detlassnake/emap-petProject)
This application implements CRUD and REST, allow to communicate with database via POST requests and provide CRUD interfaces. 

Application has next entities:
Skill, 
Account, 
Developer. 


Layers: 
model - POJO classes; 
view - all data that are required for user/console interaction; 
controller - users requests handling; repository - classes that provide access to text files; 
storage - tables in db. 

Create basic interface for repository layer: 
interface GenericRepository<T,ID>;
interface DeveloperRepository extends GenericRepository<Developer, Long>; 
class JdbcDeveloperRepositoryImpl implements DeveloperRepository. 

Liquibase is used to initialize tables in DB and fill them. 

There are a few endpoints in this API, each of them associate with certain servlets: 
/api/v1/skills; 
/api/v1/accounts; 
/api/v1/developers. 

There is one index.jsp page, which contains description of project in three languages: 
English, 
German,
Italian. 

Technology stack: Java, MySQL, JDBC, Servlets, Liquibase, JUnit.

Link to project: https://epam-petproject.herokuapp.com

Link to project documentation: https://epam-petproject.herokuapp.com/documentation