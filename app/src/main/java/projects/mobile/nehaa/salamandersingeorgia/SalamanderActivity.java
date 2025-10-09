package projects.mobile.nehaa.salamandersingeorgia;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SalamanderActivity extends AppCompatActivity {
    // initialize variable
    private int selectedIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_salamander);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // action bar to navigate to previous page using back button
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // load selected index if exists already
        if (savedInstanceState != null) {
            selectedIndex = savedInstanceState.getInt("selectedIndex", 0);
        }

        // add list fragment
        SalamanderListFragment listFragment = new SalamanderListFragment();
        Bundle args = new Bundle();
        args.putInt("selectedIndex", selectedIndex);
        listFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.salamanderListFragmentContainer, listFragment).commit();

        // if landscape orientation (check if info container exists) create info fragment
        if (findViewById(R.id.salamanderInfoFragmentContainer) != null) {
            SalamanderInfoFragment salamanderInfoFragment = SalamanderInfoFragment.newInstance(selectedIndex);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.salamanderInfoFragmentContainer, salamanderInfoFragment).commit();
        }

    } //onCreate
}
