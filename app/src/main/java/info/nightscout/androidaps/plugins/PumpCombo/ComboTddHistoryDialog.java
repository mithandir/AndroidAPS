package info.nightscout.androidaps.plugins.PumpCombo;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.List;

import de.jotomo.ruffy.spi.history.Tdd;
import info.nightscout.androidaps.R;

/**
 * Created by adrian on 17/08/17.
 */

public class ComboTddHistoryDialog extends DialogFragment {
    private static Logger log = LoggerFactory.getLogger(ComboTddHistoryDialog.class);

    private TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.combo_tdd_history_fragment, container, false);
        text = (TextView) layout.findViewById(R.id.combo_tdd_history_text);
        List<Tdd> tdds = ComboPlugin.getPlugin().getPump().history.tddHistory;
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM");
        if (tdds.isEmpty()) {
            text.setText("No TDDs. To retrieve the TDD history from the pump, long press the Refresh button.");
        } else {
            for (Tdd tdd : tdds) {
                sb.append(simpleDateFormat.format(tdd.timestamp));
                sb.append("  ");
                sb.append(tdd.total);
                sb.append("\n");
            }
            text.setText(sb.toString());
        }
        return layout;
    }
}
