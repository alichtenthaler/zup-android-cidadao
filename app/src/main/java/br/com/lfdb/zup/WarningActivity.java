package br.com.lfdb.zup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import br.com.lfdb.zup.service.LoginService;
import br.com.lfdb.zup.util.FontUtils;

public class WarningActivity extends FragmentActivity {

    private static final int LOGIN_REQUEST = 1010;
    private static final int REGISTER_REQUEST = 1011;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning);

        ((TextView) findViewById(R.id.labelNaoPossuiConta)).setTypeface(FontUtils.getRegular(this));
        ((TextView) findViewById(R.id.labelPossuiConta)).setTypeface(FontUtils.getRegular(this));
        ((TextView) findViewById(R.id.mensagem)).setTypeface(FontUtils.getRegular(this));

        TextView linkCancelar = (TextView) findViewById(R.id.linkCancelar);
        linkCancelar.setTypeface(FontUtils.getBold(this));
        linkCancelar.setOnClickListener(v -> finish());

        TextView botaoCadastrar = (TextView) findViewById(R.id.botaoCadastrar);
        botaoCadastrar.setTypeface(FontUtils.getRegular(this));
        botaoCadastrar.setOnClickListener(v -> startActivityForResult(new Intent(WarningActivity.this, CadastroActivity.class), REGISTER_REQUEST));

        TextView botaoLogin = (TextView) findViewById(R.id.botaoLogin);
        botaoLogin.setTypeface(FontUtils.getRegular(this));
        botaoLogin.setOnClickListener(v -> startActivityForResult(new Intent(WarningActivity.this, LoginActivity.class), LOGIN_REQUEST));

        if (new LoginService().usuarioLogado(this)) {
            setResult(Activity.RESULT_OK);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == LOGIN_REQUEST || requestCode == REGISTER_REQUEST) && resultCode == Activity.RESULT_OK) {
            setResult(Activity.RESULT_OK);
            finish();
        }
    }
}