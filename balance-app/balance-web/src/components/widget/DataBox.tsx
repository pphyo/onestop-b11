import { cn } from '@/lib/utils';
import { IconDto } from '@/model/dto/balance.dto';
import React from 'react';
import BalanceDropdownMenu from './BalanceDropdownMenu';
import { Button } from '@/components/ui/button';
import { Ellipsis } from 'lucide-react';
import { Link } from 'react-router';

type DataBoxProps = {
    dataName: string;
    dataValue: string | undefined;
    dataIcon: IconDto;
    link: string;
    children: React.ReactNode;
}

const DataBox: React.FC<DataBoxProps> = ({dataName, dataValue, dataIcon, link, children}) => {
  return (
    <div className={cn("border border-gray-300 rounded-md p-2 max-h-20 bg-slate-50 hover:bg-slate-200 flex items-center space-x-4")}>
        <img src={dataIcon.path} alt={dataIcon.name} className={cn("object-contain h-14 w-14")} />

        <div className={cn("inline-flex flex-col flex-grow justify-between")}>
            <Link to={link} className={cn("text-xl text-neutral-600 font-bold text-pretty hover:cursor-pointer hover:underline")}>
                {dataName}
            </Link>
            <span className={cn("text-neutral-950 text-md text-pretty")}>{dataValue}</span>
        </div>

        <BalanceDropdownMenu side="bottom" trigger={<Button variant={"ghost"} size={"icon"}><Ellipsis /></Button>}>
            {children}
        </BalanceDropdownMenu>
    </div>
  )
}

export default DataBox;