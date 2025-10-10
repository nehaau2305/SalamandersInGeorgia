package projects.mobile.nehaa.salamandersingeorgia;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

public class SalamanderListFragment extends ListFragment {
    // initialize variables
    private boolean twoFragmentsActivity = false;
    private final String[] salamanderOptions = {"Frosted Flatwoods Salamander","Reticulated Flatwoods Salamander",
            "Pigeon Mountain Salamander", "Green Salamander", "Tennessee Cave Salamander",
            "Georgia Blind Salamander", "Patch-nosed Salamander\n"};
    private int salamanderIndex;

    // default constructor is required
    public SalamanderListFragment() { }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // create a new ArrayAdapter for the list with built in layout
        setListAdapter( new ArrayAdapter<>( getActivity(), android.R.layout.simple_list_item_activated_1, salamanderOptions ) );
        // set the twoFragmentsActivity variable to true iff we are in 2 fragment (landscape) view
        View detailsFrame = getActivity().findViewById(R.id.salamanderInfoFragmentContainer);
        twoFragmentsActivity = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        if(savedInstanceState != null) {
            // Restore last state for checked position.
            salamanderIndex = savedInstanceState.getInt("salamanderSelections", 0);
        } else if(getArguments() != null) {
            salamanderIndex = getArguments().getInt("selectedIndex", 0);
        }

        // set the list mode as single choice so only 1 item can be selected at a time
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        getListView().setItemChecked(salamanderIndex, true);

        if(twoFragmentsActivity) {
            showSalamanderInfo(salamanderIndex);
            getListView().smoothScrollToPosition(salamanderIndex);
        }
    } //onViewCreated


    @Override
    public void onListItemClick(@NonNull ListView lv, @NonNull View v, int position, long id) {
        showSalamanderInfo(position);
    } //onListItemClick

    @Override
    public void onSaveInstanceState( Bundle outState ) {
        super.onSaveInstanceState(outState);
        // save the list index selection
        outState.putInt("salamanderSelections", salamanderIndex);
    } //onSaveInstanceState

    private void showSalamanderInfo(int salamanderIndex) {
        this.salamanderIndex = salamanderIndex;
        getListView().setItemChecked(salamanderIndex, true );
        // update activity
        if(getActivity() instanceof SalamanderActivity) {
            ((SalamanderActivity) getActivity()).setSelectedIndex(salamanderIndex);
        }

        View infoContainer = getActivity().findViewById(R.id.salamanderInfoFragmentContainer);
        SalamanderInfoFragment details = SalamanderInfoFragment.newInstance(salamanderIndex);
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        if (infoContainer != null && infoContainer.getVisibility() == View.VISIBLE) {
            transaction.replace(R.id.salamanderInfoFragmentContainer, details);
        } else {
            transaction.replace(R.id.salamanderListFragmentContainer, details).addToBackStack(null);
        }
        transaction.commit();
    } //showSalamanderInfo

}
