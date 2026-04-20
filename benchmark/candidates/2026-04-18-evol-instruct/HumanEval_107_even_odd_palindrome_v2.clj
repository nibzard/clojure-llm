(defn palindromic-even-odd-count
  "Given a positive integer n, return a vector [even-count odd-count] containing
  the number of even and odd palindromic integers in the range 1 through n, inclusive.

  A palindromic integer reads the same forward and backward.

  Examples:
  (palindromic-even-odd-count 3)
  ;; => [1 2]
  ;; Palindromes: 1, 2, 3

  (palindromic-even-odd-count 12)
  ;; => [4 6]
  ;; Palindromes: 1, 2, 3, 4, 5, 6, 7, 8, 9, 11

  Constraints:
  - 1 <= n <= 1000
  - Use standard Clojure sequence functions; no explicit recursion required."
  [n]
  (let [pal? #(= (seq (str %)) (reverse (seq (str %))))
        pals (filter pal? (range 1 (inc n)))]
    [(count (filter even? pals))
     (count (filter odd? pals))]))