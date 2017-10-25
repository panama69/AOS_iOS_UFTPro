#A simple UFTPro (LeanFT) / MC script.

++++++++++++++++++++++++++++

**LeanFT 14.01

MC 2.51**

++++++++++++++++++++++++++++

It uses Advantage Online Shopping iOS app to purchase an item.

Note that you may need to change the user name and password. The scrip uses the setSecure method to encrypt the password. Use the tool from the LeanFT > Tools menu in your IDE or from the Windows Start menu.

Note:
-----
This project requires adding MCUtils.jar file into the local Maven repository.
The project includes the jar file under the *MCUtils_jar* folder but the most updated version of it will be found here:
https://github.com/Rishon73/MCUtils.git

This git repository also has a ready to use jar file under MCUtils/out/artifacts/MCUtils_jar/ folder.
to include it in the maven repository run this command in terminal:
mvn install:install-file -Dfile=<location of MCUtils.jar> -DgroupId=com.mf -DartifactId=MCUtilities -Dversion=4.0.0 -Dpackaging=jar
