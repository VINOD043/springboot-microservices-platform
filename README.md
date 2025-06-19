# springboot-microservices-platform

Note:

WebSecurityConfigurerAdapter was deprecated in Spring Security 5.7 and removed in Spring Security 6.

Since we are using Spring Boot 3.x, which includes Spring Security 6, the correct way is to use the new component-based security configuration approach using SecurityFilterChain.

<b>Creating a multi-module application:<b>

1. Create root maven project:
Create a Maven Project. Select the checkbox for "Create a simple project (skip archetype selection)" and click on Next. Provide the required details, the packaging should be pom and click Finish. This creates the root maven project.
![image](https://github.com/user-attachments/assets/69548f6f-28e0-489d-bce8-b3a28a7e8534)

