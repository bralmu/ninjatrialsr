Ninja Trials Resurrection
=========================

This project is a fork of Ninja Trials and it's aimed to complete at least a simplified version of the game.

I've had to cut the original project to make this viable:
 * No animated sequences (intro/outro/story/credits/...).
 * No special transitions.
 * Includes the first 4 of 8 trials.
 * Includes 42 of the 130+ sounds and 10 of 20 music tracks.
 * Windows, Linux and OS X compatible (no Android, HTML5 or iOS support).
 * Keyboard and usb controller only. No touchable interface.
 * No how-to-play screen, character profiles, afk loops, extras menu or Steam integration.

How to import the project into Eclipse
--------------------------------------

1. Install Gradle extensions for Eclipse.
2. File -> Import -> Gradle project
3. If you see any error at the end of the import process you might ignore it, the build will work anyway.
4. Go to ninja-trials-core -> properties -> Java build path -> Libraries -> Add JARs -> select ninja-trials-core/tablelayout.jar

How to play
-----------------------

Run the main class (com.madgeargames.ninjatrials.desktop.DesktopLauncher).
Default controls are:
Player 1 (keyboard): W=up, A=left, S=down, D=right, F=button1, G=button2, H=button3, T=button4, RightCtrl=select, RightShift=pause, Esc=esc
Player 2 (keyboard): Up=up, Left=left, Down=down, Right=right, J=button1, K=button2, L=button3, I=button4, LeftCtrl=select, LeftShift=pause, Esc=esc

Project history
---------------

Ninja Trials started on 2013 by [Mad Gear Games](http://madgeargames.com):

 * Bralmu (software developer)
 * Danpelgar (software developer, sound producer and music composer, scrum master)
 * JJHagar (project leader, game designer and a little of everything)
 * PigSaint (lead graphic designer)
 * Virako (software developer)
 * Wargo (software developer)

The project was almost halt on April 2015 with a playable demo shown at the GamerCon of the Salón Manga de Jerez. On May 2015 Mad Gear Games was established as a Private Limited Company by three members (now shareholders) who focused all efforts on its second project called [A Hole New World](http://madgeargames.com/ahnw/).

The original Ninja Trials project is held under a private [repository](https://bitbucket.org/virako/ninjatrials) on Bitbucket to avoid spoilers. 
