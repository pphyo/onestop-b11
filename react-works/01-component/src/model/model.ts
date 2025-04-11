export type Gender = "Male" | "Female" | "Other"

export interface Student {
    id: number;
    name: string;
    dob: Date;
    gender: Gender;
    email: string;
    subjects: string[];
}

export type StudentDto = Omit<Student, "id">;

export type Students = Student[];

export const DUMMY_DATA: Students = [
    {
        id: 1,
        name: "Kyaw Naing",
        dob: new Date(2000, 2, 20),
        gender: "Male",
        email: "kyawnaing@yahoo.com",
        subjects: [
            "English", "Maths", "Geology",
            "History", "Music", "Sport"
        ]
    },
    {
        id: 2,
        name: "Thidar Nway Oo",
        dob: new Date(2002, 4, 11),
        gender: "Female",
        email: "thidar@nwayoo.com",
        subjects: [
            "English", "Maths", "Geology",
            "History", "Music", "Korean"
        ]
    },
    {
        id: 3,
        name: "Satt D Tun",
        dob: new Date(2005, 10, 4),
        gender: "Male",
        email: "sattdtun@gmail.com",
        subjects: [
            "Myanmar", "English", "Maths", "Geology",
            "History", "Science"
        ]
    }
]