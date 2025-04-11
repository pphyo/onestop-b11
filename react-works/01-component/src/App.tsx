import { useState } from "react";
import StudentManagement from "./student/StudentManagement";

export default function App() {
  return (
    <main className="min-h-screen p-4 d-flex flex-col gap-3 place-items-center justify-center items-center">
      <StudentManagement />
    </main>
  );
}

export function Counter() {
  const [count, setCount] = useState<number>(0);

  const handlePlus = () => setCount(count + 1);
  const handleMinus = () => setCount(count - 1);
  const handleReset = () => setCount(0);

  return (
    <>
      <h3 className="text-2xl text-center">{ count }</h3>
      <div className="flex gap-2 justify-center items-center">
        <button
          className="w-[100px] rounded-xl p-2 bg-slate-500 text-white hover:bg-slate-600 hover:cursor-pointer"
          onClick={handleMinus}>Minus</button>
        <button
          className="w-[100px] rounded-xl p-2 bg-red-500 text-white hover:bg-red-600 hover:cursor-pointer"
          onClick={handleReset}>Reset</button>
        <button
          className="w-[100px] rounded-xl p-2 bg-lime-500 text-white hover:bg-lime-600 hover:cursor-pointer"
          onClick={handlePlus}>Plus</button>
      </div>
    </>
  );
}