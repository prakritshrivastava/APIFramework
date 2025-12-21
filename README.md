This Repository contains a Rest Assured framework created in Java, below is the sample overview of the same.

<img width="1920" height="1001" alt="image" src="https://github.com/user-attachments/assets/3cb38f2b-0706-4540-8b81-b89f55f3cb39" />


1. The Rest Client Java houses all the CRUD operations realized using java methods.
2. The Base Test takes care of the Initialization of RestClient.java using the @BeforeTest TestNG annotation along with other actions such as starting the wiremock server, while the @AfterTest annotation takes care of postcondtions such as shutting down the wiremock server.
3. The Tests are seperately housed inside the test layer (under src/test/java).
4. Since dealing with APIs requires assertions to be made w.r.t JSON files/XML files the same is housed in the utilities section.
5. To create nested JSON requests the POJO with Lombok approach is used housed in respective classes in the src/main/java.
6. The config.properties houses all the config info such as the urls and the same is initialized using ConfigManager class.
7. The test runners in .xml take care of running the tests in bulk
8. The .json/.xml files are housed in the src/test/resources
9. The Containerization is achieved using the docker file to enable remote execution.
10. The pom.xml contains all dependencies and plugins required for building, packaging and deploying the project. 

The git commit contains major executables only
