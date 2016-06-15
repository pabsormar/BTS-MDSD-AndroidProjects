package org.bts_netmind.materialdesignmanager;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

// This class defines a specific behaviour to be applied to a View
public class TextViewBehaviour extends CoordinatorLayout.Behavior<TextView>
{
    // This constructor is MANDATORY in order to make the class inflatable from XML
    public TextViewBehaviour(Context context, AttributeSet attrs) { super(context, attrs); }

    // This method tells 'CoordinatorLayout' that we want our 'TextView' to depend on 'SnackBar'
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency)
    {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    // To actually receive the notification aforementioned (in this case related to the SnackBar changes)
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency)
    {
        child.setTranslationY((float) -24);
        return false;
    }

    @Override
    public void onDependentViewRemoved(CoordinatorLayout parent, TextView child, View dependency)
    {
        //CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.WRAP_CONTENT, CoordinatorLayout.LayoutParams.WRAP_CONTENT);
        //params.gravity = Gravity.CENTER;
        //child.setLayoutParams(params);
        child.setTranslationY((float) +24);
    }
}
