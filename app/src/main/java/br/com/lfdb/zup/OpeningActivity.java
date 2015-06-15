package br.com.lfdb.zup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.viewpagerindicator.IconPageIndicator;
import com.viewpagerindicator.PageIndicator;

import java.util.ArrayList;
import java.util.List;

import br.com.lfdb.zup.base.BaseActivity;
import br.com.lfdb.zup.service.FeatureService;
import br.com.lfdb.zup.service.LoginService;
import br.com.lfdb.zup.util.FontUtils;
import br.com.lfdb.zup.widget.ImageResourcePagerAdapter;

public class OpeningActivity extends BaseActivity {

    private static final int LOGIN_REQUEST = 1010;
    private static final int REGISTER_REQUEST = 1011;

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        findViewById(R.id.logo).setVisibility(getResources().getBoolean(R.bool.show_logo_header) ?
                View.VISIBLE : View.INVISIBLE);

        List<Integer> images = new ArrayList<>();
        for (int i = 1; ; i++) {
            int identifier = getResources().getIdentifier("tour_img" + i, "drawable", getPackageName());
            if (identifier != 0) images.add(identifier);
            else break;
        }

        ImageResourcePagerAdapter mAdapter = new ImageResourcePagerAdapter(getSupportFragmentManager(),
                images);

        ViewPager mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        PageIndicator mIndicator = (IconPageIndicator) findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);

        ((TextView) findViewById(R.id.labelNaoPossuiConta)).setTypeface(FontUtils.getRegular(this));
        ((TextView) findViewById(R.id.labelPossuiConta)).setTypeface(FontUtils.getRegular(this));

        TextView linkPular = (TextView) findViewById(R.id.linkPular);
        linkPular.setTypeface(FontUtils.getBold(this));
        linkPular.setOnClickListener(v -> {
            startActivity(new Intent(OpeningActivity.this, MainActivity.class));
            finish();
        });

        if (!FeatureService.getInstance(this).isExploreEnabled()) {
            linkPular.setVisibility(View.GONE);
        }

        TextView botaoCadastrar = (TextView) findViewById(R.id.botaoCadastrar);
        botaoCadastrar.setTypeface(FontUtils.getRegular(this));
        botaoCadastrar.setOnClickListener(v -> startActivityForResult(new Intent(OpeningActivity.this, CadastroActivity.class), REGISTER_REQUEST));

        TextView botaoLogin = (TextView) findViewById(R.id.botaoLogin);
        botaoLogin.setTypeface(FontUtils.getRegular(this));
        botaoLogin.setOnClickListener(v -> startActivityForResult(new Intent(OpeningActivity.this, LoginActivity.class), LOGIN_REQUEST));

        if (checkPlayServices()) {
            if (new LoginService().usuarioLogado(this)) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPlayServices();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == LOGIN_REQUEST || requestCode == REGISTER_REQUEST) && resultCode == Activity.RESULT_OK) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i("ZUP", "This device is not supported.");
                Toast.makeText(this, "Dispositivo não suportado", Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    protected String getScreenName() {
        return "Tela de Slides (abertura)";
    }
}
