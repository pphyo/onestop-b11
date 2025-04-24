import { Button } from "@/components/ui/button";
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from "@/components/ui/card";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import useCurrency from "@/hooks/useCurrency";
import { cn } from "@/lib/utils";
import { CurrencyPosition, DecimalPlace, SettingDto } from "@/model/dto/balance.dto";
import { getSettingService } from "@/model/service/setting.service";
import { useEffect } from "react";
import { useState } from "react";
import { useNavigate } from "react-router";

const SetUpWizard = () => {

    const settingService = getSettingService();

    const [state, setState] = useState<1 | 2 | 3>(1);

    const {currencies} = useCurrency({name: "", code: ""});

    const [setting, setSetting] = useState<SettingDto>();

    const [title, setTitle] = useState<string>("Card Title");

    const [description, setDescription] = useState<string>("Card description");

    const [error, setError] = useState<string>("");

    const [content, setContent] = useState<React.ReactNode>();

    const navigate = useNavigate();

    useEffect(() => {
        switch(state) {
            case 1:
                setTitle("Currency");
                setDescription("Set up your currency");
                setContent((
                    <>
                        <Select key="currency"
                            onValueChange={handleChangeCurrency}
                            value={setting?.currency ? setting.currency.toString() : undefined}>
                            <SelectTrigger className={cn("w-full")}>
                                <SelectValue placeholder="Select currency" />
                            </SelectTrigger>
                            <SelectContent>
                                {
                                    currencies.map(c => (
                                        <SelectItem key={c.id} value={c.id.toString()}>
                                            { c.name + " - " + c.code }
                                        </SelectItem>
                                    ))
                                }
                            </SelectContent>
                        </Select>
                    </>
                ));
                break;
            case 2:
                setTitle("Currency Position");
                setDescription("Set up currency position");
                setContent((
                    <>
                        <Select key="currency-position"
                            onValueChange={handleChangeCurrencyPosition}
                            value={setting?.currencyPosition ? setting.currencyPosition : undefined}
                        >
                            <SelectTrigger className={cn("w-full")}>
                                <SelectValue placeholder="Select currency position" />
                            </SelectTrigger>
                            <SelectContent>
                                <SelectItem value="BEFORE">At start of amount</SelectItem>
                                <SelectItem value="AFTER">At end of amount</SelectItem>
                                <SelectItem value="NONE">Do not use currency sign</SelectItem>
                            </SelectContent>
                        </Select>
                    </>
                ));
                break;
            case 3:
                setTitle("Decimal Place");
                setDescription("Set up decimal place");
                setContent((
                    <>
                        <Select key="decimal-place"
                            onValueChange={handleChangeDecimalPlace}
                            value={setting?.decimalPlace ? setting.decimalPlace : undefined}
                        >
                            <SelectTrigger className={cn("w-full")}>
                                <SelectValue placeholder="Select decimal place" />
                            </SelectTrigger>
                            <SelectContent>
                                <SelectItem value="ZERO">0 (eg.10)</SelectItem>
                                <SelectItem value="ONE">1 (eg.10.1)</SelectItem>
                                <SelectItem value="TWO">2 (eg.10.11)</SelectItem>
                            </SelectContent>
                        </Select>
                    </>
                ))
        }
    }, [state, currencies, setting]);

    const handleChangeCurrency = (value: string) => {
        setSetting((prev) => {
            return {
                id: 0,
                ...prev,
                currency: + value
            } as SettingDto;
        });
    };

    const handleChangeCurrencyPosition = (value: string) => {
        setSetting((prev) => {
            return {
                ...prev,
                currencyPosition: value as CurrencyPosition
            } as SettingDto;
        })
    };

    const handleChangeDecimalPlace = (value: string) => {
        setSetting((prev) => {
            return {
                ...prev,
                decimalPlace: value as DecimalPlace
            } as SettingDto;
        })
    };

    const handleSetUp = () => {
        switch(state) {
            case 1:
                toStateTwo();
                break;
            case 2:
                toStateThree();
                break;
            case 3:
                setUp();
        }
    };

    const toStateTwo = () => {
        if(!setting?.currency) {
            setError("Please select currency");
        } else {
            setState(2);
            clearError();
        }
    };

    const toStateThree = () => {
        if(!setting?.currencyPosition) {
            setError("Please select currency position");
        } else {
            setState(3);
            clearError();
        }
    }

    const setUp = async () => {
        if(!setting?.decimalPlace) {
            setError("Please select decimal place");
        } else {
            const resp = await settingService.save(setting);
            if(resp) {
                navigate("/balance/app/user");
            }
        }
    };

    const clearError = () => setError("");

    return (
        <div className={cn("flex justify-center items-center min-h-screen")}>
            <Card className={cn("w-[380px]")}>
                <CardHeader>
                    <CardTitle className={cn("text-2xl font-semibold leading-none")}>{ title }</CardTitle>
                    <CardDescription className={cn("leading-none")}>{ description }</CardDescription>
                </CardHeader>
                <CardContent>
                    { content }
                    { error && <div className={cn("text-xs mt-1 text-red-600")}>{ error }</div> }
                </CardContent>
                <CardFooter>
                    <Button variant={"outline"} className={cn("w-full")} onClick={handleSetUp}>
                        { state === 3 ? "Set Up" : "Next" }
                    </Button>
                </CardFooter>
            </Card>
        </div>
    )
}

export default SetUpWizard;