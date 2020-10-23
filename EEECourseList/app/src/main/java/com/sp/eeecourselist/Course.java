package com.sp.eeecourselist;

public class Course {
    private String course_desc="";
    private String course_title="";
    private String course_code="";

    public String getCourse_desc(){return course_desc;}
    public void setCourse_desc(String course_desc){this.course_desc=course_desc;}

    public String getCourse_title(){return course_title;}
    public void setCourse_title(String course_title){this.course_title=course_title;}

    public String getCourse_code(){return course_code;}
    public void setCourse_code(String course_code){this.course_code=course_code;}

    @Override
    public String toString() {return (getCourse_desc());}

}
