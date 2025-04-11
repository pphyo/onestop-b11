import { useState } from "react";
import { Student, Students } from "../model/model";
import StudentForm from "./StudentForm"
import { getStudentService } from "../service/student.service";

const StudentManagement = () => {
    const studentService = getStudentService();
    const [students, setStudents] = useState<Students>([]);

    const onAddStudent = (student: Student) => {
        const resp = studentService.save(student);
        if(resp) {
            setStudents(studentService.getAll());
        }
    };

    return (
        <>
        <StudentForm onSave={onAddStudent} />
        <ul>
            {
            students.map(stu => (
                <li key={stu.id}>{ stu.name + ": " + stu.gender }</li>
            ))
        }
        </ul>
        </>
    )
}

export default StudentManagement;