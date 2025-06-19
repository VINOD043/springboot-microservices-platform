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

<h4><b>	Step-by-Step: Add a Test LDAP User via phpLDAPadmin:
Access GUI </b></h4>

Open browser → http://localhost:8082
<br>	• Login DN: cn=admin,dc=mycompany,dc=com
<br> 	• Password: admin

![image](https://github.com/user-attachments/assets/c5d1eb9f-26c5-4b62-ab6a-8f1fa551e545)


![image](https://github.com/user-attachments/assets/c07aef69-5018-47ee-aa88-78cf7621ce84)


<h4><b>	Step-by-Step to Create a Group: </b></h4>
        1. In phpLDAPadmin, expand the tree:
<br>		→ dc=mycompany,dc=com
<br>	2. Click Create a child entry under it
<br>	3. Choose template: Generic: Posix Group
<br>	4. Fill:
<br>		○ cn: developers
<br>		○ gidNumber: 5000 (pick any unique number)
<br>	5. Click Create Object, then Commit
<br>	✅ This will create a group with gidNumber = 5000
<br>
<h4><b> Create ou=people and move your users there (recommended)</b></h4>
        This aligns with common LDAP structures and Spring Boot defaults.
<br>	📄 Steps in phpLDAPadmin:
<br>		1. Expand: dc=mycompany,dc=com
<br>		2. Click "Create a child entry"
<br>		3. Choose template: Generic: Organizational Unit
<br>		4. Enter:
<br>			○ ou: people
<br>		5. Click Create Object, then Commit
<br>
<h4><b>Create a User</b></h4>
    		1. Go back to dc=mycompany,dc=com
<br>		2. Under the new ou=people, click "Create a child entry"
<br>		3. Choose Generic: User Account
<br>		4. Fill form:
<br>			○ uid: testuser
<br>			○ givenName: Test
<br>			○ sn: User
<br>			○ cn: Test User
<br>			○ userPassword: testpass (choose a strong password)
<br>		5. Click Create Object
<br>		6. Click Commit
<br>	✅ Done! Now you have a user testuser with password testpass in LDAP.
<br>
<h2><b>Recommendation (if you're learning)</b></h2>
✅ Change the code to use cn={0},ou=people for now, so you can finish the authentication module quickly.
<br>	🛠 Later, once you're comfortable, switch back to uid={0},ou=people and use .ldif to create proper users — like you'd do in a real enterprise setup.








