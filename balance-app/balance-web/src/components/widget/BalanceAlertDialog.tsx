import React from 'react';
import { AlertDialog, AlertDialogAction, AlertDialogCancel, AlertDialogContent, AlertDialogFooter, AlertDialogHeader, AlertDialogTitle } from '@/components/ui/alert-dialog';

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
                <AlertDialogCancel>Close</AlertDialogCancel>
                <AlertDialogAction onClick={onAction}>{actionText}</AlertDialogAction>
            </AlertDialogFooter>
        </AlertDialogContent>
    </AlertDialog>
  )
}

export default BalanceAlertDialog;