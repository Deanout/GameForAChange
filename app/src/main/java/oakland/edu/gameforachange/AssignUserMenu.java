package oakland.edu.gameforachange;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Dean on 2/22/2015.
 */
public class AssignUserMenu extends Activity {
    TextView displayInt;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.assignusermenu);
    }

}