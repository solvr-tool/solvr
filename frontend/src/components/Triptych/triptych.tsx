import React, {useState} from 'react';
import './triptych.css'
import {Editor} from "@monaco-editor/react";
import {editor} from "monaco-editor";
import IStandaloneEditorConstructionOptions = editor.IStandaloneEditorConstructionOptions;

const Triptych = () => {

    const [codeBlock1, setCodeBlock1] = useState(`const greet = (name) => {
    return \`Hello, \${name}!\`;
};

console.log(greet("Alice"));
`);

    const [codeBlock2, setCodeBlock2] = useState(`const add = (a, b) => {
    return a + b;
};

console.log(add(2, 3));
`);

    const [codeBlock3, setCodeBlock3] = useState(`const factorial = (n) => {
    if (n === 0) {
        return 1;
    } else {
        return n * factorial(n - 1);
    }
};
        
console.log(factorial(5));
`);

    const handleCodeChange = (value: string | undefined) => {
        console.log("CHANGE", value);
        setCodeBlock1(value || '');
    }

    const editorOptions: IStandaloneEditorConstructionOptions = {
        autoIndent: 'none',
        minimap: {
            enabled: false
        },
        padding: {
            top: 10
        }

    }

    return (
        <div className={'triptych-container'}>
            <div className={'code-block'}>
                <Editor
                    height="90vh"
                    defaultLanguage="javascript"
                    defaultValue={codeBlock1}
                    onChange={handleCodeChange}
                    theme="vs-dark"
                    options={editorOptions}

                />
            </div>
            <div className={'code-block'}>
                <Editor
                    height="90vh"
                    defaultLanguage="javascript"
                    defaultValue={codeBlock2}
                    onChange={handleCodeChange}
                    theme="vs-dark"
                    options={editorOptions}

                />
            </div>
            <div className={'code-block'}>
                <Editor
                    height="90vh"
                    defaultLanguage="javascript"
                    defaultValue={codeBlock3}
                    onChange={handleCodeChange}
                    theme="vs-dark"
                    options={editorOptions}

                />
            </div>
        </div>
    );
};

export default Triptych;