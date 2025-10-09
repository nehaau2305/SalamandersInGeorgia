package projects.mobile.nehaa.salamandersingeorgia;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

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
}
