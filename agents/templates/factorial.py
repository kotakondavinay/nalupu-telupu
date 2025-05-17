class Factorial:
    def __init__(self):
        pass

    def factorial(self, n):
        if n == 0:
            return 1
        output = []
        carries = [0] * n;
        carries[0] = 1;
        for i in range(n):
            while carries[i] > 0:
                carry = carries[i] % 10;
                for j in range(i + 1, n):
                    carries[j] = ((j + 1) * carry) + carries[j]
                    carry = carries[j] % 10;
                    carries[j] = carries[j] // 10;
                    if carry == 0 and carries[j] == 0 and carries[n - 1] == 0:
                        break
                output.append(carry)
                carries[i] //= 10;
        print("length of output", len(output))
        return ''.join(map(str, reversed(output)))

print(Factorial().factorial(100))