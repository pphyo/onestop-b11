import React from 'react';
import { AlertDialog, AlertDialogAction, AlertDialogCancel, AlertDialogContent, AlertDialogFooter, AlertDialogHeader, AlertDialogTitle } from '@/components/ui/alert-dialog';
import { cn } from '@/lib/utils';

type BalanceAlertDialogProps = {
    title: string;
    children: React.ReactNode;
    open: boolean;
    onClose: () => void;
    onAction: () => void;
    actionText: string;
}

const BalanceAlertDialog: React.FC<BalanceAlertDialogProps> = ({title, children, open, onClose, onAction, actionText}) => {
  return (
    <AlertDialog open={open} onOpenChange={onClose}>
        <AlertDialogContent aria-describedby={undefined} aria-description={undefined}>
            <AlertDialogHeader>
                <AlertDialogTitle>{title}</AlertDialogTitle>
            </AlertDialogHeader>
            <div>
            {children}
            </div>
            <AlertDialogFooter>
                <AlertDialogCancel className={cn("w-24")}>Close</AlertDialogCancel>
                <AlertDialogAction className={cn("w-24")} onClick={onAction}>{actionText}</AlertDialogAction>
            </AlertDialogFooter>
        </AlertDialogContent>
    </AlertDialog>
  )
}

export default BalanceAlertDialog;