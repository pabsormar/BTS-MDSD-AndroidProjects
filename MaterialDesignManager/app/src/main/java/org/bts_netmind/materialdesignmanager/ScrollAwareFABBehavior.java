package org.bts_netmind.materialdesignmanager;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;


// Normally when implementing CoordinatorLayout behaviors, we need to implement 'layoutDependsOn()'
// and 'onDependentViewChanged()', which are used to track changes in other views contained within
// the CoordinatorLayout. Since we only need to monitor scroll changes, we use the existing behavior
// defined for the Floating Action Button, which is currently implemented to track changes for
// Snackbars and AppBarLayout as discussed
public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior
{
    // This constructor is MANDATORY in order to make the class inflatable from XML
    // Because we are defining this behavior statically within the XML, we must also implement a
    // constructor to enable layout inflation to work correctly
    public ScrollAwareFABBehavior(Context context, AttributeSet attributeSet) { }


}
