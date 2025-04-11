import { cn } from '@/lib/utils';
import { LucideIcon } from 'lucide-react'
import React from 'react'

type MainPageTitleProps = {
    icon: LucideIcon;
    title: string;
}

const MainPageTitle: React.FC<MainPageTitleProps> = (props) => {
  return (
    <header className={cn("flex gap-2 ps-2 mt-2 border-l-4 border-neutral-600")}>
        {<props.icon size={30} />}
        <h3 className={cn("text-2xl font-semibold text-neutral-700")}>{props.title}</h3>
    </header>
  )
}

export default MainPageTitle;