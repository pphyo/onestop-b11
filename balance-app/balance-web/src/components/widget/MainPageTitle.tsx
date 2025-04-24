import { cn } from '@/lib/utils';
import { LucideIcon } from 'lucide-react'
import React from 'react'

type MainPageTitleProps = {
    icon: LucideIcon;
    title: string;
}

const MainPageTitle: React.FC<MainPageTitleProps> = (props) => {
  return (
    <header className={cn("flex gap-2 mt-2")}>
        {<props.icon size={30} />}
        <h3 className={cn("text-2xl font-semibold text-neutral-700")}>Your <span className={cn("p-1 px-1.5 bg-neutral-700 rounded-sm text-white")}>{props.title}</span></h3>
    </header>
  )
}

export default MainPageTitle;