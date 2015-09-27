# EclipseIconEnlarger
Workaround for small eclipse icons in high resolution screens (3200x1800px): 
http://stackoverflow.com/questions/20718093/eclipse-interface-icons-very-small-on-high-resolution-screen-in-windows-8-1
https://bugs.eclipse.org/bugs/show_bug.cgi?id=421383

running the utility in cmd prompt:
java images.EclipseIconEnlarger eclipseBasePath magnificaitonfactor

eg: java images.EclipseIconEnlarger C:\Users\Amrit\Codes\eclipse 2

eclipseBasePath = path where eclipse.exe is located, for e.g. C:\Users\Amrit\Codes\eclipse
magnificationfactor = factor by which the image should be enlarged, for e.g. 2 = double
