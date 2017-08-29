# School Compiler
✦✦✦✦✦✦✦✦✦✦✦✦

#### School Compiler August 25, 2017
✦✦✦✦✦✦✦✦✦✦✦✦

#### By Kira Loo
✦✦✦✦✦✦✦✦✦✦✦✦

## Description
✦✦✦✦✦✦✦✦✦✦✦✦

_This website allows the user to create a new School and Student for an API database, you can add students to your school or change your school's name and other details._

## Setup/Installation Requirements
✦✦✦✦✦✦✦✦✦✦✦✦

You will need Gradle installed on your device.

* ->✦ Clone the repo from git hub.
* ->✦ Open in your favorite editor
* ->✦ Checkout all the good stuff !

## Specifications
✦✦✦✦✦✦✦✦✦✦✦✦

| ✦ Behavior      | ✦ Example Input      | ✦ Example Output       |
| Add A Type of School | schoolType: "Private" | "Private" |
| Add A Course | courseTitle: "Math" courseDescription: "basic math." | "Math", "basic math." |
| Add Student | name: "jim", age: "15", gender: "male", schoolType: "Private", grade: "10" | "jim", "15", "male", "Private", "10" |

## EASY JSON/POSTMAN INPUTS 
✦✦✦✦✦✦✦✦✦✦✦✦


SCHOOL

localhost:4567/school/new
{
"schoolType": "Private"
}

STUDENT

localhost:4567/students/new
{
  "name" : "Bob",
  "age" : "15",
  "gender": "male",
  "schoolType" : "Highschool",
  "grade" : "10"
}

COURSE

localhost:4567/courses/new
{
  "courseTitle": "Math",
  "courseDescription": "Basic Math Class"
}

## Known Bugs
✦✦✦✦✦✦✦✦✦✦✦✦

_none known._

## Technologies Used
✦✦✦✦✦✦✦✦✦✦✦✦

* _IntelliJ_
* _Java_
* _HandleBars_
* _Spark_
* _PostMan_


### License
✦✦✦✦✦✦✦✦✦✦✦✦
Copyright &copy; 2017 _Kira Loo_
