@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 0
    var number = abs(n)
    if (n == 0) return 1
    while (number > 0) {
        count++
        number /= 10
    }
    return count
}


/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var fn = 1
    var sn = 1
    var tn = fn + sn
    if (n < 3) return 1
    else {
        for (i in 1..n - 3) {
            fn = sn
            sn = tn
            tn = fn + sn
        }
    }
    return tn
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var n1 = m
    var n2 = n
    while (true) {
        when {
            n1 > n2 -> n1 -= n2
            n2 > n1 -> n2 -= n1
            else -> return m * n / n1
        }
    }
}
/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var k = 2
    while (n % k != 0) k++
    return k
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var k = n - 1
    while (n % k != 0) k--
    return k
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var mm = m
    var nn = n
    while (true) {
        when {
            mm > nn -> mm -= nn
            nn > mm -> nn -= mm
            else -> return (mm == 1)
        }
    }
}



/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    val mx = max(n, m)
    val mi = min(m, n)
    for (i in mi..mx) {
        if (sqrt(i.toDouble()) == sqrt(i.toDouble()).toInt().toDouble()) return true
    }
    return false
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var xx = x
    var count = 0
    while (xx != 1) {
        if (xx % 2 == 0) xx /= 2
        else xx = 3 * xx + 1
        count++
    }
    return count
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var xx: Double = x
    var si = 0.0
    var t: Double
    var cn = 1
    if (x > 0) {
        while (xx > 2 * PI) {
            xx -= 2 * PI
        }
    } else {
        while (xx < 0) {
            xx += 2 * PI
        }
    }
    do {
        t = (xx.pow(2 * cn - 1) / factorial(2 * cn - 1)) * (-1.0).pow(cn + 1)
        si += t
        cn++
    } while (abs(t) >= eps)
    return si
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var xx: Double = x
    var co = 0.0
    var t: Double
    var cn = 0
    if (x > 0) {
        while (xx > 2 * PI) {
            xx -= 2 * PI
        }
    } else {
        while (xx < 0) {
            xx += 2 * PI
        }
    }
    do {
        t = (xx.pow(2 * cn) / factorial(2 * cn)) * (-1.0).pow(cn)
        co += t
        cn++
    } while (abs(t) >= eps)
    return co
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var count: Int = -1
    var xx = n
    var newDigit = 0
    while (xx > 0) {
        xx /= 10
        count++
    }
    xx = n
    while (xx > 0) {
        newDigit += ((xx % 10) * (10.0).pow(count)).toInt()
        xx /= 10
        count--
    }
    return newDigit
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var count: Int = -1
    var xx = n
    var newDigit = 0
    while (xx > 0) {
        xx /= 10
        count++
    }
    xx = n
    while (xx > 0) {
        newDigit += ((xx % 10) * (10.0).pow(count)).toInt()
        xx /= 10
        count--
    }
    return n == newDigit
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var n1: Int
    var n2: Int
    var xx = n
    while (xx > 9) {
        n1 = xx % 10
        xx /= 10
        n2 = xx % 10
        if (n1 != n2) return true
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var pos = 0
    var s = 1
    var cn = 0
    while (pos < n) {
        cn = s * s
        pos += digitNumber(s * s)
        s++
    }
    return if (pos == n) cn % 10
    else {
        for (i in 1..(pos - n)) {
            cn /= 10
        }
        cn % 10
    }
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var pos = 0
    var s = 1
    var cn = 0
    while (pos < n) {
        cn = fib(s)
        pos += digitNumber(fib(s))
        s++
    }
    return if (pos == n) cn % 10
    else {
        for (i in 1..(pos - n)) {
            cn /= 10
        }
        cn % 10
    }
}
