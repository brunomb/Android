package com.github.brnmb.amounty.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import com.github.brnmb.amounty.R;
import com.github.brnmb.amounty.adapter.AmountyAdapter;
import com.github.brnmb.amounty.model.Amounty;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    private RecyclerView mRecyvlerView;
    private GridLayoutManager lLayout;
    private AmountyAdapter adapter;
    private TextView tvNoting;
    private FloatingActionButton fab;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int cont = 0;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View mView = inflater.inflate(R.layout.fragment_main, container, false);

        Log.v("FRAGMENT ID", getId()+"");

        tvNoting = (TextView) mView.findViewById(R.id.tv_nothing);
        mRecyvlerView = (RecyclerView) mView.findViewById(R.id.my_recycler_view);

        // FloatingActionButton - BEGIN
        fab = (FloatingActionButton) mView.findViewById(R.id.fab);

        if (!(fab == null)) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }
        // FloatingActionButton - END
        updateAdapter(mView);

        // Inflate the layout for this fragment
        return mView;
    }

    private void updateAdapter(View mView) {
        List<Amounty> amountys = Amounty.getAllAmounty();

        if (amountys.isEmpty()) {
            tvNoting.setVisibility(View.VISIBLE);
        } else {
            tvNoting.setVisibility(View.GONE);
            lLayout = new GridLayoutManager(mView.getContext(), 2);
            adapter = new AmountyAdapter(amountys);
//            LinearLayoutManager llm = new LinearLayoutManager(mView.getContext());
            mRecyvlerView.setLayoutManager(lLayout);
            mRecyvlerView.setAdapter(adapter);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    public void filter(String text) {
        adapter.getFilter().filter(text);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void hideFab() {
        if (!(fab == null)) {
            fab.setVisibility(View.GONE);
        }
    }

    public void showFab() {
        if (!(fab == null)) {
            fab.setVisibility(View.VISIBLE);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
