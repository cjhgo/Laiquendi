package xyz.rasp.laiquendi.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    private Header      header;
    private StateLayout state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inject
        header = HeaderView.get(this, R.id.header);
        state = StateLayoutView.get(this, R.id.state);

        header.attach(this);

        final int[] seed = {0};
        header.mTvHeader.setOnClickListener(v -> {
            seed[0]++;
            if (seed[0] % 2 == 0) {
                state.showContent();
            } else {
                state.showEmpty();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Mock Menu
        menu.add("Item1");
        menu.add("Item2");
        menu.add("Item3");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        header.setTitle((String) item.getTitle());
        return true;
    }
}
