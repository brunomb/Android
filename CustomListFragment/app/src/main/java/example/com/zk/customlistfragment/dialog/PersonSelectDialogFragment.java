package example.com.zk.customlistfragment.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import example.com.zk.customlistfragment.R;
import example.com.zk.customlistfragment.adapter.PersonItemAdapter;
import example.com.zk.customlistfragment.listener.OnPersonClickListener;

public class PersonSelectDialogFragment extends DialogFragment{

    public PersonSelectDialogFragment() {}

    private static PersonItemAdapter mData;
    
    public static PersonSelectDialogFragment newInstance(PersonItemAdapter data) {
        PersonSelectDialogFragment myFragment = new PersonSelectDialogFragment();
        myFragment.setRetainInstance(true);
        mData = data;
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        View rootView = inflater.inflate(R.layout.dialog_select_person, container, false);
        ListView personList = (ListView) rootView.findViewById(R.id.lv_person_list);
        personList.setAdapter(mData);
        //personList.setOnItemClickListener(new OnPersonClickListener());
        return rootView;
    }

    @Override
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

}
