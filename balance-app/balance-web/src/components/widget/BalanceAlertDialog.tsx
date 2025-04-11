import React from 'react';
import { AlertDialog, AlertDialogAction, AlertDialogCancel, AlertDialogContent, AlertDialogDescription, AlertDialogFooter, AlertDialogHeader, AlertDialogTitle } from '@/components/ui/alert-dialog';

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
        <AlertDialogContent>
            <AlertDialogHeader>
                <AlertDialogTitle>{title}</AlertDialogTitle>
                <AlertDialogDescription className="sr-only"></AlertDialogDescription>
            </AlertDialogHeader>
            {children}
            <AlertDialogFooter>
                <AlertDialogCancel>Close</AlertDialogCancel>
                <AlertDialogAction onClick={onAction}>{actionText}</AlertDialogAction>
            </AlertDialogFooter>
        </AlertDialogContent>
    </AlertDialog>
  )
}

export default BalanceAlertDialog;