import { Separator } from '@/components/ui/separator';
import { cn } from '@/lib/utils';
import {TransactionDto, TransactionForMonthly, AccountDto, IconDto, CategoryDto} from '@/model/dto/balance.dto';
import { format } from 'date-fns';
import React from 'react';
import transferImg from "@/assets/images/transfer1.png";
import { Edit, Ellipsis, MoveRight, Trash } from 'lucide-react';
import BalanceDropdownMenu from '@/components/widget/BalanceDropdownMenu';
import { Button } from '@/components/ui/button';
import { DropdownMenuItem } from '@/components/ui/dropdown-menu';

type TransactionListProps = {
    data: TransactionForMonthly;
    onDelete: (id: number) => void;
    onEdit: (dto: TransactionDto<AccountDto<IconDto>, AccountDto<IconDto> | CategoryDto<IconDto>>) => void;
}

const TransactionList: React.FC<TransactionListProps> = ({data, onDelete, onEdit}) => {
  return (
    <div className={cn("flex flex-col gap-4 justify-between")}>
        {
            data.dailyTransactions.map((dt, index) => (
                <div key={index} className={cn("w-full")}>
                    <h3 className={cn("text-pretty text-neutral-800")}>
                        { format(dt.date, "MMM dd, EEEE") }
                    </h3>

                    <Separator className={cn("my-2 mx-1")} />

                    <div className={cn("grid lg:grid-cols-2 md:grid-cols-1 gap-2")}>
                        {
                            dt.transactions.map(tx => (
                                <div key={tx.id} className={cn("border border-gray-300 rounded-md p-2 max-h-20 bg-slate-50 hover:bg-slate-200 flex items-center justify-between space-x-4")}>
                                    <img className={cn("object-left h-14 w-14")} src={tx.type === "Transfer" ? transferImg : tx.category.icon.path} alt={tx.type === "Transfer" ? "Transfer" : tx.category.icon.name} />
                                    <div className={cn("flex-grow flex flex-col gap-2 justify-between")}>
                                        <h4 className={cn("text-pretty text-lg text-neutral-600 font-semibold")}>
                                            { tx.type === "Transfer" ? "Transfer" : tx.category.name }
                                        </h4>
                                        {
                                            tx.type === "Transfer" ?
                                            (
                                                <div className={cn("flex gap-2 items-center")}>
                                                    <div className={cn("flex gap-1 items-center")}>
                                                        <img src={tx.accountFrom.icon.path} alt={tx.accountFrom.icon.name} className={cn("object-left h-5 w-5")} />
                                                        <p className={cn("text-pretty font-semibold text-sm text-neutral-500")}>{ tx.accountFrom.name }</p>
                                                    </div>

                                                    <MoveRight size={16} />

                                                    <div className={cn("flex gap-1 items-center")}>
                                                        <img src={tx.accountTo.icon.path} alt={tx.accountTo.icon.name} className={cn("object-left h-5 w-5")} />
                                                        <p className={cn("text-pretty font-semibold text-sm text-neutral-500")}>{ tx.accountTo.name }</p>
                                                    </div>
                                                </div>
                                            ) :
                                            (
                                                <div className={cn("flex gap-2 items-center")}>
                                                    <p className={cn("text-pretty font-semibold text-sm text-neutral-500")}>{ tx.type }</p>
                                                    <p className={cn("text-pretty font-semibold text-sm text-neutral-500")}>{ tx.type === "Income" ? " to " : " from " }</p>
                                                    <img src={tx.account.icon.path} alt={tx.account.icon.name} className={cn("object-left h-5 w-5")} />
                                                    <p className={cn("text-pretty font-semibold text-sm text-neutral-500")}>{ tx.account.name }</p>
                                                </div>
                                            )
                                        }
                                    </div>

                                    <span className={cn("text-neutral-700 font-bold text-pretty leading-1")}>
                                            { tx.amountWithFormat }
                                    </span>

                                    <BalanceDropdownMenu side="bottom" trigger={<Button variant={"ghost"} size={"icon"}><Ellipsis /></Button>}>
                                        <DropdownMenuItem onClick={() => onEdit(tx)}>
                                            <Edit /> Edit
                                        </DropdownMenuItem>
                                        <DropdownMenuItem onClick={() => onDelete(tx.id)}>
                                            <Trash /> Delete
                                        </DropdownMenuItem>
                                    </BalanceDropdownMenu>
                                </div>
                            ))
                        }
                    </div>
                </div>
            ))
        }
    </div>
  )
}

export default TransactionList;