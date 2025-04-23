import { cn } from "@/lib/utils";

const Loading = () => {
  return (
    <div className={cn("flex items-center justify-center h-[350px]")}>
        <span className={cn("text-4xl font-semibold text-neutral-800 inline-flex")}>
            <span className={cn("animate-wavy delay-0")}>.</span>
            <span className={cn("animate-wavy delay-200")}>.</span>
            <span className={cn("animate-wavy delay-400")}>.</span>
        </span>
    </div>
  )
}

export default Loading;