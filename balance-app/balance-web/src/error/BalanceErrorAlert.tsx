import { AlertDialog, AlertDialogAction, AlertDialogContent, AlertDialogDescription, AlertDialogFooter, AlertDialogHeader, AlertDialogTitle } from '@/components/ui/alert-dialog';
import React, { useEffect, useState } from 'react'
import { useErrorStore } from './error.store';
import { format } from 'date-fns';
import { cn } from '@/lib/utils';
import { getAuthService } from '@/model/service/auth.service';
import { useNavigate } from 'react-router';

const BalanceErrorAlert: React.FC = () => {

    const authService = getAuthService();

    const {error, setError} = useErrorStore();

    const [actionText, setActionText] = useState("OK");

    const navigate = useNavigate();

    useEffect(() => {
        if(error?.title === "Session Invalid") {
            setActionText("Sign In");
        } else {
            setActionText("OK")
        }
    }, [error]);

    const handleAction = () => {
        setError(null);
        if(error?.title === "Session Invalid") {
            authService.signOut();
            navigate("/auth/signin");
        }
    };

    return (
        <AlertDialog
            open={!!error}
            onOpenChange={() => !open && setError(null)}
        >
            <AlertDialogContent>
                <AlertDialogHeader>
                    <AlertDialogTitle>{ error?.title }</AlertDialogTitle>
                    <AlertDialogDescription>
                        {error?.errorAt ? `Error at ${format(error.errorAt, "dd MMM, yyyy hh:mm a(EEEE)")}` : "Unknown time"}
                    </AlertDialogDescription>
                </AlertDialogHeader>

                {
                    error?.messages.map((message: string, index: number) => (
                        <div key={ index } className={cn("text-neutral-500")}>{ message }</div>
                    ))
                }

                <AlertDialogFooter>
                    <AlertDialogAction
                        className={cn("w-[100px]")}
                        onClick={handleAction}
                    >
                        { actionText }
                    </AlertDialogAction>
                </AlertDialogFooter>
            </AlertDialogContent>
        </AlertDialog>
    );
}

export default BalanceErrorAlert;