# springboot-microservices-platform

Note:

WebSecurityConfigurerAdapter was deprecated in Spring Security 5.7 and removed in Spring Security 6.

Since we are using Spring Boot 3.x, which includes Spring Security 6, the correct way is to use the new component-based security configuration approach using SecurityFilterChain.

Creating a multi-module application:
	1. Create root maven project:
	Create a Maven Project. Select the checkbox for "Create a simple project (skip archetype selection)" and click on Next. Provide the required details, the packaging should be pom and click Finish. This creates the root maven project.
	![image](https://github.com/user-attachments/assets/7d8b3392-cf40-40f5-8d42-973095547570)

		
	
	2. To create modules inside the project: 
	Right click on the project -> New and select Maven Module option. Select the checkbox for "Create a simple project (skip archetype selection)". Provide the module name and browse the parent project in  which the module needs to be created and Click on Finish.
	
		
	 
![image](https://github.com/user-attachments/assets/94310b19-420c-4d9d-90e2-5674c4f8f54d)
