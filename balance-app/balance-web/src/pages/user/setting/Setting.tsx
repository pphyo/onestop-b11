import { Label } from "@/components/ui/label";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import MainPageTitle from "@/components/widget/MainPageTitle";
import useCurrency from "@/hooks/useCurrency";
import MainPageLayout from "@/layouts/MainPageLayout";
import { cn } from "@/lib/utils";
import { CurrencyDto, CurrencyPosition, DecimalPlace, SettingDto } from "@/model/dto/balance.dto";
import { getSettingService } from "@/model/service/setting.service";
import { Settings } from "lucide-react";
import { useEffect } from "react";
import { useState } from "react";

const Setting = () => {
  const settingService = getSettingService();
  const {currencies} = useCurrency({name: "", code: ""});
  const [setting, setSetting] = useState<SettingDto<CurrencyDto> | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      const resp = await settingService.searchCurrent();
      if(resp) {
        setSetting(resp.payload as SettingDto<CurrencyDto>);
      }
    };

    fetchData();
  }, [settingService]);

  const handleChangeCurrency = async (value: string) => {
    const updatedSetting: SettingDto<number> = {
      id: setting?.id ?? 0,
      currencyPosition: setting?.currencyPosition ?? "BEFORE",
      decimalPlace: setting?.decimalPlace ?? "ZERO",
      currency: + value
    }
    const resp = await settingService.save(updatedSetting);
    if(resp) {
      setSetting(resp);
    }
  };

  const handleChangeCurrencyPosition = async (value: string) => {
    const updatedSetting: SettingDto<number> = {
      id: setting?.id ?? 0,
      currencyPosition: value as CurrencyPosition,
      decimalPlace: setting?.decimalPlace ?? "ZERO",
      currency: setting?.currency.id ?? 0
    }
    const resp = await settingService.save(updatedSetting);
    if(resp) {
      setSetting(resp);
    }
  };

  const handleChangeDecimalPlace = async (value: string) => {
    const updatedSetting: SettingDto<number> = {
      id: setting?.id ?? 0,
      currencyPosition: setting?.currencyPosition ?? "BEFORE",
      decimalPlace: value as DecimalPlace,
      currency: setting?.currency.id ?? 0
    }
    const resp = await settingService.save(updatedSetting);
    if(resp) {
      setSetting(resp);
    }
  };

  return (
    <MainPageLayout>
      <MainPageTitle icon={Settings} title="Your Settings" />

      <div className={cn("flex flex-col gap-6")}>
        <div className={cn("flex gap-4 items-center")}>
          <Label htmlFor="currency">Currency Sign:</Label>
          <Select value={setting?.currency.id.toString()} onValueChange={handleChangeCurrency}>
            <SelectTrigger id="currency" className={cn("w-[230px]")}>
              <SelectValue placeholder="Select currency" />
            </SelectTrigger>
            <SelectContent>
              {
                currencies.map(c => (
                  <SelectItem key={c.id} value={c.id.toString()}>{c.name + " - " + c.code}</SelectItem>
                ))
              }
            </SelectContent>
          </Select>
        </div>

        <div className={cn("flex gap-4 items-center")}>
          <Label htmlFor="position">Currency Position:</Label>
          <Select value={setting?.currencyPosition} onValueChange={handleChangeCurrencyPosition}>
            <SelectTrigger id="position" className={cn("w-[230px]")}>
              <SelectValue placeholder="Select currency position" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="BEFORE">At start of amount</SelectItem>
              <SelectItem value="AFTER">At end of amount</SelectItem>
              <SelectItem value="NONE">Do not use currency sign</SelectItem>
            </SelectContent>
          </Select>
        </div>

        <div className={cn("flex gap-4 items-center")}>
          <Label htmlFor="place">Decimal Place:</Label>
          <Select value={setting?.decimalPlace} onValueChange={handleChangeDecimalPlace}>
            <SelectTrigger id="place" className={cn("w-[230px]")}>
              <SelectValue placeholder="Select decimal place" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="ZERO">0 (eg.10)</SelectItem>
              <SelectItem value="ONE">1 (eg.10.1)</SelectItem>
              <SelectItem value="TWO">2 (eg.10.11)</SelectItem>
            </SelectContent>
          </Select>
        </div>

      </div>
    </MainPageLayout>
  )
}

export default Setting;
