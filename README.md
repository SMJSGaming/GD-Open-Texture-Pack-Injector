# GD-Open-Texture-Pack-Injector
An open source smooth texture pack injector for gd with easy configuration

# How to use
To use this project for own use put the [version file](server/version.txt) on a website and edit the second line to the link of your jar file
<br><br>
Then edit version reference link in the [Updater class](program/src/Updater.java) to the link of where your [version file](server/version.txt) is located on your site
<br><br>
Compile the program using the java 1.8 compiler and put as main class [Main.java](program/src/Main.java)

# How to install
<h3>Requirements</h3>
<ul>
  <li>Be sure to have the steam version of GD</li>
  <li>Be sure to have java runtime: https://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html</li>
</ul>
<br>
When installed grab the texture pack injector and put it in the same folder as where the exe file of you GD is located, DO NOT change the jar name since some codes use this as value
<br><br>
Run the app once, this will create a folder called r
<br><br>
Put a folder with the texture pack and all other remaining resource files in the r folder and add a 1:1 ratio image called icon.png in the texture pack folder and a file called info.data containing the description of the texture pack
<br><br>
Run the program again and click the texture pack you want to use and press the button in the popup
<br><br>
Run gd and it should have loaded the texture pack, press the default button in the application to go back to the default resources
