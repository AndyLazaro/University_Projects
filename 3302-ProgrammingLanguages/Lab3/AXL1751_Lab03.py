# ANDY LAZARO 1001821751
# Running on python version 3.10

import fileinput

operands = {
    '+': "add",
    '-': "sub",
    '*': "multi",
    '/': "div",
}


def operation(token, stack):
    b = stack.pop()
    a = stack.pop()
    match token:
        case '+':
            stack.append(a + b)
        case '-':
            stack.append(a - b)
        case '*':
            stack.append(a * b)
        case '/':
            stack.append(a / b)


def main():
    # Grab each line of the input file named "input_RPN.txt"
    i = 1
    for line in fileinput.input(files="input_RPN.txt"):
        # print the result of the RPN notation
        # the input of the RPN function is an array of each token in the line

        # print the RPN line result
        print(f"Result of the line {i} is: " + "".join(map(str,RPN(line.split()))))
        i += 1


def RPN(arr):
    # Creates a stack to push the elements that appear first
    stack = []
    # for each token in the array if they are an operand pop the 2 elements before the operand and evaluate them
    # then push the result back into the stack and continue
    # if the token is not an operand then push it into the stack
    for token in arr:
        if token in operands:
            operation(token, stack)
        else:
            stack.append(eval(token))
    # return the final value of the stack for printing
    return stack


if __name__ == "__main__":
    main()
