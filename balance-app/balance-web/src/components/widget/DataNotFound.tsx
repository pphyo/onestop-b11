import { cn } from '@/lib/utils';
import React from 'react';
import noData from '@/assets/images/no-data.png';

const DataNotFound: React.FC<{data: string}> = ({data}) => {
  return (
    <div className={cn("flex flex-col flex-wrap gap-2 place-content-center")}>
        <img src={noData} alt="No Data" className={cn("h-1/2 w-1/2 object-contain")} />
        <p className={cn("text-pretty text-center font-bold text-lg text-neutral-700")}>
            {"Oops, no " + data + " found!"}
        </p>
    </div>
  )
}

export default DataNotFound;