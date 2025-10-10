package projects.mobile.nehaa.salamandersingeorgia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.InputStream;
import java.util.Scanner;

public class SalamanderInfoFragment extends Fragment {
    // initialize variables
    private int salamanderIndex;
    private TextView titleTV;
    private TextView textV;
    private ImageView img;
    private final int[] salamandersImages = {};
    private final int[] salamandersInfoTexts = {R.raw.frosted_flatwoods_sal, R.raw.reticulated_flatwoods_sal,
            R.raw.pigeon_mountain_sal, R.raw.green_sal, R.raw.tennessee_cave_sal,
            R.raw.georgia_blind_sal, R.raw.patch_nosed_sal};

    // required default constructor
    public SalamanderInfoFragment() {  }

    public static SalamanderInfoFragment newInstance(int salamanderIndex) {
        // this uses the default constructor (not defined in this class, but Java-supplied)
        SalamanderInfoFragment fragment = new SalamanderInfoFragment();
        // save the selected list weedIndex in the new fragment's Bundle data
        // the WeedWebViewFragment needs to know the weed info to display
        Bundle args = new Bundle();
        args.putInt("salamanderIndex", salamanderIndex);
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View view = inflater.inflate( R.layout.fragment_info, container, false );
        // retrieve selected salamander's index
        if(getArguments() != null) {
            salamanderIndex = getArguments().getInt("salamanderIndex", 0);
        }
        // connect layout components to those in the xml file
        img = view.findViewById(R.id.salamanderImageView);
        titleTV = view.findViewById(R.id.titleText);
        textV = view.findViewById(R.id.infoText);
        // set images
        if (salamanderIndex >=0 && salamanderIndex < salamandersInfoTexts.length) {
            //img.setImageResource(salamandersImages[salamanderIndex]);
            String fullText = readTextFile(salamandersInfoTexts[salamanderIndex]);
            // separate title line from text file
            String[] lines = fullText.split("\n", 2);
            titleTV.setText(lines[0]);
            textV.setText(lines.length > 1 ? lines[1] : "");
        } // set scenic UI components according to selected park index
        return view;
    } //onViewCreated


    /**
     * Helper method to read the text file
     * @param fileId the id of the file to be read
     * @return the text of the file as a String
     */
    private String readTextFile(int fileId) {
        InputStream inputStream = getResources().openRawResource(fileId);
        Scanner scan = new Scanner(inputStream);
        StringBuilder stringBuilder = new StringBuilder();
        while (scan.hasNextLine()) {
            stringBuilder.append(scan.nextLine()).append("\n");
        }
        return stringBuilder.toString().trim();
    } //readTextFile

    public int getShownParkIndex() {
        return getArguments().getInt("salamanderIndex", 0 );
    }
}
