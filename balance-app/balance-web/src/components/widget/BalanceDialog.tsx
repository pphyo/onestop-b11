import React from 'react'
import { Dialog, DialogContent, DialogFooter, DialogHeader, DialogTitle } from '@/components/ui/dialog';
import { cn } from '@/lib/utils';
import { Button } from '@/components/ui/button';

type BalanceDialogProps = {
    title: string;
    children: React.ReactNode;
    open: boolean;
    onClose: () => void;
    onAction: () => void;
    actionText: string;
}

const BalanceDialog: React.FC<BalanceDialogProps> = ({title, children, open, onAction, onClose, actionText}) => {

    // Restore focus when the dialog closes
    return (
        <Dialog open={open} onOpenChange={onClose}>
            <DialogContent className={cn("flex flex-col gap-6 sm:max-w-[425px]")} aria-describedby={undefined}>
                <DialogHeader>
                    <DialogTitle className={cn("text-2xl leading-none")}>{title}</DialogTitle>
                </DialogHeader>

                <div className={cn("flex flex-col gap-4")}>
                    {children}
                </div>

                <DialogFooter className={cn("flex justify-end space-x-3")}>
                    <Button className={cn("w-24")} variant={"outline"} type="button" onClick={onClose}>Close</Button>
                    <Button className={cn("w-24")} type="submit" onClick={onAction}>{ actionText }</Button>
                </DialogFooter>
            </DialogContent>
        </Dialog>
    )
}

export default BalanceDialog;