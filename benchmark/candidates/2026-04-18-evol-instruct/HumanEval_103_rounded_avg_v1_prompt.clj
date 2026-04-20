(defn median_binary
  "You are given two positive integers n and m, and your task is to compute the
  median of the integers from n through m (including n and m). If the number of
  integers in the range is odd, the median is the middle value. If it is even,
  the median is the average of the two middle values, rounded to the nearest
  integer. Convert the result to binary, prefixed with \"0b\".

  If n is greater than m, return -1.

  Examples:
  >>> (median_binary 1 5)
  \"0b11\"
  >>> (median_binary 1 4)
  \"0b11\"
  >>> (median_binary 7 5)
  -1
  >>> (median_binary 10 20)
  \"0b1111\""
  [n m])