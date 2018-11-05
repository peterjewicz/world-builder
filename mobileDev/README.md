This folder contains the dev files and build tools needed to create the mobile version of the app.

It is split into two folders


devBuild
  Contains the files and scripts to build. Nearly the same to the front-end of the code in the parent directory. The difference
  is this folders contains the copied contents of mobileSpecific (sibling folder) that contains the needed
  changes to make the mobile version work correctly.

mobileSpecific
  Contains a few files that have changes unique to the mobile version of the app. For example it contains
  the correct routing folder so that the mobile app doesn't load the web landing page. Before build, these files
  should be copied and replace the devBuild version of the files to create the correct mobile build. 



Once that's all done, the build output can be copied to the WWW folder in the mobile directory and build for the
mobile platforms there.

TODO make the above copy automatic. We can configure webpack in here to output build files there and route it
correclty.
