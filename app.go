package main

import (
	"bytes"
	"context"
	"fmt"
	"log"
	"os/exec"
	"strings"
)

// App struct
type App struct {
	ctx context.Context
}

// NewApp creates a new App application struct
func NewApp() *App {
	return &App{}
}

// startup is called when the app starts. The context is saved
// so we can call the runtime methods
func (a *App) startup(ctx context.Context) {
	a.ctx = ctx
}

// Greet returns a greeting for the given name
// TODO: Pass files to UI instead of printing and receive file from UI
func (a *App) Greet(name string) string {
	file := "README.md"
	if fileHasConflicts(file) {
		fmt.Println(getHeadFile(file))
		fmt.Println("------------------------------")
		fmt.Println(getOtherFile(file))
		fmt.Println("------------------------------")
		fmt.Println(getPreConflictFile(file))
	} else {
		fmt.Println("No conflicts on provided file")
	}
	return fmt.Sprintf("Hello %s, It's show time!", name)
}

func fileHasConflicts(file string) bool {
	conflictFiles := executeCommand("git", "diff", "--name-only", "--diff-filter=U", "--relative")
	return strings.Contains(conflictFiles, file)
}

func getHeadFile(fileName string) string {
	fileArg := fmt.Sprintf(":2:%s", fileName)
	return executeCommand("git", "show", fileArg)
}

func getOtherFile(fileName string) string {
	fileArg := fmt.Sprintf(":3:%s", fileName)
	return executeCommand("git", "show", fileArg)
}

func getPreConflictFile(fileName string) string {
	fileArg := fmt.Sprintf(":1:%s", fileName)
	return executeCommand("git", "show", fileArg)
}

func executeCommand(command string, args ...string) string {
	cmd := exec.Command(command, args...)
	var outb, errb bytes.Buffer
	cmd.Stdout = &outb
	cmd.Stderr = &errb
	if err := cmd.Run(); err != nil {
		log.Fatal(err)
	}
	return outb.String()
}
