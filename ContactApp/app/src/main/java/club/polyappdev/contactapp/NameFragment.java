package club.polyappdev.contactapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NameFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class NameFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    protected EditText firstName;
    protected EditText lastName;
    protected Button nextBtn;

    public NameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_name, container, false);

        firstName = (EditText) view.findViewById(R.id.first_name_edit_text);
        lastName = (EditText) view.findViewById(R.id.last_name_edit_text);
        nextBtn = (Button) view.findViewById(R.id.name_next_button);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!firstName.getText().toString().isEmpty()  && !lastName.getText().toString().isEmpty()){
                    mListener.NameFragmentListener(firstName.getText().toString(), lastName.getText().toString());
                    Fragment fragment = new ClassFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_frame, fragment)
                            .commit();
                }
                else {
                    Toast.makeText(getActivity(), "Invalid first or last name.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void NameFragmentListener(String first, String last);
    }
}
