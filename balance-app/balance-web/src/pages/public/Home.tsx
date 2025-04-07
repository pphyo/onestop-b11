import { Button } from '@/components/ui/button';
import { cn } from '@/lib/utils';
import { Link } from 'react-router';

export const Home = () => {
  return (
    <div className={cn("min-h-screen grid grid-cols-2 grid-rows-1")}>
        <div className={cn("flex flex-col gap-4 justify-center items-center")}>
            <h3 className={cn("text-3xl font-bold text-slate-800")}>Get Start</h3>
            <div className={cn("flex gap-2 justify-center")}>

                <Link className={cn("w-full rounded-full")} to="/auth/signup">
                    <Button className={cn("rounded-full w-full")} variant={"outline"} size={'lg'}>
                        Sign Up
                    </Button>
                </Link>

                <Link className={cn("w-full rounded-full")} to="/auth/signin">
                    <Button className={cn("w-full rounded-full")} size={'lg'}>
                            Sign In
                    </Button>
                </Link>
            </div>
        </div>
        <header className={cn("flex flex-col gap-2 justify-center items-center bg-neutral-100 border-l-2 border-l-neutral-200")}>
            <h3 className={cn("text-3xl text-pretty text-neutral-700 font-semibold leading-none")}>
                Balance Application
            </h3>
            <span className={cn("text-sm text-neutral-500")}>Easily manage for your income and expense</span>
        </header>
    </div>
  )
}
