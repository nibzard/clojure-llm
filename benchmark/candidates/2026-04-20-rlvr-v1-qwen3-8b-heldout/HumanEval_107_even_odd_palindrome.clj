(defn even_odd_palindrome
  "Given a positive integer n, return a vector that has the number of even and odd
  integer palindromes that fall within the range(1, n), inclusive.
  Example 1:
  >>> (even_odd_palindrome 3)
  [1 2]
      Explanation:
      Integer palindrome are 1, 2, 3. one of them is even, and two of them are odd.
  Example 2:
  >>> (even_odd_palindrome 12)
  [4 6]
      Explanation:
      Integer palindrome are 1, 2, 3, 4, 5, 6, 7, 8, 9, 11. four of them are even, and 6 of them are odd.
  Note:
      1. 1 <= n <= 10^3
      2. returned vector has the number of even and odd integer palindromes respectively."
  [n]
  (let [palindrome? (fn [x]
                      (let [s (str x)]
                        (= s (apply str (reverse s)))))]
    (->> (range 1 (inc n))
         (filter palindrome?)
         (reduce (fn [result x]
                   (let [r (if (even? x) 0 1)]
                     [(inc (first result))
                      (+ (second result) r)]))
                 [0 0]))))