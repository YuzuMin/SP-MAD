package com.sp.eeecourselist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CourseList extends AppCompatActivity {

    private ListView list;
    private List<Course> model =new ArrayList<Course>();
    //private ArrayAdapter<Course>adapter=null;
    private CourseAdapter adapter=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        list = findViewById(R.id.courses);
        adapter=new CourseAdapter();
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        String s90title="Aerospace Electronics (S90)";
        String s90desc="Highly recognised Aerospace Electronics diploma course to develop future-ready engineers for growing Singapore as a Smart Aviation Hub";
        String s90code="s90";
        String s53title="Computer Engineering (S53)";
        String s53desc="Comprehensive and highly sought-after Computing diploma course to develop engineers who can devise innovative solutions for a Smart Nation";
        String s53code="s53";
        String s99title="Electrical & Electronic Engineering (S99)";
        String s99desc="Well established Electrical & Electronic Engineering diploma course with the longest history of producing solution-minded engineers who are versatile and able to work across industries";
        String s99code="s99";
        String s42title="Engineering with Business (S42)";
        String s42desc="Most popular Engineering with Business diploma course in Singapore to nurture technopreneurs who can leverage technology to exploit business opportunities";
        String s42code="s42";
        String s45title="Energy Systems & Management (S45)";
        String s45desc="With effect from AY2019, Energy Systems & Management diploma course has been incorporated into the Diploma in Electrical & Electronic Engineering";
        String s45code="s45";
        String s33title="Engineering Systems (S33)";
        String s33desc="With effect from AY2019, Engineering Systems diploma course has been incorporated into the Diploma in Electrical & Electronic Engineering";
        String s33code="s33";



        Course s90=new Course();
        s90.setCourse_title(s90title);
        s90.setCourse_desc(s90desc);
        s90.setCourse_code(s90code);
        adapter.add(s90);

        Course s53=new Course();
        s53.setCourse_title(s53title);
        s53.setCourse_desc(s53desc);
        s53.setCourse_code(s53code);
        adapter.add(s53);

        Course s99=new Course();
        s99.setCourse_title(s99title);
        s99.setCourse_desc(s99desc);
        s99.setCourse_code(s99code);
        adapter.add(s99);

        Course s42=new Course();
        s42.setCourse_title(s42title);
        s42.setCourse_desc(s42desc);
        s42.setCourse_code(s42code);
        adapter.add(s42);

        Course s45=new Course();
        s45.setCourse_title(s45title);
        s45.setCourse_desc(s45desc);
        s45.setCourse_code(s45code);
        adapter.add(s45);

        Course s33=new Course();
        s33.setCourse_title(s33title);
        s33.setCourse_desc(s33desc);
        s33.setCourse_code(s33code);
        adapter.add(s33);



    }
    static class CourseHolder {
        private TextView courseName =null;
        private TextView details=null;
        private ImageView image =null;
        CourseHolder(View row){
            courseName = row.findViewById(R.id.coursename);
            details = row.findViewById(R.id.coursedetails);
            image = row.findViewById(R.id.courseimg);
        }
        void populateFrom(Course r){
            courseName.setText(r.getCourse_title());
            details.setText(r.getCourse_desc());

            if (r.getCourse_code().equals("s90")){
                image.setImageResource(R.drawable.dase);
            }
            else if(r.getCourse_code().equals("s53")){
                image.setImageResource(R.drawable.dcpe);
            }
            else if(r.getCourse_code().equals("s99")){
                image.setImageResource(R.drawable.deee);
            }
            else if(r.getCourse_code().equals("s42")){
                image.setImageResource(R.drawable.deb);
            }
            else if(r.getCourse_code().equals("s45")){
                image.setImageResource(R.drawable.desm);
            }
            else if(r.getCourse_code().equals("s33")){
                image.setImageResource(R.drawable.des);
            }
            else {
                image.setImageResource(R.drawable.desm);
            }

        }
    }

        class CourseAdapter extends ArrayAdapter<Course>{
        CourseAdapter(){
            super(CourseList.this, R.layout.row, model);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View row =convertView;
            CourseHolder holder;
            if (row ==null){
                LayoutInflater inflater =getLayoutInflater();
                row =inflater.inflate(R.layout.row,parent,false);
                holder=new CourseHolder(row);
                row.setTag(holder);
            }
            else{
                holder=(CourseHolder)row.getTag();
            }
            holder.populateFrom(model.get(position));
            return (row);
        }
    }


}