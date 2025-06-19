# springboot-microservices-platform

Note:

WebSecurityConfigurerAdapter was deprecated in Spring Security 5.7 and removed in Spring Security 6.

Since we are using Spring Boot 3.x, which includes Spring Security 6, the correct way is to use the new component-based security configuration approach using SecurityFilterChain.

<H3><b>Creating a multi-module application:</b></H3>
<b>
1. Create root maven project:</b>
Create a Maven Project. Select the checkbox for "Create a simple project (skip archetype selection)" and click on Next. Provide the required details, the packaging should be pom and click Finish. This creates the root maven project.
![image](https://github.com/user-attachments/assets/395cd836-007f-4dc4-a367-7cbd7a69ce5a)



2. To create modules inside the project: 
Right click on the project -> New and select Maven Module option. Select the checkbox for "Create a simple project (skip archetype selection)". Provide the module name and browse the parent project in  which the module needs to be created and Click on Finish.
