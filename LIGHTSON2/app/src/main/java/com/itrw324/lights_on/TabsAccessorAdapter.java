package com.itrw324.lights_on;




import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabsAccessorAdapter extends FragmentPagerAdapter
{
    private TabsAccessorAdapter myTabsAccessorAdapter;
    public TabsAccessorAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int i)
    {
        switch (i)
        {
            case 0:
                TableViewFragment tableFragment = new TableViewFragment();
                return tableFragment;

            case 1:
                GraphsViewFragment graphsFragment = new GraphsViewFragment();
                return graphsFragment;

            default:
                return null;
        }
    }


    @Override
    public int getCount()
    {
        return 3;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0:
                return "Table view";

            case 1:
                return "Graph view";

            default:return null;

        }
    }
}
