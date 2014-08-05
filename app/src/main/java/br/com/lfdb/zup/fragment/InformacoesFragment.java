package br.com.lfdb.zup.fragment;

import java.util.Locale;
import java.util.Map;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.lfdb.zup.R;
import br.com.lfdb.zup.util.FontUtils;

public class InformacoesFragment extends Fragment {

	private LinearLayout layout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_informacoes, container, false);
		
		layout = (LinearLayout) view.findViewById(R.id.conteudo);
		
		ViewPager mPager = (ViewPager) view.findViewById(R.id.pager);
		mPager.setVisibility(View.GONE);

		return view;
	}
	
	@SuppressLint("DefaultLocale")
	private void addView(String label, String content) {
		final float scale = getActivity().getResources().getDisplayMetrics().density;
		
		TextView tvLabel = new TextView(getActivity());
		tvLabel.setText(label.toUpperCase(Locale.US));
		tvLabel.setTextColor(Color.rgb(0x33, 0x33, 0x33));
		tvLabel.setTypeface(FontUtils.getBold(getActivity()));
		tvLabel.setPadding((int) (15 * scale + 0.5f), (int) (5 * scale + 0.5f), 0, 0);
		layout.addView(tvLabel);
		
		TextView tvContent = new TextView(getActivity());
		tvContent.setTypeface(FontUtils.getLight(getActivity()));
		tvContent.setTextColor(Color.rgb(0x33, 0x33, 0x33));
		tvContent.setText(content);
		tvContent.setPadding((int) (15 * scale + 0.5f), 0, (int) (15 * scale + 0.5f), (int) (15 * scale + 0.5f));
		layout.addView(tvContent);
	}
	
	public void setDados(String sectionTitle, Map<String, String> camposDinamicos) {
        if (!camposDinamicos.isEmpty()) {

            if (sectionTitle != null && !sectionTitle.trim().isEmpty()) adicionarSubtitulo(sectionTitle);

            for (String key : camposDinamicos.keySet()) {
                addView(key, camposDinamicos.get(key));
            }
        }
	}

    private void adicionarSubtitulo(String subtitulo) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.inventory_section_title, null);
        ((TextView) view.findViewById(R.id.title)).setText(subtitulo);
        layout.addView(view);
    }
}
