# ms-user
Technical challenge for Sermaluc using Spring, JPA y h2 in-memory database

## Prerequisites 
- Java 11
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/guides/index.html)
- [H2 Database](https://www.h2database.com/html/main.html)
- [Lombok](https://objectcomputing.com/resources/publications/sett/january-2010-reducing-boilerplate-code-with-project-lombok)
- [Jacoco](https://www.eclemma.org/jacoco/)
- [Jjwt](https://github.com/jwtk/jjwt)
- [Mapstruct](https://mapstruct.org/)
- [Junit](https://junit.org/junit4/)

## Tools
- VS Code, Eclipse or NetBeans (or any preferred IDE) with embedded Maven
- Maven (version >= 3.6.0)
- Postman (or any RESTful API testing tool)
- Swagger can be launched in Browser: http://localhost:8080/swagger-ui/index.html

![image](https://github.com/Jhavo/ms-user/blob/main/doc/images/swagger.jpg)

- H2 Console On Browser: http://localhost:8080/h2-ui

![image](https://github.com/Jhavo/ms-user/blob/main/doc/images/h2.jpg)

- Code Coverage: After building the project you can find code coverage in the target path :- /ma-user/target/site/jacoco/index.html

For E.g.: Code Coverage of StudentController looks like this:

![image](https://github.com/Jhavo/ms-user/blob/main/doc/images/jacoco.jpg)

###  Build and Run application
_GOTO >_ **the root of the project** and try below command in terminal
> **```mvn spring-boot:run```** it will run application as spring boot application

or
> **```mvn clean install```** it will build application and create **jar** file under target directory 

Run jar file from below path with given command
> **```java -jar ~/path-to-ms-user/target/ms-user-0.0.1-SNAPSHOT```**

Or
> run main method from `MsUserApplication.java` as spring boot application.  


### Code Snippets
1. #### Maven Dependencies
    Need to add below dependencies to enable H2 DB related config in **pom.xml**. Lombok's dependency is to get rid of boiler-plate code.   
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt</artifactId>
        <version>0.9.1</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
    </dependency>
    <dependency>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct</artifactId>
        <version>1.4.2.Final</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    ```
   
   The following dependencies have been added for testing and documentation in this application..
    ```
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-ui</artifactId>
        <version>1.6.15</version>
    </dependency>
    ```



2. #### Properties file
    Reading H2 DB related properties from **application.yml** file and configuring JPA connection factory for H2 database.  

    **src/main/resources/application.yml**
    ```
    server:
    port: 8080

    logging:
    level:
        com.sermaluc.msuser: info

    spring:
        jpa:
            show-sql: true
            properties:
                hibernate:
                    dialect: org.hibernate.dialect.H2Dialect
            hibernate:
                ddl-auto: update
        h2:
            console:
                enabled: true
                path: /h2-ui
        datasource:
            url: jdbc:h2:mem:sermalucdb;DB_CLOSE_DELAY=- 1;DB_CLOSE_ON_EXIT=FALSE
            username: test
            password: test

    springdoc:
        api-docs:
            path: /documentation/json
        swagger-ui:
            path: /documentation

    app:
        validation:
            email:
                pattern: "[a-z0-9._%+-]+@[a-z0-9.-]+\\\\.[a-z]{2,3}"
            password:
                pattern: "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,12}$" 
    ```

Swagger can be launched in Browser: http://localhost:8080/swagger-ui/index.html   

H2 Console On Browser: http://localhost:8080/h2-ui



3. #### Jacoco Configuration
    Jacoco plugin is used for getting Code coverage Report, Offcial Documentation [Jacoco](https://www.eclemma.org/jacoco/)  

    **pom.xml**
    ```
    <plugin>
        <groupId>org.jacoco</groupId>
        <artifactId>jacoco-maven-plugin</artifactId>
        <version>0.8.5</version>
        <configuration>
            <rules>
                <rule implementation="org.jacoco.maven.RuleConfiguration">
                    <element>BUNDLE</element>
                    <limits>
                        <limit implementation="org.jacoco.report.check.Limit">
                            <counter>INSTRUCTION</counter>
                            <value>COVEREDRATION</value>
                            <minimun>0.80</minimun>
                        </limit>
                    </limits>
                </rule>
            </rules>
        </configuration>
        <executions>
            <execution>
                <id>default-prepare-agent</id>
                <goals>
                    <goal>prepare-agent</goal>
                </goals>
            </execution>
            <execution>
                <id>default-report</id>
                <phase>verify</phase>
                <goals>
                    <goal>report</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
    ```



4. #### Model class
    Below are the model classes which we will store in H2 DB and perform CRUD operations.  
    **UserEntity.java**  
    ```
    @Getter
    @Setter
    @Entity
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "users")
    public class UserEntity implements Serializable {

        @Id
        @Column(columnDefinition = "uuid")
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;

        private String name;

        @Column(unique = true)
        private String email;

        private String password;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<UserPhoneEntity> phones = new ArrayList<>();

        private Date created;

        private Date modified;

        private Date lastLogin;

        private String token;

        private Boolean isActive;

        @PrePersist
        public void onCreated() {
            Instant instant = Instant.now();
            this.created = Date.from(instant);
            this.lastLogin = Date.from(instant);
            this.isActive = true;
        }

        @PreUpdate
        private void onUpdated() {
            Instant instant = Instant.now();
            this.modified = Date.from(instant);
        }

    }
    ```
   
    **UserPhoneEntity.java**
    ```
    @Getter
    @Setter
    @Entity
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "userPhones")
    public class UserPhoneEntity implements Serializable {

        private static final long serialVersionUID = 1L;
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @JoinColumn(name = "userId", referencedColumnName = "id")
        @ManyToOne(optional = true, fetch = FetchType.LAZY)
        private UserEntity user;

        private String number;

        private String cityCode;

        private String countryCode;

        private Date created;

        private Date modified;

        private Boolean isActive;

        @PrePersist
        private void onCreated() {
            Instant instant = Instant.now();
            this.created = Date.from(instant);
            this.isActive = true;
        }

        @PreUpdate
        private void onUpdated() {
            Instant instant = Instant.now();
            this.modified = Date.from(instant);
        }

    }
    ```


    
### API Endpoint
Test this API endpoint from the **API User.postman_collection.json** file.
    **doc/postman/API User.postman_collection.json**

- #### Create user
    > **POST Mapping** http://localhost:8080/v1/user  - Add new user  

    Request Body  
    ```
    {
        "name": "Alexis Sanchez",
        "email": "alexis@sanchez.cl",
        "password": "H@nter1",
        "phones": [
            {
                "number": "12345678",
                "cityCode": "1",
                "countryCode": "57"
            }
        ]
    }
    ```

