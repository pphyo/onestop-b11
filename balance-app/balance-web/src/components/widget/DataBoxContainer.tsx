import React from 'react'
import { cn } from '@/lib/utils';

const DataBoxContainer: React.FC<{children: React.ReactNode}> = ({children}) => {
  return (
    <div className={cn("border border-gray-300 rounded-md p-2 grid lg:grid-cols-3 md:grid-cols-2 sm:grid-cols-1 gap-2")}>
        {children}
    </div>
  )
}

export default DataBoxContainer;