package com.example.projekadalahini;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TrainScheduleActivity extends AppCompatActivity {

    ListView lv;
    String[] title={
            "Pantai Maju",
            "The Violin",
            "Melody Golf 2",
            "Seberang Food Street",
            "The Piano",
            "Fresh Market PIK",
            "PIK Avenue",
            "Ruko Cordoba 1",
            "Waterboom Jakarta 1",
            "RS PIK",
            "Mandara Permai 6",
            "Mediterania Boulevard",
            "Galeri Niaga Mediterania 2",
            "SMAK Penabur Enam 1",
            "Muara Angke",
            "Baywalk 2",
            "SD Diakona 1",
            "Waduk Pluit",
            "Landmark Pluit Auto Plaza",
            "Pakin",
            "Gedong Panjang",
            "Simpang Kopi Roa Malaka",
            "Jembatan Kota Intan",
            "Museum Fatahillah",
            "Kota",
            "Glodok",
            "Olimo",
            "Sawah Besar",
            "Mangga Besar",
            "Harmoni",
            "Monas",
            "Balai Kota 1",
            "Balai Kota"
    };

    String[] title2={
            "2 pedo1",
            "2 pedo2",
            "2 pedo3",
            "2 pedo4"
    };

    String[] title3={
            "3 pedo1",
            "3 pedo2",
            "3 pedo3",
            "3 pedo4"
    };

    String[] title4={
            "4 pedo1",
            "4 pedo2",
            "4 pedo3",
            "4 pedo4"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_schedule);

        lv = findViewById(R.id.listView_train);

        Spinner dropdown = findViewById(R.id.dropdown_train);

        List<String> items = new ArrayList<>();

        final int[] selected = {1};

        TextView route = findViewById(R.id.route_txt_train);

        getSupportActionBar().hide();

        items.add("1A. Pantai Maju - Balai Kota");
        items.add("1B. Stasiun Palmerah - Tosari");
        items.add("1C. Pesanggrahan - Blok M");
        items.add("1E. Pondok Labu - Blok M");
        items.add("1F, Stasiun Palmerah - Bundaran Senayan");
        items.add("1H. Tanah Abang - Stasiun Gondangdia");
        items.add("1K. Cibubur - Blok M");
        items.add("1M. Meruya - Blok M");
        items.add("1N. Blok M - Tanah Abang");
        items.add("1P, Senen - Blok M");
        items.add("1Q. Rempoa - Blok M");
        items.add("1R. Tanah Abang - Senen");
        items.add("2A. Pulo gadung 1 - Rawa Buaya");
        items.add("2B. harapan Indah - Asmi");
        items.add("2E. Rusun Rawa Bebek - Pakin");
        items.add("2F. Rusun Cakung Barat Albo - Pulo Gadung");
        items.add("2H. Rusun Jati Rawasari - Senen");
        items.add("3A. Rusun Pesakih - Kalideres");
        items.add("3B. Rusun Flamboyan - Kalideres");
        items.add("3C. Penjaringan - Rusun Kapuk Muara");
        items.add("3E. Sentraland Cengkarenf - Puri Kembangan");
        items.add("3F. Gelora Bung Karno - Kalideres");
        items.add("4B. Stasiun Manggarai - Universitas Indonesia");
        items.add("4D. Pulo gadung 2 - Kuningan");
        items.add("4E. Rusun Jatinegara Kaum - Pulo Gadung");
        items.add("4F. Pinang Ranti - Pulo gadung");
        items.add("5. Kampung Melayu - Ancol");
        items.add("5D. Ancol - PGC 1");
        items.add("5F. Kampung Melayu - Tanah Abang");
        items.add("5M. Kampung Melayu - Tanah Abang Via Cikini");
        items.add("5N. Kampung Melayu - Ragunan");
        items.add("6. Ragunan - Dukun Atas 2");
        items.add("6C. Stasiun Tebet - Karet Via Patra Kuningan");
        items.add("6H. Lebak Bulus - Senen");
        items.add("6N. Ragunan - Blok M Via Kemang");
        items.add("6P. Kuningan - Cibubur");
        items.add("6Q. Epicentrum - Kota Kasablanka");
        items.add("7. Kampung Rambutan - Kampung Melayu");
        items.add("7A. kampung Rambutan - Lebak Bulus");
        items.add("7B. Kampung Rambutan - Blok M");
        items.add("7C. BKN - Cibubur");
        items.add("7E. kampung Rambutan - Ragunan");
        items.add("7P. Pondok Kelapa - BKN");
        items.add("8C. Kebayotan Lama - tanah Abang");
        items.add("8D. Blok M - Joglo");
        items.add("8E. Bintaro - Blok M");
        items.add("9. Pinang Ranti - Pluit");
        items.add("9D. tanah Abang - Pasar Minggu");
        items.add("9E. Jelambar - Kebayoran Lama");
        items.add("9F. Pluit - Rusun Tambora");
        items.add("10. Tanhung Priok - PGC 2");
        items.add("10B. Penas Kalimalang - Rusun Cipinang Besar Selatan");
        items.add("10D. Kampung Rambutan - Tanjung Priok");
        items.add("10H. Tanjung Priok - Blok M");
        items.add("10K. Tanjung Priok - Senen Via Taman BMW");
        items.add("11B. Rusun Rawa Bebek - Penggilingan");
        items.add("11C. Rusun Pulo Gebang - Rusun Rawa Bebek");
        items.add("11D. Pulo gadung - Pulo Gebang Via PIK");
        items.add("11K. Penggilingan - Rusun Komarudin");
        items.add("11M. Rusun Rawa Bebek - Bukit Duri");
        items.add("11Q. Kampung Melayu - Pulo Gebang Via Bkt");
        items.add("11R. Rusun Cakung Km2 - Bukit Duri");
        items.add("12. Penjaringan - Sunter Boulevard Barat");
        items.add("12A. Pelabuhan Kali Adem - Kota");
        items.add("12B. Senen - Pluit");
        items.add("12C. Penjaringan - Rusun Waduk Pluit");
        items.add("12F. Rusun Waduk Pluit - Rusun Marunda");
        items.add("12H. Penjaringan - Rusun Penjaringan");
        items.add("12P. Stasiun LRT Pengangsaan Dua - Jakarta Internasional Stadium");
        items.add("13. Cbd Ciledug - Tendean");
        items.add("13C. Puri Beta - Dukuh Atas");
        items.add("14. Jakarta Internasional Stadium - Senen");

        items.add("JAK-01. Tanjung Priuk - Plumpang");
        items.add("JAK-02. Kampung Melayu - Duren Sawit");
        items.add("JAK-03. Andara - Lebak Bulus");
        items.add("JAK-04. Grogol - Tubagus Angke");
        items.add("JAK-05. Kampar - Rorotan");
        items.add("JAK-06. Kampung Rambutan - Pondok Gede");
        items.add("JAK-07. Grogol - Tanah Abang");
        items.add("JAK-08. Roxy - Benhil");
        items.add("JAK-09. Roxy Mas - Karet");
        items.add("JAK-10. Tanah Abang - Kota");
        items.add("JAK-11. Tanah Abang - Kebayoran Lama");
        items.add("JAK-12. Tanah Abang - Kebayoran Lama Via Pos Pengumben");
        items.add("JAK-13. Taman Kota Intan - Tanah Abang Via Jembantan Lima");
        items.add("JAK-14. Tanah Abang - Meruya");
        items.add("JAK-15. Tanjung Priok - Bulak Turi");
        items.add("JAK-16. Cililitan - Condet");
        items.add("JAK-17. Pulo gadung - Senen");
        items.add("JAK-18. Kalibata - Kuningan");
        items.add("JAK-19. Pinang Ranti - Setu");
        items.add("JAK-20. Lubang Buaya - Cawang Uki");
        items.add("JAK-21. Cililitan - Dwikora");
        items.add("JAK-22. Penas Kalimatang - Dwikora");
        items.add("JAK-24. Pulo Gadung - Senen Via Kelapa Gading");
        items.add("JAK-25. kalisari - Pasar Rebo");
        items.add("JAK-26. Rawamangun - Duren Sawit");
        items.add("JAK-27. Pulo Gebang - Rorotan");
        items.add("JAK-28. Taman Wiladatika - KP Rambutan");
        items.add("JAK-29. Tanjung Priok - Sukapura");
        items.add("JAK-30. Meruya - Grogol Via Roxy");
        items.add("JAK-31. Blok M - Pondok labu");
        items.add("JAK-32. Petukangan - Lebak Bulus");
        items.add("JAK-33. Pulo Gadung - Kota");
        items.add("JAK-34. Rawamangun - klender");
        items.add("JAK-35. Rawamangun - Pangkalan Jati");
        items.add("JAK-36. Cilangkap - Cililitan");
        items.add("JAK-37. Cililitan - Condet Via Kayu Manis");
        items.add("JAK-38. Bulak Ringin - Kampung Rambutan");
        items.add("JAK-39. Kalimalang - Duren Sawit");
        items.add("JAK-40. Harapan baru - Pulo Gebang Via Rawa Kuning");
        items.add("JAK-41. Kampung melayu - Pulo Gadung");
        items.add("JAK-42. Kampung Melayu - Pondok Kelapa");
        items.add("JAK-43. Cililitan - Tongtek");
        items.add("JAK-44. Andara - Stasiun Universitas Pancasila");
        items.add("JAK-45. Ragunan - Lebak Bulus");
        items.add("JAK-46. Pasar Minggu - Jagakarsa");
        items.add("JAK-47. Pasar Minggu - Ciganjur via Kb Ragunan");
        items.add("JAK-49. Lebak Bulus - Cipulir");
        items.add("JAK-50. kalideres - Puri Kembangan");
        items.add("JAK-51. Taman Kota - Budi Luhur");
        items.add("JAK-53. Grogol - Pos Pengumben Via Slipi");
        items.add("JAK-54. Grogol - Benhil");
        items.add("JAK-56. Grogol - Srengseng");
        items.add("JAK-58. Cilincing - Rorotan");
        items.add("JAK-59. Rawamangun - Rawa Sengan");
        items.add("JAK-60. Kelapa Gading - Rusun Kemayoran");
        items.add("JAK-61. Pulo Gadung - Cempaka Putih Via Kelapa Gading");
        items.add("JAK-64. Lenteng Agung - Aseli");
        items.add("JAK-71. Kampung Rambutan - Pinang ranti");
        items.add("JAK-72. Kampung Rambutan - Pasar Rebo Via Poncol");
        items.add("JAK-73. Jambore Cibubur - Pasar Rebo");
        items.add("JAK-74. Rawamangun - Cipinang Muara");
        items.add("JAK-75. Cililitan - Kampung Pulo");
        items.add("JAK-77. Tanjung Priok - Jembatan Item");
        items.add("JAK-80. Rawa Buaya - Rawa Kompeni");
        items.add("JAK-84. Kampung Melayu - Kapin");
        items.add("JAK-85. Bintara - Cipinang Indah");
        items.add("JAK-88. Terminal Tanjung Priok - Ancol Barat");
        items.add("JAK-112. Pulo Gadung - Tanah Merah");
        items.add("JAK-117. Tanah Merdeka - Tanjung Priok");

        items.add("B13. Bekasi Barat - Blok M");
        items.add("B14. Bekasi Barat - Kuningan");
        items.add("D11. Depok - BKN");
        items.add("D21. Universitas Indonesia - Lebak Bulus");
        items.add("S12. Bsd Serpong - Fatmawati");
        items.add("S22. Ciputat - kampung Rambutan");
        items.add("S31. Bintaro - Fatmawati");
        items.add("T12. Juanda - Poris Plawad");
        items.add("M1. Blok M - Kota");
        items.add("M2. Pulo Gadung 2 - Harmoni");
        items.add("M3. kalideres - Pasar Baru");
        items.add("M5. Ancol - PGC 2");
        items.add("M6. Harmoni - Ragunan");
        items.add("M7. Harmoni - Kampung Rambutan");
        items.add("M8. Harmoni - Lebak Bulus");
        items.add("M9. Pinang Ranti - Pluit");
        items.add("M10. PGC 2 - Tanjung Priok");
        items.add("M11. Pulo Gebang - Pulo Gadung 1");



        ArrayAdapter<String> spinAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item, items);
        spinAdapter.setDropDownViewResource(R.layout.dropdown_item);
        dropdown.setAdapter(spinAdapter);

        dropdown.setSelection(0);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                route.setText(items.get(position));
                MainAdapter2 adapter;
                switch (position){
                    case 0:
                        dropdown.setSelection(0);
                        adapter = new MainAdapter2(TrainScheduleActivity.this, title);
                        lv.setAdapter(adapter);
                        break;
                    case 1:
                        dropdown.setSelection(1);
                        adapter = new MainAdapter2(TrainScheduleActivity.this, title2);
                        lv.setAdapter(adapter);
                        break;
                    case 2:
                        dropdown.setSelection(2);
                        adapter = new MainAdapter2(TrainScheduleActivity.this, title3);
                        lv.setAdapter(adapter);
                        break;
                    case 3:
                        dropdown.setSelection(3);
                        adapter = new MainAdapter2(TrainScheduleActivity.this, title4);
                        lv.setAdapter(adapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), ""+position, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public static class MainAdapter2 extends BaseAdapter {

        TrainScheduleActivity mainActivity;
        String[] title;

        public MainAdapter2(TrainScheduleActivity mainActivity, String[] title){
            this.mainActivity = mainActivity;
            this.title = title;
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = LayoutInflater.from(mainActivity).inflate(R.layout.new_item_layout, parent, false);

            TextView textView = convertView.findViewById(R.id.textview);
            RelativeLayout ll_bg = convertView.findViewById(R.id.ll_bg);

            textView.setText(title[position]);
            ll_bg.setBackground(ContextCompat.getDrawable(mainActivity, R.drawable.rectangle_35_shape));

            return convertView;
        }
    }
}