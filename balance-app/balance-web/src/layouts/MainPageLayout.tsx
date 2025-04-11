import React from "react";

const MainPageLayout: React.FC<{children: React.ReactNode}> = ({children}) => {
  return (
    <div className="flex flex-col gap-6">
        {children}
    </div>
  )
}

export default MainPageLayout;