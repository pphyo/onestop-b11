import { cn } from '@/lib/utils';
import React from 'react'

const CategoryControl: React.FC<{children: React.ReactNode}> = ({children}) => {
  return (
    <div className={cn("flex justify-between")}>
        {children}
    </div>
  )
}

export default CategoryControl;