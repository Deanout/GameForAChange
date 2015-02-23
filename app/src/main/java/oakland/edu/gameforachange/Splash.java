package oakland.edu.gameforachange;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Dean on 2/22/2015.
 */
public class Splash extends Activity implements View.OnClickListener {
    public ImageView proceedButton;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash);

        proceedButton = (ImageView)findViewById(R.id.splashLogo);
        proceedButton.setOnClickListener(this);
    }



    private void splashLogoClick() {
        startActivity(new Intent("oakland.edu.gameforachange.MainMenu"));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.splashLogo:
                splashLogoClick();
                break;
        }
    }
}
