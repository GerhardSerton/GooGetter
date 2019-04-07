SRTPIE001
Assignment 5: Game AI

For this assignment, I've introduced a form of A* pathfinding into the game. As each level is already a grid, it runs the path finding on that level. To better visualise this,
I have made the underlying grid visible to the player.

Due to the non-deterministic nature of my implementation of the A* algorithm, the highlighted path will often flicker between multiple viable paths. This is due to the
algorithm finding multiple, equally valid paths, and randomly picking a different one each time. At the end of the day, the ultimate path taken by an npc will be an optimal one.
Also, since it tracks the enemy position based on the bottom corners, it will be aligned to it, resulting in what may seem like off-center paths if taken at face value.

For my "advanced AI" implementation, I have made it so that multiple npcs will flock and "swarm" before chasing the player. This can be seen in the 3rd level.

COMPILING AND RUNNING:
1: Install gradle, if you haven't yet. libGDX uses this when it builds your project. Instructions here: https://gradle.org/install/
2: Navigate to the GooGetter directory, and run the following command:
	gradle desktop:run
   I know this works using Windows Powershell, but it might work with the regular commandline too.

NB: I did this on windows, so if you're attempting to run it in another operating system I can't guarantee that the gradle command will work. Maybe try "gradlew" in place
of "gradle", as that's what the official libGDX documentation suggests (https://github.com/libgdx/libgdx/wiki/Gradle-on-the-Commandline).
