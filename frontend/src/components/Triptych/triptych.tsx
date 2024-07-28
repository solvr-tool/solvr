import React, {useState} from 'react';
import Editor from 'react-simple-code-editor';
import { highlight, languages } from "prismjs";
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
                <Editor
                    value={codeBlock1}
                    highlight={code => highlight(code, languages.js, 'js')}
                    onValueChange={code => setCodeBlock1(code)}
                    padding={10}
                    style={{
                        fontFamily: '"Fira code", "Fira Mono", monospace',
                        fontSize: 12,
                    }}
                />
            </div>
            <div className={'code-block'}>
                <Editor
                    value={codeBlock2}
                    highlight={code => highlight(code, languages.js, 'js')}
                    onValueChange={code => setCodeBlock2(code)}
                    padding={10}
                    style={{
                        fontFamily: '"Fira code", "Fira Mono", monospace',
                        fontSize: 12,
                    }}
                />
            </div>
            <div className={'code-block'}>
                <Editor
                    value={codeBlock3}
                    highlight={code => highlight(code, languages.js, 'js')}
                    onValueChange={code => setCodeBlock3(code)}
                    padding={10}
                    style={{
                        fontFamily: '"Fira code", "Fira Mono", monospace',
                        fontSize: 12,
                    }}
                />
            </div>
        </div>
    );
};

export default Triptych;