package com.charlesli.androidgames.framework;

import com.charlesli.androidgames.framework.Graphics.PixmapFormat;

public interface Pixmap 
{
    public int getWidth();
    public int getHeight();
    public PixmapFormat getFormat();
    public void dispose();
}

