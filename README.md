
# Simple UFTPro (LeanFT) / MC script.

++++++++++++++++++++++++++++

**LeanFT 14.03

**MC 2.51

++++++++++++++++++++++++++++

It uses Advantage Online Shopping iOS app to purchase an item.

Note that you may need to change the user name and password. The scrip uses the setSecure method to encrypt the password. Use the tool from the LeanFT > Tools menu in your IDE or from the Windows Start menu.

Important Note:
---------------
This project requires adding **utils-1.1.jar** file into the local Maven repository.
The project includes the jar file under the _**MCUtils_jar**_ folder but the most updated version of it will be found here:
https://github.com/Rishon73/MCUtils.git

This git repository has a ready to use jar file under _MCUtils/out/artifacts/MCUtils_jar/ folder_.
To include it in your local maven repository, run this command in terminal:

_mvn install:install-file -Dfile=\<location of utils-1.1.jar\> -DgroupId=com.mf -DartifactId=utils -Dversion=1.1 -Dpackaging=jar_
