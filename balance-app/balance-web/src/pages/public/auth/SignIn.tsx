import { Button } from "@/components/ui/button";
import { Form, FormField } from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { BalanceCard } from "@/components/widget/BalanceCard";
import { BalanceFormControl } from "@/components/widget/BalanceFormControl";
import { useAuth } from "@/hooks/useAuth";
import { cn } from "@/lib/utils";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { Link, useNavigate } from "react-router";
import { z } from "zod";

const SignInFormSchema = z.object({
    username: z.string().min(1, {message: "Email required!"}).email({message: "Invalid email!"}),
    password: z.string().min(5, {message: "Password required!"})
})

type SignInFormData = z.infer<typeof SignInFormSchema>;

export const SignIn = () => {
    const {user, signIn} = useAuth();

    const form = useForm<SignInFormData>({
        resolver: zodResolver(SignInFormSchema),
        defaultValues: {
            username: "",
            password: ""
        }
    });

    const navigate = useNavigate();

    const handleSubmitSignInForm = async (data: SignInFormData) => {
        signIn(data);
        if(user?.setUpSetting) {
            navigate(`/balance/app/${user.admin ? "admin" : "user"}`);
        } else {
            navigate(`/balance/app/setup`);
        }
    };

  return (
    <Form {...form}>
        <form onSubmit={form.handleSubmit(handleSubmitSignInForm)}>
            <BalanceCard cardSize="w-[400px]"
                        cardTitle="Welcome back"
                        cardDescription="Sign in to your account"
                        content={
                        <>
                            <FormField control={form.control} name="username"
                                render={({field}) => (
                                    <BalanceFormControl label="Email" labelFor="username">
                                        <Input type="email" id="email" {...field} placeholder="someone@example.com" />
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
                        </>
                        }
                        footer={
                            <div className={cn("flex flex-col gap-1 w-full")}>
                                <Button type="submit" className={cn("w-full rounded-full")}>
                                    Sign In
                                </Button>
                                <div className={cn("flex gap-1 items-center justify-center")}>
                                    <span className={cn("text-sm text-pretty")}>Don't have an account?</span>
                                    <Link to="/auth/signup">
                                        <Button className={cn("p-0 text-teal-700")} variant={"link"}>Sign Up</Button>
                                    </Link>
                                </div>
                            </div>
                        }
            />
        </form>
    </Form>
  )
}
