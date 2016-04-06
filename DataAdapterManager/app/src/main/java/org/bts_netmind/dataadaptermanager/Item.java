package org.bts_netmind.dataadaptermanager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Item
{
    private String mImage;
    private String mTitle;
    private String mBody;

    public Item(String imgRef, String aTitle, String aBody)
    {
        this.mImage = imgRef;
        this.mTitle = aTitle;
        this.mBody = aBody;
    }

    public String getmImageRef() { return mImage; }
    public String getmTitle() { return mTitle; }
    public String getmBody() { return mBody; }
}
