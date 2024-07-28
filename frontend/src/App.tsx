import {useState} from 'react';
import {Greet} from "../wailsjs/go/main/App";
import Triptych from "./components/Triptych/triptych";

function App() {
    const [resultText, setResultText] = useState("Please enter your name below 👇");
    const [name, setName] = useState('');
    const updateName = (e: any) => setName(e.target.value);
    const updateResultText = (result: string) => setResultText(result);

    function greet() {
        Greet(name).then(updateResultText);
    }

    return (
        <Triptych/>
    )
}

export default App
