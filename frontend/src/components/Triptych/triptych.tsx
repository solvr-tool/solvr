import React, {useState} from 'react';
import './triptych.css'

const Triptych = () => {

    const [codeBlock1, setCodeBlock1] = useState(`
        const greet = (name) => {
            return \`Hello, \${name}!\`;
        };
        
        console.log(greet("Alice"));
        `);

    const [codeBlock2, setCodeBlock2] = useState(`
        const add = (a, b) => {
            return a + b;
        };
        
        console.log(add(2, 3));
        `);

    const [codeBlock3, setCodeBlock3] = useState(`
        const factorial = (n) => {
            if (n === 0) {
                return 1;
            } else {
                return n * factorial(n - 1);
            }
        };
        
        console.log(factorial(5));
        `);

    return (
        <div className={'triptych-container'}>
            <div className={'code-block'}>
                {codeBlock1}
            </div>
            <div className={'code-block'}>
                {codeBlock2}
            </div>
            <div className={'code-block'}>
                {codeBlock3}
            </div>
        </div>
    );
};

export default Triptych;