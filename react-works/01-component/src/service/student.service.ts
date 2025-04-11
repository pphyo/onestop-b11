import { DUMMY_DATA, Student, StudentDto, Students } from "../model/model";

class StudentService {

    private static instance: StudentService;
    private students: Students = [...DUMMY_DATA];

    private constructor(){}

    static getInstance(): StudentService {
        if(!StudentService.instance) {
            StudentService.instance = new StudentService();
        }
        return StudentService.instance;
    }

    save(data: Student): Student | undefined {
        const {id, ...value} = data;
        return id ? this.update(id, value) : this.create(value);
    }

    private create(value: StudentDto): Student | undefined {
        const result: Student = {
            id: this.students[this.students.length - 1].id + 1,
            ...value
        }
        const changed = this.students.push(result);
        return changed ? result : undefined;
    }

    private update(id: number, value: StudentDto): Student {
        const existedStudent = this.students.find(s => s.id === id);
        if(!existedStudent) {
            throw new Error("No student found with given id.");
        }

        const index = this.students.findIndex(s => s.id === id);
        this.students[index] = {
            id,
            ...value
        }
        return this.students[index];
    }

    getAll(): Students {
        return [...this.students];
    }

}

export const getStudentService = () => StudentService.getInstance();