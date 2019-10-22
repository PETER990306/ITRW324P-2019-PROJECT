package com.itrw324.lights_on;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;





/**
 * A simple {@link Fragment} subclass.
 */
public class GraphsViewFragment extends Fragment
{
    private View graphView;


    public GraphsViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        graphView = inflater.inflate(R.layout.fragment_graphview, container, false);

        return  graphView;
    }

}

