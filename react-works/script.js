const studentForm = document.querySelector("form");

const onSubmitForm = (e) => {
    e.preventDefault();
    const errors = [];

    const nameInput = document.querySelector("#name");
    if(nameInput.required && !nameInput.value) {
        errors.push("Name required!");
    }

    const emailInput = document.querySelector("#email");
    if(emailInput.required && !emailInput.value) {
        errors.push("Email required!");
    }

    const dobInput = document.querySelector("#dob");
    if(dobInput.required && !dobInput.value) {
        errors.push("Dob required!");
    }

    const phoneInput = document.querySelector("#phone");
    const genderInput = document.querySelector("#male");
    const myanmar = document.querySelector("#myanmar");
    const english = document.querySelector("#english");
    const maths = document.querySelector("#maths");

    const subjects = [];
    if(myanmar.checked) {
        subjects.push(myanmar.value);
    }

    if(english.checked) {
        subjects.push(english.value);
    }

    if(maths.checked) {
        subjects.push(maths.value);
    }
    const student = {
        name: nameInput.value,
        email: emailInput.value,
        phone: phoneInput.value ? phoneInput.value : null,
        dob: dobInput.value,
        gender: genderInput.checked ? "Male" : "Female",
        subjects
    }
    console.log("Student", student)
};

const submitButton = document.querySelector("#submitButton");
submitButton.addEventListener("click", (e) => {
    onSubmitForm(e);
})