import { Button } from "@/components/ui/button";
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from "@/components/ui/card";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import useCurrency from "@/hooks/useCurrency";
import { cn } from "@/lib/utils";
import { SettingDto } from "@/model/dto/balance.dto";
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
                        <Select key="currency">
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
                break;
            case 3:
        }
    }, [state, currencies]);

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