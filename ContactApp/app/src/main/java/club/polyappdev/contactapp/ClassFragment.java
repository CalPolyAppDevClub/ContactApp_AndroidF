package club.polyappdev.contactapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ClassFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ClassFragment} factory method to
 * create an instance of this fragment.
 */
public class ClassFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    protected SeekBar seekBar;
    protected Button goButton;
    protected TextView year;
    protected String classLevel;
    private float progress = 0.0f;

    private OnFragmentInteractionListener mListener;

    public ClassFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_class, container, false);
        this.seekBar = (SeekBar) view.findViewById(R.id.class_progress);
        this.goButton = (Button) view.findViewById(R.id.class_button);
        this.year = (TextView) view.findViewById(R.id.class_text_view);

        //initial first year
                year.setText("Freshman");
        //max for the slider
        /**
         * 0 - 5 Freshman
         * 6 - 10 Sophomore
         * 11 - 15 Junior
         * 16 - 20 Senior
         * 21 - 25 Grad
         */
        seekBar.setMax(25);
        //set everyone initial ois fresman
        seekBar.setProgress((int)progress * 0);

        initListener();
        return view;
    }

    protected void initListener() {
        this.goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.ClassFragmentListener(year.getText().toString());
                Fragment fragment = new PlatformSelectFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .commit();
            }
        });

        this.seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                year.setText(convertValue(seekBar.getProgress()));
                return false;
            }
        });
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
        void ClassFragmentListener(String classlevel);
    }

    private String convertValue(float value) {
        if (value > 20) {
            return "Grad";
        }
        else if (value > 15) {
            return "Senior";
        }
        else if (value > 10) {
            return "Junior";
        }
        else if (value > 5) {
            return "Sophomore";
        }
        return "Freshman";
    }
}
