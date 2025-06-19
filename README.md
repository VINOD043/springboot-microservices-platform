# springboot-microservices-platform

Note:

WebSecurityConfigurerAdapter was deprecated in Spring Security 5.7 and removed in Spring Security 6.

Since we are using Spring Boot 3.x, which includes Spring Security 6, the correct way is to use the new component-based security configuration approach using SecurityFilterChain.

<H3><b>Creating a multi-module application:</b></H3>
<b>
1. Create root maven project:</b>
Create a Maven Project. Select the checkbox for "Create a simple project (skip archetype selection)" and click on Next. Provide the required details, the packaging should be pom and click Finish. This creates the root maven project.

![image](https://github.com/user-attachments/assets/48000d50-03d0-468f-b7ae-1863ec2a01f8)



<b>
2. To create modules inside the project: </b>
Right click on the project -> New and select Maven Module option. Select the checkbox for "Create a simple project (skip archetype selection)". Provide the module name and browse the parent project in  which the module needs to be created and Click on Finish.

![image](https://github.com/user-attachments/assets/f7ebc55e-9bac-4235-b25e-2b6ddc1ae765)


**----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------**
</h3><b>	Set up OpenLDAP + phpLDAPadmin using Docker and create a test user that we’ll authenticate via your /auth/login endpoint.</b></h3>
<h4><b>	Run the Containers:</b></h4>  
From the same folder: docker-compose up -d

![image](https://github.com/user-attachments/assets/8e09375a-81a8-437f-8754-6187e6193169)




