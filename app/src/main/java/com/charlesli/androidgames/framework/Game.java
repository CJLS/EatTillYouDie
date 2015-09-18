package com.charlesli.androidgames.framework;

public interface Game 
{
    public Input getInput();
    public com.charlesli.androidgames.framework.FileIO getFileIO();
    public com.charlesli.androidgames.framework.Graphics getGraphics();
    public Audio getAudio();
    public void setScreen(com.charlesli.androidgames.framework.Screen screen);
    public com.charlesli.androidgames.framework.Screen getCurrentScreen();
    public com.charlesli.androidgames.framework.Screen getStartScreen();
}

