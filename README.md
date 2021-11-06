# DsMcBOT
Really bad Discord Bot & Spigot Plugin for integrating Minecraft in your server that runs as a plugin
<br><br>

<p align="center">
<img src="https://forthebadge.com/images/badges/fuck-it-ship-it.svg" alt="I want to delete this repo"/>
</p>

## Disclaimer
<br>
Do not take this seriously as it is really buggy, also /reload and plugman won't work on this and will mess up the internal JDA creating more instances of it (JDA not terminating correctly).
<br>
<br> 
How to complete the code:<br>(I did not supply a config system so please create a Data.java class and put this inside as a "configuration". You may also need to change some channel ids that i use for my own project)<br>( Also please forgive the many static abuse you will find around this project :] )&nbsp;
<br><br>

```java
package net.reg.dsmcbot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Data {
    public static String[] hooks = {
            "HOOK1",
            "HOOK2",
            "HOOK3"            //ADD AS MANY AS YOU LIKE (Used for sending server -> discord messages)
    };
    public static String tkn = "BOT-TOKEN-AAAAAAAA"; //YOUR BOT'S TOKEN
    public static List<Long> ids = new ArrayList<>(Arrays.asList(new Long[]{id1L, id2L, 1234353L}));  //IDs of the people who can send console commands and server info commands
}
```
