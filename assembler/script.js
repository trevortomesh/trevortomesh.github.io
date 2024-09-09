document.getElementById('runButton').addEventListener('click', runCode);
document.getElementById('speedSlider').addEventListener('input', updateDelay);

let delay = 500;

function updateDelay() {
    delay = document.getElementById('speedSlider').value;
    document.getElementById('delayValue').innerText = delay;
}

function assemble(assemblyCode) {
    const instructions = {
        'MOV': 0x01,
        'LOAD': 0x02,
        'STORE': 0x03,
        'ADD': 0x04,
        'SUB': 0x05,
        'INC': 0x06,
        'DEC': 0x07,
        'JMP': 0x08,
        'JZ': 0x09,
        'JNZ': 0x0A,
        'IN': 0x0B,
        'OUT': 0x0C,
        'LOADM': 0x0D,
        'MUL': 0x0E,
        'DIV': 0x0F
    };

    const lines = assemblyCode.split('\n');
    const labelMap = {};
    const machineCode = [];
    let currentAddress = 0;

    // First pass: Identify labels and generate machine code
    lines.forEach(line => {
        const trimmedLine = line.trim();
        if (trimmedLine.endsWith(':')) {
            const label = trimmedLine.slice(0, -1).trim();
            labelMap[label] = currentAddress;
        } else if (trimmedLine) {
            const parts = trimmedLine.split(/[\s,]+/);
            const opcode = instructions[parts[0]];
            if (opcode !== undefined) {
                const operands = parts.slice(1).map(op => op.startsWith('#') ? parseInt(op.slice(1)) : (isNaN(op) ? op : parseInt(op)));
                machineCode.push([opcode, operands]);
                currentAddress++;
            }
        }
    });

    // Second pass: Resolve labels
    machineCode.forEach((instruction, index) => {
        const [opcode, operands] = instruction;
        if ([0x08, 0x09, 0x0A].includes(opcode)) { // JMP, JZ, JNZ
            const label = operands[0];
            if (typeof label === 'string' && labelMap[label] !== undefined) {
                machineCode[index] = [opcode, [labelMap[label]]];
            } else if (typeof label === 'number') {
                machineCode[index] = [opcode, [label]]; // For numeric addresses
            }
        }
    });

    return machineCode;
}

class Emulator {
    constructor() {
        this.registers = { 'r1': 0, 'r2': 0, 'r3': 0, 'r4': 0 };
        this.memory = new Array(256).fill(0);
        this.pc = 0;
        this.output = '';
        this.zeroFlag = false;
    }

    executeInstruction(machineCode, delay) {
        if (this.pc >= machineCode.length) {
            this.updateUI();
            return;
        }

        const [opcode, operands] = machineCode[this.pc];
        switch (opcode) {
            case 0x01: // MOV
                this.registers[operands[0]] = this.registers[operands[1]];
                break;
            case 0x02: // LOAD
                this.registers[operands[0]] = operands[1];
                break;
            case 0x03: // STORE
                this.memory[operands[1]] = this.registers[operands[0]];
                break;
            case 0x04: // ADD
                this.registers[operands[0]] += this.registers[operands[1]];
                this.zeroFlag = this.registers[operands[0]] === 0;
                break;
            case 0x05: // SUB
                this.registers[operands[0]] -= this.registers[operands[1]];
                this.zeroFlag = this.registers[operands[0]] === 0;
                break;
            case 0x06: // INC
                this.registers[operands[0]] += 1;
                this.zeroFlag = this.registers[operands[0]] === 0;
                break;
            case 0x07: // DEC
                this.registers[operands[0]] -= 1;
                this.zeroFlag = this.registers[operands[0]] === 0;
                break;
            case 0x08: // JMP
                this.pc = operands[0] - 1; // -1 to account for the increment at the end
                break;
            case 0x09: // JZ
                if (this.zeroFlag) {
                    this.pc = operands[0] - 1; // -1 to account for the increment at the end
                }
                break;
            case 0x0A: // JNZ
                if (!this.zeroFlag) {
                    this.pc = operands[0] - 1; // -1 to account for the increment at the end
                }
                break;
            case 0x0B: // IN
                this.registers[operands[0]] = parseInt(prompt("Input: "));
                break;
            case 0x0C: // OUT
                this.output += `Output: ${this.registers[operands[0]]}\n`;
                break;
            case 0x0D: // LOADM
                this.registers[operands[0]] = this.memory[operands[1]];
                break;
            case 0x0E: // MUL
                this.registers[operands[0]] = this.registers[operands[1]] * this.registers[operands[2]];
                break;
            case 0x0F: // DIV
                if (this.registers[operands[2]] !== 0) {
                    this.registers[operands[0]] = Math.floor(this.registers[operands[1]] / this.registers[operands[2]]);
                    this.zeroFlag = this.registers[operands[0]] === 0;
                } else {
                    console.error('Division by zero');
                }
                break;

        }

        this.pc += 1;

        // Update UI after each instruction to reflect real-time changes
        this.updateUI();

        setTimeout(() => this.executeInstruction(machineCode, delay), delay);
    }

    execute(machineCode, delay = 500) {
        this.executeInstruction(machineCode, delay);
    }

    updateUI() {
        document.getElementById('reg1').textContent = this.registers['r1'];
        document.getElementById('reg2').textContent = this.registers['r2'];
        document.getElementById('reg3').textContent = this.registers['r3'];
        document.getElementById('reg4').textContent = this.registers['r4'];

        const memoryTableBody = document.getElementById('memoryTable').getElementsByTagName('tbody')[0];
        memoryTableBody.innerHTML = '';
        for (let i = 0; i < 256; i++) {
            if (this.memory[i] !== 0) {
                const row = memoryTableBody.insertRow();
                const cell1 = row.insertCell(0);
                const cell2 = row.insertCell(1);
                cell1.textContent = `0x${i.toString(16).toUpperCase().padStart(2, '0')}`;
                cell2.textContent = this.memory[i];
            }
        }

        document.getElementById('outputArea').textContent = this.output;
    }
}

function runCode() {
    const codeArea = document.getElementById('codeArea').value;
    const machineCode = assemble(codeArea);
    const emulator = new Emulator();
    emulator.execute(machineCode, delay);
}
