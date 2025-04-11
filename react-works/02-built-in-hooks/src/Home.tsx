import {useEffect, useState} from 'react';
import { Link } from 'react-router';

export const Home = () => {
  const [value, setvalue] = useState<number>(0);
    const [other, setOther] = useState("Hello");

    useEffect(() => {
      const timer = setInterval(() => {
        console.log("Time: ", new Date());
      }, 1000);

      return () => { // effect clean up
        clearInterval(timer);
      };
    }, []);

    return (
      <>
        <h1>Value is {value}</h1>
        <h1>Other value is {other}</h1>
        <div>
          <button onClick={() => setvalue(value + 1)}>Increase</button>
          <button onClick={() => setOther("Other value")}>Change Other</button>
          <Link to={"/page"}>To Page</Link>
        </div>
      </>
    )
}

export default Home;
