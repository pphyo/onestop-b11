import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { cn } from "@/lib/utils";
import { Button } from "@/components/ui/button";
import { Link } from "react-router";
import { Form, FormField } from "@/components/ui/form";
import { BalanceFormControl } from "@/components/widget/BalanceFormControl";
import { Input } from "@/components/ui/input";
import { BalanceCard } from "@/components/widget/BalanceCard";
import { getAuthService } from "@/model/service/auth.service";
import { useEffect } from "react";

const SingUpFormSchema = z.object({
    name: z.string().min(1, {message: "Name required!"}),
    username: z.string().min(1, {message: "Email required!"})
                .email({message: "Invalid email"}),
    password: z.string().min(5, {message: "Password must be at least 5 characters!"}),
    confirm: z.string().min(1, {message: "Confirm password!"})
}).refine(form => form.password === form.confirm, {
    message: "Password did not match!",
    path: ["confirm"]
})

type SignUpFormData = z.infer<typeof SingUpFormSchema>;

export const SignUp = () => {
    const authService = getAuthService();

    const form = useForm<SignUpFormData>({
        resolver: zodResolver(SingUpFormSchema),
        defaultValues: {
            name: "",
            username: "",
            password: "",
            confirm: ""
        }
    })

    const handleSubmitSignUpForm = async (data: SignUpFormData) => {
        const {confirm, ...dto} = data;
        const result: boolean =  await authService.signUp(dto);

        if(result) {
            console.info("Result", result);
        }
    };

    useEffect(() => {
        
    })

  return (
    <Form {...form}>
        <form onSubmit={form.handleSubmit(handleSubmitSignUpForm)}>
            <BalanceCard cardSize="w-[400px]"
                        cardTitle="Create an account"
                        cardDescription="Balance user account"
                        content={
                        <>
                            <FormField control={form.control} name="name"
                                render={({field}) => (
                                    <BalanceFormControl label="Name" labelFor="name">
                                        <Input type="text" id="name" {...field} placeholder="Eg. John" />
                                    </BalanceFormControl>
                                )}
                            />

                            <FormField control={form.control} name="username"
                                render={({field}) => (
                                    <BalanceFormControl label="Email" labelFor="username">
                                        <Input type="email" id="username" {...field} placeholder="someone@example.com" />
                                    </BalanceFormControl>
                                )}
                            />

                            <FormField control={form.control} name="password"
                                render={({field}) => (
                                    <BalanceFormControl label="Password" labelFor="password">
                                        <Input type="password" id="password" {...field} placeholder="Enter password" />
                                    </BalanceFormControl>
                                )}
                            />

                            <FormField control={form.control} name="confirm"
                                render={({field}) => (
                                    <BalanceFormControl label="Confirm" labelFor="confirm">
                                        <Input type="password" id="confirm" {...field} placeholder="Confirm password" />
                                    </BalanceFormControl>
                                )}
                            />
                        </>
                        }
                        footer={
                            <div className={cn("flex flex-col gap-1 w-full")}>
                                <Button type="submit" className={cn("rounded-full w-full")}>Sign Up</Button>
                                <div className={cn("flex gap-1 items-center justify-center")}>
                                    <span className={cn("text-sm text-pretty")}>Already have an account?</span>
                                    <Link to="/auth/signin">
                                        <Button className={cn("p-0 text-teal-700")} variant={"link"}>Sign In</Button>
                                    </Link>
                                </div>
                            </div>
                        }
            />
        </form>
    </Form>
  )
}
