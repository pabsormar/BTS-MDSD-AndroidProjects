package org.bts_netmind.contentprovidermanager;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider
{
    private static final String TAG_MY_CONTENT_PROVIDER = "In-MyContentProvider";
    // 'ContentProvider' name declaration (or 'authority') and parsing
    private static final String PROVIDER_AUTHORITY = "org.bts_netmind.contentprovidermanager";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_AUTHORITY);

    // Declaring a 'handle' to an object which extends from 'SQLiteOpenHelper'
    private MyDatabase myDatabaseObject;

    @Override
    public boolean onCreate()
    {
        // The database is created through the 'MyDatabase' constructor
        this.myDatabaseObject = new MyDatabase(this.getContext());
        // Create the database table using 'onCreate()'; there is actually no need to do this here
        //this.myDatabaseObject.getWritableDatabase();

        return true;
    }

    @Override
    // 'selection' accepts the MySQL command WHERE
    // 'selectionArgs' is an array with values to check for all those fields included in 'selection'
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        // Implement this to handle requests to delete one or more rows.
        SQLiteDatabase db = this.myDatabaseObject.getWritableDatabase();

        return db.delete(ParamsDB.TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri)
    {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // Retrieves the URI which refers to the inserted register
    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        // Get an 'SQLiteDatabase' object to access the database
        SQLiteDatabase db = this.myDatabaseObject.getWritableDatabase();

        // Returns the row id of the newly inserted register
        long regId = db.insert(ParamsDB.TABLE_NAME, null, values);

        // 'ContentUris.withAppendedId' simply concatenates the provider URI with an ID
        return ContentUris.withAppendedId(MyContentProvider.CONTENT_URI, regId);
    }

    @Override
    // 'projection' is an array with all the fields we want to be retrieved
    // 'selection' accepts the MySQL command WHERE
    // 'selectionArgs' is an array with values to check for all those fields included in 'selection'
    // 'sortOrder' points out which column we want to order
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder)
    {
        // Get an 'SQLiteDatabase' object to access the database
        SQLiteDatabase db = this.myDatabaseObject.getReadableDatabase();

        return db.query(ParamsDB.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs)
    {
        SQLiteDatabase db = this.myDatabaseObject.getWritableDatabase();

        return db.update(ParamsDB.TABLE_NAME, values, selection, selectionArgs);
    }
}
