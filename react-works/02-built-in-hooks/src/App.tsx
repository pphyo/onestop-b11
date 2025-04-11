import { Route, Routes } from "react-router";
import Home from "./Home";
import Page from "./Page";

function App() {
  return (
    <Routes>
      <Route path="/" children={
        <>
          <Route path="/home" element={<Home />} />
          <Route path="/page" element={<Page />} />
          <Route index={true} element={<Home />} />
        </>
      } />
    </Routes>
  );
}

export default App
