@file:Suppress("UNUSED_PARAMETER")

package lesson7

import java.lang.Integer.max
import java.lang.Integer.rotateLeft

/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
 * При сравнении подстрок, регистр символов *имеет* значение.
 *
 * T = O(m * n)
 * R = O(m * n)
 * где m = first.length
 *     n = second.length
 */
//решение взято с neerc.ifmo.ru

fun longestCommonSubSequence(first: String, second: String): String {
    val m = first.length
    val n = second.length
    val table = Array(m + 1) { IntArray(n + 1) }

    for (i in 0..m)
        table[i][n] = 0
    for (j in 0..n)
        table[m][j] = 0

    for (i in m - 1 downTo 0)
        for (j in n - 1 downTo 0)
            if (first[i] == second[j])
                table[i][j] = table[i + 1][j + 1] + 1
            else
                table[i][j] = max(table[i][j + 1], table[i + 1][j])

    return printResult(first, second, table)
}

private fun printResult(
    first: String,
    second: String,
    table: Array<IntArray>
): String {
    val m = first.length
    val n = second.length
    val result = StringBuilder()
    var i = 0
    var j = 0

    while (i < m && j < n)
        if (first[i] == second[j]) {
            result.append(first[i])
            i++
            j++
        } else
            if (table[i + 1][j] >= table[i][j + 1])
                i++
            else
                j++

    return result.toString()
}

/**
 * Наибольшая возрастающая подпоследовательность
 * Сложная
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 * 
 * T = O(n^2) можно лучше
 * R = O(n)
 */
//решение взято с neerc.ifmo.ru

fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    val n = list.size
    val prev = IntArray(n)
    val pos = IntArray(n + 1)
    var length = 0

    length = binarySearch(n, length, list, pos, prev)

    val result = mutableListOf<Int>()
    var z = pos[length]

    for (i in length - 1 downTo 0) {
        result.add(list[z])
        z = prev[z]
    }

    return result
}

private fun binarySearch(
    n: Int,
    length: Int,
    list: List<Int>,
    pos: IntArray,
    prev: IntArray
): Int {
    var length1 = length
    for (i in n - 1 downTo 0) {
        var left = 1
        var right = length1

        while (left <= right) {
            val middle = (left + right) / 2
            if (list[pos[middle]] < list[i])
                right = middle - 1
            else
                left = middle + 1
        }

        prev[i] = pos[left - 1]
        pos[left] = i

        if (left > length1)
            length1 = left
    }
    return length1
}

/**
 * Самый короткий маршрут на прямоугольном поле.
 * Средняя
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 */
fun shortestPathOnField(inputName: String): Int {
    TODO()
}
