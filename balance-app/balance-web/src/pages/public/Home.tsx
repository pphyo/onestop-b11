import { Button } from '@/components/ui/button';
import { cn } from '@/lib/utils';
import { useEffect, useRef } from 'react';
import { Link } from 'react-router';
import Typed from "typed.js";

export const Home = () => {

    const texts = [
        "Manage Your Money with Ease",
        "Track Every Money You Spend",
        "Save More, Spend Smarter",
        "Your Financial Freedom Starts Here",
        "Budget Better Every Day",
    ];

    const element = useRef(null);

    useEffect(() => {
        const typed = new Typed(element.current, {
            strings: texts,
            typeSpeed: 100,
            backSpeed: 50,
            smartBackspace: true,
            loop: true
        })
        return () => {
            typed.destroy();
        };
    });

  return (
    <div className={cn("min-h-screen w-full grid grid-cols-2 grid-rows-1")}>
        <header className={cn("w-full flex justify-center items-center bg-neutral-100 border-r-2 border-l-neutral-200")}>
            <span ref={element} className={cn("text-2xl font-bold text-neutral-700")} />
        </header>
        <div className={cn("flex flex-col gap-4 justify-center items-center")}>
            <header className={cn("text-center")}>
                <h3 className={cn("text-4xl font-bold text-slate-800 leading-none")}>Get Start</h3>
                <h3 className={cn("text-lg font-bold text-slate-800 leading-none")}>Balance Application</h3>
            </header>
            <div className={cn("flex gap-2 justify-center w-full px-12")}>

                <Link className={cn("flex-1/2")} to="/auth/signup">
                    <Button className={cn("w-full rounded-full")} variant={"outline"} size={'lg'}>
                        Sign Up
                    </Button>
                </Link>

                <Link className={cn("flex-1/2")} to="/auth/signin">
                    <Button className={cn("w-full rounded-full")} size={'lg'}>
                            Sign In
                    </Button>
                </Link>
            </div>
        </div>
    </div>
  )
}
