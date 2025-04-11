import AccessedDenied from '@/components/widget/AccessedDenied';
import { useAuth } from '@/hooks/useAuth';
import React from 'react'

type AuthenticatedRouteProps = {
    children: React.ReactNode;
    requiredAdmin: boolean;
}

const AuthenticatedRoute: React.FC<AuthenticatedRouteProps> = ({children, requiredAdmin}) => {

    const {user, authenticated} = useAuth();

    if(!authenticated)
        return <AccessedDenied three={true} />;

    if(typeof requiredAdmin == "boolean" && user) {
        if(requiredAdmin && !user?.admin) {
            return <AccessedDenied three={true} />
        }

        if(!requiredAdmin && user?.admin) {
            return <AccessedDenied three={true} />
        }
    }

  return <>{children}</>;
}

export default AuthenticatedRoute;