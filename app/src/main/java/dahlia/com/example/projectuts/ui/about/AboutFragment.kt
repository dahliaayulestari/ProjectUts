package dahlia.com.example.projectuts.ui.about

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import dahlia.com.example.projectuts.R

class AboutFragment : Fragment(R.layout.fragment_about) {

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // Nomor HP
        view.findViewById<TextView>(R.id.tvNoHp).text =
            "Isi nomor HP kamu"

        // Program Studi
        view.findViewById<TextView>(R.id.tvProdi).text =
            "Isi program studi kamu"

        // Jurusan
        view.findViewById<TextView>(R.id.tvJurusan).text =
            "Isi jurusan kamu"

        // Perguruan Tinggi
        view.findViewById<TextView>(R.id.tvPerguruanTinggi).text =
            "Politeknik Negeri Bengkalis"
    }
}
