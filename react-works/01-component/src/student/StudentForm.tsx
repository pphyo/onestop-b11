import { useState } from "react";
import { Gender, Student } from "../model/model";

type StudentFormProps = {
    onSave: (student: Student) => void;
}

const StudentForm: React.FC<StudentFormProps> = ({onSave}) => {

    const [form, setForm] = useState<Student>({
        id: 0,
        name: "",
        email: "",
        dob: new Date(),
        gender: "Male",
        subjects: []
    });
    // const nameRef = useRef<HTMLInputElement>(null);
    // const emailRef = useRef<HTMLInputElement>(null);
    // const dobRef = useRef<HTMLInputElement>(null);
    const [gender, setGender] = useState<Gender>("Male");
    const subjects: string[] = [];

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setForm(prev => ({...prev, [name]: value}))
    }

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        const studentForSave: Student = {
            id: form.id,
            name: form.name,
            email: form.email,
            dob: form.dob,
            gender: form.gender,
            subjects: form.subjects
        };
        onSave(studentForSave);
    };

    function handleCheckedForSubject(value: string): boolean | undefined {
        return subjects.find(s => s === value) ? true : undefined;
    }

    const handleChangedForSubject = (e: React.ChangeEvent<HTMLInputElement>) => {
        const value = e.target.value;
        const exist = subjects.find(s => s === value);
        if(exist) {
            const index = subjects.findIndex(s => s === value);
            subjects.splice(index, 1);
        } else {
            subjects.push(value);
        }

        handleCheckedForSubject(value);
        setForm(prev => ({...prev, subjects}))
    }

    return (
    <form action="#" className="flex gap-2" onSubmit={handleSubmit}>
        <div className="flex flex-col gap-2">
            <h1 className="text-3xl font-semibold text-pretty leading-none">Student Form</h1>
            <div>
                <label htmlFor="name">Name</label> <br />
                <input onChange={handleChange} type="text" id="name" name="name" placeholder="Enter name" />
            </div>

            <div>
                <label htmlFor="email">Email</label> <br />
                <input onChange={handleChange} type="email" id="email" name="email" placeholder="Enter email" />
            </div>

            <div>
                <label htmlFor="dob">Date of Birth</label> <br />
                <input onChange={handleChange} type="date" id="dob" name="dob" placeholder="Enter dob" />
            </div>

            <div>
                <label>Gender</label> <br />
                <input checked={gender === "Male"} onChange={(e) => {
                    setGender(e.target.value as Gender);
                    handleChange(e);
                }} type="radio" id="male" name="gender" value="Male" className="mr-2" />
                <label htmlFor="male" className="mr-4">Male</label>

                <input checked={gender === "Female"} onChange={(e) => {
                    setGender(e.target.value as Gender);
                    handleChange(e);
                }} type="radio" id="female" name="gender" value="Female" className="mr-2" />
                <label htmlFor="female" className="mr-4">Female</label>

                <input checked={gender === "Other"} onChange={(e) => {
                    setGender(e.target.value as Gender);
                    handleChange(e);
                }} type="radio" id="other" name="gender" value="Other" className="mr-2" />
                <label htmlFor="other">Other</label>
            </div>

            <div>
                <label>Subjects</label> <br />
                <input checked={handleCheckedForSubject("Myanmar")} onChange={handleChangedForSubject} type="checkbox" id="myanmar" name="myanmar" value="Myanmar" className="mr-2" />
                <label htmlFor="myanmar" className="mr-4">Myanmar</label>

                <input checked={handleCheckedForSubject("English")} onChange={handleChangedForSubject} type="checkbox" id="english" name="english" value="English" className="mr-2" />
                <label htmlFor="english" className="mr-4">English</label>

                <input checked={handleCheckedForSubject("Maths")} onChange={handleChangedForSubject} type="checkbox" id="maths" name="maths" value="Maths" className="mr-2" />
                <label htmlFor="maths" className="mr-4">Maths</label>

                <input checked={handleCheckedForSubject("Geology")} onChange={handleChangedForSubject} type="checkbox" id="geology" name="geology" value="Geology" className="mr-2" />
                <label htmlFor="geology" className="mr-4">Geology</label>

                <input checked={handleCheckedForSubject("History")} onChange={handleChangedForSubject} type="checkbox" id="history" name="history" value="History" className="mr-2" />
                <label htmlFor="history" className="mr-4">History</label>

                <input checked={handleCheckedForSubject("Science")} onChange={handleChangedForSubject} type="checkbox" id="science" name="science" value="Science" className="mr-2" />
                <label htmlFor="science" className="mr-4">Science</label>

                <input checked={handleCheckedForSubject("Music")} onChange={handleChangedForSubject} type="checkbox" id="music" name="music" value="Music" className="mr-2" />
                <label htmlFor="music" className="mr-4">Music</label>

                <input checked={handleCheckedForSubject("Sport")} onChange={handleChangedForSubject} type="checkbox" id="sport" name="sport" value="Sport" className="mr-2" />
                <label htmlFor="sport" className="mr-4">Sport</label>

                <input checked={handleCheckedForSubject("Korean")} onChange={handleChangedForSubject} type="checkbox" id="korean" name="korean" value="Korean" className="mr-2" />
                <label htmlFor="korean" className="mr-4">Korean</label>
            </div>

            <div>
                <button type="submit">Save</button>
            </div>
        </div>
        <div className="flex-1/3">
            <h4>Show error</h4>
        </div>
    </form>
    );
}

export default StudentForm;