# Set up Greenfoot for VSCode

[Mac]
1. Open `Finder` and navigate to the Applications folder.
2. Right-click on the Greenfoot app and select `Show Package Contents`.
3. In the opened folder, go to `Contents -> Resources -> Java` and find the `greenfoot.jar` file.
4. Copy this `greenfoot.jar` to the `/lib` Folder in this project.

[Windows]
1. Open `File Explorer` and navigate to `C:\Program Files\Greenfoot` and locate the `greenfoot.jar` file.
2. Copy this `greenfoot.jar` to the `\lib` Folder in this project.

<br>
<br>

# project.greenfoot
Copy the `project.greenfoot.md` file and rename it to `project.greenfoot`

# ReadMe
**Purpose:** 

This code was created for Mr.Chan's P5 ICS3U Intro to CS Class as an assignment.

**How to Play:**

The user can press the space bar to start the game, the game then goes through the storyline (kill all the monsters and collect all the potions to save a friend of the witch) and tutorial which teaches the user how to play, the left <-- and right arrow --> key helps move the witch left and right, the up key moves the witch up to fly and the user can press SHIFT for attack 1 and SPACE for attack 2, note that flying and attacking requires time to cool down. The games goes through three levels, in each level, there be will corresponding amounts of monsters to kill and a potion to collect when all the monsters of the current stage are killed. The objective is to kill all the monsters in each stages and collect 3 potions in total. The witch and the monsters all have health bars which will decrease when being attacked and one healing potion in each level will be spawned when the health of the witch is low. If the witch's health becomes 0, the game over sign will appear, indicating that the game is over. If the witch succeeded to kill all the monsters and collect all the potions, the endscreen will show up indicating that the witch have completed the mission to save her friend.
